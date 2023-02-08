package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Aluno;
import models.Atividade;
import models.Aula;
import models.Professor;
import models.Sala;
import util.ConnectionFactory;

public class SalaDAOImpl implements SalaDAO {

	private Connection connection;

	public SalaDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public int cadastrarSala(Sala sala) throws DAOException {
		try {
			String sql = "INSERT INTO salas (titulo, descricao, materia, cor, cor_back) VALUES (?, ?, ?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, sala.getTitulo());
			stmt.setString(2, sala.getDescricao());
			stmt.setString(3, sala.getMateria());
			stmt.setString(4, sala.getCor());
			stmt.setString(5, sala.getCor_back());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				return id;
			}

			rs.close();
			stmt.close();
			return 0;
		} catch (Exception e) {
			throw new DAOException("Erro ao cadastrar sala: " + e.getMessage());
		}

	}

	@Override
	public void atualizarSala(Sala sala) throws DAOException {
		try {
			String sql = "UPDATE salas SET titulo = ?, descricao = ?, materia = ?, cor = ?, cor_back = ? WHERE id = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, sala.getTitulo());
			stmt.setString(2, sala.getDescricao());
			stmt.setString(3, sala.getMateria());
			stmt.setString(4, sala.getCor());
			stmt.setString(5, sala.getCor_back());
			stmt.setInt(6, sala.getId());

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar sala: " + e.getMessage());
		}
	}

	@Override
	public void removerSala(int id) throws DAOException {
		try {
			String sql = "DELETE FROM salas WHERE id = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover sala: " + e.getMessage());
		}
	}

	@Override
	public Sala buscarSalaPeloId(int id) throws DAOException {
		try {
			String sql = "SELECT * FROM salas WHERE id = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Sala sala = new Sala();
			if (rs.next()) {
				String titulo = rs.getString("titulo");
				String descricao = rs.getString("descricao");
				String materia = rs.getString("materia");
				String cor = rs.getString("cor");
				String cor_back = rs.getString("cor_back");

				sala.setId(id);
				sala.setTitulo(titulo);
				sala.setDescricao(descricao);
				sala.setMateria(materia);
				sala.setCor(cor);
				sala.setCor_back(cor_back);

			}
			rs.close();
			stmt.close();
			return sala;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar sala pelo id: " + e.getMessage());
		}
	}

	@Override
	public List<Aula> aulasSala(int id) throws DAOException {
		List<Aula> aulas = new ArrayList<Aula>();
		try {
			String sql = "SELECT * FROM aulas WHERE id_sala = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id_aula = rs.getInt("id_aula");
				String titulo = rs.getString("titulo");
				String descricao = rs.getString("descricao");
				String video = rs.getString("video");
				Date data_inicio = rs.getDate("data_inicio");
				Date data_fim = rs.getDate("data_fim");

				Aula aula = new Aula();
				aula.setId_aula(id_aula);
				aula.setTitulo(titulo);
				aula.setDescricao(descricao);
				aula.setVideo(video);
				aula.setData_inicio(data_inicio);
				aula.setData_fim(data_fim);
				aula.setId_sala(id);

				aulas.add(aula);
			}
			rs.close();
			stmt.close();
			return aulas;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar aulas de uma sala: " + e.getMessage());
		}
	}

	@Override
	public List<Atividade> atividadesSala(int id) throws DAOException {
		List<Atividade> atividades = new ArrayList<Atividade>();
		try {
			String sql = "SELECT * FROM atividades WHERE id_sala = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id_atividade = rs.getInt("id_atividade");
				String titulo = rs.getString("titulo");
				String descricao = rs.getString("descricao");
				Date data_inicio = rs.getDate("data_inicio");
				Date data_fim = rs.getDate("data_fim");

				Atividade atividade = new Atividade();
				atividade.setId_atividade(id_atividade);
				atividade.setTitulo(titulo);
				atividade.setDescricao(descricao);
				atividade.setData_inicio(data_inicio);
				atividade.setData_fim(data_fim);
				atividade.setId_sala(id);

				atividades.add(atividade);
			}
			rs.close();
			stmt.close();
			return atividades;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar atividades de uma sala: " + e.getMessage());
		}
	}

	@Override
	public List<Aluno> alunosSala(int id) throws DAOException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			String sql = "SELECT * FROM salas_alunos, alunos WHERE salas_alunos.email = alunos.email AND salas_alunos.id = ? ORDER BY salas_alunos.cadastro DESC;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString("email");
				String nome = rs.getString("nome");
				String situacao = rs.getString("situacao");

				Aluno aluno = new Aluno();
				aluno.setEmail(email);
				aluno.setNome(nome);
				aluno.setSituacao(situacao);

				alunos.add(aluno);
			}
			rs.close();
			stmt.close();
			return alunos;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar alunos de uma sala: " + e.getMessage());
		}
	}

	@Override
	public List<Professor> professoresSala(int id) throws DAOException {
		List<Professor> professores = new ArrayList<Professor>();
		try {
			String sql = "SELECT * FROM salas_professores, professores WHERE salas_professores.email = professores.email AND salas_professores.id = ? ORDER BY salas_professores.cadastro DESC;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString("email");
				String nome = rs.getString("nome");
				String situacao = rs.getString("situacao");

				Professor professor = new Professor();
				professor.setEmail(email);
				professor.setNome(nome);
				professor.setSituacao(situacao);

				professores.add(professor);
			}
			rs.close();
			stmt.close();
			return professores;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar professores de uma sala: " + e.getMessage());
		}
	}
}