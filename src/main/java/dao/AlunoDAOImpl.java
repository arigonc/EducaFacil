package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Aluno;
import models.Sala;
import util.ConnectionFactory;

public class AlunoDAOImpl implements AlunoDAO {

	private Connection connection;

	public AlunoDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public void cadastrarAluno(Aluno aluno) throws DAOException {
		try {
			String sql = "INSERT INTO alunos (email, nome, senha) VALUES (?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, aluno.getEmail());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getSenha());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao cadastrar aluno: " + e.getMessage());
		}

	}

	@Override
	public void atualizarAluno(Aluno aluno) throws DAOException {
		try {
			String sql = "UPDATE alunos SET senha = ? WHERE email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, aluno.getSenha());
			stmt.setString(2, aluno.getEmail());

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar aluno: " + e.getMessage());
		}
	}

	@Override
	public void removerAluno(String email) throws DAOException {
		try {
			String sql = "DELETE FROM alunos WHERE email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover aluno: " + e.getMessage());
		}
	}

	@Override
	public Aluno buscarAlunoPorEmail(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM alunos WHERE email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			Aluno aluno = new Aluno();
			if (rs.next()) {
				String nome = rs.getString("nome");

				aluno.setEmail(email);
				aluno.setNome(nome);
				return aluno;
			}
			rs.close();
			stmt.close();
			return null;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar aluno pelo email: " + e.getMessage());
		}
	}

	public boolean login(String email, String senha) throws DAOException {
		boolean message = false;
		try {
			String sql = "SELECT senha FROM alunos WHERE email = ?;";

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
	public List<Aluno> alunos() throws DAOException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			String sql = "SELECT * FROM alunos;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString("email");
				String nome = rs.getString("nome");

				Aluno aluno = new Aluno();
				aluno.setEmail(email);
				aluno.setNome(nome);

				alunos.add(aluno);
			}
			rs.close();
			stmt.close();
			return alunos;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar todos os alunos: " + e.getMessage());
		}
	}

	@Override
	public String verificarAlunoSala(int id, String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_alunos WHERE id = ? AND email = ?;";

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
			throw new DAOException("Erro ao verificar se aluno está numa sala: " + e.getMessage());
		}

	}

	@Override
	public void entrarNovaSala(int id, String email) throws DAOException {
		try {
			String sql = "INSERT INTO salas_alunos (id, email, situacao) VALUES (?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, id);
			stmt.setString(2, email);
			stmt.setString(3, "Pendente");

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao entrar aluno em nova sala: " + e.getMessage());
		}
	}

	@Override
	public void atualizarEntradaSala(int id, String email, String situacao) throws DAOException {
		try {
			String sql = "UPDATE salas_alunos SET situacao = ? WHERE id = ? AND email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, situacao);
			stmt.setInt(2, id);
			stmt.setString(3, email);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar solicitação de entrada do aluno na sala: " + e.getMessage());
		}
	}

	@Override
	public void cancelarEntradaSala(int id, String email) throws DAOException {
		try {
			String sql = "DELETE FROM salas_alunos WHERE id = ? AND email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover solicitação de entrada do aluno na sala: " + e.getMessage());
		}
	}

	@Override
	public List<Sala> salasAluno(String email) throws DAOException {
		List<Sala> salas = new ArrayList<Sala>();
		try {
			String sql = "SELECT * FROM salas, salas_alunos WHERE salas.id = salas_alunos.id AND salas_alunos.email = ?;";

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
			throw new DAOException("Erro ao buscar as salas do aluno: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeSalasAtivas(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_alunos, alunos WHERE salas_alunos.email = alunos.email AND salas_alunos.situacao = 'Ativo' AND salas_alunos.email = ?;";

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
			throw new DAOException("Erro ao buscar quantidade de salas ativas de um aluno: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeAulas(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_alunos, aulas WHERE salas_alunos.id = aulas.id_sala AND salas_alunos.situacao = 'Ativo' AND salas_alunos.email = ?;";

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
			throw new DAOException("Erro ao buscar quantidade de aulas das salas de um aluno: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeAtividades(String email) throws DAOException {
		try {
			String sql = "SELECT * FROM salas_alunos, atividades WHERE salas_alunos.id = atividades.id_sala AND salas_alunos.situacao = 'Ativo' AND salas_alunos.email = ?;";

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
			throw new DAOException("Erro ao buscar quantidade de atividades das salas de um aluno: " + e.getMessage());
		}
	}
}