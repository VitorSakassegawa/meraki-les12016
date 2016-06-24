package meraki.com.br.core;

import java.sql.SQLException;
import java.util.List;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public interface IDAO {
    public void salvar(EntidadeDominio entidade) throws SQLException;
    public void atualizar(EntidadeDominio entidade) throws SQLException;
    public void excluir(EntidadeDominio entidade) throws SQLException;
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;

    // IDAO - Trocar Produtos
	public void trocar(EntidadeDominio entidade) throws SQLException;
	public void trocar2(EntidadeDominio entidade) throws SQLException;
	public void trocaradm(EntidadeDominio entidade) throws SQLException;
    public List<EntidadeDominio> consultartroca(EntidadeDominio entidade) throws SQLException;
    public List<EntidadeDominio> consultartrocacupom(EntidadeDominio entidade) throws SQLException;

    // IDAO - Relatorio
    public List<EntidadeDominio> consultargrafico(EntidadeDominio entidade) throws SQLException;

}
