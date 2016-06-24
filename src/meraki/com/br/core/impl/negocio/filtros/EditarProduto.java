package meraki.com.br.core.impl.negocio.filtros;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meraki.com.br.domain.Produto;

/**
*
* @author Vitor Sakassegawa
*/
public class EditarProduto implements Filter
{
    
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    
    public EditarProduto()
    {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("EditarProduto:DoBeforeProcessing");
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("EditarProduto:DoAfterProcessing");
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain)throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Produto produto = new Produto();
        
        int id = Integer.parseInt(request.getParameter("txtId"));
        String titulo = request.getParameter("txtTitulo");
        String genero = request.getParameter("txtGenero");
        String numeracao = request.getParameter("txtNumeracao");
        String ano = request.getParameter("txtAno");
        String descricao = request.getParameter("txtDescricao");
        String categoria = request.getParameter("txtCategoria");
        String origem = request.getParameter("txtOrigem");
        String material = request.getParameter("txtMaterial");
        float valor = Float.parseFloat(request.getParameter("txtValor"));
        
        produto.setId(id);
        produto.setTitulo(titulo);
        produto.setGenero(genero);
        produto.setNumeracao(numeracao);
        produto.setAno(ano);
        produto.setValor(valor);
        produto.setDescricao(descricao);
        produto.setCategoria(categoria);
        produto.setOrigem(origem);
        produto.setMaterial(material);
        
        request.setAttribute("produto", produto);
        
        request.getRequestDispatcher("AtualizarProduto.jsp").forward(req, resp);
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
                log("EditarProduto:Initializing filter");
            }
        }
    }

    @Override
    public String toString()
    {
        if (filterConfig == null)
        {
            return ("EditarProduto()");
        }
        StringBuffer sb = new StringBuffer("EditarProduto(");
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