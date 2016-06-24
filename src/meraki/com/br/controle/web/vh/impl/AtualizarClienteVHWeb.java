package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

import java.io.IOException;
import java.text.DateFormat;
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
public class AtualizarClienteVHWeb implements IViewHelper
{
	// Construtor default
    public AtualizarClienteVHWeb()
    {

    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");
        
        Endereco endereco = cliente.getEndereco();
        
        cliente.setNome(request.getParameter("txtNome"));
        cliente.setStatus(1);
        cliente.setCpf(request.getParameter("txtCpf"));
        cliente.setSexo(request.getParameter("selSexo"));
        cliente.setTelefone(request.getParameter("txtTelefone"));

        if (request.getParameter("txtDataNasc").trim().equals(""))
        {
            cliente.setDataNascimento(null);
        } 
        else
        {
            try
            {
            	// Estabelecendo a data
                DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dt = sdFormat.parse(request.getParameter("txtDataNasc"));
                cliente.setDataNascimento(Calendar.getInstance());
            	// Estabelecendo a data
                cliente.getDataNascimento().setTime(dt);
            } catch (ParseException ex)
            {
                cliente.setDataNascimento(null);
            }
        }
    	// Estabelecendo o endereço
        endereco.setLogradouro(request.getParameter("txtLogradouro"));
        endereco.setNumero(request.getParameter("txtNumero"));
        endereco.setBairro(request.getParameter("txtBairro"));
        endereco.setCEP(request.getParameter("txtCep"));
        endereco.setCidade(request.getParameter("txtCidade"));
        endereco.setEstado(request.getParameter("txtEstado"));

        cliente.setEndereco(endereco);

        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (resultado.getListMensagens().isEmpty())
        {
            request.setAttribute("sucessAtualizaCliente", "Dados Atualizados com Sucesso!");
        } else
        {
            request.setAttribute("erroAtualizaCliente", resultado.getListMensagens());
        }

        try
        {
            request.getRequestDispatcher("SalvarCliente.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}