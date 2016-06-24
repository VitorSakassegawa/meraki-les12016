package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class ConsultarClienteVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        String result = request.getParameter("txtPesquisar");
        Usuario cliente = new Usuario();

        try
        {
            Integer number = new Integer(result);
            if (result.length() == 9)
            {
                cliente.setRg(result);
            } else
            {
                cliente.setId(number);
            }
        } catch (NumberFormatException ex)
        {
            if (!result.contains(" ") && result.length() == 11)
            {
                cliente.setCpf(result);
            } else
            {
                cliente.setNome(result);
            }
        }
        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        List<EntidadeDominio> entidades = resultado.getEntidades();

        if (entidades == null||entidades.isEmpty())
        {
            request.getSession().setAttribute("msg", "Cliente Nao encontrado");
            request.getSession().setAttribute("clientes", null);
        } else
        {
            request.getSession().setAttribute("msg", "");
            request.getSession().setAttribute("clientes", entidades);
        }

        try
        {
            if (request.getRequestURI().contains("ConsultarTodos"))
            {
                request.getRequestDispatcher("ConsultarTodos.jsp").forward(request, response);
            } else
            {
                request.getRequestDispatcher("ConsultarCliente.jsp").forward(request, response);
            }

        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}