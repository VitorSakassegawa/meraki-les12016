package meraki.com.br.core.impl.dao;

import meraki.com.br.core.IDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.ItemPedido;
import meraki.com.br.domain.Produto;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.PedidoProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.core.impl.dao.ProdutoDAO;

/**
*
* @author Vitor Sakassegawa
*/
public class PedidoProdutoDAO extends AbstractJdbcDAO
{

    public PedidoProdutoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public PedidoProdutoDAO(Connection connection)
    {
        super(connection, "pedidos_produtos", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        PedidoProduto pedidoProduto = (PedidoProduto) entidade;

        try
        {
            if(conn == null || conn.isClosed())
            {     
            	// Abrir conexão
                openConnection();
                
                // Set AutoCommit p/ false
                conn.setAutoCommit(false);
            }            
            
            StringBuilder sql = new StringBuilder();    

            sql.append("INSERT INTO PEDIDOS_PRODUTOS ");
            sql.append("(id_pedidos,id_produtos,quantidade,desconto) VALUES ");
            sql.append("(?,?,?,?)");

            // Criando o caminho para conexão com o banco de dados
            pst = conn.prepareStatement(sql.toString()); 

            pst.setInt(1, pedidoProduto.getIdPedido());
            pst.setInt(2, pedidoProduto.getIdProduto());
            pst.setInt(3, pedidoProduto.getQuantidade());
            pst.setFloat(4, pedidoProduto.getItem().getDesconto());
            
            // Execute a query no banco de dados
            pst.execute();
            
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
            throw new SQLDataException(ex);
        } finally
        {
            if (!ctrlTransaction)
            {
                try
                {
                    conn.close();
                    pst.close();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Irá retornar uma lista com os produtos do pedido
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        try
        {
            if(conn == null || conn.isClosed())
            	// Abrir conexão
                openConnection();  
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PEDIDOS_PRODUTOS WHERE ID_PEDIDOS = ?");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, pedido.getId());
            
            ResultSet rs = pst.executeQuery();
            
            IDAO dao = new ProdutoDAO(conn, "", "");
            List<EntidadeDominio> itens = new ArrayList<>();
            float desconto = 0;
            while(rs.next())
            {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produtos"));
                
                // Get lista de produtos na busca
                List<EntidadeDominio> produtos = dao.consultar(produto);  
                
                // Get primeiro produto da lista
                produto = (Produto) produtos.get(0);    

                // Criando ItemPedido
                ItemPedido item = new ItemPedido(); 
                item.setDesconto(rs.getFloat("desconto"));
                item.setProduto(produto);
                item.setQuantidade(rs.getInt("quantidade"));
                
                // Retornando a lista de itens
                itens.add(item);    
            }
            return itens;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
                
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
	
    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException	{
		// TODO Auto-generated method stub
    }
}