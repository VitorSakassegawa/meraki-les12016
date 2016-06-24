package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IStrategy;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidaEntrega implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        EnderecoEntrega endereco = (EnderecoEntrega) entidade;
        
        StringBuilder mensagem = new StringBuilder();
        
        if( (endereco.getLogradouro().equals("") || endereco.getLogradouro() == null) || 
            (endereco.getNumero().equals("") || endereco.getNumero() == null) || 
            (endereco.getBairro().equals("") || endereco.getBairro() == null) ||
            (endereco.getCep().equals("") || endereco.getCep() == null) || 
            (endereco.getCidade().equals("") || endereco.getCidade() == null) || 
            (endereco.getEstado().equals("") || endereco.getEstado() == null))
        {
            mensagem.append("Preencha os campos obrigatórios: ");
            mensagem.append("Logradouro, número, bairro, CEP, cidade e estado");
        }
        if(mensagem.length() > 0)
            return mensagem.toString();
        else
            return null;
    }    
}