package models;

import java.util.Date;

public class Resposta {
	private int id_resposta;
	private String resposta;
	private String correcao;
	private Date cadastro;
	private String email;
	private int id_questao;

	public Resposta(int id_resposta, String resposta, String correcao, Date cadastro, String email, int id_questao) {
		super();
		this.id_resposta = id_resposta;
		this.resposta = resposta;
		this.correcao = correcao;
		this.cadastro = cadastro;
		this.email = email;
		this.id_questao = id_questao;
	}

	public Resposta() {

	}

	public int getId_resposta() {
		return id_resposta;
	}

	public void setId_resposta(int id_resposta) {
		this.id_resposta = id_resposta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getCorrecao() {
		return correcao;
	}

	public void setCorrecao(String correcao) {
		this.correcao = correcao;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId_questao() {
		return id_questao;
	}

	public void setId_questao(int id_questao) {
		this.id_questao = id_questao;
	}

}
