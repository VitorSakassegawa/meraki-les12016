package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IStrategy;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Fornecedor;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidadorDadosFornecedor implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Fornecedor f = (Fornecedor) entidade;
        StringBuilder msg = new StringBuilder();
        
        // Validando valores do CNPJ
        String temp = f.getCnpj().replace(".", "").replace("/", "").replace("-", "");
        
        if(temp.length() < 14 || temp.length() > 14)
        {
            msg.append("Formato Invalido do CNPJ\n");
        }
        
        if(temp.equals(""))
        {
            msg.append("CNPJ não pode estar vazio\n");
        }
        // Validando nome
        if(f.getNome().equals(""))
        {
            msg.append("Preencha o nome do fornecedor\n");
        }
        
        // Validando telefone
        if(f.getEmail().equals(""))
        {
            msg.append("E-mail deve ser preenchido\n");
        }
        else if(!f.getEmail().contains("@"))
        {
            msg.append("Formato inválido de e-mail\n");
        }
        
        if(msg.length() == 0)
        {
            return null;
        }
        return msg.toString();
    }   
}