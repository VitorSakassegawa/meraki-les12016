package meraki.com.br.core.impl.negocio.filtros;

import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.util.BuscaCEP;
import meraki.com.br.util.EnderecoJson;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
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
public class CarregaCepFilter implements Filter
{

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public CarregaCepFilter()
    {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("CarregaCepFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("CarregaCepFilter:DoAfterProcessing");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        BuscaCEP busca = new BuscaCEP();
        EnderecoJson endJson;
        
        Map<String,String> url = new HashMap<>();
        url.put("/Meraki/BuscaCep", "SalvarCliente.jsp");
        url.put("/Meraki/CarregaCep", "ConfirmaEndereco.jsp");
        
        try
        {
            endJson = busca.getCEP(req.getParameter("txtCep"));
            
            if(endJson.getResultado().equals("0"))
            {
                req.setAttribute("msgEndereco", "CEP Inválido");
            }
            else
            {
                EnderecoEntrega endereco = endJson.toEnderecoEntrega();
                endereco.setCep(req.getParameter("txtCep"));
                
                req.setAttribute("endereco", endereco);
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            req.setAttribute("msgEndereco", "Desculpe! Estamos com problemas \nVocê pode digitar Manualmente?");
        }
        finally
        {
            String path = url.get(req.getRequestURI());
            
            req.getRequestDispatcher(path).forward(request, response);
        }
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
                log("CarregaCepFilter:Initializing filter");
            }
        }
    }

    @Override
    public String toString()
    {
        if (filterConfig == null)
        {
            return ("CarregaCepFilter()");
        }
        StringBuffer sb = new StringBuffer("CarregaCepFilter(");
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