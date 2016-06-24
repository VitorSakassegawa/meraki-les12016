package meraki.com.br.controle.web.command.impl;

import meraki.com.br.controle.web.command.impl.AbstractCommand;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class CommandConsultarGrafico extends AbstractCommand {
    @Override
    public Resultado execute(EntidadeDominio entidade) {
        return fachada.consultargrafico(entidade);
        }   
    }