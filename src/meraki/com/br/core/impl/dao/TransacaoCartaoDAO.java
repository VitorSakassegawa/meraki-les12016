package meraki.com.br.core.impl.dao;

import meraki.com.br.core.IDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.TransacaoCartao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.core.impl.dao.PedidoDAO;
import meraki.com.br.core.impl.dao.SaldoDAO;

/**
*
* @author Vitor Sakassegawa
*/
public class TransacaoCartaoDAO extends AbstractJdbcDAO
{

    public TransacaoCartaoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public TransacaoCartaoDAO()
    {
        super(null, null);
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        TransacaoCartao transacao = (TransacaoCartao) entidade;
        try
        {
        	// Abrir conexão
            openConnection(); 
            
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);
            
            IDAO dao = new SaldoDAO(conn, "", ""); 
            
            // Atualizar o saldo do cliente
            dao.atualizar(transacao);   
            
            // O pagamento foi efetuado - Mudar o status do pedido para Pago
            transacao.getPedido().setPosicao("Pago!");
            
            dao = new PedidoDAO(conn, "", "");
            
            // Salvando pedido do cliente
            dao.salvar(transacao.getPedido());  
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO TRANSACOES ");
            sql.append("(ID_PEDIDO,ID_CLIENTE,STATUS,DATA_TRANSACAO,DESCRICAO,VALOR,NUMERO_CARTAO,NUMERO_PARCELAS,NOME_CARTAO) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?) ");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, transacao.getPedido().getId());
            pst.setInt(2, transacao.getCliente().getId());
            pst.setString(3, transacao.getStatus());
            java.sql.Date dt = new Date(Calendar.getInstance().getTimeInMillis());
            pst.setDate(4, dt);
            pst.setString(5, transacao.getDescricao());
            pst.setFloat(6, transacao.getValor());
            pst.setString(7, transacao.getCartao().getNumeroFinalCartao());
            pst.setString(8, transacao.getCartao().getParcelas());
            pst.setString(9, transacao.getCartao().getNomeCartao());
            
            pst.executeUpdate();
            
            conn.commit();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            try
            {
               conn.rollback();
            }
            catch(SQLException ex1)
            {
                ex1.printStackTrace();
            }
            throw new SQLException(ex);
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }


    //---------------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------------MERAKI-------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
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
    public void atualizar(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub		
    }
	
	@Override
	public void trocaradm(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub		
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