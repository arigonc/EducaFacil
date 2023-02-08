package models;

import java.util.Date;

public class Subcomentario {
	private int id_subcomentario;
	private String subcomentario;
	private Date cadastro;
	private String nome;
	private String email;
	private String tipo;
	private int id_comentario;

	public Subcomentario(int id_subcomentario, String subcomentario, Date cadastro, String nome, String email,
			String tipo, int id_comentario) {
		super();
		this.id_subcomentario = id_subcomentario;
		this.subcomentario = subcomentario;
		this.cadastro = cadastro;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.id_comentario = id_comentario;
	}

	public Subcomentario() {

	}

	public int getId_subcomentario() {
		return id_subcomentario;
	}

	public void setId_subcomentario(int id_subcomentario) {
		this.id_subcomentario = id_subcomentario;
	}

	public String getSubcomentario() {
		return subcomentario;
	}

	public void setSubcomentario(String subcomentario) {
		this.subcomentario = subcomentario;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}

}
