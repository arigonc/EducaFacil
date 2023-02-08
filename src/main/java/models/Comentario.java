package models;

import java.util.Date;
import java.util.List;

public class Comentario {
	private int id_comentario;
	private String comentario;
	private Date cadastro;
	private String nome;
	private String email;
	private String tipo;
	private int id_aula;
	private List<Subcomentario> subcomentarios;

	public Comentario(int id_comentario, String comentario, Date cadastro, String nome, String email, String tipo,
			int id_aula, List<Subcomentario> subcomentarios) {
		super();
		this.id_comentario = id_comentario;
		this.comentario = comentario;
		this.cadastro = cadastro;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.id_aula = id_aula;
		this.subcomentarios = subcomentarios;
	}

	public Comentario() {

	}

	public int getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public int getId_aula() {
		return id_aula;
	}

	public void setId_aula(int id_aula) {
		this.id_aula = id_aula;
	}

	public List<Subcomentario> getSubcomentarios() {
		return subcomentarios;
	}

	public void setSubcomentarios(List<Subcomentario> subcomentarios) {
		this.subcomentarios = subcomentarios;
	}

}
