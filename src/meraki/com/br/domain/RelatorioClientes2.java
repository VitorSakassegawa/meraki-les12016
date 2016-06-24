package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.RelatorioClientes2;

/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioClientes2 extends EntidadeDominio
{
    private String nome;
    private float gasto;
    private int compras;
    private String idade;
    private String dt;
	private String estado;

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public float getGasto()
    {
        return gasto;
    }

    public void setGasto(float gasto)
    {
        this.gasto = gasto;
    }

    public int getCompras()
    {
        return compras;
    }

    public void setCompras(int compras)
    {
        this.compras = compras;
    }

    public String getIdade()
    {
        return idade;
    }

    public void setIdade(String idade)
    {
        this.idade = idade;
    }

    public String getDt()
    {
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}