package meraki.com.br.domain;

import meraki.com.br.domain.Pessoa;

/**
*
* @author Vitor Sakassegawa
*/
public class Fornecedor extends Pessoa
{
    private String Cnpj;
    private String telefone;
    private String email;

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getCnpj()
    {
        return Cnpj;
    }

    public void setCnpj(String Cnpj)
    {
        this.Cnpj = Cnpj;
    }
}