package meraki.com.br.util;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public interface FiltroSQL
{
    public String getFilter(EntidadeDominio entidade);
}