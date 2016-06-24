package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.InformarProdutoVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

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
public class InformarProdutoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Produto produto = new Produto();
        
        produto.setTitulo(request.getParameter("txtTitulo"));
        
        return produto;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        request.setAttribute("produto", resultado.getEntidades().get(0));
        
        try
        {
            request.getRequestDispatcher("VisualizarProduto.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(InformarProdutoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(InformarProdutoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}