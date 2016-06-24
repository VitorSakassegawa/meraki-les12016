package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.LimparCarrinhoVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class LimparCarrinhoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
    	// Limpando o carrinho
        request.getSession().setAttribute("carrinho", null);  
        
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            response.sendRedirect("Produtos.jsp"); 
        } catch (IOException ex)
        {
            Logger.getLogger(LimparCarrinhoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}