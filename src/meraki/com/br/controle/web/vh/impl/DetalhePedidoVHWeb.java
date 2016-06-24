package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Frete;
import meraki.com.br.domain.Pedido;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class DetalhePedidoVHWeb implements IViewHelper
{
    // Construtor default
    public DetalhePedidoVHWeb()
    {
    }
    private Pedido pedido;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        pedido = new Pedido();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataPedido = Calendar.getInstance();
        int id = Integer.parseInt(request.getParameter("txtId"));
        Date data = null;
        try
        {
            data = sdf.parse(request.getParameter("txtData"));
            dataPedido.setTime(data);
        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        String tipoPagamento = request.getParameter("txtTipoPagamento");
        String posicao = request.getParameter("txtPosicao");
        String status = request.getParameter("txtStatus");
        int idEntrega = Integer.parseInt(request.getParameter("txtIdEntrega"));
        float frete = Float.parseFloat(request.getParameter("txtFrete"));
        
        pedido.setId(id);
        pedido.setFrete(new Frete(frete));
        pedido.setStatus(status);
        pedido.setTipoPagamento(tipoPagamento);
        pedido.setPosicao(posicao);
        pedido.setDataPedido(dataPedido);
        pedido.setEntrega(new EnderecoEntrega(idEntrega));
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        
        if(resultado.getListMensagens().isEmpty())
        {
            pedido = (Pedido) resultado.getEntidades().get(0);
            
            request.setAttribute("pedido", pedido);
            
            dispatcher = request.getRequestDispatcher("DetalhePedido.jsp");
        }
        //Erro?!
        else    
        {
            request.setAttribute("MsgErroPedido", resultado.getListMensagens());
            
            dispatcher = request.getRequestDispatcher("Pedidos.jsp");
        }
         
        // Enviar requisição de volta para o cliente
        try
        {
        	// Enviar requisição
            dispatcher.forward(request, response); 
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
