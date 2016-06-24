package meraki.com.br.teste;

import java.util.ArrayList;
import java.util.List;

import meraki.com.br.domain.Endereco;

/**
*
* @author Vitor Sakassegawa
*/
public class Teste
{
    private String nome;
    private List<Endereco> enderecos;
    
    public void addEndereco(Endereco end)
    {        
        enderecos = new ArrayList<>();
        enderecos.add(end);
    }
}