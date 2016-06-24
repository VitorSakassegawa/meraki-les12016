package meraki.com.br.teste;

/**
*
* @author Vitor Sakassegawa
*/
public class Servicos
{

    public Servicos()
    {
        cServico = new cServico();
    }
    public final cServico cServico;
    
    public class cServico
    {
        public String Codigo;
        public String Valor;
        public String PrazoEntrega;
        public String ValorSemAdicionais;
        public String ValorMaoPropria;
        public String ValorAvisoRecebimento;
        public String ValorValorDeclarado;
        public String EntregaDomiciliar;
        public String EntregaSabado;
        public String Erro;
        public String MsgErro;
    }
}