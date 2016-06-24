package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.impl.negocio.ValidaCamposEndereco;
import meraki.com.br.core.IStrategy;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidadorAtualizacaoCliente implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Usuario cliente = (Usuario) entidade;
        StringBuilder sb = new StringBuilder();
        
        String nome = cliente.getNome();
        String cpf = cliente.getCpf();
        String telefone = cliente.getTelefone();
        String sexo = cliente.getSexo();
        String email = cliente.getEmail();
        String senha = cliente.getSenha();
        
        if(nome == null || cpf == null || telefone == null || sexo == null)
        {
            return "Nome, CPF, telefone e sexo. São campos obrigatórios!!";
        }
        
        if(nome.equals("") || cpf.equals("") || telefone.equals("") || sexo.equals(""))
        {
            return "Nome, CPF, telefone, sexo. São campos obrigatórios!!";
        }
        
        String endereco = new ValidaCamposEndereco().processar(cliente.getEndereco());
        
        return endereco;
    }
}