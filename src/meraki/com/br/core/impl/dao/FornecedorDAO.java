package meraki.com.br.core.impl.dao;

import meraki.com.br.core.IDAO;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.core.impl.dao.EnderecoDAO;

/**
*
* @author Vitor Sakassegawa
*/
public class FornecedorDAO extends AbstractJdbcDAO
{

    public FornecedorDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public FornecedorDAO(String table, String idTable)
    {
        super(table, idTable);
    }

    public FornecedorDAO()
    {
        super("", "");
    } 

    @Override
    public void salvar(EntidadeDominio entidade)
    {
        IDAO enderecoDAO = new EnderecoDAO(conn, "enderecos", "id");

    	// Criando caminho para executar SQL no banco de dados
        PreparedStatement pst = null;
        try
        {
        	// Salvar primeiro endereço
            enderecoDAO.salvar(((Fornecedor) entidade).getEndereco());   

            // Verificar Foreign Key do endereço
            System.out.println("FK_ENDERECO: " + ((Fornecedor) entidade).getEndereco().getId());   

            if (conn == null || conn.isClosed())
            {
            	// Abrir conexão
                openConnection();
            }
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);

            StringBuilder sb = new StringBuilder();

            sb.append("INSERT INTO FORNECEDORES (nome,cnpj,telefone,email,id_endereco) ");
            sb.append("VALUES (?,?,?,?,?)");

            // Abrindo o caminho para a conexão com o banco de dados
            pst = conn.prepareStatement(sb.toString());

            // Set parâmetros
            pst.setString(1, ((Fornecedor) entidade).getNome());
            pst.setString(2, ((Fornecedor) entidade).getCnpj());
            pst.setString(3, ((Fornecedor) entidade).getTelefone());
            pst.setString(4, ((Fornecedor) entidade).getEmail());
            pst.setInt(5, ((Fornecedor) entidade).getEndereco().getId());

            // Execute query no banco de dados
            pst.execute();

            // Commit das alterações
            conn.commit();
        } catch (SQLException ex)
        {
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex.printStackTrace();
                System.out.println("Erro ao executar Rollback (FornecedorDAO)");
            }
            ex.printStackTrace();
        } finally
        {
            if (ctrlTransaction)
            {
                try
                {
                    pst.close();    

                    if (ctrlTransaction)
                    {
                        conn.close();   
                    }
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                    System.out.println("Erro ao fechar a conexao (FORNECEDOR DAO)");
                }
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade)
    {
    	// Criando caminho para executar SQL no banco de dados
        PreparedStatement pst = null;   
        try
        {
            if (conn == null || conn.isClosed())
            {
            	// Abrir conexão
                openConnection(); 
            }
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);

            // SQL de update
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE FORNECEDORES SET ");
            sb.append("nome = (?),");
            sb.append("cnpj = (?),");
            sb.append("telefone = (?),");
            sb.append("email = (?) ");
            sb.append("WHERE ID = ?");

            IDAO dao = new EnderecoDAO();

            dao.atualizar(((Fornecedor) entidade).getEndereco());

            // Abrindo o caminho para a conexão com o banco de dados
            pst = conn.prepareStatement(sb.toString()); 

            pst.setString(1, ((Fornecedor) entidade).getNome());
            pst.setString(2, ((Fornecedor) entidade).getCnpj());
            pst.setString(3, ((Fornecedor) entidade).getTelefone());
            pst.setString(4, ((Fornecedor) entidade).getEmail());
            pst.setInt(5, entidade.getId());

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
                if (ctrlTransaction)
                {
                    pst.close();
                }

                if (ctrlTransaction)
                {
                    conn.close();
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;
        List<EntidadeDominio> entidades = new ArrayList<>();

        try
        {
            if (conn == null || conn.isClosed()) 
            {
            	// Abrir conexão
                openConnection();  
            }

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM FORNECEDORES ");
            sb.append("WHERE cnpj = (?)");

            // Abrindo o caminho para a conexão com o banco de dados
            pst = conn.prepareStatement(sb.toString());

            // Set parâmetros do SQL
            pst.setString(1, ((Fornecedor) entidade).getCnpj());

            // Get resultados da query de consulta
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setEndereco(new Endereco());
                f.getEndereco().setId(rs.getInt("id_endereco"));

                IDAO dao = new EnderecoDAO();
                Endereco end = (Endereco) dao.consultar(f.getEndereco());
                f.setEndereco(end);

                // Adicionando um fornecedor na lista
                entidades.add(f);   

                return entidades;
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
            if (ctrlTransaction)
            {
                try
                {
                    pst.close();

                    if (ctrlTransaction)
                    {
                        conn.close();
                    }

                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        return entidades;
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        super.excluir(entidade);

        IDAO dao = new EnderecoDAO("enderecos", "id");

        dao.excluir(((Fornecedor) entidade).getEndereco());
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