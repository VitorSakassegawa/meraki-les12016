package meraki.com.br.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import meraki.com.br.domain.Frete;
import meraki.com.br.teste.Servicos;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
*
* @author Vitor Sakassegawa
*/
public class CalculaFrete
{
    private Servicos servico;
    
    public Servicos getServico()
    {
        return servico;
    }
    
    public CalculaFrete()
    {
        
    }
    /**
     * Calcula o valor de Frete de um determinado produto
     * @param cep   -   Cep do endereco de destino
     * @param servico - Sedex ou Pac
     */
    public CalculaFrete(String cep, String servico)
    {
        
    }
    /**
     * Retorna os valores calculados de um Frete
     * @param CEP - Cep de Destino 
     * @param tipoServico - Tipo de Servico Sedex ou Pac 
     * @return um objeto Frete com todos os dados do calculo ou null se houver algum erro
     * @throws MalformedURLException
     * @throws IOException 
     */
    public Frete getInfoFrete(String CEP, String tipoServico) throws MalformedURLException, IOException
    {
        if (tipoServico.equalsIgnoreCase("Sedex"))
        {
            tipoServico = "40010";
        } else
        {
            tipoServico = "41106";
        }
        
        CEP = CEP.replace("-", "").replace(".", "");
        
        StringBuilder urlPath = new StringBuilder();

        urlPath.append("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?");
        urlPath.append("nCdEmpresa=&");
        urlPath.append("sDsSenha=&");
        urlPath.append("sCepOrigem=07113970&");
        urlPath.append("sCepDestino=").append(CEP);
        urlPath.append("&nVlPeso=0,300&");
        urlPath.append("nCdFormato=1&");
        urlPath.append("nVlComprimento=16&");
        urlPath.append("nVlAltura=28,5&");
        urlPath.append("nVlLargura=17,5&");
        urlPath.append("sCdMaoPropria=n&");
        urlPath.append("nVlValorDeclarado=0&");
        urlPath.append("sCdAvisoRecebimento=n&");
        urlPath.append("nCdServico=").append(tipoServico).append("&");
        urlPath.append("nVlDiametro=0&");
        urlPath.append("StrRetorno=xml&");
        urlPath.append("nIndicaCalculo=3");

        URL url = new URL(urlPath.toString());
        
        // Enviando requisição para o web service
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();    
        
        // Pegando resultado da requisicao
        InputStream input = httpConnection.getInputStream();    

        XStream stream = new XStream(new DomDriver());
        stream.alias("Servicos", Servicos.class);
        servico = (Servicos) stream.fromXML(input);

        if (!servico.cServico.MsgErro.equals(""))
        {
            return null;
        }

        Frete frete = new Frete();
        frete.setCep(CEP);
        frete.setValor(new Float(servico.cServico.Valor.replace(",", ".")));
        frete.setEntrega(servico.cServico.PrazoEntrega+" á 5 dia(s)");
        frete.setTipoServico(servico.cServico.Codigo);
        
        System.out.println("Prazo: "+servico.cServico.PrazoEntrega+" dia(s)");
        
        return frete;
    }
}