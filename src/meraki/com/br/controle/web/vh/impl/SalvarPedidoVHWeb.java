package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Pedido;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class SalvarPedidoVHWeb implements IViewHelper
{
    private Pedido pedido;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
    	// Criando objeto pedido
        pedido = new Pedido();   

        pedido = (Pedido) request.getSession().getAttribute("carrinho");
        pedido.setTipoPagamento((String)request.getSession().getAttribute("tipoPagamento"));
        pedido.setDataNormal(new Date());
        pedido.setDataPedido(Calendar.getInstance());

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (resultado.getListMensagens().isEmpty())
        {
            String Message = "Seu pedido foi realizado com sucesso!";

            request.setAttribute("finalizado", Message);
            
            request.getSession().setAttribute("carrinho", null);

            try
            {
            	// Redirecionando para página
                request.getRequestDispatcher("Sucess.jsp").forward(request, response);  
            } catch (ServletException ex)
            {
                ex.printStackTrace();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        } else
        {
            String Message = null;

            for (String msg : resultado.getListMensagens())
            {
                Message = msg += "\n";
            }

            request.setAttribute("ErroFinalizado", Message);

            try
            {
            	// Redirecionando para a página
                request.getRequestDispatcher("Credito.jsp").forward(request, response);   
            } catch (ServletException ex)
            {
                ex.printStackTrace();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
