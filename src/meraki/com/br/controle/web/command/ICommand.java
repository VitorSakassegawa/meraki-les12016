package meraki.com.br.controle.web.command;

import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public interface ICommand {
    public Resultado execute(EntidadeDominio entidade);
}
