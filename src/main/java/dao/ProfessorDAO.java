package dao;

import java.util.List;

import models.Professor;
import models.Sala;

public interface ProfessorDAO {
	public void cadastrarProfessor(Professor professor) throws DAOException;

	public void atualizarProfessor(Professor professor) throws DAOException;

	public void removerProfessor(String email) throws DAOException;

	public Professor buscarProfessorPorEmail(String email) throws DAOException;

	public boolean login(String email, String senha) throws DAOException;

	public List<Professor> professors() throws DAOException;

	public String verificarProfessorSala(int id, String email) throws DAOException;

	public void entrarNovaSala(int id, String email, String situacao) throws DAOException;

	public void atualizarEntradaSala(int id, String email, String situacao) throws DAOException;

	public void cancelarEntradaSala(int id, String email) throws DAOException;

	public List<Sala> salasProfessor(String email) throws DAOException;

	public int quantidadeSalasAtivas(String email) throws DAOException;
	
	public int quantidadeAlunosSalas(String email) throws DAOException;

	public int quantidadeAulas(String email) throws DAOException;
	
	public int quantidadeAtividades(String email) throws DAOException;
}
