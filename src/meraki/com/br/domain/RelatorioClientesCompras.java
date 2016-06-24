package meraki.com.br.domain;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.RelatorioClientesCompras;
import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.RelatorioClientesComprasDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;

/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioClientesCompras extends EntidadeDominio implements DatasetProducer
{
    private String titulo;
    private int quantidade;
    private String data;
    
    @Override
    public Object produceDataset(Map map) throws DatasetProduceException
    {
        IDAO dao = new RelatorioClientesComprasDAO();
        String [] month = {"Jan","Fev","Mar","Abr","Mai","Jun","Jul"};
        List<EntidadeDominio> entidades = null;
        try
        {
            entidades = dao.consultar(null);
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        
        for (EntidadeDominio ed : entidades)
        {
            Integer data = new Integer(((RelatorioClientesCompras)ed).getData());
            ds.addValue(((RelatorioClientesCompras)ed).getQuantidade(), ((RelatorioClientesCompras)ed).getTitulo(), month[data-1]);
        }
        return ds;
    }

    @Override
    public boolean hasExpired(Map map, Date date)
    {
        return true;
    }

    @Override
    public String getProducerId()
    {
        return "Compras";
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}