package meraki.com.br.domain;

import java.util.List;

import meraki.com.br.domain.EntidadeDominio;

/**
*
* @author Vitor Sakassegawa
*/
public class Produto extends EntidadeDominio
{
	private String ano;
    private String categoria;
    private String descricao;
    private String genero;
    private String image;
    private String origem;
    private String numeracao;
    private List<String> plataformas;
    private int quantidade;
    private String material; 
    private String titulo;
    private float valor;
    
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getNumeracao() {
		return numeracao;
	}
	public void setNumeracao(String numeracao) {
		this.numeracao = numeracao;
	}
	public List<String> getPlataformas() {
		return plataformas;
	}
	public void setPlataformas(List<String> plataformas) {
		this.plataformas = plataformas;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}