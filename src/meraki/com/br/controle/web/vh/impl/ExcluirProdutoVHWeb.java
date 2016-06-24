package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class ExcluirProdutoVHWeb implements IViewHelper
{
    private Produto produto;
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
    	// Instanciando Produto
        produto = new Produto();  
        
        int id = Integer.parseInt(request.getParameter("txtIdProduto"));
        
        produto.setId(id);
        produto.setTitulo(request.getParameter("txtTitulo"));
        
        return produto;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {        
        request.setAttribute("MsgExcluirProduto", produto.getTitulo()+" inativado com sucesso!");
        
        try
        {
            request.getRequestDispatcher("/root/dashboard.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}