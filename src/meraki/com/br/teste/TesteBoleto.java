package meraki.com.br.teste;

import java.io.File;
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

import meraki.com.br.teste.TesteBoleto;

/**
*
* @author Vitor Sakassegawa
*/
public class TesteBoleto
{

    private static Cedente cedente;
    private static Sacado sacado;
    private static ContaBancaria contaBancaria;
    private static Titulo titulo;

    public TesteBoleto()
    {
        cedente = new Cedente("Meraki", "441.018.348-65");

        sacado = new Sacado("Vitor Keidi Sakassegawa", "441.018.348-65");

        contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
        contaBancaria.setAgencia(new Agencia(1234, "1"));
        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
        contaBancaria.setCarteira(new Carteira(30));

        titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(100.00));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.OUTROS);
        titulo.setDesconto(BigDecimal.valueOf(0.15));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);
    }

    public static void main(String[] args)
    {
        TesteBoleto teste = new TesteBoleto();
        
        Boleto boleto = new Boleto(titulo);
        
        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em "
                + "qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
                + "cobrado não é o esperado, aproveite o DESCONTÃO!");
        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");
        
        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        
        File arquivoPdf = boletoViewer.getPdfAsFile("PagamentoBoleto.pdf");
    }
}