package models;

import java.util.Date;

public class Aula {
	private int id_aula;
	private String titulo;
	private String descricao;
	private String video;
	private Date data_inicio;
	private Date data_fim;
	private Date cadastro;
	private int id_sala;

	public Aula(int id_aula, String titulo, String descricao, String video, Date data_inicio, Date data_fim,
			Date cadastro, int id_sala) {
		super();
		this.id_aula = id_aula;
		this.titulo = titulo;
		this.descricao = descricao;
		this.video = video;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.cadastro = cadastro;
		this.id_sala = id_sala;
	}

	public Aula() {

	}

	public int getId_aula() {
		return id_aula;
	}

	public void setId_aula(int id_aula) {
		this.id_aula = id_aula;
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
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
