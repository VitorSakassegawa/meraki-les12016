package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.VerificaQuantidadeVHWeb;
import meraki.com.br.core.IStrategy;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.core.impl.negocio.ValidaQuantidadeEstoque;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.ItemPedido;
import meraki.com.br.domain.Produto;
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
public class VerificaQuantidadeVHWeb implements IViewHelper
{
    private Produto produto;
    private int quantidade;
    private int index;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        produto = new Produto();
        String titulo = request.getParameter("txtTitulo");
        quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
        index = Integer.parseInt(request.getParameter("txtIndex"));
        
        produto.setTitulo(titulo);
        produto.setQuantidade(quantidade);
        
        return produto;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        IStrategy bussines = new ValidaQuantidadeEstoque();
        
        String result = bussines.processar(produto);
        
        if(result == null)
        {
            Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
            
            // Verificando a quantidade permitida para o produto
            pedido.getItens().get(index).setQuantidade(quantidade);
            
            request.getSession().setAttribute("carrinho", pedido);
        }
        else
        {
            request.setAttribute("erroQtde", result);
        }
        
        try
        {
        	// Mandando req. de volta
            request.getRequestDispatcher("VisualizarCarrinho.jsp").forward(request, response);  
        } catch (ServletException ex)
        {
            Logger.getLogger(VerificaQuantidadeVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(VerificaQuantidadeVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}