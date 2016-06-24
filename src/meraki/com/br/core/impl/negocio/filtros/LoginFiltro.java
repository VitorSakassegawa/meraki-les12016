package meraki.com.br.core.impl.negocio.filtros;

import meraki.com.br.core.IStrategy;
import meraki.com.br.core.impl.negocio.ValidaLogin;
import meraki.com.br.domain.Usuario;

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

/**
*
* @author Vitor Sakassegawa
*/
public class LoginFiltro implements Filter
{

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public LoginFiltro()
    {

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {
        if (debug)
        {
            log("LoginFiltro:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {
        if (debug)
        {
            log("LoginFiltro:DoAfterProcessing");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");

        Usuario usuario = new Usuario();

        usuario.setEmail(email);
        usuario.setSenha(senha);

        IStrategy valida = new ValidaLogin();

        String login = valida.processar(usuario);

        if (login == null)
        {
            ((HttpServletRequest) request).getSession().setAttribute("user", usuario);

            ((HttpServletRequest) request).getSession().setAttribute("endereco", usuario.getEndereco());

            String lastRequest = (String) ((HttpServletRequest) request).getSession().getAttribute("requestUrl");

            if (usuario.getTipo().equalsIgnoreCase("admin"))
            {
                ((HttpServletResponse) response).sendRedirect("/Meraki/root/dashboard.jsp");
            } else
            {
                if (lastRequest == null || lastRequest.equals(""))
                {
                    ((HttpServletResponse) response).sendRedirect("Produtos.jsp");
                } else
                {
                    ((HttpServletResponse) response).sendRedirect(lastRequest);
                }
            }
        } else
        {
            request.setAttribute("msgLoginErro", login);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
                log("LoginFiltro:Initializing filter");
            }
        }
    }

    @Override
    public String toString()
    {
        if (filterConfig == null)
        {
            return ("LoginFiltro()");
        }
        StringBuilder sb = new StringBuilder("LoginFiltro(");
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