package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class Frete extends EntidadeDominio
{
    private float valor;
    private String entrega;
    private String tipoServico;
    private String cep;

    public Frete()
    {
        valor = 0;
    }
    
    public Frete(float valor)
    {
        this.valor = valor;
    }
    
    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }
    
    public float getValor()
    {
        return valor;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }

    public String getEntrega()
    {
        return entrega;
    }

    public void setEntrega(String entrega)
    {
        this.entrega = entrega;
    }

    public String getTipoServico()
    {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico)
    {
        this.tipoServico = tipoServico;
    }   
}