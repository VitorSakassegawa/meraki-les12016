package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.IStrategy;
import meraki.com.br.core.impl.dao.ClienteDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

import java.sql.SQLException;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidaLogin implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Usuario cliente  = new Usuario();
        
        IDAO dao = new ClienteDAO();
        
        try
        {
            ((ClienteDAO)dao).consultaEmailSenha(entidade);
            
        } catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return null;
    }
}