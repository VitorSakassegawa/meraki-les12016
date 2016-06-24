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
public class Transacao extends EntidadeDominio
{
    private Pedido pedido;
    private Usuario cliente;
    private String status;
    private String descricao;
    private Date dataTransacao;
    private float valor;
    private Cartao cartao;

    public Cartao getCartao()
    {
        return cartao;
    }

    public void setCartao(Cartao cartao)
    {
        this.cartao = cartao;
    }
    
    public float getValor()
    {
        return valor;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }
    
    
    
    public Transacao(){ } //CONSTRUTOR DEFAULT
    
    public Transacao(Pedido pedido,Usuario cliente)
    {
        this.cliente = cliente;
        this.pedido = pedido;
        this.status = "pendente";
    }
    
    public Usuario getCliente()
    {
         return this.cliente;
    }       
    
    public void setCliente(Usuario cliente)
    {
        this.cliente = cliente;
    }
    
    public Pedido getPedido()
    {
        return pedido;
    }

    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getDataTransacao()
    {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao)
    {
        this.dataTransacao = dataTransacao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

	public String getCupom() {
		// TODO Auto-generated method stub
		return null;
	}
}