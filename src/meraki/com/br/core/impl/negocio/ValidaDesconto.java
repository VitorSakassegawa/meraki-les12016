package meraki.com.br.core.impl.negocio;

import meraki.com.br.core.IStrategy;
import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.ItemPedido;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Transacao;
import meraki.com.br.domain.TransacaoCartao;

/**
*
* @author Vitor Sakassegawa
*/
public class ValidaDesconto implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        if(entidade instanceof TransacaoCartao)
        {
            float desconto = 0;
            
            TransacaoCartao tc = (TransacaoCartao) entidade;
            int parcelas = Integer.parseInt(tc.getCartao().getParcelas().substring(0, 1));
            
            if(parcelas == 1)
                desconto = 0.1f;
            else if(parcelas > 1 && parcelas < 4)
                desconto = 0.05f;
            else
                desconto = 0f;
            
            Pedido pedido = tc.getPedido();
            
            for(ItemPedido ip : pedido.getItens())
            {
                ip.setDesconto(desconto);
            }
        }
        else
        {
            Transacao tc = (Transacao) entidade;
            Pedido pedido = tc.getPedido();
            
            for(ItemPedido ip : pedido.getItens())
            {
                ip.setDesconto(0.15f);
            }
        }
        return null;
    }
}