package meraki.com.br.domain;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.RelatorioClientesIdade;
import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.RelatorioClientesDAOIdade;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;

/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioClientesIdade extends EntidadeDominio implements DatasetProducer
{
    private String nome;
    private float gasto;
    private int compras;
    private String idade;
    private String dt;
	private String estado;
    
    @Override
    public Object produceDataset(Map map) throws DatasetProduceException
    {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        List<EntidadeDominio> entidades = null;
        IDAO dao = new RelatorioClientesDAOIdade();
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
            RelatorioClientesIdade rc = (RelatorioClientesIdade) ed;
            Integer mth = new Integer(rc.dt);
            ds.setValue(rc.getGasto(), rc.getIdade(), month[mth-1]);
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

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public float getGasto()
    {
        return gasto;
    }

    public void setGasto(float gasto)
    {
        this.gasto = gasto;
    }

    public int getCompras()
    {
        return compras;
    }

    public void setCompras(int compras)
    {
        this.compras = compras;
    }

    public String getIdade()
    {
        return idade;
    }

    public void setIdade(String idade)
    {
        this.idade = idade;
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