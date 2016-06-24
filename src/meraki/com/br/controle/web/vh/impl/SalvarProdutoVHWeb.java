package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.SalvarProdutoVHWeb;
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
public class SalvarProdutoVHWeb implements IViewHelper
{
    private Produto produto;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        produto = new Produto();
        
        produto.setTitulo(request.getParameter("txtTitulo"));
        produto.setDescricao(request.getParameter("txtDescricao"));
        produto.setGenero(request.getParameter("txtGenero"));
        produto.setNumeracao(request.getParameter("txtNumeracao"));
        
        String valor = request.getParameter("txtValor").replace(",", ".");
        valor = valor == null || valor.equals("") ? "0": valor;
        produto.setValor(Float.parseFloat(valor));
        produto.setAno(request.getParameter("txtAnoLancamento"));
        
        String orig = request.getParameter("ckbOrigem") == null ? "off":request.getParameter("ckbOrigem");
        produto.setOrigem(orig.equals("on") ? "S":"N"); 
        
        String mate = request.getParameter("ckbMaterial") == null ? "N":request.getParameter("ckbMaterial");
        produto.setMaterial(mate.equals("on")?"S":"N");
        
        produto.setCategoria(request.getParameter("txtCategoria"));
        
        String imagem = "images/"+request.getParameter("txtImagem");
        
        if( request.getParameter("txtImagem") == null || request.getParameter("txtImagem").equals(""))
            imagem = "images/sem-imagem.jpg";
        
        produto.setImage(imagem);
        
        if(request.getParameter("txtQuantidade") == null || request.getParameter("txtQuantidade").equals(""))
            produto.setQuantidade(0);
        else
            produto.setQuantidade(Integer.parseInt(request.getParameter("txtQuantidade")));
        
        return produto;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())
        {
           request.setAttribute("sucessSalvarProduto", "Produto Salvo com Sucesso!");
        }
        else
        {
            request.setAttribute("produto", produto);
            request.setAttribute("erroSalvarProduto", resultado.getListMensagens());
        }
        try
        {
            request.getRequestDispatcher("SalvarProdutos.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(SalvarProdutoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(SalvarProdutoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}