package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.ConsultaViaBoletoVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Usuario;
import meraki.com.br.teste.MeuBoleto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class ConsultaViaBoletoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido pedido = new Pedido();
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");
        
        pedido.setCliente(cliente);
        
        int id = Integer.parseInt(request.getParameter("txtIdPedido"));
        int idEntrega = Integer.parseInt(request.getParameter("txtIdEntrega"));
        
        pedido.setId(id);
        pedido.setEntrega(new EnderecoEntrega(idEntrega));
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
    	// Não existe mensagens de erro?!
        if(resultado.getListMensagens().isEmpty())  
        {
            MeuBoleto boleto = new MeuBoleto();

            // Get resultado retornado
            Pedido pedido = (Pedido) resultado.getEntidades().get(0);               
         
            ByteArrayOutputStream byteArray = boleto.getBoleto(pedido);
            
            response.setContentLength(byteArray.size());
            
            try
            {
                ServletOutputStream outServlet = response.getOutputStream();
                outServlet.write(byteArray.toByteArray(),0,byteArray.size());
                outServlet.flush();
                outServlet.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ConsultaViaBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        else
        {
            request.setAttribute("msgPedidos", resultado.getListMensagens());
            
            try
            {
                request.getRequestDispatcher("Pedidos.jsp").forward(request, response);
            } catch (ServletException ex)
            {
                Logger.getLogger(ConsultaViaBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(ConsultaViaBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}