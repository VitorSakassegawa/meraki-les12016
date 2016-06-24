package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IStrategy;
import meraki.com.br.domain.EntidadeDominio;

import java.util.Calendar;

/**
*
* @author Vitor Sakassegawa
*/
public class ComplementadorDtCadastro implements IStrategy
{
    @Override
    public String processar(EntidadeDominio entidade)
    {
        Calendar c = Calendar.getInstance();
        entidade.setDate(c);       
        return null;
    }
}