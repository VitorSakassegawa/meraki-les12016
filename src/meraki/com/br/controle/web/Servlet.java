package meraki.com.br.controle.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meraki.com.br.controle.web.command.ICommand;
import meraki.com.br.controle.web.command.impl.CommandAtualizar;
import meraki.com.br.controle.web.command.impl.CommandConsultar;
import meraki.com.br.controle.web.command.impl.CommandConsultarGrafico;
import meraki.com.br.controle.web.command.impl.CommandConsultarTroca;
import meraki.com.br.controle.web.command.impl.CommandExcluir;
import meraki.com.br.controle.web.command.impl.CommandSalvar;
import meraki.com.br.controle.web.command.impl.CommandTrocar;
import meraki.com.br.controle.web.command.impl.CommandTrocarADM;
import meraki.com.br.controle.web.vh.IViewHelper;
import meraki.com.br.controle.web.vh.impl.AtualizarClienteVHWeb;
import meraki.com.br.controle.web.vh.impl.AtualizarProdutoVHWeb;
import meraki.com.br.controle.web.vh.impl.AtualizarQuantidadeVHWeb;
import meraki.com.br.controle.web.vh.impl.CalcularFreteVHWeb;
import meraki.com.br.controle.web.vh.impl.ConsultaViaBoletoVHWeb;
import meraki.com.br.controle.web.vh.impl.ConsultarClienteVHWeb;
import meraki.com.br.controle.web.vh.impl.ConsultarPedidoVHweb;
import meraki.com.br.controle.web.vh.impl.ConsultarProdutoVHWeb;
import meraki.com.br.controle.web.vh.impl.ConsultarTrocaVHWeb;
import meraki.com.br.controle.web.vh.impl.DeletarClienteVHWeb;
import meraki.com.br.controle.web.vh.impl.DetalhePedidoVHWeb;
import meraki.com.br.controle.web.vh.impl.ExcluirClienteVHWeb;
import meraki.com.br.controle.web.vh.impl.ExcluirProdutoVHWeb;
import meraki.com.br.controle.web.vh.impl.InformarProdutoVHWeb;
import meraki.com.br.controle.web.vh.impl.LimparCarrinhoVHWeb;
import meraki.com.br.controle.web.vh.impl.ListaProdutoVHWeb;
import meraki.com.br.controle.web.vh.impl.PagamentoBoletoVHWeb;
import meraki.com.br.controle.web.vh.impl.RelatorioVHWeb;
import meraki.com.br.controle.web.vh.impl.RemoverItemCarrinhoVHWeb;
import meraki.com.br.controle.web.vh.impl.SalvarClienteVHWeb;
import meraki.com.br.controle.web.vh.impl.SalvarEnderecoVHweb;
import meraki.com.br.controle.web.vh.impl.SalvarItemCarrinhoVHWeb;
import meraki.com.br.controle.web.vh.impl.SalvarPagamentoVHWeb;
import meraki.com.br.controle.web.vh.impl.SalvarPedidoVHWeb;
import meraki.com.br.controle.web.vh.impl.SalvarProdutoVHWeb;
import meraki.com.br.controle.web.vh.impl.SalvarTransacaoBoletoVHWeb;
import meraki.com.br.controle.web.vh.impl.SalvarTransacaoCartaoVHWeb;
import meraki.com.br.controle.web.vh.impl.SelecionaEnderecoVHWeb;
import meraki.com.br.controle.web.vh.impl.TrocaADMVHWeb;
import meraki.com.br.controle.web.vh.impl.TrocaVHWeb;
import meraki.com.br.controle.web.vh.impl.VerificaQuantidadeVHWeb;
import meraki.com.br.core.application.Resultado;
import meraki.com.br.controle.web.command.impl.CommandConsultarTrocaCupom;
import meraki.com.br.controle.web.command.impl.CommandTrocar2;
import meraki.com.br.controle.web.vh.impl.SalvarTrocaVHWeb;
import meraki.com.br.controle.web.vh.impl.ConsultarTrocaVHWeb2;
import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Map<String, ICommand> commands;
    private static Map<String, IViewHelper> vhs;
    
    /**
     * Default constructor. 
     */
    public Servlet() {
        
        /* Utilizando o command para chamar a fachada e indexando cada command 
         * pela operação garantimos que esta servlet atenderá qualquer operação */
        commands = new HashMap<String, ICommand>();
      
        commands.put("Salvar", new CommandSalvar());
        commands.put("Atualizar", new CommandAtualizar());
        commands.put("Consultar",new CommandConsultar());
        commands.put("Excluir", new CommandExcluir());
        
        commands.put("Trocar", new CommandTrocar());
        commands.put("Trocar2", new CommandTrocar2());
        commands.put("TrocarADM", new CommandTrocarADM());
        commands.put("ConsultarTroca", new CommandConsultarTroca());
        commands.put("ConsultarTrocaCupom", new CommandConsultarTrocaCupom());
        
        commands.put("ConsultarGrafico", new CommandConsultarGrafico());

        /* Utilizando o viewhelper para tratar especificações de qualquer tela e indexando 
         * cada viewhelper pela url em que esta servlet é chamada no form
         * garantimos que esta servlet atenderá qualquer entidade */
        vhs = new HashMap<String, IViewHelper>();
        
        /* A chave do mapa é o mapeamento da servlet para cada form que 
         * está configurado no web.xml e sendo utilizada no action do html */
        vhs.put("/Meraki/SalvarCliente", new SalvarClienteVHWeb());
        vhs.put("/Meraki/ConsultarCliente", new ConsultarClienteVHWeb());
        vhs.put("/Meraki/ConsultarTodos", new ConsultarClienteVHWeb());
        vhs.put("/Meraki/AtualizarCliente", new AtualizarClienteVHWeb());
        vhs.put("/Meraki/ExcluirCliente", new ExcluirClienteVHWeb());
        vhs.put("/Meraki/root/SalvarProduto", new SalvarProdutoVHWeb());
        vhs.put("/Meraki/InfoProduto",new InformarProdutoVHWeb());
        vhs.put("/Meraki/FinalizarCompra", new SalvarPedidoVHWeb());
        vhs.put("/Meraki/ConsultarPedido", new ConsultarPedidoVHweb());
        vhs.put("/Meraki/DetalhePedido", new DetalhePedidoVHWeb());
        vhs.put("/Meraki/SalvarEnderecoEntrega", new SalvarEnderecoVHweb());
        vhs.put("/Meraki/FinalizarPagamento", new SalvarPagamentoVHWeb());
        vhs.put("/Meraki/PagamentoBoleto", new SalvarTransacaoBoletoVHWeb());
        vhs.put("/Meraki/PagamentoCartao", new SalvarTransacaoCartaoVHWeb());
        vhs.put("/Meraki/dashboard.jsp", new ListaProdutoVHWeb());
        vhs.put("/Meraki/ConsultaProdutos", new ConsultarProdutoVHWeb());
        vhs.put("/Meraki/root/ConsultaProdutos", new ConsultarProdutoVHWeb());
        vhs.put("/Meraki/root/ExcluirProduto", new ExcluirProdutoVHWeb());
        vhs.put("/Meraki/root/AtualizarProduto", new AtualizarProdutoVHWeb());
        vhs.put("/Meraki/root/AtualizaQuantidade", new AtualizarQuantidadeVHWeb());
        vhs.put("/Meraki/Produtos.jsp", new ConsultarProdutoVHWeb());
        vhs.put("/Meraki/DesativarConta", new DeletarClienteVHWeb());
        vhs.put("/Meraki/Carrinho", new SalvarItemCarrinhoVHWeb());
        vhs.put("/Meraki/RemoveItem", new RemoverItemCarrinhoVHWeb());
        vhs.put("/Meraki/LimparCarrinho", new LimparCarrinhoVHWeb());
        vhs.put("/Meraki/CalcularFrete", new CalcularFreteVHWeb());
        vhs.put("/Meraki/AtualizarQuantidade", new VerificaQuantidadeVHWeb());
        vhs.put("/Meraki/SelecionarEndereco", new SelecionaEnderecoVHWeb());
        vhs.put("/Meraki/SegundaViaBoleto", new ConsultaViaBoletoVHWeb());
        vhs.put("/Meraki/PagamentoDiretoBoleto", new PagamentoBoletoVHWeb());
        
        vhs.put("/Meraki/TrocaProduto", new TrocaVHWeb());
        vhs.put("/Meraki/TrocaProduto2", new TrocaADMVHWeb());
        vhs.put("/Meraki/TrocaProduto3", new SalvarTrocaVHWeb());
        vhs.put("/Meraki/ConsultarTroca", new ConsultarTrocaVHWeb());
        vhs.put("/Meraki/ConsultarTroca2", new ConsultarTrocaVHWeb2());
        vhs.put("/Meraki/root/ConsultarTroca", new ConsultarTrocaVHWeb());
        
        vhs.put("/Meraki/ConsultarGrafico", new RelatorioVHWeb());

    }
 
    public static IViewHelper create(String url)
    {
        return vhs.get(url);
    }
    
    /** 
     * TODO Descrição do Método
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doProcessRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doProcessRequest(request, response);
    }


    protected void doProcessRequest(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
    	
        // Obtém a uri que invocou esta servlet (O que foi definido no metédo do form html)
        String uri = request.getRequestURI();
        
        // Obtém a operação executada
        String operacao = request.getParameter("operacao") == null ? (String) request.getAttribute("operacao") : request.getParameter("operacao");
        
        // Obtém um viewhelper indexado pela uri que invocou esta servlet
        IViewHelper vh = vhs.get(uri);
        
        // A viewhelper retorna a entidade especifica para a tela que chamou esta servlet
        EntidadeDominio entidade =  vh.getEntidade(request, response);

        // Obtém o command para executar a respectiva operação
        ICommand command = commands.get(operacao);
                    
        /* Executa o command que chamará a fachada para executar a operação requisitada
         * o retorno é uma instância da classe resultado que pode conter mensagens derro 
         * ou entidades de retorno
         */
        Resultado resultado = command.execute(entidade);
        
        /*
         * Executa o método setView do view helper específico para definir como deverá ser apresentado 
         * o resultado para o usuário
         */
        vh.setView(resultado, request, response); 
    }
}