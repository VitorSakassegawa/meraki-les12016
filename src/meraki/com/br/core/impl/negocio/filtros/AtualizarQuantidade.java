package meraki.com.br.core.impl.negocio.filtros;

import meraki.com.br.core.IStrategy;
import meraki.com.br.core.impl.negocio.ValidaQuantidadeEstoque;
import meraki.com.br.domain.Produto;
import meraki.com.br.domain.Pedido;

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

/**
*
* @author Vitor Sakassegawa
*/
public class AtualizarQuantidade implements Filter
{

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public AtualizarQuantidade()
    {
    	
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("AtualizarQuantidade:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("AtualizarQuantidade:DoAfterProcessing");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {

        try
        {
        	// Get carrinho
            Pedido pedido = (Pedido) ((HttpServletRequest) request).getSession().getAttribute("carrinho"); 

            // Qtde inserida pelo cliente
            Integer quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));   

            if (quantidade == 0)
            {
                quantidade++;
            }

            // ID do objeto na lista
            Integer index = Integer.parseInt(request.getParameter("txtIndex")); 

            IStrategy validador = new ValidaQuantidadeEstoque();

            Produto produto = new Produto(); 
            produto.setTitulo(pedido.getItens().get(index).getProduto().getTitulo());
            // Set a qtde pedida pelo cliente
            produto.setQuantidade(quantidade); 

            // Validando se qtde está disponivel para venda
            String resultado = validador.processar(produto);   

            // Ocorreu algum erro?
            if (resultado != null)   
            {
                request.setAttribute("erroQtde", resultado);
            } 
            else
            {
            	// Set a qtde de um item
                pedido.getItens().get(index).setQuantidade(quantidade); 

                ((HttpServletRequest) request).getSession().setAttribute("carrinho", pedido);    
            }
            
            // Redirecionando os dados de volta
            request.getRequestDispatcher("VisualizarCarrinho.jsp").forward(request, response);   
            
        } catch (NumberFormatException ex)
        {
            ex.printStackTrace();
        } catch (NullPointerException ex1)
        {
            ex1.printStackTrace();
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
                log("AtualizarQuantidade:Initializing filter");
            }
        }
    }

    @Override
    public String toString()
    {
        if (filterConfig == null)
        {
            return ("AtualizarQuantidade()");
        }
        StringBuffer sb = new StringBuffer("AtualizarQuantidade(");
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