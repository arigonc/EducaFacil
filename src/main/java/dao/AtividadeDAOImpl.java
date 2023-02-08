package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Atividade;
import models.Questao;
import util.ConnectionFactory;

public class AtividadeDAOImpl implements AtividadeDAO {

	private Connection connection;

	public AtividadeDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public int cadastrarAtividade(Atividade atividade) throws DAOException {
		try {
			String sql = "INSERT INTO atividades (titulo, descricao, data_inicio, data_fim, id_sala) VALUES (?, ?, ?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, atividade.getTitulo());
			stmt.setString(2, atividade.getDescricao());
			stmt.setDate(3, new java.sql.Date(atividade.getData_inicio().getTime()));
			stmt.setDate(4, new java.sql.Date(atividade.getData_fim().getTime()));
			stmt.setInt(5, atividade.getId_sala());

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
			throw new DAOException("Erro ao cadastrar atividade: " + e.getMessage());
		}

	}

	@Override
	public void atualizarAtividade(Atividade atividade) throws DAOException {
		try {
			String sql = "UPDATE atividades SET titulo = ?, descricao = ?, data_inicio = ?, data_fim = ? WHERE id_atividade = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, atividade.getTitulo());
			stmt.setString(2, atividade.getDescricao());
			stmt.setDate(3, new java.sql.Date(atividade.getData_inicio().getTime()));
			stmt.setDate(4, new java.sql.Date(atividade.getData_fim().getTime()));
			stmt.setInt(5, atividade.getId_atividade());

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar atividade: " + e.getMessage());
		}
	}

	@Override
	public void removerAtividade(int id) throws DAOException {
		try {
			String sql = "DELETE FROM atividades WHERE id_atividade = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover atividade: " + e.getMessage());
		}
	}

	@Override
	public Atividade buscarAtividadePeloId(int id) throws DAOException {
		try {
			String sql = "SELECT * FROM atividades WHERE id_atividade = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Atividade atividade = new Atividade();
			if (rs.next()) {
				String titulo = rs.getString("titulo");
				String descricao = rs.getString("descricao");
				Date data_inicio = rs.getDate("data_inicio");
				Date data_fim = rs.getDate("data_fim");
				int id_sala = rs.getInt("id_sala");

				atividade.setId_atividade(id);
				atividade.setTitulo(titulo);
				atividade.setDescricao(descricao);
				atividade.setData_inicio(data_inicio);
				atividade.setData_fim(data_fim);
				atividade.setId_sala(id_sala);

			}
			rs.close();
			stmt.close();
			return atividade;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar atividade pelo id: " + e.getMessage());
		}
	}

	@Override
	public List<Questao> questoesAtividade(int id) throws DAOException {
		List<Questao> questoes = new ArrayList<Questao>();
		try {
			String sql = "SELECT * FROM questoes WHERE id_atividade = ? ORDER BY cadastro ASC;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id_questao = rs.getInt("id_questao");
				String pergunta = rs.getString("pergunta");
				String altA = rs.getString("altA");
				String altB = rs.getString("altB");
				String altC = rs.getString("altC");
				String altD = rs.getString("altD");
				String altE = rs.getString("altE");
				String gabarito = rs.getString("gabarito");
				String tipo = rs.getString("tipo");

				Questao questao = new Questao();
				questao.setId_questao(id_questao);
				questao.setPergunta(pergunta);
				questao.setAltA(altA);
				questao.setAltB(altB);
				questao.setAltC(altC);
				questao.setAltD(altD);
				questao.setAltE(altE);
				questao.setGabarito(gabarito);
				questao.setTipo(tipo);
				questao.setId_atividade(id);

				questoes.add(questao);
			}
			rs.close();
			stmt.close();
			return questoes;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar questões de uma atividade: " + e.getMessage());
		}
	}
}