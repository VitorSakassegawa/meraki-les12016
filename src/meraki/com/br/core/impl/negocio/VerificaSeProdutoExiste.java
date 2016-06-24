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
public class VerificaSeProdutoExiste implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        IDAO dao = new ProdutoDAO();
        
        Produto produto = new Produto();
        produto.setTitulo(((Produto) entidade).getTitulo());
        
        try
        {
            List<EntidadeDominio> entidades = dao.consultar(produto);
            
            if(entidades.isEmpty())
                return null;
            
            Produto p = (Produto) entidades.get(0);
            
            if(produto.getTitulo().equalsIgnoreCase(p.getTitulo()))
                return "O Produto cadastrado já existe!";
            else
                return null;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return "Desculpe, algum erro ocorreu";
        }
    }   
}