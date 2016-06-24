package meraki.com.br.teste;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.EnderecoEntregaDAO;
import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.EntidadeDominio;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author Vitor Sakassegawa
*/
public class TesteEndereco
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        EnderecoEntrega entrega = new EnderecoEntrega();
        
        entrega.setLogradouro("Rua Praia Grande");
        entrega.setNumero("65");
        entrega.setBairro("Jardim Santa Clara");
        entrega.setCep("07123179");
        entrega.setCidade("Guarulhos");
        entrega.setEstado("Sao Paulo");
        
        IDAO dao = new EnderecoEntregaDAO();
        
        try
        {
            dao.salvar(entrega);
            List<EntidadeDominio> entidades = dao.consultar(entrega);
            
            for (EntidadeDominio ed : entidades )
            {
                System.out.println("logradouro: "+((EnderecoEntrega)ed).getLogradouro());
            }
        } catch (SQLException ex)
        {
        	// Imprimirá pilha de erros
            ex.printStackTrace();   
        }
    }    
}