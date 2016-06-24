package meraki.com.br.domain;

import java.util.Date;

import meraki.com.br.domain.Cartao;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Usuario;

/**
*
* @author Vitor Sakassegawa
*/
public class TransacaoCartao extends EntidadeDominio
{
    private Pedido pedido;
    private Usuario cliente;
    private Cartao cartao;
    private String status;
    private Date data;
    private String descricao;
    private float valor;
    private String numeroCartao;
    private String nomeCartao;

    public Pedido getPedido()
    {
        return pedido;
    }

    public Cartao getCartao()
    {
        return cartao;
    }

    public void setCartao(Cartao cartao)
    {
        this.cartao = cartao;
    }
    
    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }

    public Usuario getCliente()
    {
        return cliente;
    }

    public void setCliente(Usuario cliente)
    {
        this.cliente = cliente;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public float getValor()
    {
        return valor;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }

    public String getNumeroCartao()
    {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao)
    {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeCartao()
    {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao)
    {
        this.nomeCartao = nomeCartao;
    }
    
    public float TotalTransacao()
    {
        return pedido.ValorTotal();
    }
}