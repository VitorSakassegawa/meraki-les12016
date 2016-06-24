package meraki.com.br.core;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public interface IStrategy {
    public String processar(EntidadeDominio entidade);
}
