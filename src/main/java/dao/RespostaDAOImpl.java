package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Resposta;
import util.ConnectionFactory;

public class RespostaDAOImpl implements RespostaDAO {

	private Connection connection;

	public RespostaDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public void cadastrarResposta(Resposta resposta) throws DAOException {
		try {
			String sql = "INSERT INTO respostas (resposta, correcao, email, id_questao) VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, resposta.getResposta());
			stmt.setString(2, resposta.getCorrecao());
			stmt.setString(3, resposta.getEmail());
			stmt.setInt(4, resposta.getId_questao());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao cadastrar resposta: " + e.getMessage());
		}

	}

	@Override
	public void atualizarResposta(Resposta resposta) throws DAOException {
		try {
			String sql = "UPDATE respostas SET correcao = ? WHERE id_resposta = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, resposta.getCorrecao());
			stmt.setInt(2, resposta.getId_resposta());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar resposta: " + e.getMessage());
		}
	}

	@Override
	public Resposta buscarRespostaPeloIdQuestaoEmail(int id, String email) throws DAOException {
		try {
			String sql = "SELECT * FROM respostas WHERE id_questao = ? AND email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			ResultSet rs = stmt.executeQuery();

			Resposta resposta = new Resposta();
			if (rs.next()) {
				int id_resposta = rs.getInt("id_resposta");
				String resp = rs.getString("resposta");
				String correcao = rs.getString("correcao");

				resposta.setId_resposta(id_resposta);
				resposta.setResposta(resp);
				resposta.setCorrecao(correcao);
				resposta.setEmail(email);
				resposta.setId_questao(id);
			}
			rs.close();
			stmt.close();
			return resposta;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar resposta pelo id da questão e pelo email: " + e.getMessage());
		}
	}

	@Override
	public boolean verificarAtividadeRespondida(int id, String email) throws DAOException {
		try {
			String sql = "SELECT * FROM respostas, questoes WHERE respostas.id_questao = questoes.id_questao AND questoes.id_atividade = ? AND respostas.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
			return false;
		} catch (Exception e) {
			throw new DAOException("Erro ao verificar se atividade foi respondida: " + e.getMessage());
		}
	}

	@Override
	public boolean verificarAtividadeDiscursivaPendente(int id, String email) throws DAOException {
		try {
			String sql = "SELECT * FROM respostas, questoes WHERE respostas.id_questao = questoes.id_questao AND questoes.tipo = 'Discursiva' AND respostas.correcao = 'Pendente' AND questoes.id_atividade = ? AND respostas.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
			return false;
		} catch (Exception e) {
			throw new DAOException(
					"Erro ao verificar se há atividade discursiva pendente de correção: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeQuestoesObjetivas(int id) throws DAOException {
		try {
			String sql = "SELECT COUNT(*) FROM questoes WHERE questoes.tipo = 'Objetiva' AND questoes.id_atividade = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("COUNT(*)");
			}
			rs.close();
			stmt.close();
			return 0;
		} catch (Exception e) {
			throw new DAOException("Erro ao verificar quantidade de questões objetivas: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeQuestoesObjetivasAcertadas(int id, String email) throws DAOException {
		try {
			String sql = "SELECT COUNT(*) FROM respostas, questoes WHERE questoes.tipo = 'Objetiva' AND respostas.id_questao = questoes.id_questao AND respostas.resposta = questoes.gabarito AND questoes.id_atividade = ? AND respostas.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("COUNT(*)");
			}

			rs.close();
			stmt.close();
			return 0;
		} catch (Exception e) {
			throw new DAOException("Erro ao verificar quantidade de questões objetivas acertadas: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeQuestoesDiscursivas(int id) throws DAOException {
		try {
			String sql = "SELECT COUNT(*) FROM questoes WHERE questoes.tipo = 'Discursiva' AND questoes.id_atividade = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("COUNT(*)");
			}
			rs.close();
			stmt.close();
			return 0;
		} catch (Exception e) {
			throw new DAOException("Erro ao verificar quantidade de questões discursivas: " + e.getMessage());
		}
	}

	@Override
	public int quantidadeQuestoesDiscursivasAcertadas(int id, String email) throws DAOException {
		try {
			String sql = "SELECT COUNT(*) FROM respostas, questoes WHERE questoes.tipo = 'Discursiva' AND respostas.id_questao = questoes.id_questao AND respostas.correcao = 'Correto' AND questoes.id_atividade = ? AND respostas.email = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("COUNT(*)");
			}

			rs.close();
			stmt.close();
			return 0;
		} catch (Exception e) {
			throw new DAOException("Erro ao verificar quantidade de questões discursivas acertadas: " + e.getMessage());
		}
	}
}