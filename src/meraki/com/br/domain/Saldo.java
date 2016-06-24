package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class Saldo extends EntidadeDominio
{
    private int idCliente;
    private float saldo;

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    public float getSaldo()
    {
        return saldo;
    }

    public void setSaldo(float saldo)
    {
        this.saldo = saldo;
    }
}