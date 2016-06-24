package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.IStrategy;
import meraki.com.br.core.impl.dao.ClienteDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidadorCliente implements IStrategy
{
    @Override
    public String processar(EntidadeDominio entidade)
    {
        IDAO dao = new ClienteDAO();
        Usuario us1 = (Usuario) entidade;
        Usuario us2 = new Usuario();
        try
        {
            List<EntidadeDominio> resultado = dao.consultar(entidade);
            
            if(!resultado.isEmpty())
            {
                us2 = (Usuario) resultado.get(0);
            
                if(us2.getEmail().equals(us1.getEmail()) && us2.getCpf().equals(us1.getCpf()))
                    return "Email e CPF já cadastrados!";
                if(us2.getEmail().equals(us1.getEmail()))
                    return "Email já cadastrado!";
                else if(us2.getCpf().equals(us1.getCpf()))
                    return "CPF já cadastrado";
            }
             return null;
            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return "Erro ao consultar cliente";
        }
    }
}