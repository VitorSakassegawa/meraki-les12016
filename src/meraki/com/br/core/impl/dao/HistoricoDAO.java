package meraki.com.br.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Historico;

/**
*
* @author Vitor Sakassegawa
*/
public class HistoricoDAO extends AbstractJdbcDAO
{

    public HistoricoDAO()
    {
        super("", "");
    }
    
    public HistoricoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        Historico historico = (Historico) entidade;
        PreparedStatement pst = null;
        List<EntidadeDominio> entidades = new ArrayList<>();

        try
        {
        	// Abrir conexão
            openConnection();   
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("(SELECT SUM(h.qtde_compra) FROM historico_compras  AS h ");
            sql.append(" WHERE h.id_cliente = ? AND h.id_produto = ?)");
            sql.append(" AS qtde_compra, e.quantidade ");
            sql.append(" FROM estoque AS e ");
            sql.append(" WHERE e.id_produto = ?");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, historico.getIdCliente());
            pst.setInt(2, historico.getIdProduto());
            pst.setInt(3, historico.getIdProduto());
            
            // Get resultado da consulta
            ResultSet rs = pst.executeQuery();  
            
            if(rs.next())
            {                
                historico.setQuantidadeComprada(rs.getInt("qtde_compra"));
                historico.setQuantidadeEstoque(rs.getInt("quantidade"));
                
                entidades.add(historico);
            }
            
            return entidades;
            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
                pst.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        
        return entidades;
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
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
	
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub
    }
}