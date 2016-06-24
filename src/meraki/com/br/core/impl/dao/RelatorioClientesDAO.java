package meraki.com.br.core.impl.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Frete;
import meraki.com.br.domain.Genero;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.RelatorioClientes;
import meraki.com.br.domain.Usuario;

/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioClientesDAO extends AbstractJdbcDAO
{

    public RelatorioClientesDAO()
    {
        super(null, null);
    }

    public RelatorioClientesDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

	public List<EntidadeDominio> grafico1(EntidadeDominio entidade) {

        PreparedStatement pst = null;
        String sql = null;
        List<EntidadeDominio> entidades = new ArrayList<>();
        try
        {
        	// Abrir conexão
            openConnection();
				
				sql = "SELECT DISTINCT c.id,c.nome, sum(p.total) as gasto, count(c.id) as compras,(2016 - date_part('year',c.dt_nasc)) as idade, date_part('month',p.data_normal) as dt "
							+ "FROM clientes as c, pedidos as p, pedidos_produtos as pj WHERE p.id_cliente = c.id and p.id = pj.id_pedidos and date_part('month',p.data_normal) between 4 and 6"
							+ "GROUP BY c.id, c.nome,c.dt_nasc, date_part('month',p.data_normal) "
							+ "	ORDER BY dt,gasto ASC ";
	            
	            ResultSet rs = pst.executeQuery();
	            
	            while(rs.next())
	            {
	                RelatorioClientes rc = new RelatorioClientes();
	                
	                rc.setNome(rs.getString("nome"));
	                rc.setGasto(rs.getFloat("gasto"));
	                rc.setCompras(rs.getInt("compras"));
	                rc.setIdade(rs.getString("idade"));
	                rc.setDt(rs.getString("dt"));
	                
	                entidades.add(rc);
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
	            try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
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
            sql.append("c.id,c.nome, sum(p.total) as gasto, count(c.id) as compras,(2016 - date_part('year',c.dt_nasc)) as idade, date_part('month',p.data_normal) as dt ");
            sql.append("FROM clientes as c, pedidos as p, pedidos_produtos as pj ");
            sql.append("WHERE p.id_cliente = c.id and p.id = pj.id_pedidos and date_part('month',p.data_normal) between 4 and 6 ");
            sql.append("GROUP BY c.id, c.nome,c.dt_nasc, date_part('month',p.data_normal) ");
            sql.append("ORDER BY dt,gasto ASC ");
            
            pst = conn.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                RelatorioClientes rc = new RelatorioClientes();
                
                rc.setNome(rs.getString("nome"));
                rc.setGasto(rs.getFloat("gasto"));
                rc.setCompras(rs.getInt("compras"));
                rc.setIdade(rs.getString("idade"));
                rc.setDt(rs.getString("dt"));
                
                entidades.add(rc);
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
    
	public List<EntidadeDominio> listaGenero(EntidadeDominio entidade) throws SQLException {
		List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
		PreparedStatement pst = null;
		Usuario cliente = (Usuario) entidade;
		openConnection();
		String sql = null;
		try {

			sql = "SELECT * FROM clientes inner join enderecos on clientes.id_endereco = enderecos.id WHERE email='admin@gmail.com' and senha='admin'";

            conn.setAutoCommit(false);
                        
			pst = conn.prepareStatement(sql);
            pst.setString(1, cliente.getSenha());
            pst.setString(2, cliente.getEmail());
            
            ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				/*********** CONSULTANDO AS INFORMACOES DO CLIENTE ************/
                Usuario c = new Usuario();
                
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("Nome"));
                c.setRg(rs.getString("rg"));
                c.setCpf(rs.getString("cpf"));
                c.setSexo(rs.getString("sexo"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setStatus(rs.getInt("status"));
                c.setEndereco(new Endereco());

                java.util.Date dt = new Date(rs.getTimestamp("dt_nasc").getTime());
                c.setDataNascimento(Calendar.getInstance());
                c.getDataNascimento().setTime(dt);
                
				/*********** CONSULTANDO AS INFORMACOES DO ENDERECO ***********/

                c.getEndereco().setId(rs.getInt("id_endereco"));
                IDAO dao = new EnderecoDAO(conn, "enderecos", "id");

                List<EntidadeDominio> entidades = dao.consultar(c.getEndereco());

                Endereco end = (Endereco) entidades.get(0);

                end.setId(end.getId());
                end.setLogradouro(end.getLogradouro());
                end.setBairro(end.getBairro());
                end.setCEP(end.getCEP());
                end.setCidade(end.getCidade());
                end.setEstado(end.getEstado());
                end.setNumero(end.getNumero());
                c.setEndereco(end);

				// LISTA GENERO //
				String sql2 = null;
				PreparedStatement pst2 = null;

				sql2 = 	"select produtos.genero as genero, sum(pedidos_produtos.quantidade) as qtde "
						+ "from produtos inner join pedidos_produtos on (produtos.id = pedidos_produtos.id_produtos) "
						+ "group by produtos.genero order by 1";

				pst2 = conn.prepareStatement(sql2);
				ResultSet rs2 = pst2.executeQuery();
				List<Genero> listaGeneros = new ArrayList<Genero>();
				while (rs2.next()) {
					Genero genero = new Genero();

					genero.setGenero(rs2.getString("genero"));
					genero.setQtde(rs2.getInt("qtde"));
					listaGeneros.add(genero);
				}
				cliente.setListaGeneros(listaGeneros);

				clientes.add(c);
			}
			return clientes;
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EntidadeDominio> consultargrafico(EntidadeDominio entidade) throws SQLException {
	   
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        List<EntidadeDominio> entidades = new ArrayList<>();
        try
        {
        	// Abrir conexão
            openConnection();
            
            sql.append("SELECT DISTINCT ");
            sql.append("c.id,c.nome, sum(p.total) as gasto, count(c.id) as compras,(2016 - date_part('year',c.dt_nasc)) as idade, date_part('month',p.data_normal) as dt ");
            sql.append("FROM clientes as c, pedidos as p, pedidos_produtos as pj ");
            sql.append("WHERE p.id_cliente = c.id and p.id = pj.id_pedidos and date_part('month',p.data_normal) between 4 and 6 ");
            sql.append("GROUP BY c.id, c.nome,c.dt_nasc, date_part('month',p.data_normal) ");
            sql.append("ORDER BY dt,gasto ASC ");
            
            pst = conn.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                RelatorioClientes rc = new RelatorioClientes();
                
                rc.setNome(rs.getString("nome"));
                rc.setGasto(rs.getFloat("gasto"));
                rc.setCompras(rs.getInt("compras"));
                rc.setIdade(rs.getString("idade"));
                rc.setDt(rs.getString("dt"));
                
                entidades.add(rc);
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
	public void trocar2(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<EntidadeDominio> consultartrocacupom(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

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
    public void salvar(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub        
    }
}