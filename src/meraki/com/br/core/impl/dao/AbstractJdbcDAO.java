package meraki.com.br.core.impl.dao;

import meraki.com.br.core.IDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
*
* @author Vitor Sakassegawa
*/
public abstract class AbstractJdbcDAO implements IDAO
{
    protected Connection conn;
    protected String table;
    protected String idTable;
    protected boolean ctrlTransaction = true;
    
    /**
     * Construtor da classe
     * @param connection Aceita uma conexao valida  
     * @param table - Nome da Tabela que para exclusões futuras
     * @param idTable - Nome do id (PK) da tabela para exclusao de registros
     */
    public AbstractJdbcDAO(Connection connection, String table, String idTable)
    {
        this.conn = connection;
        this.table = table;
        this.idTable = idTable;
    }
    
    /**
     * Construtor que para conexaos abertas
     * @param table -  Nome da Tabela que para exclusões futuras
     * @param idTable - Nome do id (PK) da tabela para exclusao de registros
     */
    public AbstractJdbcDAO(String table, String idTable)
    {
        this.table = table;
        this.idTable = idTable;
    }

    /**
     * Exclui um registro no banco de dados
     * OBS: Método de Exclusao genérico!
     * @param entidade entidade domínio contendo
     * o id populado para exclusao na tabela
     * @throws java.sql.SQLException
     */
    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        //abrindo conexao
        openConnection();
        
        PreparedStatement pst = null;
        
        // Concatenação no SQL em uma única string
        StringBuilder sb = new StringBuilder(); 
        
        sb.append("DELETE FROM ");
        sb.append(table);
        sb.append(" WHERE ");
        sb.append(idTable);
        sb.append(" = ");
        sb.append("?");
        
        try
        {
            // Set AutoCommit p/ false
            conn.setAutoCommit(false);
            
            // Conexão com o banco
            pst = conn.prepareStatement(sb.toString()); 
            // Set o parâmetro da tabela do banco
            pst.setInt(1, entidade.getId());    
            
            pst.executeUpdate();
            
            // Commit das alteracao
            conn.commit();  
        }
        catch(SQLException ex)
        {
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex.printStackTrace();
                System.out.println("Erro ao tentar Roolback");
            }
            ex.printStackTrace();
            System.out.println("Erro ao efetuar commit");
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
                System.out.println("Erro ao tentar fechar a conexao");
            }
        }
    }
    /**
     * Abre conexao com o banco de dados
     */
    protected void openConnection()
    {
        try
        {
        	// Conexão está fechada?!
            if(conn == null || conn.isClosed())
            {
            	// Abrir conexão
                conn = Conexao.getConnetion();
            }
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Driver não encontrado");
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("Erro ao abrir a conexao");
        }
    }
}