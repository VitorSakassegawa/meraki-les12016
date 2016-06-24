package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IStrategy;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidaCamposProduto implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Produto produto = (Produto) entidade;
        
        String titulo = produto.getTitulo();
        String descricao = produto.getDescricao();
        String numeracao = produto.getNumeracao();
        String genero = produto.getGenero();
        float valor = produto.getValor();
        String anoLancamento = produto.getAno();
        String origem = produto.getOrigem();
        String material = produto.getMaterial();
        String categoria = produto.getCategoria();
        int quantidade = 0;
        
        StringBuilder msg = new StringBuilder();
        
        if(titulo == null || titulo.equals(""))
            msg.append("Titulo, ");
        
        if(descricao == null || descricao.equals(""))
            msg.append("Descricao, ");
            
        if(numeracao == null || numeracao.equals(""))
            msg.append("Numeracao, ");
        
        if(genero == null || genero.equals(""))
            msg.append("Genero, ");
        
        if(valor == 0)
            msg.append("Valor, ");
        
        if(anoLancamento == null || anoLancamento.equals(""))
            msg.append("Ano, ");
        
        if(origem == null || origem.equals(""))
            msg.append("Origem, ");
        
        if(material == null || material.equals(""))
            msg.append("material, ");
        
        if(categoria == null || categoria.equals(""))
            msg.append("Categoria, ");
        
        if(msg.length() > 0)
        {
            msg.delete(msg.length()-2,msg.length());
            msg.insert(0, "São campos obrigatórios: ");
            return msg.toString();
        }
        else
        {
            return null;
        }
    }
}