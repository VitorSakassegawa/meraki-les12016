package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.ConsultarProdutoVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;
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
public class ConsultarProdutoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Produto produto = new Produto();
        
        String titulo = request.getParameter("txtProcuraProduto"); 
        String genero = request.getParameter("selGenero");
        String ano = request.getParameter("txtAno");
        String numeracao = request.getParameter("txtNumeracao");
        
        // Prevenindo nullpointer
        produto.setTitulo(titulo == null ? "":titulo); 
        produto.setGenero(genero == null ? "":genero);
        produto.setAno(ano == null ? "":ano);
        produto.setNumeracao(numeracao == null ? "":numeracao);
        
        if(request.getParameter("operacao") == null)
            request.setAttribute("operacao", "Consultar");
        
        return produto;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getEntidades().isEmpty())
        {
            request.setAttribute("ConsultaProdutoErro", "Nenhum produto encontrado com esse titulo");
        }
        else
        {
            request.setAttribute("produtos", resultado.getEntidades());
        }
        
        try
        {
            Usuario usuario = (Usuario) request.getSession().getAttribute("user");
            
            if(usuario != null && usuario.getTipo().equalsIgnoreCase("admin"))
                request.getRequestDispatcher("/root/dashboard.jsp").forward(request, response);
            else
                request.getRequestDispatcher("Produtos.jsp").forward(request, response);
            
        } catch (ServletException ex)
        {
            Logger.getLogger(ConsultarProdutoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(ConsultarProdutoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}