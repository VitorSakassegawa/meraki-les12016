package meraki.com.br.core;

import meraki.com.br.core.application.Resultado;
import meraki.com.br.domain.EntidadeDominio;

import java.util.List;

/**
*
* @author Vitor Sakassegawa
*/
public interface IFachada {
    public Resultado salvar(EntidadeDominio entidade);
    public Resultado atualizar(EntidadeDominio entidade);
    public Resultado excluir(EntidadeDominio entidade);
    public Resultado consultar(EntidadeDominio entidade);

    
    // IFachada - Trocar Produtos
    public Resultado trocar(EntidadeDominio entidade);
    public Resultado trocar2(EntidadeDominio entidade);
    public Resultado trocaradm(EntidadeDominio entidade);
    public Resultado consultartroca(EntidadeDominio entidade);
    public Resultado consultartrocacupom(EntidadeDominio entidade);
    
    // IFachada - Relatorio
    public Resultado consultargrafico(EntidadeDominio entidade);
    }
