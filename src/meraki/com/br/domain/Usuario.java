package meraki.com.br.domain;

import java.util.ArrayList;
import java.util.List;

import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.Pessoa;

/**
*
* @author Vitor Sakassegawa
*/
public class Usuario extends Pessoa
{
    private String email;
    private String senha;
    private int status;
    private String telefone;
    private String celular;
    private EnderecoEntrega enderecoEntrega;
    private List<EnderecoEntrega> enderecos = new ArrayList<>();
	private List<Genero> listaGeneros;
	private List<Genero> listaGenerosF;
	private List<Genero> listaGenerosM;

    public List<EnderecoEntrega> getEnderecos()
    {
        enderecos.indexOf(id);
        return enderecos;
    }
    
    public void addEnderecos(EnderecoEntrega enderecos)
    {
        this.enderecos.add(enderecos);
    }
    
    public EnderecoEntrega getEnderecoEntrega()
    {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega)
    {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getCelular()
    {
        return celular;
    }

    public void setCelular(String celular)
    {
        this.celular = celular;
    }
    
	public List<Genero> getListaGeneros() {
		return listaGeneros;
	}

	public void setListaGeneros(List<Genero> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}

	public List<Genero> getListaGenerosF() {
		return listaGenerosF;
	}

	public void setListaGenerosF(List<Genero> listaGenerosF) {
		this.listaGenerosF = listaGenerosF;
	}

	public List<Genero> getListaGenerosM() {
		return listaGenerosM;
	}

	public void setListaGenerosM(List<Genero> listaGenerosM) {
		this.listaGenerosM = listaGenerosM;
	}
}