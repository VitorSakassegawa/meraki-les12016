package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.ExcluirClienteVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

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
public class ExcluirClienteVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Usuario cliente = new Usuario();
        int idCliente = 0;
        int idEndereco = 0;

        // ID vazio?!
        if (!request.getParameter("txtIdCliente").trim().equals(""))    
        { 	// Vazio
            idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
            idEndereco = Integer.parseInt(request.getParameter("txtIdEndereco"));
        }

        cliente.setId(idCliente);
        cliente.setEndereco(new Endereco());
        cliente.getEndereco().setId(idEndereco);
        
        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (resultado == null)
        {
            request.getSession().setAttribute("msg", "Cliente Excluido");
            request.getSession().setAttribute("clientes", null);
        } else
        {
            request.getSession().setAttribute("msg", resultado.getListMensagens());
        }

        try
        {
            request.getRequestDispatcher("ConsultarCliente.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(ExcluirClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(ExcluirClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}