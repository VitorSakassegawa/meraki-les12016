package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Frete;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Produto;

import java.io.IOException;
import java.sql.Timestamp;
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
public class ConsultarTrocaVHWeb implements IViewHelper
{
    private Pedido pedido = new Pedido();
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
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
                request.getRequestDispatcher("Trocas.jsp").forward(request, response);
            else
                request.getRequestDispatcher("Trocas.jsp").forward(request, response);
        } 
        catch (ServletException | IOException ex)
        {
            ex.printStackTrace();
        }
    }
}