package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Professor;
import models.Sala;
import util.ConnectionFactory;

public class ProfessorDAOImpl implements ProfessorDAO {
	private Connection connection;

	public ProfessorDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public void cadastrarProfessor(Professor professor) throws DAOException {
		try {
			String sql = "INSERT INTO professores (email, nome, senha) VALUES (?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, professor.getEmail());
			stmt.setString(2, professor.getNome());
			stmt.setString(3, professor.getSenha());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao cadastrar professor: " + e.getMessage());
		}

	}

	@Override
	public void atualizarProfessor(Professor professor) throws DAOException {
		try {
			String sql = "UPDATE professores SET nome = ?, senha = ? WHERE email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getSenha());
			stmt.setString(3, professor.getEmail());

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar professor: " + e.getMessage());
		}
	}

	@Override
	public void removerProfessor(String email) throws DAOException {
		try {
			String sql = "DELETE FROM professores WHERE email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover professor: " + e.getMessage());
		}
	}

	@Override
	public Professor buscarProfessorPorEmail(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM professores WHERE email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			Professor professor = new Professor();
			if (rs.next()) {
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");

				professor.setEmail(email);
				professor.setNome(nome);
				professor.setSenha(senha);
				return professor;
			}
			rs.close();
			stmt.close();
			return null;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar professor pelo email: " + e.getMessage());
		}
	}

	public boolean login(String email, String senha) throws DAOException {
		boolean message = false;
		try {
			String sql = "SELECT senha FROM professores WHERE email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String senhaCadastrada = rs.getString("senha");
				if (senhaCadastrada.equals(senha)) {
					message = true;
				}
			}

			rs.close();
			stmt.close();
			return message;
		} catch (Exception e) {
			throw new DAOException("Erro no login: " + e.getMessage());
		}
	}

	@Override
	public List<Professor> professors() throws DAOException {
		List<Professor> professors = new ArrayList<Professor>();
		try {
			String sql = "SELECT * FROM professors;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString("email");
				String nome = rs.getString("nome");

				Professor professor = new Professor();
				professor.setEmail(email);
				professor.setNome(nome);

				professors.add(professor);
			}
			rs.close();
			stmt.close();
			return professors;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar todos os professores: " + e.getMessage());
		}
	}

	@Override
	public String verificarProfessorSala(int id, String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_professores WHERE id = ? AND email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String situacao = rs.getString("situacao");
				return situacao;
			}

			rs.close();
			stmt.close();
			return null;
		} catch (Exception e) {
			throw new DAOException("Erro ao verificar se professor está numa sala: " + e.getMessage());
		}

	}

	@Override
	public void entrarNovaSala(int id, String email, String situacao) throws DAOException {
		try {
			String sql = "INSERT INTO salas_professores (id, email, situacao) VALUES (?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, id);
			stmt.setString(2, email);
			stmt.setString(3, situacao);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao entrar professor em nova sala: " + e.getMessage());
		}

	}

	@Override
	public void atualizarEntradaSala(int id, String email, String situacao) throws DAOException {
		try {
			String sql = "UPDATE salas_professores SET situacao = ? WHERE id = ? AND email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, situacao);
			stmt.setInt(2, id);
			stmt.setString(3, email);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar solicitação de entrada do professor na sala: " + e.getMessage());
		}
	}

	@Override
	public void cancelarEntradaSala(int id, String email) throws DAOException {
		try {
			String sql = "DELETE FROM salas_professores WHERE id = ? AND email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover solicitação de entrada do professor na sala: " + e.getMessage());
		}
	}

	@Override
	public List<Sala> salasProfessor(String email) throws DAOException {
		List<Sala> salas = new ArrayList<Sala>();
		try {
			String sql = "SELECT * FROM salas, salas_professores WHERE salas.id = salas_professores.id AND salas_professores.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String descricao = rs.getString("descricao");
				String materia = rs.getString("materia");
				String cor = rs.getString("cor");
				String cor_back = rs.getString("cor_back");
				String situacao = rs.getString("situacao");

				Sala sala = new Sala();
				sala.setId(id);
				sala.setTitulo(titulo);
				sala.setDescricao(descricao);
				sala.setMateria(materia);
				sala.setCor(cor);
				sala.setCor_back(cor_back);
				sala.setSituacao(situacao);

				salas.add(sala);
			}
			rs.close();
			stmt.close();
			return salas;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar as salas do professor: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeSalasAtivas(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_professores, professores WHERE salas_professores.email = professores.email AND salas_professores.situacao = 'Admin' OR salas_professores.situacao = 'Ativo' AND salas_professores.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			int quantidade = 0;

			while (rs.next()) {
				quantidade++;
			}
			rs.close();
			stmt.close();
			return quantidade;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar quantidade de salas ativas de um professor: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeAlunosSalas(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_alunos JOIN salas_professores WHERE salas_alunos.id = salas_professores.id AND salas_professores.situacao = 'Admin' OR salas_professores.situacao = 'Ativo' AND salas_alunos.situacao = 'Admin' OR salas_alunos.situacao = 'Ativo' AND salas_professores.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			int quantidade = 0;

			while (rs.next()) {
				quantidade++;
			}
			rs.close();
			stmt.close();
			return quantidade;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar quantidade de alunos das salas de um professor: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeAulas(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_professores, aulas WHERE salas_professores.id = aulas.id_sala AND salas_professores.situacao = 'Admin' OR salas_professores.situacao = 'Ativo' AND salas_professores.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			int quantidade = 0;

			while (rs.next()) {
				quantidade++;
			}
			rs.close();
			stmt.close();
			return quantidade;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar quantidade de aulas das salas de um professor: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeAtividades(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_professores, atividades WHERE salas_professores.id = atividades.id_sala AND salas_professores.situacao = 'Admin' OR salas_professores.situacao = 'Ativo' AND salas_professores.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			int quantidade = 0;

			while (rs.next()) {
				quantidade++;
			}
			rs.close();
			stmt.close();
			return quantidade;
		} catch (Exception e) {
			throw new DAOException(
					"Erro ao buscar quantidade de atividades das salas de um professor: " + e.getMessage());
		}
	}
}
