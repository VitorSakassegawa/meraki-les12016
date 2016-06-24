package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.Cartao;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Frete;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.TransacaoCartao;
import meraki.com.br.domain.Usuario;

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
public class SalvarTrocaVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {   
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");
        Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
        pedido.setDataNormal(new Date());
        pedido.setDataPedido(Calendar.getInstance());
        pedido.setTipoPagamento("Cartão de Crédito: "+request.getParameter("txtFormaPagamento"));
        pedido.setCliente(cliente);
        pedido.setPosicao("Aguardando Pagamento");
        pedido.setStatus("Aguardando Entrega");
        
        Cartao cartao = new Cartao();
        
        cartao.setNomeCartao(request.getParameter("txtNomeCartao"));
        cartao.setParcelas(request.getParameter("txtFormaPagamento"));
        cartao.setNumeroCartao(request.getParameter("txtNumeroCartao"));
        cartao.setNumeroFinalCartao(request.getParameter("txtNumeroCartao"));
        
        TransacaoCartao transacao = new TransacaoCartao();
        transacao.setCartao(cartao);
        transacao.setPedido(pedido);
        transacao.setCliente(cliente);
        transacao.setStatus("Pedente!");
        transacao.setDescricao("Cartão de Crédito");
        transacao.setData(new Date());
        
        return transacao;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())
        {
            try
            {
                request.getSession().setAttribute("carrinho", null);
                
                request.getRequestDispatcher("Sucess.jsp").forward(request, response);
            }
            catch (ServletException | IOException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            try
            {
                request.setAttribute("msgErroCartao", resultado.getListMensagens());
                
                request.getRequestDispatcher("MetodoPagamento.jsp").forward(request, response);
                
            } catch (IOException ex)
            {
                ex.printStackTrace();
                
            } catch (ServletException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}