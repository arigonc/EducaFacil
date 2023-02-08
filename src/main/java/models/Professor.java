package models;

import java.util.Date;

public class Professor {
	private String email;
	private String nome;
	private String senha;
	private String situacao;
	private Date cadastro;

	public Professor(String email, String nome, String senha, String situacao, Date cadastro) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.situacao = situacao;
		this.cadastro = cadastro;
	}

	public Professor() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}
}
