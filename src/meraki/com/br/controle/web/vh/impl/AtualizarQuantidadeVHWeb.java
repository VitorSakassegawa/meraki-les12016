package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

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
public class AtualizarQuantidadeVHWeb implements IViewHelper
{
	// Construtor default
    public AtualizarQuantidadeVHWeb()   
    {
        
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        int quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
        int id = Integer.parseInt(request.getParameter("txtId"));
        
        Produto produto = new Produto();
        
        produto.setId(id);
        produto.setQuantidade(quantidade);
        return produto;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(!resultado.getListMensagens().isEmpty()) 
        {
            request.setAttribute("erroMsgEstoque", resultado.getListMensagens());
        }
        else
        {
           request.setAttribute("sucessMsgEstoque", "Quantidade atualizada com sucesso!");
        }
        try
        {
            request.getRequestDispatcher("/root/dashboard.jsp").forward(request, response);
        }
        catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}