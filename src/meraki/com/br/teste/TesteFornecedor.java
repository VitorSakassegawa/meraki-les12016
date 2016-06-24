package meraki.com.br.teste;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.FornecedorDAO;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.Fornecedor;

/**
*
* @author Vitor Sakassegawa
*/
public class TesteFornecedor
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Endereco end = new Endereco();
        end.setLogradouro("Rua Lagoa Dourada, 90");
        end.setBairro("Cocaia");
        end.setCEP("07130-210");
        end.setCidade("Guarulhos");
        end.setEstado("Sao Paulo");
        
        Fornecedor f = new Fornecedor();
        f.setNome("MERAKI - Comercio Ltda");
        f.setTelefone("2403-4074");
        f.setEmail("frank.sakassegava@gmail.com.br");
        f.setCnpj("02403879000148");
        f.setEndereco(end);
        
        f.setCnpj("02403879000148");
        
        IDAO dao = new FornecedorDAO();
    }
}