package dao;

import java.util.List;

import models.Comentario;
import models.Subcomentario;

public interface ComentarioDAO {
	public void cadastrarComentario(Comentario comentario) throws DAOException;

	public void atualizarComentario(Comentario comentario) throws DAOException;

	public void removerComentario(int id) throws DAOException;
	
	public Comentario buscarComentarioPeloId(int id) throws DAOException;

	public List<Comentario> comentariosSala(int id) throws DAOException;

	public void cadastrarSubcomentario(Subcomentario subcomentario) throws DAOException;

	public void atualizarSubcomentario(Subcomentario subcomentario) throws DAOException;

	public void removerSubcomentario(int id) throws DAOException;
	
	public Subcomentario buscarSubcomentarioPeloId(int id) throws DAOException;

	public List<Subcomentario> subcomentariosComentario(int id) throws DAOException;
}