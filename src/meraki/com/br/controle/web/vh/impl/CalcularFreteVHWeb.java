package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.CalcularFreteVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Frete;
import meraki.com.br.domain.Pedido;
import meraki.com.br.util.CalculaFrete;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class CalcularFreteVHWeb implements IViewHelper
{
	// Construtor default
    public CalcularFreteVHWeb()
    {
    	
    }
    
    private Pedido pedido;
    
    CalculaFrete calcula = new CalculaFrete();
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
    	// Get carrinho
        pedido = (Pedido) request.getSession().getAttribute("carrinho");
        
        // Get CEP para efetuar o cálculo do frete
        String cep = request.getParameter("txtCep");    
        
        try
        {
            Frete frete = calcula.getInfoFrete(cep, "Sedex"); 
            
            pedido.setFrete(frete);
            
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
    	// Ocorreu um erro ao pegar o frete
        if(pedido.getFrete() == null) 
        {
        	// Passando a mensagem de erro do frete
            request.setAttribute("msgFrete", calcula.getServico().cServico.MsgErro);
            pedido.setFrete(null);
        }
        else    
        {
            request.getSession().setAttribute("carrinho", pedido);
        }
        
        try
        {
            request.getRequestDispatcher("VisualizarCarrinho.jsp").forward(request, response);
        } 
        catch (ServletException ex)
        {
            Logger.getLogger(CalcularFreteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(CalcularFreteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
