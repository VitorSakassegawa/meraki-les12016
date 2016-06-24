package meraki.com.br.core.impl.dao;

import meraki.com.br.core.IDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;
import meraki.com.br.domain.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.core.impl.dao.PedidoDAO;

/**
*
* @author Vitor Sakassegawa
*/
public class TrocaDAO extends AbstractJdbcDAO
{

    public TrocaDAO(Connection connection)
    {
        super(connection, "", "");
    }

    public TrocaDAO()
    {
        super(null, "", "");
    }   // Construtor Default

    public TrocaDAO(Connection connection, String table, String idTable)
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

            // String de SQL
            StringBuilder sql = new StringBuilder();    

            sql.append("INSERT INTO TROCA ");
            sql.append("(ID_PEDIDO,SALDO,CUPOM) ");
            sql.append("VALUES (?,?,?)");

            // Passando SQL para os banco de dados
            pst = conn.prepareStatement(sql.toString());    

            // Atributos para inserção no banco de dados
            pst.setInt(1, transacao.getPedido().getId());
            pst.setFloat(2, transacao.getPedido().ValorTotal());
            pst.setString(3, transacao.getCupom());

            // Executando a query no banco de dados
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
	public void atualizar(EntidadeDominio entidade) throws SQLException {
	    {
	        PreparedStatement pst = null;
	        Produto produto = (Produto) entidade;
	        try
	        {
	            if (conn == null || conn.isClosed()) 
	            {
	            	// Abrir conexão
	                openConnection();
	            }
	            if (!ctrlTransaction)
	            {
	                // Set AutoCommit p/ false
	                conn.setAutoCommit(false);
	            }
	            
	            StringBuilder sql = new StringBuilder();
	            sql.append("UPDATE PRODUTOS SET ");
	            sql.append("produtos.quantidade_estoque = produtos.quantidade_estoque + 1 ");
	            sql.append("WHERE produtos.id = ?");

	            pst = conn.prepareStatement(sql.toString());

	            pst.setInt(1, produto.getId());

	            pst.executeUpdate();

	            if (!ctrlTransaction)
	            {
	                conn.commit();
	            }

	        } catch (SQLException ex)
	        {
	            if (!ctrlTransaction)
	            {
	                conn.rollback();
	            }
	            ex.printStackTrace();
	        } finally
	        {
	            if (!ctrlTransaction)
	            {
	                pst.close();
	                conn.close();
	            }
	        }
	    }		
	}
	
    //---------------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------------MERAKI-------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void trocar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void trocar2(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void trocaradm(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<EntidadeDominio> consultartroca(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultartrocacupom(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultargrafico(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}