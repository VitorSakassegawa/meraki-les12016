package meraki.com.br.domain;

import java.util.ArrayList;
import java.util.List;

import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Produto;

/**
*
* @author Vitor Sakassegawa
*/
public class CarrinhoCompra extends EntidadeDominio
{
    private List<Produto> ItemPedido = new ArrayList<>();
    
    public int quantidadeItens()
    {
    	// Retornará a qtde de itens
        return ItemPedido.size();   
    }
    
    public float valorTotal()
    {
        return 10f;
    }
}