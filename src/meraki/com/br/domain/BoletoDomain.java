package meraki.com.br.domain;

import meraki.com.br.domain.EntidadeDominio;
import meraki.com.br.domain.Pedido;
import meraki.com.br.domain.Usuario;
import meraki.com.br.teste.MeuBoleto;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
*
* @author Vitor Sakassegawa
*/
// Help: http://www.jrimum.org/bopepo/wiki/Componente/Documentacao/Tutoriais/GeracaoDeBoletoPadrao
public class BoletoDomain extends EntidadeDominio
{
    private final Cedente cedente;
    private final Sacado sacado;
    private final org.jrimum.domkee.comum.pessoa.endereco.Endereco endereco;
    private final ContaBancaria contaBancaria;
    private final Titulo titulo;
    private MeuBoleto boleto;
    private BoletoViewer boletoViewer;
    private File fileBoleto;

    public BoletoDomain(Pedido pedido)
    {
        
        // Cliente dominio da aplicação
        Usuario cliente = pedido.getCliente();

        // Informando os dados sobre o cedente
        cedente = new Cedente("Vitor Sakassegawa", "441.018.348-65");

        // Informando os dados sobre o sacado
        sacado = new Sacado(cliente.getNome(), cliente.getCpf());

        // Pegando o endereço do sacado
        endereco = new org.jrimum.domkee.comum.pessoa.endereco.Endereco();
        endereco.setLocalidade(cliente.getEndereco().getLogradouro());
        endereco.setBairro(cliente.getEndereco().getBairro());
        endereco.setCep(cliente.getEndereco().getCEP());

        // Atribuindo endereço do sacado
        sacado.addEndereco(endereco);

        // Informando a conta bancário p/ depósito
        contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setAgencia(new Agencia(1234, "1"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
        
        // Criando titular do boleto
        titulo = new Titulo(contaBancaria, sacado, cedente);
        int numeroDocumento = (int) Math.random();

        titulo.setNumeroDoDocumento("" + numeroDocumento);
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(pedido.ValorTotal() + pedido.getFrete().getValor()));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.FAT_FATURA);
        titulo.setDesconto(BigDecimal.valueOf(0.15));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);
    }
}