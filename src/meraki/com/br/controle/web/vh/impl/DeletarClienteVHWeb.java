package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.DeletarClienteVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class DeletarClienteVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
     
        return usuario;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(!resultado.getListMensagens().isEmpty())
        {
            request.setAttribute("MsgDesativarErro", resultado.getListMensagens());
        }
        
        try
        {
        	// Invalidará sessão do cliente
            request.getSession().invalidate(); 
            request.getRequestDispatcher("DesativarConta.jsp").forward(request, response);
        }
        catch (ServletException ex)
        {
            Logger.getLogger(DeletarClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(DeletarClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}