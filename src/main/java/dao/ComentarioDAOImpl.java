package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Comentario;
import models.Subcomentario;
import util.ConnectionFactory;

public class ComentarioDAOImpl implements ComentarioDAO {

	private Connection connection;

	public ComentarioDAOImpl() throws DAOException {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new DAOException("Erro na conexão: " + e.getMessage());
		}
	}

	@Override
	public void cadastrarComentario(Comentario comentario) throws DAOException {
		try {
			String sql = "INSERT INTO comentarios (comentario, tipo, email, id_aula) VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, comentario.getComentario());
			stmt.setString(2, comentario.getTipo());
			stmt.setString(3, comentario.getEmail());
			stmt.setInt(4, comentario.getId_aula());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao cadastrar comentário: " + e.getMessage());
		}

	}

	@Override
	public void atualizarComentario(Comentario comentario) throws DAOException {
		try {
			String sql = "UPDATE comentarios SET comentario = ? WHERE id_comentario = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, comentario.getComentario());
			stmt.setInt(2, comentario.getId_comentario());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar comentário: " + e.getMessage());
		}
	}

	@Override
	public void removerComentario(int id) throws DAOException {
		try {
			String sql = "DELETE FROM comentarios WHERE id_comentario = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover comentário: " + e.getMessage());
		}
	}

	@Override
	public Comentario buscarComentarioPeloId(int id) throws DAOException {
		try {
			String sql = "SELECT * FROM comentarios WHERE id_comentario = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Comentario comentario = new Comentario();
			if (rs.next()) {
				String texto = rs.getString("comentario");
				Date cadastro = rs.getDate("cadastro");
				String email = rs.getString("email");
				String tipo = rs.getString("tipo");
				int id_aula = rs.getInt("id_aula");

				comentario.setId_comentario(id);
				comentario.setComentario(texto);
				comentario.setCadastro(cadastro);
				comentario.setEmail(email);
				comentario.setTipo(tipo);
				comentario.setId_aula(id_aula);
			}
			rs.close();
			stmt.close();
			return comentario;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar comentário pelo id: " + e.getMessage());
		}
	}

	@Override
	public List<Comentario> comentariosSala(int id) throws DAOException {
		List<Comentario> comentarios = new ArrayList<Comentario>();
		try {
			String sql = "SELECT * FROM comentarios WHERE comentarios.id_aula = ? ORDER BY comentarios.cadastro DESC;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id_comentario = rs.getInt("id_comentario");
				String texto = rs.getString("comentario");
				Date cadastro = rs.getDate("cadastro");
				String email = rs.getString("email");
				String tipo = rs.getString("tipo");

				Comentario comentario = new Comentario();
				comentario.setId_comentario(id_comentario);
				comentario.setComentario(texto);
				comentario.setCadastro(cadastro);
				comentario.setEmail(email);
				comentario.setTipo(tipo);
				comentario.setId_aula(id);

				comentarios.add(comentario);
			}
			rs.close();
			stmt.close();
			return comentarios;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar comentários de uma sala: " + e.getMessage());
		}
	}

	@Override
	public void cadastrarSubcomentario(Subcomentario subcomentario) throws DAOException {
		try {
			String sql = "INSERT INTO subcomentarios (subcomentario, tipo, email, id_comentario) VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, subcomentario.getSubcomentario());
			stmt.setString(2, subcomentario.getTipo());
			stmt.setString(3, subcomentario.getEmail());
			stmt.setInt(4, subcomentario.getId_comentario());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao cadastrar subcomentário: " + e.getMessage());
		}

	}

	@Override
	public void atualizarSubcomentario(Subcomentario subcomentario) throws DAOException {
		try {
			String sql = "UPDATE subcomentarios SET subcomentario = ? WHERE id_subcomentario = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, subcomentario.getSubcomentario());
			stmt.setInt(2, subcomentario.getId_subcomentario());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao atualizar subcomentário: " + e.getMessage());
		}
	}

	@Override
	public void removerSubcomentario(int id) throws DAOException {
		try {
			String sql = "DELETE FROM subcomentarios WHERE id_subcomentario = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DAOException("Erro ao remover subcomentário: " + e.getMessage());
		}
	}

	@Override
	public Subcomentario buscarSubcomentarioPeloId(int id) throws DAOException {
		try {
			String sql = "SELECT * FROM subcomentarios WHERE id_subcomentario = ?;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Subcomentario subcomentario = new Subcomentario();
			if (rs.next()) {
				String texto = rs.getString("subcomentario");
				Date cadastro = rs.getDate("cadastro");
				String email = rs.getString("email");
				String tipo = rs.getString("tipo");
				int id_comentario = rs.getInt("id_comentario");

				subcomentario.setId_subcomentario(id);
				subcomentario.setSubcomentario(texto);
				subcomentario.setCadastro(cadastro);
				subcomentario.setEmail(email);
				subcomentario.setTipo(tipo);
				subcomentario.setId_comentario(id_comentario);
			}
			rs.close();
			stmt.close();
			return subcomentario;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar subcomentário pelo id: " + e.getMessage());
		}
	}

	@Override
	public List<Subcomentario> subcomentariosComentario(int id) throws DAOException {
		List<Subcomentario> subcomentarios = new ArrayList<Subcomentario>();
		try {
			String sql = "SELECT * FROM subcomentarios, alunos WHERE subcomentarios.email = alunos.email AND subcomentarios.id_comentario = ? ORDER BY subcomentarios.cadastro DESC;";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id_subcomentario = rs.getInt("id_subcomentario");
				String texto = rs.getString("subcomentario");
				Date cadastro = rs.getDate("cadastro");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String tipo = rs.getString("tipo");

				Subcomentario subcomentario = new Subcomentario();
				subcomentario.setId_subcomentario(id_subcomentario);
				subcomentario.setSubcomentario(texto);
				subcomentario.setCadastro(cadastro);
				subcomentario.setNome(nome);
				subcomentario.setEmail(email);
				subcomentario.setTipo(tipo);
				subcomentario.setId_comentario(id);

				subcomentarios.add(subcomentario);
			}
			rs.close();
			stmt.close();
			return subcomentarios;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar subcomentários de um comentário: " + e.getMessage());
		}
	}
}