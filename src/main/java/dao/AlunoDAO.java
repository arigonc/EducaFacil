package dao;

import java.util.List;
import models.Aluno;
import models.Sala;

public interface AlunoDAO {
	public void cadastrarAluno(Aluno aluno) throws DAOException;

	public void atualizarAluno(Aluno aluno) throws DAOException;

	public void removerAluno(String email) throws DAOException;

	public Aluno buscarAlunoPorEmail(String email) throws DAOException;

	public boolean login(String email, String senha) throws DAOException;

	public List<Aluno> alunos() throws DAOException;

	public String verificarAlunoSala(int id, String email) throws DAOException;

	public void entrarNovaSala(int id, String email) throws DAOException;

	public void atualizarEntradaSala(int id, String email, String situacao) throws DAOException;

	public void cancelarEntradaSala(int id, String email) throws DAOException;

	public List<Sala> salasAluno(String email) throws DAOException;

	public int quantidadeSalasAtivas(String email) throws DAOException;

	public int quantidadeAulas(String email) throws DAOException;
	
	public int quantidadeAtividades(String email) throws DAOException;
}