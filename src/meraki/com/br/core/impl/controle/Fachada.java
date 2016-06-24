package meraki.com.br.core.impl.controle;

import java.util.Map;

import meraki.com.br.core.application.Resultado;
import meraki.com.br.core.IDAO;
import meraki.com.br.core.IFachada;
import meraki.com.br.core.IStrategy;
import meraki.com.br.core.impl.dao.ClienteDAO;
import meraki.com.br.core.impl.dao.EnderecoDAO;
import meraki.com.br.core.impl.dao.EnderecoEntregaDAO;
import meraki.com.br.core.impl.dao.FornecedorDAO;
import meraki.com.br.core.impl.dao.ProdutoDAO;
import meraki.com.br.core.impl.dao.RelatorioClientesDAO;
import meraki.com.br.core.impl.dao.PedidoDAO;
import meraki.com.br.core.impl.dao.TransacaoCartaoDAO;
import meraki.com.br.core.impl.dao.TransacaoDAO;
import meraki.com.br.core.impl.negocio.ComplementadorDtCadastro;
import meraki.com.br.core.impl.negocio.ValidaCamposEndereco;
import meraki.com.br.core.impl.negocio.ValidaCamposProduto;
import meraki.com.br.core.impl.negocio.ValidaDesconto;
import meraki.com.br.core.impl.negocio.ValidaEntrega;
import meraki.com.br.core.impl.negocio.ValidadorAtualizacaoCliente;
import meraki.com.br.core.impl.negocio.ValidadorCPF;
import meraki.com.br.core.impl.negocio.ValidadorCliente;
import meraki.com.br.core.impl.negocio.ValidadorDadosCliente;
import meraki.com.br.core.impl.negocio.ValidadorStatus;
import meraki.com.br.core.impl.negocio.ValidarSaldoCliente;
import meraki.com.br.core.impl.negocio.VerificaSeProdutoExiste;
import meraki.com.br.domain.Endereco;
import meraki.com.br.domain.EnderecoEntrega;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Fornecedor;
import meraki.com.br.domain.Produto;
import meraki.com.br.domain.RelatorioClientes;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Transacao;
import meraki.com.br.domain.TransacaoCartao;
import meraki.com.br.domain.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public class Fachada implements IFachada {
    
    /** 
     * Mapa de DAOS, será indexado pelo nome da entidade 
     * O valor é uma instância do DAO para uma dada entidade; 
     */    
    private Map<String, IDAO> daos;
    
    /**
     * Mapa para conter as regras de negócio de todas operações por entidade;
     * O valor é um mapa que de regras de negócio indexado pela operação
     */
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Resultado resultado;
    
    public Fachada() {

    	resultado = new Resultado();
        
        // Criando instancias de Daos e Strategys (Regras de Negocio)
        /* Intânciando o Map de DAOS */
        daos = new HashMap<>();

        /* Intânciando o Map de Regras de Negócio */
        rns = new HashMap<>(); 

        /* Adicionando cada dao no MAP indexando pelo nome da classe */
        daos.put(Fornecedor.class.getName(), new FornecedorDAO());
        daos.put(Usuario.class.getName(), new ClienteDAO());
        daos.put(Endereco.class.getName(), new EnderecoDAO());
        daos.put(Produto.class.getName(), new ProdutoDAO());
        daos.put(Pedido.class.getName(), new PedidoDAO());
        daos.put(Endereco.class.getName(), new EnderecoDAO());
        daos.put(EnderecoEntrega.class.getName(), new EnderecoEntregaDAO());
        daos.put(Pedido.class.getName(), new PedidoDAO());
        daos.put(Transacao.class.getName(), new TransacaoDAO());
        daos.put(TransacaoCartao.class.getName(), new TransacaoCartaoDAO());

        /* Criando instâncias de regras de negócio a serem utilizados */     
        ValidadorCPF validarCPF = new ValidadorCPF();
        ValidadorCliente validarCliente = new ValidadorCliente();
        ValidadorDadosCliente ValidaDadosCliente = new ValidadorDadosCliente();

        /* Criando uma lista para conter as regras de negócio de cliente quando a operação for salvar */
        List<IStrategy> rnsSalvarCliente = new ArrayList<>();
        /* Adicionando as regras a serem utilizadas na operação salvar do cliente */
        rnsSalvarCliente.add(ValidaDadosCliente);
        rnsSalvarCliente.add(validarCPF);
        rnsSalvarCliente.add(validarCliente);
        rnsSalvarCliente.add(new ComplementadorDtCadastro());

        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica por operação  do cliente */
        Map<String, List<IStrategy>> rnsClientes = new HashMap<>();
        
        /* Adiciona a lista de regras na operação salvar no mapa do cliente (lista criada na linha 92) */
        rnsClientes.put("Salvar", rnsSalvarCliente);
        
        /* Criando lista de regras de Clientes ao Atualizar */
        List<IStrategy> rnsAtualizarCliente = new ArrayList<>();
        rnsAtualizarCliente.add(new ValidadorAtualizacaoCliente());
        rnsAtualizarCliente.add(new ValidadorCPF());
        // Adicionando regras ao MAP
        rnsClientes.put("Atualizar", rnsAtualizarCliente);
        
        /* Criando instancia das regras do Endereco */
        IStrategy validaCampos = new ValidaCamposEndereco();
        
        /* Criando lista para conter as regras de validacao ao atualizar um endereco */
        List<IStrategy> rnsAtualizarEndereco = new ArrayList<>();
        rnsAtualizarEndereco.add(validaCampos);
        
        List<IStrategy> rnsSalvarEndereco = new ArrayList<>();
        rnsSalvarEndereco.add(validaCampos);
        
        /* Criando Map que pode conter todas as regras do Endereco */
        Map<String,List<IStrategy>> rnsEndereco = new HashMap<>();
        rnsEndereco.put("Atualizar", rnsAtualizarEndereco);
        rnsEndereco.put("Salvar", rnsSalvarEndereco);
        
        // Criando lista de regras para Salvar Transacao Carta de Credito
        List<IStrategy> rnsSalvarTransacaoCartao = new ArrayList<>();
        // rnsSalvarTransacaoCartao.add(new ValidarCartaoCredito());
        rnsSalvarTransacaoCartao.add(new ValidaDesconto());
        rnsSalvarTransacaoCartao.add(new ValidarSaldoCliente());
        
        // Criando mapa para conter as regras
        Map<String,List<IStrategy>> rnsTransacaoCartao = new HashMap<>();
        rnsTransacaoCartao.put("Salvar",rnsSalvarTransacaoCartao);
        
        // Criando lista - Regras para transacoes com boleto
        List<IStrategy> rnsSalvarTransacao = new ArrayList<>();
        rnsSalvarTransacao.add(new ValidaDesconto());    
        // Criando lista - Regras de atualizacao de uma transacao
        List<IStrategy> rnsAtualizaTransacao = new ArrayList<>();
        rnsAtualizaTransacao.add(new ValidarSaldoCliente());
        // Criando lista - Regras de atualizacao de um status
        List<IStrategy> rnsAtualizaStatus = new ArrayList<>();
        rnsAtualizaStatus.add(new ValidadorStatus());
        
        // Criando mapa para conter as regras
        Map<String,List<IStrategy>> rnsTransacao = new HashMap<>();
        rnsTransacao.put("Salvar", rnsSalvarTransacao);
        rnsTransacao.put("Atualizar", rnsAtualizaTransacao);
        rnsTransacao.put("Trocar", rnsAtualizaStatus);
        rnsTransacao.put("Trocar2", rnsAtualizaStatus);
        rnsTransacao.put("TrocarADM", rnsAtualizaStatus);

        // Criando lista de regras para salvar um produto
        List<IStrategy> rnsSalvarProduto = new ArrayList<>();
        rnsSalvarProduto.add(new ValidaCamposProduto());
        rnsSalvarProduto.add(new VerificaSeProdutoExiste());
        // Criando mapa para conter a lista de regras pra salvar!
        Map<String,List<IStrategy>> rnsProduto = new HashMap<>();
        
        // Criando a lista de regras para atualizar um produto
        List<IStrategy> rnsAtualizarProduto = new ArrayList<>();
        rnsAtualizarProduto.add(new ValidaCamposProduto());
        
        rnsProduto.put("Atualizar",rnsAtualizarProduto);
        rnsProduto.put("Salvar", rnsSalvarProduto);
        
        // Criando lista de regras para validar campos do endereco
        List<IStrategy> rnsSalvarEntrega = new ArrayList<>();
        rnsSalvarEntrega.add(new ValidaEntrega());
        
        Map<String,List<IStrategy>> rnsEnderecoEntrega = new HashMap<>();
        rnsEnderecoEntrega.put("Salvar", rnsSalvarEntrega);
        
        rns.put(EnderecoEntrega.class.getName(), rnsEnderecoEntrega);
        rns.put(Produto.class.getName(), rnsProduto);
        rns.put(Transacao.class.getName(), rnsTransacao);
        rns.put(Usuario.class.getName(), rnsClientes);
        rns.put(Endereco.class.getName(), rnsEndereco);
        rns.put(TransacaoCartao.class.getName(), rnsTransacaoCartao);
    }

    /**
     * Fachada para salvar qualquer subclasse de EntidadeDomínio no banco de
     * dados
     *
     * @param entidade - Subclasse de EntidadeDominio
     * @return - Retorna Null se tudo deu certo Caso contrário retorna uma
     * String contendo as mensagens de erro
     */
    @Override
    public Resultado salvar(EntidadeDominio entidade)
    {
    	// Get nome da classe
        String nameClass = entidade.getClass().getName();   
        StringBuilder msg = new StringBuilder();
        Resultado rs = new Resultado();

        // Regras de negocio
        rs = executaRegras(entidade, "Salvar");
        
        if (!rs.getListMensagens().isEmpty())
        {
            return rs;
        }

        try
        {
        	// Instanciando o DAO da classe
            IDAO dao = daos.get(nameClass);       
            
            // Salvando entidade no BD
            dao.salvar(entidade);   
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagem("Um erro ocorreu ao processar o seu pedido. \nTente novamente mais tarde");
            return rs;
        }
    }

    @Override
    public Resultado atualizar(EntidadeDominio entidade)
    {
        Resultado rs = new Resultado();
        try
        {
            String nameClass = entidade.getClass().getName();
            
            IDAO dao = daos.get(nameClass);                
                
            rs = executaRegras(entidade, "Atualizar");
                
            if(!rs.getListMensagens().isEmpty())
            {
                return rs;
            }
            
            dao.atualizar(entidade);
            
            return rs;
        } catch (SQLException ex)
        {
            rs.addMensagem("Erro ao atualizar a entidade");
            return rs;
        }
    }

	@Override
	public Resultado trocar(EntidadeDominio entidade) {
	    {
	        Resultado rs = new Resultado();
	        try
	        {
	            String nameClass = entidade.getClass().getName();
	            
	            IDAO dao = daos.get(nameClass);                 
	                
	            rs = executaRegras(entidade, "Trocar");
	                
	            if(!rs.getListMensagens().isEmpty())
	            {
	                return rs;
	            }
	            
	            dao.trocar(entidade);
	            
	            return rs;
	        } catch (SQLException ex)
	        {
	            rs.addMensagem("Erro ao atualizar a entidade");
	            return rs;
	        }
	    }
	}
	
	@Override
	public Resultado trocar2(EntidadeDominio entidade) {
	    {
			
			Resultado rs = new Resultado();
		        try
		        {
		            String nameClass = entidade.getClass().getName();
		            
		            IDAO dao = daos.get(nameClass);                 
		                
		            rs = executaRegras(entidade, "Trocar2");
		                
		            if(!rs.getListMensagens().isEmpty())
		            {
		                return rs;
		            }
		            
		            dao.trocar2(entidade);
		            
		            return rs;
		        } catch (SQLException ex)
		        {
		            rs.addMensagem("Erro ao atualizar a entidade");
		            return rs;
		        }
		    }
	}

	
	@Override
	public Resultado trocaradm(EntidadeDominio entidade) {
		
		Resultado rs = new Resultado();
	        try
	        {
	            String nameClass = entidade.getClass().getName();
	            
	            IDAO dao = daos.get(nameClass);                 
	                
	            rs = executaRegras(entidade, "TrocarADM");
	                
	            if(!rs.getListMensagens().isEmpty())
	            {
	                return rs;
	            }
	            
	            dao.trocaradm(entidade);
	            
	            return rs;
	        } catch (SQLException ex)
	        {
	            rs.addMensagem("Erro ao atualizar a entidade");
	            return rs;
	        }
	    }
	

	
    @Override
    public Resultado excluir(EntidadeDominio entidade)
    {
        String nameClass = entidade.getClass().getName();
        
        IDAO dao = daos.get(nameClass);
        
        Resultado resultado = new Resultado();
        
        try
        {
            dao.excluir(entidade);
            return resultado;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagem("Erro ao excluir entidade! ");
            return resultado;
        }
    }

    
    @Override
    public Resultado consultar(EntidadeDominio entidade)
    {
        String nameClass = entidade.getClass().getName();
        IDAO dao = daos.get(nameClass);
        Resultado rs = new Resultado();
        try
        {
            List<EntidadeDominio> entidades = dao.consultar(entidade);
            rs.setEntidades(entidades);
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagem("Erro ao efetuar a consulta");
            return rs;
        }
    }
    
	@Override
	public Resultado consultartroca(EntidadeDominio entidade) {
        String nameClass = entidade.getClass().getName();
        IDAO dao = daos.get(nameClass);
        Resultado rs = new Resultado();
        try
        {
            List<EntidadeDominio> entidades = dao.consultartroca(entidade);
            rs.setEntidades(entidades);
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagem("Erro ao efetuar a consulta");
            return rs;
        }
    }
	
	@Override
	public Resultado consultartrocacupom(EntidadeDominio entidade) {
        String nameClass = entidade.getClass().getName();
        IDAO dao = daos.get(nameClass);
        Resultado rs = new Resultado();
        try
        {
            List<EntidadeDominio> entidades = dao.consultartrocacupom(entidade);
            rs.setEntidades(entidades);
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagem("Erro ao efetuar a consulta");
            return rs;
        }	
	}
	
    public Resultado executaRegras(EntidadeDominio entidade, String operacao)
    {
        String nameClass = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();
        Resultado rs = new Resultado();
        
        Map<String, List<IStrategy>> regrasOperacao = rns.get(nameClass);
        rns.get(nameClass);
        if(entidade instanceof Produto && ((Produto)entidade).getQuantidade() != 0)
            regrasOperacao = null;
        
        // Não existem regras?!
        if(regrasOperacao == null)  
        {
            return new Resultado();
        }
        
        // A lista não é vazia?!
        if(!regrasOperacao.isEmpty())  
        { //Vazia!
            List<IStrategy> regras = regrasOperacao.get(operacao);
            
            for(IStrategy s : regras)
            {
                String m = s.processar(entidade);
                
                if(m != null)
                {
                    rs.addMensagem(m);
                }
            }
        }
        return rs;
    }

	@Override
	public Resultado consultargrafico(EntidadeDominio entidade) {
        String nameClass = entidade.getClass().getName();
        IDAO dao = daos.get(nameClass);
        Resultado rs = new Resultado();
        try
        {
            List<EntidadeDominio> entidades = dao.consultargrafico(entidade);
            rs.setEntidades(entidades);
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagem("Erro ao efetuar a consulta");
            return rs;
        }	
	}
}