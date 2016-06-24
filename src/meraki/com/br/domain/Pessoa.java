package meraki.com.br.domain;

import java.util.Calendar;

import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class Pessoa extends EntidadeDominio
{

    private String nome;
    private String rg;
    private String cpf;
    private String sexo;
    private Calendar dataNascimento;
    private Endereco endereco;
    private String tipo;

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Calendar getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }
}