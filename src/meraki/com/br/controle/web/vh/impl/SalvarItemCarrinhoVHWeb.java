package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.ItemPedido;
import meraki.com.br.domain.Produto;
import meraki.com.br.domain.Pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class SalvarItemCarrinhoVHWeb implements IViewHelper
{

    private Pedido pedido;
    private Produto produto;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        pedido = (Pedido) request.getSession().getAttribute("carrinho");

        if (pedido == null)  
        {
            pedido = new Pedido();
        }

        produto = new Produto();
        ItemPedido item = new ItemPedido();
        
        // Carrinho está vazio?!
        if (pedido.getItens().isEmpty()) 
        {
            pedido.setItens(new ArrayList<>());
        }
        String titulo = request.getParameter("txtTitulo");
        int id = Integer.parseInt(request.getParameter("txtCompraId"));
        float valor = Float.parseFloat(request.getParameter("txtValor"));

        produto.setTitulo(titulo);
        produto.setId(id);
        produto.setValor(valor);

        item.setProduto(produto);
        item.setQuantidade(1);

        // Verificando se já existe um produto no carrinho
        for (ItemPedido p : pedido.getItens())
        {
            if (p.getProduto().getTitulo().equals(produto.getTitulo()))
            {
                produto = null;
                return pedido;
            }
        // Não existe o produto no carrinho
        }

        pedido.addItem(item);

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
    	// O produto já existe no carrinho?!
        if (produto == null)    
        {
        	// Sim
            request.setAttribute("msgCompraDuplicada", "Esse produto já foi adicionado no Carrinho!");
        } else
        {
        	// Não
            request.setAttribute("msgCompra", produto.getTitulo() + " adicionado ao carrinho!");
        }

        request.getSession().setAttribute("carrinho", pedido);

        try
        {
            request.getRequestDispatcher("Produtos.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}