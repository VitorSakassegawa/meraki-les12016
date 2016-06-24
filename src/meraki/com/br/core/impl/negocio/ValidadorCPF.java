package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IStrategy;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

import java.util.InputMismatchException;

/**
*
* @author Vitor Sakassegawa
*/
// Help: http://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
public class ValidadorCPF implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Usuario cli = (Usuario) entidade;
        
        // O CPF contem todos os digitos?!
        if(cli.getCpf().length() < 11)
        	// Envia mensagem para o usuário, pois CPF esta incorreto
            return "CPF contem menos do que 11 digitos";    
        else if(isCPF(cli.getCpf()))
            return null;
        else
            return "O CPF digitado é inválido";
    }
    
    private static boolean isCPF(String CPF)
    {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais 
        /*if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || 
         * 	  CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || 
         *    CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || 
         *    CPF.equals("99999999999") || (CPF.length() != 11)) {
         return (false);
         } */
    	
        char dig10, dig11;
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int) 
        int sm, i, r, num, peso; 
        try
        // Cálculo do 1o. Digito Verificador
        { 
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++)
            {   // Converte o i-esimo caractere do CPF em um numero: 
                // Por exemplo, transforma o caractere '0' no inteiro 0 // (48 eh a posicao de '0' na tabela ASCII) 
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            
            r = 11 - (sm % 11);
            
            if ((r == 10) || (r == 11))
                dig10 = '0';
             else
            	// Converte no respectivo caractere numerico -  Calculo do 2o. Digito Verificador
                dig10 = (char) (r + 48); 
                
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++)
                {
                    num = (int) (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }
                
                r = 11 - (sm % 11);
                
                if ((r == 10) || (r == 11))
                    dig11 = '0';
                else
                	// Verifica se os digitos calculados conferem com os digitos informados. 
                    dig11 = (char) (r + 48); 
                
                    if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                        return (true);
                    else
                        return (false);
                    
        } catch (InputMismatchException erro)
        {
            return (false);
        }
    }

    public String imprimeCPF(String CPF)
    {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
}
