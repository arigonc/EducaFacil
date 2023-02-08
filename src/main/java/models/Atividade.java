package models;

import java.util.Date;

public class Atividade {
	private int id_atividade;
	private String titulo;
	private String descricao;
	private Date data_inicio;
	private Date data_fim;
	private Date cadastro;
	private int id_sala;

	public Atividade(int id_atividade, String titulo, String descricao, Date data_inicio, Date data_fim, Date cadastro,
			int id_sala) {
		super();
		this.id_atividade = id_atividade;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.cadastro = cadastro;
		this.id_sala = id_sala;
	}

	public Atividade() {

	}

	public int getId_atividade() {
		return id_atividade;
	}

	public void setId_atividade(int id_atividade) {
		this.id_atividade = id_atividade;
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

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}
}
