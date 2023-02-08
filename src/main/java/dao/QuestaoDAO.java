package dao;

import models.Questao;

public interface QuestaoDAO {
	public void cadastrarQuestao(Questao questao) throws DAOException;

	public void atualizarQuestao(Questao questao) throws DAOException;

	public void removerQuestao(int id) throws DAOException;

	public Questao buscarQuestaoPeloId(int id) throws DAOException;
}