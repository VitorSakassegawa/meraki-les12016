package meraki.com.br.util;

import com.google.gson.Gson;

import meraki.com.br.util.EnderecoJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
*
* @author Vitor Sakassegawa
*/
public class BuscaCEP
{
    public EnderecoJson getCEP(String cep) throws IOException
    {
        URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep="+cep+"&formato=json");
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        InputStream input = connection.getInputStream();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String json= "{";
        while(reader.read() > 0)
        {
            json += reader.readLine();
        }
        
        Gson gson = new Gson();
        
        EnderecoJson endJson = (EnderecoJson) gson.fromJson(json, EnderecoJson.class);
        
        return endJson;
    }
}