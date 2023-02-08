package dao;

import models.Resposta;

public interface RespostaDAO {
	public void cadastrarResposta(Resposta resposta) throws DAOException;

	public void atualizarResposta(Resposta resposta) throws DAOException;

	public Resposta buscarRespostaPeloIdQuestaoEmail(int id, String email) throws DAOException;

	public boolean verificarAtividadeRespondida(int id, String email) throws DAOException;

	public boolean verificarAtividadeDiscursivaPendente(int id, String email) throws DAOException;

	public int quantidadeQuestoesObjetivas(int id) throws DAOException;

	public int quantidadeQuestoesObjetivasAcertadas(int id, String email) throws DAOException;

	public int quantidadeQuestoesDiscursivas(int id) throws DAOException;

	public int quantidadeQuestoesDiscursivasAcertadas(int id, String email) throws DAOException;
}