package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Usuario;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class ConsultarPedidoVHweb implements IViewHelper
{
    private Pedido pedido = new Pedido();
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataPedido = null;
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");

        String requestData = request.getParameter("txtDataPedido") == null ? "" : request.getParameter("txtDataPedido");
        try
        {
            if (!requestData.equals(""))
            {
                dataPedido = dateFormat.parse(requestData);
            }
            else
            {
            	// Pegará a data atual se o cliente não digitar nada
                dataPedido = Calendar.getInstance().getTime();  
            }
        } catch (ParseException ex)
        {
            ex.printStackTrace();
            System.out.println("Formato de Data Invalida!");
        }
        // Estabelecendo o ID do cliente
        pedido.setCliente(cliente);  
        // Estabelecendo a dataPedido
        pedido.setDataNormal(dataPedido);   
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())
        {
            request.setAttribute("pedidos",resultado.getEntidades());
        }
        else
        {
            request.setAttribute("msgErro", resultado.getListMensagens());
        }
        try
        {
            if(pedido.getDataNormal() != null)
                request.getRequestDispatcher("Pedidos.jsp").forward(request, response);
            else
                request.getRequestDispatcher("DetalhePedido.jsp").forward(request, response);
        } 
        catch (ServletException | IOException ex)
        {
            ex.printStackTrace();
        }
    }
}