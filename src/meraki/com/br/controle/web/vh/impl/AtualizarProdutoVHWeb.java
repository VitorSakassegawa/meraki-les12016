package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public class AtualizarProdutoVHWeb implements IViewHelper 
{
    private Produto produto;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        produto = new Produto();
        
        int id = Integer.parseInt(request.getParameter("txtId"));
        String titulo = request.getParameter("txtTitulo");
        String descricao = request.getParameter("txtDescricao").trim();
        String genero = request.getParameter("txtGenero");
        String numeracao = request.getParameter("txtNumeracao");
        float valor =  request.getParameter("txtValor").equals("") ? 0 : Float.parseFloat(request.getParameter("txtValor").replace(",", "."));
        String ano = request.getParameter("txtAno");
        String origem = request.getParameter("ckbOrigem") == null ? "S" : "N";
        String material = request.getParameter("ckbMaterial") == null ? "S" : "N";
        String categoria = request.getParameter("txtCategoria");
        
        produto.setTitulo(titulo);
        produto.setDescricao(descricao);
        produto.setGenero(genero);
        produto.setNumeracao(numeracao);
        produto.setValor(valor);
        produto.setAno(ano);
        produto.setOrigem(origem);
        produto.setMaterial(material);
        produto.setCategoria(categoria);
        produto.setId(id);
        
        return produto;
    }
   
    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher rq = null;
        // Lista de mensagens nao é vazia
        if(!resultado.getListMensagens().isEmpty()) 
        {
            request.setAttribute("produto", produto);
            request.setAttribute("erroAtualizarProduto", resultado.getListMensagens());
            rq = request.getRequestDispatcher("AtualizarProduto.jsp");
        }
        else
        {
           request.setAttribute("sucessAtualizarProduto", produto.getTitulo()+" atualizado com sucesso!");
           rq = request.getRequestDispatcher("dashboard.jsp");
        }
        
        try
        {
            rq.forward(request, response);
        } 
        catch (ServletException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
}