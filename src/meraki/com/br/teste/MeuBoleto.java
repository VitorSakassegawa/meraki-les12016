package meraki.com.br.teste;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

import meraki.com.br.domain.Pedido;

/**
*
* @author Vitor Sakassegawa
*/
//Help: http://www.jrimum.org/bopepo/wiki/Componente/Documentacao/Tutoriais/GeracaoDeBoletoPadrao
public class MeuBoleto
{
    public ByteArrayOutputStream getBoleto(Pedido pedido)
    {

        // Informando dados sobre o cedente
    	Cedente cedente = new Cedente("Vitor Sakassegawa", "441.018.348-65");

        // Informando os dados sobre o sacado
        Sacado sacado = new Sacado(pedido.getCliente().getNome(), pedido.getCliente().getCpf());

        // Informando os dados sobre a conta bancária do título
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(1234, "1"));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(pedido.valorTotalDesconto(0.15f)));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.OUTROS);
        titulo.setDesconto(BigDecimal.ZERO);
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.valueOf(pedido.getFrete().getValor()));
        titulo.setValorCobrado(BigDecimal.valueOf(pedido.valorTotalDesconto(0.15f) + pedido.getFrete().getValor()));

        // Informando os dados sobre o boleto
        Boleto boleto = new Boleto(titulo);

        boleto.setLocalPagamento("Poderá ser pago em qualquer lotéria, "+
               "agência bancária ou através seu internet banking!");
        boleto.setInstrucaoAoSacado("Este boleto precisar ser pago ainda hoje"
                + " para que sua compra seja efetivada");
        boleto.setInstrucao1("Após a data de vencimento, esse boleto não é mais válido!");

        // Gerando o boleto bancário
        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        
        return boletoViewer.getPdfAsStream();
    }
}