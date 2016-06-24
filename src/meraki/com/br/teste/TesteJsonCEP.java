package meraki.com.br.teste;

import com.google.gson.Gson;

import meraki.com.br.domain.Endereco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
*
* @author Vitor Sakassegawa
*/
public class TesteJsonCEP
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException
    {
        URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=07123170&formato=json");
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        InputStream input = connection.getInputStream();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String json= "{";
        while(reader.read() > 0)
        {
            json += reader.readLine();
        }
        
        System.out.println(json);
        
        Gson gson = new Gson();
        
        Endereco end = (Endereco) gson.fromJson(json, Endereco.class);
    }
}