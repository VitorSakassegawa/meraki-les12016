package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.IStrategy;
import meraki.com.br.core.impl.dao.ProdutoDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

import java.sql.SQLException;
import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidaQuantidadeEstoque implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        IDAO dao = new ProdutoDAO();
        
        try
        {
            List<EntidadeDominio> entidades = dao.consultar(entidade);
            
            Produto produto = (Produto) entidades.get(0);
            
            int qtdePedido = ((Produto)entidade).getQuantidade();
            int qtdeEstoque = produto.getQuantidade();
            
            String quantidade = ""+((Produto)entidade).getQuantidade();
            
            if(quantidade.contains(".") || quantidade.contains(","))
                return "Digite um valor inteiro!";
            
            String resultado = qtdePedido > qtdeEstoque ? "Desculpe, não temos a quantidade desejada em estoque": null;
            
            return resultado;
            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }   
}