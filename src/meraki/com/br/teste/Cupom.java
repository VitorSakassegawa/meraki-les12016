package meraki.com.br.teste;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import meraki.com.br.domain.Endereco;

/**
*
* @author Vitor Sakassegawa
*/
public class Cupom {

	public static void main(String[] args) {
    {
    	Random r=new Random();

    	char[] possibleChars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789!".toCharArray();
    	int length=10;

    	char[] novoCupom=new char[length];

    	for (int i=0; i<length;i++)
    		novoCupom[i]=possibleChars[r.nextInt(possibleChars.length)];

    	System.out.println(new String(novoCupom));
        }
    }
}