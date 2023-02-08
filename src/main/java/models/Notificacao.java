package models;

import java.util.Date;

public class Notificacao {
	private int id;
	private String categoria;
	private String tipo;
	private String descricao;
	private String email;
	private Date cadastro;

	public Notificacao(int id, String categoria, String tipo, String descricao, String email, Date cadastro) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.tipo = tipo;
		this.descricao = descricao;
		this.email = email;
		this.cadastro = cadastro;
	}

	public Notificacao() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

}
