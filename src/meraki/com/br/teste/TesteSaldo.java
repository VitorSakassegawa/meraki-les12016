package meraki.com.br.teste;

import java.sql.SQLException;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.SaldoDAO;
import meraki.com.br.domain.Saldo;

/**
*
* @author Vitor Sakassegawa
*/
public class TesteSaldo
{
    public static void main(String[] args)
    {
    	Saldo saldo = new Saldo();
    	
    	saldo.setId(2);
    	saldo.setId(77);
    	saldo.setSaldo(1000);
    	    
    	IDAO dao = new SaldoDAO();
    	
    	try {
			dao.salvar(saldo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}