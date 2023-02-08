package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Aula;
import util.ConnectionFactory;

public class AulaDAOImpl implements AulaDAO {

	private Connection connection;

	public AulaDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public int cadastrarAula(Aula aula) throws DAOException {
		try {
			String sql = "INSERT INTO aulas (titulo, descricao, video, data_inicio, data_fim, id_sala) VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, aula.getTitulo());
			stmt.setString(2, aula.getDescricao());
			stmt.setString(3, aula.getVideo());
			stmt.setDate(4, new java.sql.Date(aula.getData_inicio().getTime()));
			stmt.setDate(5, new java.sql.Date(aula.getData_fim().getTime()));
			stmt.setInt(6, aula.getId_sala());

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
			throw new DAOException("Erro ao cadastrar aula: " + e.getMessage());
		}

	}

	@Override
	public void atualizarAula(Aula aula) throws DAOException {
		try {
			String sql = "UPDATE aulas SET titulo = ?, descricao = ?, video = ?, data_inicio = ?, data_fim = ? WHERE id_aula = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, aula.getTitulo());
			stmt.setString(2, aula.getDescricao());
			stmt.setString(3, aula.getVideo());
			stmt.setDate(4, new java.sql.Date(aula.getData_inicio().getTime()));
			stmt.setDate(5, new java.sql.Date(aula.getData_fim().getTime()));
			stmt.setInt(6, aula.getId_aula());

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar aula: " + e.getMessage());
		}
	}

	@Override
	public void removerAula(int id) throws DAOException {
		try {
			String sql = "DELETE FROM aulas WHERE id_aula = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover aula: " + e.getMessage());
		}
	}

	@Override
	public Aula buscarAulaPeloId(int id) throws DAOException {
		try {
			String sql = "SELECT * FROM aulas WHERE id_aula = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Aula aula = new Aula();
			if (rs.next()) {
				String titulo = rs.getString("titulo");
				String descricao = rs.getString("descricao");
				String video = rs.getString("video");
				Date data_inicio = rs.getDate("data_inicio");
				Date data_fim = rs.getDate("data_fim");
				int id_sala = rs.getInt("id_sala");

				aula.setId_aula(id);
				aula.setTitulo(titulo);
				aula.setDescricao(descricao);
				aula.setVideo(video);
				aula.setData_inicio(data_inicio);
				aula.setData_fim(data_fim);
				aula.setId_sala(id_sala);

			}
			rs.close();
			stmt.close();
			return aula;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar aula pelo id: " + e.getMessage());
		}
	}
}