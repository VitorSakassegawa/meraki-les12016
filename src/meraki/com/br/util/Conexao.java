package meraki.com.br.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* @author Vitor Sakassegawa
*/
public class Conexao
{
    public static Connection getConnetion() 
    		throws ClassNotFoundException, 
    		SQLException
    {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/Test";
        String user = "postgres";
        String password = "admin";
        Class.forName(driver);
        Connection conn = 
        		DriverManager.getConnection(url,user, password);
       
        return conn;     
    }
}