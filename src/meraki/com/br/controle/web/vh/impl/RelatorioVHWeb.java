package meraki.com.br.controle.web.vh.impl;

import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.RelatorioVHWeb;
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

import meraki.com.br.core.impl.dao.ClienteDAO;
import meraki.com.br.core.impl.dao.RelatorioClientesDAO;
import meraki.com.br.domain.Genero;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.RelatorioClientes;


/**
*
* @author Vitor Sakassegawa
*/
public class RelatorioVHWeb implements IViewHelper
{
	
//    private RelatorioClientes rc = new RelatorioClientes();


    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        {
            if(resultado.getListMensagens().isEmpty())
            {
                request.setAttribute("relatorios",resultado.getEntidades());
            }
            else
            {
                request.setAttribute("msgErro", resultado.getListMensagens());
            }
            try
            {
            	request.getRequestDispatcher("RelatorioClientes.jsp").forward(request, response);
            } 
            catch (ServletException | IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}