package meraki.com.br.domain;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.RelatorioClientesEstado;
import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.RelatorioClientesDAOEstado;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;

/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioClientesEstado extends EntidadeDominio implements DatasetProducer
{
    private float gasto;
    private String dt;
	private String estado;
    
    @Override
    public Object produceDataset(Map map) throws DatasetProduceException
    {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        List<EntidadeDominio> entidades = null;
        IDAO dao = new RelatorioClientesDAOEstado();
        String [] month = {"Jan","Fev","Mar","Abr","Mai","Jun","Jul"};
        try
        {
            entidades = dao.consultar(null);
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        for (EntidadeDominio ed : entidades)
        {
            RelatorioClientesEstado rc = (RelatorioClientesEstado) ed;
            Integer mth = new Integer(rc.dt);
            ds.setValue(rc.getGasto(), rc.getEstado(), month[mth-1]);
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
        return "ClienteGrafico";
    }

    public float getGasto()
    {
        return gasto;
    }

    public void setGasto(float gasto)
    {
        this.gasto = gasto;
    }

    public String getDt()
    {
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}