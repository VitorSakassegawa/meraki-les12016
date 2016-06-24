package meraki.com.br.controle.web.vh;

import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
public interface IViewHelper {
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response);
    
    public void setView(Resultado resultado,HttpServletRequest request, HttpServletResponse response);   
}