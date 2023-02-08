package models;

public class Questao {
	private int id_questao;
	private String pergunta;
	private String altA;
	private String altB;
	private String altC;
	private String altD;
	private String altE;
	private String gabarito;
	private String tipo;
	private String cadastro;
	private int id_atividade;
	private int ordem;
	private Resposta resposta;

	public Questao(int id_questao, String pergunta, String altA, String altB, String altC, String altD, String altE,
			String gabarito, String tipo, String cadastro, int id_atividade, int ordem, Resposta resposta) {
		super();
		this.id_questao = id_questao;
		this.pergunta = pergunta;
		this.altA = altA;
		this.altB = altB;
		this.altC = altC;
		this.altD = altD;
		this.altE = altE;
		this.gabarito = gabarito;
		this.tipo = tipo;
		this.cadastro = cadastro;
		this.id_atividade = id_atividade;
		this.ordem = ordem;
		this.resposta = resposta;
	}

	public Questao() {

	}

	public int getId_questao() {
		return id_questao;
	}

	public void setId_questao(int id_questao) {
		this.id_questao = id_questao;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getAltA() {
		return altA;
	}

	public void setAltA(String altA) {
		this.altA = altA;
	}

	public String getAltB() {
		return altB;
	}

	public void setAltB(String altB) {
		this.altB = altB;
	}

	public String getAltC() {
		return altC;
	}

	public void setAltC(String altC) {
		this.altC = altC;
	}

	public String getAltD() {
		return altD;
	}

	public void setAltD(String altD) {
		this.altD = altD;
	}

	public String getAltE() {
		return altE;
	}

	public void setAltE(String altE) {
		this.altE = altE;
	}

	public String getGabarito() {
		return gabarito;
	}

	public void setGabarito(String gabarito) {
		this.gabarito = gabarito;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCadastro() {
		return cadastro;
	}

	public void setCadastro(String cadastro) {
		this.cadastro = cadastro;
	}

	public int getId_atividade() {
		return id_atividade;
	}

	public void setId_atividade(int id_atividade) {
		this.id_atividade = id_atividade;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
}
