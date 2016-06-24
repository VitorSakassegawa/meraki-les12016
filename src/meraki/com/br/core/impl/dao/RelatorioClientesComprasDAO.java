package meraki.com.br.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.RelatorioClientesCompras;

/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioClientesComprasDAO extends AbstractJdbcDAO
{

    public RelatorioClientesComprasDAO()
    {
        super(null, null);
    }
    
    public RelatorioClientesComprasDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        List<EntidadeDominio> entidades = new ArrayList<>();
        try
        {
        	// Abrir conexão
            openConnection();
            
            sql.append("SELECT DISTINCT ");
            sql.append("c.nome, count(p.id) as compras,date_part('month',p.data_normal) as dt ");
            sql.append("FROM clientes as c, pedidos as p, pedidos_produtos as pj ");
            sql.append("WHERE p.id_cliente = c.id and p.id = pj.id_pedidos and date_part('month',p.data_normal) between 4 and 6 ");
            sql.append("GROUP BY c.nome, date_part('month',p.data_normal) ");
            sql.append("ORDER BY dt,compras ASC ");
            
            pst = conn.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                RelatorioClientesCompras produtos = new RelatorioClientesCompras();
                
                produtos.setTitulo(rs.getString("nome"));
                produtos.setQuantidade(rs.getInt("compras"));
                produtos.setData(rs.getString("dt"));
                
                entidades.add(produtos);
            }
            
            return entidades;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            conn.close();
        }
    }
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------------MERAKI-------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	@Override
	public void trocar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	}

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