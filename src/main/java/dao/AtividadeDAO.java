package dao;

import java.util.List;

import models.Atividade;
import models.Questao;

public interface AtividadeDAO {
	public int cadastrarAtividade(Atividade atividade) throws DAOException;

	public void atualizarAtividade(Atividade atividade) throws DAOException;

	public void removerAtividade(int id) throws DAOException;

	public Atividade buscarAtividadePeloId(int id) throws DAOException;

	public List<Questao> questoesAtividade(int id) throws DAOException;
}