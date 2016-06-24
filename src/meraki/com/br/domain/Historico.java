package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class Historico extends EntidadeDominio
{
    private int idProduto;
    private int idCliente;
    private int quantidade;
    private int quantidadeComprada;
    private int quantidadeEstoque;

    public int getQuantidadeComprada()
    {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada)
    {
        this.quantidadeComprada = quantidadeComprada;
    }

    public int getQuantidadeEstoque()
    {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque)
    {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getIdProduto()
    {
        return idProduto;
    }

    public void setIdProduto(int idProduto)
    {
        this.idProduto = idProduto;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }
}