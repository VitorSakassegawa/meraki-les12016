package meraki.com.br.core.impl.negocio.filtros;

import meraki.com.br.controle.web.Servlet;
import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.domain.Pedido;
import meraki.com.br.util.Conexao;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @author Vitor Sakassegawa
*/
@WebFilter(filterName = "CarrinhoFilter", urlPatterns =
{
    "/Carrinho"
})
public class CarrinhoFilter implements Filter
{
    private Connection conn;    
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public CarrinhoFilter()
    {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)throws IOException, ServletException, ClassNotFoundException, SQLException
    {
        if(conn.isClosed() || conn == null)
        {
        	// Get conexão com o banco de dados
            conn = Conexao.getConnetion(); 
            ((HttpServletRequest)request).getSession().setAttribute("conn",conn);  
        }  
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)throws IOException, ServletException
    {
        
    }

    private Pedido pedido = new Pedido();
    int id = 0;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        String url = request.getRequestURI();
        
        IViewHelper vh = Servlet.create(url);
        
        // Request
        vh.getEntidade(request, response);		
        
        // Set view do cliente
        vh.setView(null, request, response);	
    }

    public FilterConfig getFilterConfig()
    {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void destroy()
    {

    }

    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
        if (filterConfig != null)
        {
            if (debug)
            {
                log("CarrinhoFilter:Initializing filter");
            }
        }
    }

    @Override
    public String toString()
    {
        if (filterConfig == null)
        {
            return ("CarrinhoFilter()");
        }
        StringBuffer sb = new StringBuffer("CarrinhoFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response)
    {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals(""))
        {
            try
            {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex)
            {
            }
        } else
        {
            try
            {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex)
            {
            }
        }
    }

    public static String getStackTrace(Throwable t)
    {
        String stackTrace = null;
        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex)
        {
        }
        return stackTrace;
    }

    public void log(String msg)
    {
        filterConfig.getServletContext().log(msg);
    }
}