package meraki.com.br.teste;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.impl.dao.PedidoDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.ItemPedido;
import meraki.com.br.domain.Produto;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Usuario;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public class TestePedido
{
    public static void main(String[] args) throws SQLException
    {
        consultarPedidoData();
    }
   
    public static void salvar() throws SQLException
    {
        Pedido pedido = new Pedido();
        Usuario cliente = new Usuario();
        Produto produto = new Produto();
        ItemPedido item = new ItemPedido();
        
        pedido.setDataNormal(new Date());
        pedido.setDataPedido(Calendar.getInstance());
        
        // Dados do cliente
        cliente.setId(77);
        pedido.setCliente(cliente);
        
        // Set tipo de pagamento
        pedido.setTipoPagamento("Cartão de Crédito");
        
        // Dados do produto
        produto.setId(3);
        produto.setValor(99.99f);
        item.setProduto(produto);
        item.setQuantidade(2);
        pedido.addItem(item);
        
        item = new ItemPedido();
        Produto produto1 = new Produto();
        produto1.setId(2); 
        item.setProduto(produto1);
        item.setQuantidade(3);
        pedido.addItem(item);
        
        IDAO dao = new PedidoDAO();
        
        dao.salvar(pedido); 
    }
    
    public static void consultar() throws SQLException
    {
        Pedido pedido = new Pedido();
        Usuario cliente = new Usuario();
        
        cliente.setId(77);
        pedido.setCliente(cliente);
        
        IDAO dao = new PedidoDAO();
        
        List<EntidadeDominio> pedidos = dao.consultar(pedido);
        
        for(EntidadeDominio ed : pedidos)
        {
            System.out.println("Nome Cliente: "+((Pedido)ed).getCliente().getNome());  
        }
    }
    
    public static void consultarPedidoData() throws SQLException
    {
        Pedido pedido = new Pedido();
        Usuario cliente = new Usuario();
        
        cliente.setId(77);
        pedido.setCliente(cliente);
        pedido.setDataPedido(Calendar.getInstance());
        pedido.setDataNormal(new Date());
        
        IDAO dao = new PedidoDAO();
        
        List<EntidadeDominio> entidades = dao.consultar(pedido);
        SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy");
        for(EntidadeDominio entidade : entidades)
        {
            Pedido p = (Pedido) entidade;
            
            System.out.println(smf.format(p.getDataNormal()));
            System.out.println(p.getTipoPagamento());
        }
    }
}