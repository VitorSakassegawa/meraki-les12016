package meraki.com.br.core.application;

import meraki.com.br.core.application.EntidadeAplicacao;
import meraki.com.br.domain.EntidadeDominio;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public class Resultado extends EntidadeAplicacao {
    private String msgSimples;
    private List<EntidadeDominio> entidades = new ArrayList<>();
    private List<String> listMensagens = new ArrayList<>();

    public List<String> getListMensagens()
    {
        return listMensagens;
    }

    public void addMensagem(String msg)
    {
        this.listMensagens.add(msg);
    }

    public String getMsgSimples()
    {
        return msgSimples;
    }

    public void setMsgSimples(String msgSimples)
    {
        this.msgSimples = msgSimples;
    }

    public List<EntidadeDominio> getEntidades()
    {
        return entidades;
    }

    public void setEntidades(List<EntidadeDominio> entidades)
    {
        this.entidades = entidades;
    }    
}