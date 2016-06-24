package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.RelatorioVHWeb2;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meraki.com.br.core.impl.dao.RelatorioClientesDAO;
import meraki.com.br.domain.Genero;


/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioVHWeb2 implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
    	
    	Usuario cliente = (Usuario) resultado.getEntidades().get(0);
		RelatorioClientesDAO cliDAO = new RelatorioClientesDAO();

		Usuario cli = null;
		List<Genero> listaGenero = null;
		
		try {
			cli = (Usuario) cliDAO.listaGenero(cliente).get(0);
			listaGenero = ((Usuario) cli).getListaGeneros();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String saida = null;
		saida="['Genero', 'QuantidadeTotal'],\n";

		for (int i = 0; i < listaGenero.size(); i++) {
			Genero genero = (Genero) listaGenero.get(i);
			saida += "['" + genero.getGenero() + "', " + genero.getQtde() 
					+ "],\n";

		}
		
		saida += "]);\n";
		String novaSaida = saida.replace("],\n]);\n", "]\n");
		request.getSession().setAttribute("saidaGrafico2", novaSaida);
		request.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
	        
        try
        {
        	request.getRequestDispatcher("Chart.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(RelatorioVHWeb2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(RelatorioVHWeb2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}