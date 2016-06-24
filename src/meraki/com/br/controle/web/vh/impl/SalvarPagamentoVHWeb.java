package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.SalvarPagamentoVHWeb;
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
public class SalvarPagamentoVHWeb implements IViewHelper
{

    public SalvarPagamentoVHWeb(){}

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty()) 
        {
            StringBuilder sucess = new StringBuilder();
            sucess.append("Parabéns, sua solicitação de pagamento foi efetuada com sucesso!");
            sucess.append("\n");
            sucess.append("Acompanhe a aprovação de seu pedido");
            request.setAttribute("sucess", sucess.toString());  
            try
            {
                request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
            } catch (ServletException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            request.setAttribute("erroTransacao", resultado.getListMensagens());
            try
            {
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (ServletException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
}