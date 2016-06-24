package meraki.com.br.teste;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.ProdutoDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

import java.sql.SQLException;
import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public class Main
{
    public static void main(String[] args) throws SQLException
    {
        IDAO dao = new ProdutoDAO();
        
        Produto produto = new Produto();
        
        produto.setTitulo("");
        
        List<EntidadeDominio> jogos = dao.consultar(produto);
        
        for (EntidadeDominio ed : jogos)
        {
            System.out.println(((Produto)ed).getTitulo());
        }
        
        System.out.println(produto.getTitulo());
    }
}