package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.ItemPedido;

/**
*
* @author Vitor Sakassegawa
*/
public class PedidoProduto extends EntidadeDominio
{
    private int idPedido;
    private int idProduto;
    private int quantidade;
    private ItemPedido item;

    public ItemPedido getItem()
    {
        return item;
    }

    public void setItem(ItemPedido item)
    {
        this.item = item;
    }
    
    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public int getIdPedido()
    {
        return idPedido;
    }

    public void setIdPedido(int idPedido)
    {
        this.idPedido = idPedido;
    }

    public int getIdProduto()
    {
        return idProduto;
    }

    public void setIdProduto(int idProduto)
    {
        this.idProduto = idProduto;
    }
}