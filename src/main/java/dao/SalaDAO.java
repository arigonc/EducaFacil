package dao;

import java.util.List;

import models.Aluno;
import models.Atividade;
import models.Aula;
import models.Professor;
import models.Sala;

public interface SalaDAO {
	public int cadastrarSala(Sala sala) throws DAOException;

	public void atualizarSala(Sala sala) throws DAOException;

	public void removerSala(int id) throws DAOException;

	public Sala buscarSalaPeloId(int id) throws DAOException;

	public List<Aula> aulasSala(int id) throws DAOException;

	public List<Atividade> atividadesSala(int id) throws DAOException;

	public List<Aluno> alunosSala(int id) throws DAOException;

	public List<Professor> professoresSala(int id) throws DAOException;
}