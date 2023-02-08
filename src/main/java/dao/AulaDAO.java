package dao;

import models.Aula;

public interface AulaDAO {
	public int cadastrarAula(Aula aula) throws DAOException;

	public void atualizarAula(Aula aula) throws DAOException;

	public void removerAula(int id) throws DAOException;

	public Aula buscarAulaPeloId(int id) throws DAOException;
}