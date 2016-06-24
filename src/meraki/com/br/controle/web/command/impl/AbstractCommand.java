package meraki.com.br.controle.web.command.impl;

import meraki.com.br.controle.web.command.ICommand;
import meraki.com.br.core.IFachada;
import meraki.com.br.core.impl.controle.Fachada;

/**
*
* @author Vitor Sakassegawa
*/
public abstract class AbstractCommand implements ICommand {
	protected IFachada fachada = new Fachada();
	}