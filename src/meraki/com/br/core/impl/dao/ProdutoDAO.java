 package meraki.com.br.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

/**
*
* @author Vitor Sakassegawa
*/
public class ProdutoDAO extends AbstractJdbcDAO
{

    public ProdutoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public ProdutoDAO()
    {
        super("produtos", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;
        try
        {
        	// Abrir conexão
            openConnection();   
            
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO PRODUTOS ");
            sql.append("(TITULO,DESCRICAO,NUMERACAO,GENERO,VALOR,ANO_LANCAMENTO,ORIGEM,MATERIAL,CATEGORIA,IMAGE,QUANTIDADE_ESTOQUE,ATIVO) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            pst = conn.prepareStatement(sql.toString());
           
            // Set os parâmetros 
            pst.setString(1, produto.getTitulo());
            pst.setString(2, produto.getDescricao());
            pst.setString(3, produto.getNumeracao());
            pst.setString(4, produto.getGenero());
            pst.setFloat(5, produto.getValor());
            pst.setString(6, produto.getAno());
            pst.setString(7, produto.getOrigem());
            pst.setString(8, produto.getMaterial());
            pst.setString(9, produto.getCategoria());
            pst.setString(10, produto.getImage());
            pst.setInt(11, produto.getQuantidade());
            pst.setInt(12, 1);
            
            // Execute a query no banco de dados
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
            throw new SQLException(ex);
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
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;
        StringBuilder sql = new StringBuilder();

        try
        {
        	// Abrir conexão
            openConnection(); 
            
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);

            if (produto.getQuantidade() != 0)
            {
                sql.append("UPDATE PRODUTOS SET ");
                sql.append("quantidade_estoque = ? ");
                sql.append("WHERE ID = ?");

                pst = conn.prepareStatement(sql.toString());

                pst.setInt(1, produto.getQuantidade());
                pst.setInt(2, produto.getId());
            } 
            else
            {
                sql.append("UPDATE PRODUTOS SET ");
                sql.append("titulo = ?, ");
                sql.append("descricao = ?, ");
                sql.append("numeracao = ?, ");
                sql.append("genero = ?, ");
                sql.append("valor = ?, ");
                sql.append("ano_lancamento = ?, ");
                sql.append("origem = ?, ");
                sql.append("material = ?, ");
                sql.append("categoria = ? ");
                sql.append("WHERE id = ?");

                pst = conn.prepareStatement(sql.toString());

                pst.setString(1, produto.getTitulo());
                pst.setString(2, produto.getDescricao());
                pst.setString(3, produto.getNumeracao());
                pst.setString(4, produto.getGenero());
                pst.setDouble(5, produto.getValor());
                pst.setString(6, produto.getAno());
                pst.setString(7, produto.getOrigem());
                pst.setString(8, produto.getMaterial());
                pst.setString(9, produto.getCategoria());
                pst.setInt(10, produto.getId());
            }

            pst.executeUpdate();
            
            conn.commit();
        } catch (SQLException ex)
        {
            conn.rollback();
            ex.printStackTrace();
        } finally
        {
            pst.close();
            conn.close();
        }
    }

    @Override
    @SuppressWarnings("null")
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        List<EntidadeDominio> produtos = new ArrayList<>();
        Produto pro = (Produto) entidade;
        List<String> parametros = new ArrayList<String>();
        try
        {
        	// Abrir conexão
            openConnection();

            // Evitando nullpointer 
            if (pro.getTitulo() == null)
            {
            	pro.setTitulo("");
            }

            if (pro.getGenero() == null)
            {
            	pro.setGenero("");
            }

            if (pro.getNumeracao() == null)
            {
            	pro.setNumeracao("");
            }

            if (pro.getAno() == null)
            {
            	pro.setAno("");
            }

            sql.append("SELECT * FROM PRODUTOS WHERE ");

            if (!pro.getTitulo().equals(""))
            {
                sql.append("TITULO ILIKE ? and ");
                parametros.add("%" + pro.getTitulo() + "%");
            }

            if (!pro.getGenero().equals("") && !pro.getGenero().equals("Genero"))
            {
                sql.append("GENERO ILIKE ? and ");
                parametros.add("%" + pro.getGenero() + "%");
            }

            if (!pro.getNumeracao().equals(""))
            {
                sql.append("NUMERACAO = ? and ");
                parametros.add(pro.getNumeracao());
            }

            if (!pro.getAno().equals(""))
            {
                sql.append("ANO_LANCAMENTO = ? and ");
                parametros.add(pro.getAno());
            }
            
            sql.append("ativo = 1 ORDER BY quantidade_estoque ASC");
            
            if (parametros.isEmpty())
            {
                sql = new StringBuilder("SELECT * FROM PRODUTOS WHERE ativo = 1 ORDER BY quantidade_estoque ASC");
            }

            pst = conn.prepareStatement(sql.toString());

            if (!parametros.isEmpty())
            {
                int i = 1;
                for (String pr : parametros)
                {
                    pst.setString(i, pr);
                    i++;
                }
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setAno(rs.getString("ano_lancamento"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setGenero(rs.getString("genero"));
                produto.setNumeracao(rs.getString("numeracao"));
                produto.setValor(rs.getFloat("valor"));
                produto.setOrigem(rs.getString("origem"));
                produto.setMaterial(rs.getString("material"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setImage(rs.getString("image"));
                produto.setQuantidade(rs.getInt("quantidade_estoque"));

                // Adicionando produto na lista de produtos
                produtos.add(produto);    
            }

            return produtos;

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            conn.close();
            pst.close();
        }
        return null;
    }

    public List<EntidadeDominio> consultarTodos() throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        List<EntidadeDominio> produtos = new ArrayList<>();

        try
        {
        	// Abrir conexão
            openConnection();

            sql.append("SELECT * FROM PRODUTOS ");

            pst = conn.prepareStatement(sql.toString());

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setGenero(rs.getString("genero"));
                produto.setNumeracao(rs.getString("numeracao"));
                produto.setValor(rs.getFloat("valor"));
                produto.setImage(rs.getString("image"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidade(rs.getInt("quantidade_estoque"));
                // Adicionando produto na lista de produtos
                produtos.add(produto);   
            }

            if (!produtos.isEmpty())
            {
                return produtos;
            } else
            {
                return null;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            conn.close();
            pst.close();
        }
        return null;
    }

    public void atualizarAquantidade(EntidadeDominio entidade) throws SQLException
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
                conn.setAutoCommit(false);
            }

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PRODUTOS SET ");
            sql.append("quantidade_estoque = quantidade_estoque - (?) ");
            sql.append("WHERE id = ? and quantidade_estoque >= ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setInt(1, produto.getQuantidade());
            pst.setInt(2, produto.getId());
            pst.setInt(3, produto.getQuantidade());

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

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        Produto produto = (Produto) entidade;
        try
        {
        	// Abrir conexão
            openConnection();
            conn.setAutoCommit(false);

            sql.append("UPDATE PRODUTOS SET ");
            sql.append("ativo = 0 ");
            sql.append("WHERE id = ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setInt(1, produto.getId());

            // Execute query de update
            pst.executeUpdate();    

            // Commit das alterações
            conn.commit();
        } catch (SQLException ex)
        {
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
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