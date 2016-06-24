package meraki.com.br.core.impl.dao;

import meraki.com.br.core.IDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Transacao;
import meraki.com.br.domain.TransacaoCartao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.core.impl.dao.PedidoDAO;
import meraki.com.br.core.impl.dao.SaldoDAO;

/**
*
* @author Vitor Sakassegawa
*/
public class TransacaoDAO extends AbstractJdbcDAO
{

    public TransacaoDAO(Connection connection)
    {
        super(connection, "", "");
    }

    // Construtor Default
    public TransacaoDAO()
    {
        super(null, "", "");
    }   

    public TransacaoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Transacao transacao = (Transacao) entidade;
        try
        {
        	// Abrir conexão
            openConnection();

            // Set AutoCommit p/ false
            conn.setAutoCommit(false);

            IDAO dao = new PedidoDAO(conn, "pedidos", "id");

            // Salvando o pedido do cliente
            dao.salvar(transacao.getPedido()); 

            StringBuilder sql = new StringBuilder();    

            sql.append("INSERT INTO TRANSACOES ");
            sql.append("(ID_PEDIDO,ID_CLIENTE,STATUS,DATA_TRANSACAO,DESCRICAO,VALOR) ");
            sql.append("VALUES (?,?,?,?,?,?)");

            // Passando SQL para o banco de dados
            pst = conn.prepareStatement(sql.toString());    
            
            pst.setInt(1, transacao.getPedido().getId());
            pst.setInt(2, transacao.getCliente().getId());
            pst.setString(3, transacao.getStatus());
            java.sql.Date dt = new Date(transacao.getDataTransacao().getTime());
            pst.setDate(4, dt);
            pst.setString(5, transacao.getDescricao());
            pst.setFloat(6, transacao.getPedido().valorTotalDesconto(0.15f));

            // Execute query no banco de dados
            pst.execute(); 

            // Commit das alterações
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

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Transacao transacao = (Transacao) entidade;
        StringBuilder sql = new StringBuilder();

        try
        {
        	// Abrir conexão
            openConnection();
            
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);
            
            // Retirar o valor do saldo do cliente
            IDAO dao = new SaldoDAO(conn, null, null);
            
            TransacaoCartao trCartao = new TransacaoCartao();
            trCartao.setCliente(((Transacao)entidade).getCliente());
            trCartao.setValor(((Transacao)entidade).getPedido().valorTotalDesconto(0.15f));
            trCartao.setPedido(((Transacao)entidade).getPedido());
         
            // Atualizar o saldo do cliente
            dao.atualizar(trCartao);    
            
            sql.append("UPDATE TRANSACOES ");
            sql.append("SET STATUS = ? ");
            sql.append("WHERE ID_PEDIDO = ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setString(1, "Pago!");
            
            // Passar ID do pedido como parâmetro
            pst.setInt(2, transacao.getPedido().getId());   

            // Execute query de update
            pst.executeUpdate();    

            dao = new PedidoDAO(conn, null, null);

            // Atualizar o valor da transação
            dao.atualizar(transacao.getPedido());   

            // Commit das alterações
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
            throw new SQLException(ex);
        } finally
        {
            try
            {
                pst.close();
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
	public void trocaradm(EntidadeDominio entidade) throws SQLException 
        {
		
            PreparedStatement pst = null;
            Transacao transacao = (Transacao) entidade;
            StringBuilder sql = new StringBuilder();
            IDAO dao = new PedidoDAO(conn, null, null);

            try
            {
            	// Abrindo conexao com o banco!
                openConnection();   
                conn.setAutoCommit(false);
                
                sql.append("UPDATE TRANSACOES ");
                sql.append("SET STATUS = ? ");
                sql.append("WHERE ID_PEDIDO = ?");

                pst = conn.prepareStatement(sql.toString());

                pst.setString(1, "Troca Autorizada");
                
                // Passando id do pedido como parametro
                pst.setInt(2, transacao.getPedido().getId());

                // Executando a atualizacao
                pst.executeUpdate();    

                dao = new PedidoDAO(conn, null, null);
  
                // Atualizando com o pedido de troca
                dao.trocaradm(transacao.getPedido());   
              
                // Comitando as alteracoes
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
                throw new SQLException(ex);
            } finally
            {
                try
                {
                    pst.close();
                    conn.close();
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    
	//---------------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Trocar (Solicitar)-------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------//
    @Override
	public void trocar(EntidadeDominio entidade) throws SQLException {
        {
            PreparedStatement pst = null;
            Transacao transacao = (Transacao) entidade;
            StringBuilder sql = new StringBuilder();
            IDAO dao = new PedidoDAO(conn, null, null);

            try
            {
            	// Abrindo conexao com o banco!
                openConnection();   
                conn.setAutoCommit(false);
                
                sql.append("UPDATE TRANSACOES ");
                sql.append("SET STATUS = ? ");
                sql.append("WHERE ID_PEDIDO = ?");

                pst = conn.prepareStatement(sql.toString());

                pst.setString(1, "Aguardando Troca");
                
                // Passando id do pedido como parametro
                pst.setInt(2, transacao.getPedido().getId());

                // Executando a atualizacao
                pst.executeUpdate();    

                dao = new PedidoDAO(conn, null, null);
  
                // Atualizando com o pedido de troca
                dao.trocar(transacao.getPedido());   
              
                // Comitando as alteracoes
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
                throw new SQLException(ex);
            } finally
            {
                try
                {
                    pst.close();
                    conn.close();
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }		
	}

	//---------------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Trocar2 (Cupom)----------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------//
	@Override
	public void trocar2(EntidadeDominio entidade) throws SQLException 
    {
        PreparedStatement pst = null;
        Transacao transacao = (Transacao) entidade;
        StringBuilder sql = new StringBuilder();
        IDAO dao = new PedidoDAO(conn, null, null);

        try
        {
        	// Abrir conexão
            openConnection(); 
            
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);
            
            sql.append("UPDATE TRANSACOES ");
            sql.append("SET STATUS = ? ");
            sql.append("WHERE ID_PEDIDO = ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setString(1, "Cupom gerado");
            
            // Passando ID do pedido como parâmetro
            pst.setInt(2, transacao.getPedido().getId());

            // Execute query de update
            pst.executeUpdate();    

            dao = new PedidoDAO(conn, null, null);

            // Atualizando com o pedido de troca
            dao.trocar2(transacao.getPedido());   
          
            // Commit das alterações
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
            throw new SQLException(ex);
        } finally
        {
            try
            {
                pst.close();
                conn.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }		

    //---------------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------------MERAKI-------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	@Override
	public List<EntidadeDominio> consultartrocacupom(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EntidadeDominio> consultartroca(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub
		return null;
	}
    
	@Override
    public void excluir(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub	
    }

	@Override
	public List<EntidadeDominio> consultargrafico(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}		
}	