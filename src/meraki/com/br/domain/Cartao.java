package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class Cartao extends EntidadeDominio
{
    private String numeroCartao;
    private String numeroFinalCartao;
    private String nomeCartao;
    private String parcelas;

    public String getNumeroCartao()
    {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao)
    {
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroFinalCartao()
    {
        return numeroFinalCartao;
    }

    public void setNumeroFinalCartao(String numeroFinalCartao)
    {
        this.numeroFinalCartao = numeroFinalCartao;
    }

    public String getNomeCartao()
    {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao)
    {
        this.nomeCartao = nomeCartao;
    }

    public String getParcelas()
    {
        return parcelas;
    }

    public void setParcelas(String parcelas)
    {
        this.parcelas = parcelas;
    }   
}