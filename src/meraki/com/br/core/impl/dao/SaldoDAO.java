package meraki.com.br.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Saldo;
import meraki.com.br.domain.Transacao;
import meraki.com.br.domain.TransacaoCartao;
import meraki.com.br.domain.Usuario;

/**
*
* @author Vitor Sakassegawa
*/
public class SaldoDAO extends AbstractJdbcDAO
{

    public SaldoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public SaldoDAO()
    {
        super(null, null);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        try
        {
            if(conn == null || conn.isClosed())
            	// Abrir conexão
                openConnection();
            
            sql.append("INSERT INTO SALDOS ");
            sql.append("(ID_CLIENTE,SALDO) ");
            sql.append("VALUES (?,?)");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, entidade.getId());
            pst.setFloat(2, 500f);
            
            pst.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        TransacaoCartao transacao = (TransacaoCartao) entidade;
        try
        {
            if (conn == null || conn.isClosed())
            {
            	// Abrir conexão
                openConnection();
            }

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SALDOS ");
            sql.append("SET saldo = saldo - ? ");
            sql.append("WHERE ID_CLIENTE = ? and saldo > ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setFloat(1, transacao.TotalTransacao());
            pst.setInt(2, transacao.getCliente().getId());
            pst.setFloat(3, transacao.TotalTransacao());

            pst.executeUpdate();

        } catch (SQLException ex)
        {
             ex.printStackTrace();
             throw new SQLException();
        }
        finally
        {
            if(!ctrlTransaction)
                pst.close();
            
            if(!ctrlTransaction)
                conn.close();
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntidadeDominio consultaSaldoEspecifico(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;
        
        TransacaoCartao transacao =(TransacaoCartao) entidade;
        
        try
        {
            openConnection();
            
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT saldo FROM SALDOS ");
            sql.append("WHERE SALDO >= (?) and ID_CLIENTE = (?)");

            pst = conn.prepareStatement(sql.toString());

            pst.setFloat(1, transacao.getValor());
            pst.setInt(2, transacao.getCliente().getId());

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                Saldo saldo = new Saldo();
                saldo.setSaldo(rs.getFloat("saldo"));
                return saldo;
            } else
            {
                return null;
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
	public List<EntidadeDominio> consultartroca(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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