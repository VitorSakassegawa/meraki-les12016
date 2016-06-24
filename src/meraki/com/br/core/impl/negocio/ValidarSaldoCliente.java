package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IDAO;
import meraki.com.br.core.IStrategy;
import meraki.com.br.core.impl.dao.PedidoDAO;
import meraki.com.br.core.impl.dao.SaldoDAO;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Transacao;
import meraki.com.br.domain.TransacaoCartao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidarSaldoCliente implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        SaldoDAO dao = new SaldoDAO();
        TransacaoCartao trCartao = new TransacaoCartao();
        
        if(entidade instanceof Transacao)
        {
            trCartao.setCliente(((Transacao)entidade).getCliente());
            trCartao.setPedido(((Transacao)entidade).getPedido());
            trCartao.setValor(((Transacao)entidade).getValor());
        }
        else
        {
            trCartao = (TransacaoCartao) entidade;
        }
        
        if(trCartao.getPedido().ValorTotal() == 0.0)
        {
            IDAO pedidoDAO =  new PedidoDAO();
            
            try
            {
                List<EntidadeDominio> entidades = pedidoDAO.consultar(trCartao.getPedido());
                trCartao.setPedido((Pedido)entidades.get(0));
                trCartao.setValor(trCartao.TotalTransacao());
                
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                return "Erro ao consultar o valor do pedido";
            }
        }
        
        EntidadeDominio saldo = dao.consultaSaldoEspecifico(trCartao);
        
	    // O cliente não possui saldo
        if(saldo == null)   
        {
            return "Seu pedido foi recusado pelo operadora do cartão";
        }
        return null;
    }
}
