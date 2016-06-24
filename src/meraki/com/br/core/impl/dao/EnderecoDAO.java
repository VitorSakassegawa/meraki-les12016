package meraki.com.br.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import meraki.com.br.core.impl.dao.AbstractJdbcDAO;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class EnderecoDAO extends AbstractJdbcDAO
{

    public EnderecoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public EnderecoDAO()
    {
        super("enderecos", "id");
    }
    
    public EnderecoDAO(String table, String idTable)
    {
        super(table,idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        try
        {
            if (conn == null || conn.isClosed())
            {
            	// Abrir conexão
                openConnection();   
            }

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO ENDERECOS ");
            sql.append("(LOGRADOURO,CEP,BAIRRO,ESTADO,CIDADE,NUMERO) ");
            sql.append(" VALUES (?,?,?,?,?,?) ");

            // Set AutoCommit p/ false
            conn.setAutoCommit(false);

            // Criando o caminho para conexão com o banco de dados
            pst = conn.prepareStatement(sql.toString(),
            		// Retornará a última chave inserida no banco de dados
                    Statement.RETURN_GENERATED_KEYS);   

            // Set parâmetros do insert no banco de dados
            pst.setString(1, ((Endereco) entidade).getLogradouro());
            pst.setString(2, ((Endereco) entidade).getCEP());
            pst.setString(3, ((Endereco) entidade).getBairro());
            pst.setString(4, ((Endereco) entidade).getEstado());
            pst.setString(5, ((Endereco) entidade).getCidade());
            pst.setString(6,((Endereco)entidade).getNumero());

            // Execute a query no banco de dados
            pst.executeUpdate();  

            // Get ID da ultima inserção no banco de dados
            ResultSet rs = pst.getGeneratedKeys();

            // Conseguiu concluir?!
            if (rs.next())
            {
            	// Conseguiu
                entidade.setId(rs.getInt("id"));
            }

        } catch (SQLException ex)
        {
            try
            {
            	// Não efetua as alteracoes no banco de dados
                conn.rollback();    
            } catch (SQLException ex1)
            {
            	// Imprimirá o erro no console
                ex1.printStackTrace();  
                throw new SQLException(ex1);
            }
            ex.printStackTrace();
            throw new SQLException(ex);
        } finally
        {
            if (!ctrlTransaction)
            {
                try
                {
                    pst.close();

                    if (!ctrlTransaction)
                    {
                    	// Fecha a conexão com o banco de dados
                        conn.close();    
                    }
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
    	// Criando caminho para executar SQL no banco de dados
        PreparedStatement pst = null;   
        try
        {
            if(conn == null || conn.isClosed())
            {
            	// Abrir conexão
                openConnection();
            }
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);
            
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ENDERECOS SET ");
            sb.append("LOGRADOURO = (?),");
            sb.append("BAIRRO = (?),");
            sb.append("CEP = (?),");
            sb.append("CIDADE = (?),");
            sb.append("NUMERO = (?),");
            sb.append("ESTADO = ? ");
            sb.append("WHERE ID = (?)");
            
            // Abrindo o caminho para a conexão com o banco de dados
            pst = conn.prepareStatement(sb.toString()); 
            pst.setString(1,((Endereco)entidade).getLogradouro());
            pst.setString(2, ((Endereco)entidade).getBairro());
            pst.setString(3, ((Endereco)entidade).getCEP());
            pst.setString(4, ((Endereco)entidade).getCidade());
            pst.setString(5, ((Endereco)entidade).getNumero());
            pst.setString(6, ((Endereco)entidade).getEstado());
            pst.setInt(7,((Endereco)entidade).getId());
            
            // Execute query de update
            pst.executeUpdate(); 
            
            // Commit das alterações
            conn.commit(); 
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            try
            {
            	// Cancela as alterações
                conn.rollback();    
            } 
            catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
        }
        finally
        {
            try
            {
                if(!ctrlTransaction)
                    pst.close();
                
                if(!ctrlTransaction)
                    conn.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade)
    {
    	// Criando caminho para executar SQL no banco de dados
        PreparedStatement pst = null;   
        List<EntidadeDominio> entidades = new ArrayList<>();
        try
        {
            if(conn == null || conn.isClosed())
            {
            	// Abrir conexão
                openConnection();
            }
            
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ENDERECOS ");
            sb.append("WHERE ID = (?)");
            
            pst = conn.prepareStatement(sb.toString());
            
            // Set parâmetros da query
            pst.setInt(1,entidade.getId()); 
            
            // Get resultado da consulta
            ResultSet rs = pst.executeQuery();  
            
            if(rs.next())
            {
                Endereco end = new Endereco();
                end.setId(rs.getInt("id"));
                end.setLogradouro(rs.getString("logradouro"));
                end.setBairro(rs.getString("bairro"));
                end.setCEP(rs.getString("cep"));
                end.setEstado(rs.getString("estado"));
                end.setCidade(rs.getString("cidade"));
                end.setNumero(rs.getString("numero"));
                entidades.add(end);
            }
            return entidades;
        }
        catch(SQLException ex)
        {
            try
            {
                conn.rollback();
            } 
            catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
        }
        finally
        {
            try 
            {
                if(ctrlTransaction)
                pst.close();
                
                if(ctrlTransaction)
                conn.close();
            }
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
        return entidades;
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