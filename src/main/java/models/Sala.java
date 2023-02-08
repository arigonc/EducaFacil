package models;

import java.util.Date;

public class Sala {
	private int id;
	private String titulo;
	private String descricao;
	private String materia;
	private String cor;
	private String cor_back;
	private String situacao;
	private Date cadastro;

	public Sala(int id, String titulo, String descricao, String materia, String cor, String cor_back, String situacao,
			Date cadastro) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.materia = materia;
		this.cor = cor;
		this.cor_back = cor_back;
		this.situacao = situacao;
		this.cadastro = cadastro;
	}

	public Sala() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCor_back() {
		return cor_back;
	}

	public void setCor_back(String cor_back) {
		this.cor_back = cor_back;
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
