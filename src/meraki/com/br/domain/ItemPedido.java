package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

/**
*
* @author Vitor Sakassegawa
*/
public class ItemPedido extends EntidadeDominio
{
    private Produto produto;
    private int quantidade = 0;
    private float desconto;

    public float getDesconto()
    {
        return desconto;
    }

    public void setDesconto(float desconto)
    {
        this.desconto = desconto;
    }

    public Produto getProduto()
    {
        return produto;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }
    
    // Valor total do item com o desconto aplicado
    public float valorTotalItem()
    {
        float total = (produto.getValor() - (produto.getValor() * desconto)) * quantidade;
        return total;
    }
}