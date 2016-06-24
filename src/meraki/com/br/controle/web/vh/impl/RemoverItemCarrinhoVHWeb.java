package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.RemoverItemCarrinhoVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Pedido;

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
public class RemoverItemCarrinhoVHWeb implements IViewHelper
{
    private Pedido pedido;
    int indexItem;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {   
        pedido = (Pedido) request.getSession().getAttribute("carrinho");
        
        indexItem = Integer.parseInt(request.getParameter("txtIdItem"));
        
        // Removendo item do carrinho
        pedido.getItens().remove(indexItem);  
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().setAttribute("carrinho", pedido);
        
        try
        {
            request.getRequestDispatcher("VisualizarCarrinho.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(RemoverItemCarrinhoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(RemoverItemCarrinhoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}