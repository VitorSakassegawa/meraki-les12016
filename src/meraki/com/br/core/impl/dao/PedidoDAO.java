package meraki.com.br.core.impl.dao;

import meraki.com.br.core.IDAO;
import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Frete;
import meraki.com.br.domain.ItemPedido;
import meraki.com.br.domain.Produto;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.PedidoProduto;
import meraki.com.br.domain.Transacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.core.impl.dao.EnderecoEntregaDAO;
import meraki.com.br.core.impl.dao.ProdutoDAO;

/**
*
* @author Vitor Sakassegawa
*/
public class PedidoDAO extends AbstractJdbcDAO
{

    public PedidoDAO()
    {
        super("pedidos", "id");
    }

    public PedidoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;

        try
        {
            Pedido pedido = (Pedido) entidade;
            PedidoProduto pedidoProduto = new PedidoProduto();

            if (conn.isClosed() || conn == null) 
            {
            	// Abrir conexão
                openConnection();
            }
            
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PEDIDOS ");
            sql.append("(data_pedido,total,id_cliente,tipo_pagamento,data_normal,posicao,id_endereco_entrega,status,frete) ");
            sql.append("VALUES ");
            sql.append("(?,?,?,?,?,?,?,?,?)");

            // Criando o caminho para conexão com o banco de dados
            pst = conn.prepareStatement(sql.toString(),
            		// Retornará a última chave inserida no banco de dados
                    Statement.RETURN_GENERATED_KEYS);   

            // Convertendo data para o banco de dados!
            Timestamp dataTime = new Timestamp(pedido.getDataPedido().getTimeInMillis());
            pst.setTimestamp(1, dataTime);  

            pst.setFloat(2, pedido.ValorTotal());
            pst.setInt(3, pedido.getCliente().getId());
            pst.setString(4, pedido.getTipoPagamento());
            pst.setDate(5, new java.sql.Date(pedido.getDataNormal().getTime()));
            pst.setString(6, pedido.getPosicao());
            pst.setInt(7, pedido.getEntrega().getId());
            pst.setString(8, pedido.getStatus());
            pst.setFloat(9, pedido.getFrete().getValor());

            // Execute a query no banco de dados
            pst.executeUpdate();  

            // Get ID da ultima inserção no banco de dados
            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next())
            {
                pedido.setId(rs.getInt("id"));
            } else
            {
            	// Impossível continuar sem o ID do pedido!
                throw new SQLException();   
            }
            
            IDAO dao;

            for (ItemPedido ip : pedido.getItens())
            {
                
                sql = new StringBuilder();
                
                sql.append("INSERT INTO PEDIDOS_PRODUTOS ");
                sql.append("(id_pedidos,id_produtos,quantidade,desconto) ");
                sql.append("VALUES (?,?,?,?) ");
                
                pst = conn.prepareStatement(sql.toString());
                
                pst.setInt(1, pedido.getId());
                pst.setInt(2, ip.getProduto().getId());
                pst.setInt(3, ip.getQuantidade());
                pst.setFloat(4, ip.getDesconto());
                
                pst.execute();
                
                dao = new ProdutoDAO(conn, "", "");

                // Set quantidade de produto
                ip.getProduto().setQuantidade(ip.getQuantidade());   

                // Atualizando a quantidade no banco de dados
                ((ProdutoDAO) dao).atualizarAquantidade(ip.getProduto());  
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
        } finally
        {
            try
            {
                if (!ctrlTransaction)
                {
                	// Caso não tenha uma transação fechar a conexão
                    conn.close();   
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        StringBuilder sql = new StringBuilder();
        
        try
        {
            if(conn == null || conn.isClosed())
            	// Abrir conexão
                openConnection();
            
            sql.append("UPDATE PEDIDOS ");
            sql.append("SET POSICAO = ? ");
            sql.append("WHERE ID = ?");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setString(1, "Pago!");
            pst.setInt(2, pedido.getId());
            
            pst.executeUpdate();
            
            // O commit e o close irá ser feito pela classe TransacaoDAO
        }
        catch(SQLException ex)
        {
            throw new SQLException(ex);
        }
    }
    
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        List<EntidadeDominio> entidades = new ArrayList<>();
        int mes, ano;
        StringBuilder sql = new StringBuilder();

        try
        {
        	// Abrir conexão
            openConnection(); 

            if (pedido.getDataNormal() != null)
            {
                sql.append("SELECT DISTINCT ");
                sql.append("p.id, p.data_pedido, p.tipo_pagamento, p.posicao, p.status, p.frete,p.id_endereco_entrega ");
                sql.append("FROM PEDIDOS AS p ");
                sql.append("WHERE p.id_cliente = ? and ");
                sql.append("date_part('month',p.data_normal) = ? and ");
                sql.append("date_part('year',p.data_normal) = ?");

                // Get mês e ano
                Calendar cl = Calendar.getInstance();
                cl.setTime(pedido.getDataNormal());
                mes = cl.get(Calendar.MONTH) + 1;
                ano = cl.get(Calendar.YEAR);

                pst = conn.prepareStatement(sql.toString());

                pst.setInt(1, pedido.getCliente().getId());
                pst.setInt(2, mes);
                pst.setInt(3, ano);

                // Get resultado da consulta
                ResultSet rs = pst.executeQuery();  

                while (rs.next()) 
                {
                    Pedido ped = new Pedido();
                    ped.setId(rs.getInt("id"));
                    Calendar dtPedido = Calendar.getInstance();
                    dtPedido.setTimeInMillis(rs.getDate("data_pedido").getTime());
                    ped.setDataPedido(dtPedido);
                    ped.setTipoPagamento(rs.getString("tipo_pagamento"));
                    ped.setPosicao(rs.getString("posicao"));
                    ped.setStatus(rs.getString("status"));
                    ped.setFrete(new Frete(rs.getFloat("frete")));
                    ped.setEntrega(new EnderecoEntrega(rs.getInt("id_endereco_entrega")));

                    // Inserindo os pedidos
                    entidades.add(ped); 
                }

                // Retornando os pedidos
                return entidades;   
            } else
            {
                sql.append("SELECT DISTINCT j.*,pj.quantidade, pj.desconto ");
                sql.append("FROM PRODUTOS as j, PEDIDOS_PRODUTOS as pj, PEDIDOS as p ");
                sql.append("WHERE pj.id_pedidos = ? and j.id = pj.id_produtos ");

                pst = conn.prepareStatement(sql.toString());

                pst.setInt(1, pedido.getId());

                ResultSet rs = pst.executeQuery();
                
                // Acessando EnderecoEntregaDAO
                IDAO dao = new EnderecoEntregaDAO(conn, null, null);

                while (rs.next())
                {
                	// Criando objeto de produto
                    Produto produto = new Produto(); 

                    produto.setId(rs.getInt("id"));
                    produto.setTitulo(rs.getString("titulo"));
                    produto.setValor(rs.getFloat("valor"));

                    // Criando objeto de itemPedido
                    ItemPedido item = new ItemPedido(); 

                    item.setProduto(produto);
                    item.setDesconto(rs.getFloat("desconto"));
                    item.setQuantidade(rs.getInt("quantidade"));

                    // Adicionando o item em pedidos
                    pedido.addItem(item);  
                }

                // Get endereco de entrga
                List<EntidadeDominio> enderecos = dao.consultar(pedido.getEntrega());

                // Set entrega
                pedido.setEntrega((EnderecoEntrega) enderecos.get(0));  

                entidades.add(0, pedido);

                return entidades;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        } finally
        {
            try
            {
                conn.close();
                pst.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public List<EntidadeDominio> consultaPedidoData(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        List<EntidadeDominio> entidades = new ArrayList<>();
        int mes, ano;    
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM PEDIDOS ");
        sql.append("WHERE id_cliente = ? and ");
        sql.append("date_part('month',data_normal) = ? and date_part('year',data_normal) = ?");

        try
        {
        	// Abrir conexão
            openConnection();

            pst = conn.prepareStatement(sql.toString());
            Calendar dt = Calendar.getInstance();
            dt.setTime(pedido.getDataNormal());

            mes = dt.get(Calendar.MONTH);
            ano = dt.get(Calendar.YEAR);

            pst.setInt(1, pedido.getCliente().getId());
            pst.setInt(2, mes);
            pst.setInt(3, ano);

            // Get retorno da consulta
            ResultSet rs = pst.executeQuery();   

            // Get todas as informacoes do pedido
            while (rs.next())   
            {
                Pedido ped = new Pedido();

                ped.setId(rs.getInt("id"));
                Timestamp time = rs.getTimestamp("data_pedido");
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(time.getTime());
                ped.setDataPedido(date);
                ped.setTipoPagamento(rs.getString("tipo_pagamento"));
                ped.setPosicao(rs.getString("posicao"));
                Frete frete = new Frete();
                frete.setValor(rs.getFloat("frete"));
                ped.setFrete(frete);

                entidades.add(ped);
            }

            return entidades;

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        } finally
        {
            try
            {
                conn.close();
                pst.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
   
    //---------------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Trocar (Solicitar)------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	@Override
	public void trocar(EntidadeDominio entidade) throws SQLException {
    	PreparedStatement pst = null;
    	Pedido pedido = (Pedido) entidade;
    	StringBuilder sql = new StringBuilder();
	  
		  try
		  {
		      if(conn == null || conn.isClosed())
		    	  // Abrir conexão
		          openConnection();
		      
		      sql.append("UPDATE PEDIDOS ");
		      sql.append("SET STATUS = ? ");
		      sql.append("WHERE ID = ?");
		      
		      pst = conn.prepareStatement(sql.toString());
		      
		      pst.setString(1, "Aguardando Troca");
		      pst.setInt(2, pedido.getId());
		      
		      pst.executeUpdate();
		      
		      // O commit e o close irá ser feito pela classe TransacaoDAO
		  }
		  catch(SQLException ex)
		  {
		      throw new SQLException(ex);
		  }
	}
	

	//---------------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Trocar2 (Cupom)----------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------//	
	@Override
	public void trocar2(EntidadeDominio entidade) throws SQLException {
	        PreparedStatement pst = null;
	        Pedido pedido = (Pedido) entidade;
	        try
	        {
	        	// Abrindo a conexao
	            openConnection();   

	            conn.setAutoCommit(false);

	            // String de SQL
	            StringBuilder sql = new StringBuilder();    

	            sql.append("INSERT INTO TROCAS ");
	            sql.append("(ID_PEDIDO,SALDO,CUPOM) ");
	            sql.append("VALUES (?,?,?)");

	            // Passando SQL para os dados
	            pst = conn.prepareStatement(sql.toString());    

	            // Atributos para insercao no banco de daods
	            pst.setInt(1, pedido.getId());
	            pst.setFloat(2, pedido.getTotal());
	            pst.setString(3, pedido.getCupom());

	            // Executando a query no banco de dados
	            pst.execute(); 

	            // Commit - Alteracoes no banco de dados
	            conn.commit(); 

	        } catch (SQLException ex)
	        {
	            ex.printStackTrace();
	            try
	            {
	                conn.rollback();
	            } catch (SQLException ex1)
	            {
	                ex1.printStackTrace();
	            }
	        } finally
	        {
	            try
	            {
	                conn.close();
	            } catch (SQLException ex)
	            {
	                ex.printStackTrace();
	            }
	        }
	    }	
	
	//---------------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------TrocarADM (Autorizar)----------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------//
	@Override
	public void trocaradm(EntidadeDominio entidade) throws SQLException {
	   	PreparedStatement pst = null;
    	Pedido pedido = (Pedido) entidade;
    	StringBuilder sql = new StringBuilder();
	  
		  try
		  {
		      if(conn == null || conn.isClosed())
		          openConnection(); 
		      
		      sql.append("UPDATE PEDIDOS ");
		      sql.append("SET STATUS = ? ");
		      sql.append("WHERE ID = ?");
		      
		      pst = conn.prepareStatement(sql.toString());
		      
		      pst.setString(1, "Troca Autorizada");
		      pst.setInt(2, pedido.getId());
		      
		      pst.executeUpdate();
		      
		  }
		  catch(SQLException ex)
		  {
		      throw new SQLException(ex);
		  }
	}		

	//---------------------------------------------------------------------------------------------------------------------------//
	//---------------------------------------------Consultar (Troca)-------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------//
	@Override
	public List<EntidadeDominio> consultartroca(EntidadeDominio entidade) throws SQLException 
	{
	       PreparedStatement pst = null;
	       List<EntidadeDominio> entidades = new ArrayList<>();
	       StringBuilder sql = new StringBuilder();

	       sql.append("SELECT * FROM PEDIDOS ");
	       sql.append("WHERE STATUS = 'Aguardando Troca' ");

	        try
	        {
	            openConnection();    //abrindo conexao com o banco

	            pst = conn.prepareStatement(sql.toString());
	        
	            ResultSet rs = pst.executeQuery();   //pegando o retorno da consulta!
	           

	            while (rs.next())   //pegando todas as informacoes do pedido
	            {
	                Pedido ped = new Pedido();

	                ped.setId(rs.getInt("id"));
	                Timestamp time = rs.getTimestamp("data_pedido");
	                Calendar date = Calendar.getInstance();
	                date.setTimeInMillis(time.getTime());
	                ped.setDataPedido(date);
	                ped.setTipoPagamento(rs.getString("tipo_pagamento"));
	                ped.setPosicao(rs.getString("posicao"));
	                Frete frete = new Frete();
	                frete.setValor(rs.getFloat("frete"));
	                ped.setFrete(frete);
	                EnderecoEntrega entrega = new EnderecoEntrega();
	                entrega.setId(rs.getInt("id_endereco_entrega"));
	                ped.setEntrega(entrega);
	                ped.setStatus(rs.getString("status"));


	                entidades.add(ped);
	            }

	            return entidades;

	        } catch (SQLException ex)
	        {
	            ex.printStackTrace();
	            return null;
	        } finally
	        {
	            try
	            {
	                conn.close();
	                pst.close();
	            } catch (SQLException ex)
	            {
	                ex.printStackTrace();
	            }
	        }
	    }

	@Override
	public List<EntidadeDominio> consultartrocacupom(EntidadeDominio entidade) throws SQLException {
		       PreparedStatement pst = null;
		       List<EntidadeDominio> entidades = new ArrayList<>();
		       StringBuilder sql = new StringBuilder();

				Pedido pedido = (Pedido) entidade;
		       sql.append("SELECT * FROM PEDIDOS ");
		       sql.append("WHERE id_cliente = ? and ");
		       sql.append("STATUS = 'Troca Autorizada' ");

		        try
		        {
		        	// Abrindo conexao
		            openConnection();   

		            pst = conn.prepareStatement(sql.toString());
		            
		            pst.setInt(1, pedido.getCliente().getId());

		            // Get o retorno da consulta
		            ResultSet rs = pst.executeQuery();   

		            // Get todas as informacoes do pedido
		            while (rs.next())   
		            {
		                Pedido ped = new Pedido();

		                ped.setId(rs.getInt("id"));
		                Timestamp time = rs.getTimestamp("data_pedido");
		                Calendar date = Calendar.getInstance();
		                date.setTimeInMillis(time.getTime());
		                ped.setDataPedido(date);
		                ped.setTipoPagamento(rs.getString("tipo_pagamento"));
		                ped.setPosicao(rs.getString("posicao"));
		                Frete frete = new Frete();
		                frete.setValor(rs.getFloat("frete"));
		                ped.setFrete(frete);
		                EnderecoEntrega entrega = new EnderecoEntrega();
		                entrega.setId(rs.getInt("id_endereco_entrega"));
		                ped.setEntrega(entrega);
		                ped.setStatus(rs.getString("status"));


		                entidades.add(ped);
		            }

		            return entidades;

		        } catch (SQLException ex)
		        {
		            ex.printStackTrace();
		            return null;
		        } finally
		        {
		            try
		            {
		                conn.close();
		                pst.close();
		            } catch (SQLException ex)
		            {
		                ex.printStackTrace();
		            }
		        }
	}

	@Override
	public List<EntidadeDominio> consultargrafico(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
//---------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------FIM----------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------------------------//