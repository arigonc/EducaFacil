package models;

import java.util.Date;

public class Aluno {
	private String email;
	private String nome;
	private String senha;
	private String situacao;
	private float nota;
	private Date cadastro;

	public Aluno(String email, String nome, String senha, String situacao, float nota, Date cadastro) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.situacao = situacao;
		this.nota = nota;
		this.cadastro = cadastro;
	}

	public Aluno() {

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

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}
}