package meraki.com.br.teste;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.HistoricoDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Historico;

import java.sql.SQLException;
import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public class TesteHistorico
{
    public static void main(String[] args) throws SQLException
    {
        Historico h = new Historico();
        h.setIdCliente(69);
        h.setIdProduto(2);
        IDAO dao = new HistoricoDAO();
        
        List<EntidadeDominio> entidades = dao.consultar(h);
        
        for (EntidadeDominio enti : entidades)
        {
            System.out.println("Quantidade comprada: "+((Historico)enti).getQuantidadeComprada());
            System.out.println("Quantidade estoque: "+((Historico)enti).getQuantidadeEstoque());
        }
    }
}