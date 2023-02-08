package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Questao;
import util.ConnectionFactory;

public class QuestaoDAOImpl implements QuestaoDAO {

	private Connection connection;

	public QuestaoDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public void cadastrarQuestao(Questao questao) throws DAOException {
		try {
			String sql = "INSERT INTO questoes (pergunta, altA, altB, altC, altD, altE, gabarito, tipo, id_atividade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, questao.getPergunta());
			stmt.setString(2, questao.getAltA());
			stmt.setString(3, questao.getAltB());
			stmt.setString(4, questao.getAltC());
			stmt.setString(5, questao.getAltD());
			stmt.setString(6, questao.getAltE());
			stmt.setString(7, questao.getGabarito());
			stmt.setString(8, questao.getTipo());
			stmt.setInt(9, questao.getId_atividade());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao cadastrar questão: " + e.getMessage());
		}

	}

	@Override
	public void atualizarQuestao(Questao questao) throws DAOException {
		try {
			String sql = "UPDATE questoes SET pergunta = ?, altA = ?, altB = ?, altC = ?, altD = ?, altE = ?, gabarito = ? WHERE id_questao = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, questao.getPergunta());
			stmt.setString(2, questao.getAltA());
			stmt.setString(3, questao.getAltB());
			stmt.setString(4, questao.getAltC());
			stmt.setString(5, questao.getAltD());
			stmt.setString(6, questao.getAltE());
			stmt.setString(7, questao.getGabarito());
			stmt.setInt(8, questao.getId_questao());

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar questão: " + e.getMessage());
		}
	}

	@Override
	public void removerQuestao(int id) throws DAOException {
		try {
			String sql = "DELETE FROM questoes WHERE id_questao = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover questão: " + e.getMessage());
		}
	}

	@Override
	public Questao buscarQuestaoPeloId(int id) throws DAOException {
		try {
			String sql = "SELECT * FROM questoes WHERE id_questao = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Questao questao = new Questao();
			if (rs.next()) {
				String pergunta = rs.getString("pergunta");
				String altA = rs.getString("altA");
				String altB = rs.getString("altB");
				String altC = rs.getString("altC");
				String altD = rs.getString("altD");
				String altE = rs.getString("altE");
				String gabarito = rs.getString("gabarito");
				String tipo = rs.getString("tipo");
				int id_atividade = rs.getInt("id_atividade");

				questao.setId_questao(id);
				questao.setPergunta(pergunta);
				questao.setAltA(altA);
				questao.setAltB(altB);
				questao.setAltC(altC);
				questao.setAltD(altD);
				questao.setAltE(altE);
				questao.setGabarito(gabarito);
				questao.setTipo(tipo);
				questao.setId_atividade(id_atividade);
			}
			rs.close();
			stmt.close();
			return questao;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar questão pelo id: " + e.getMessage());
		}
	}
}