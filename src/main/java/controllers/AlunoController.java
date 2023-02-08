package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AlunoDAO;
import dao.AlunoDAOImpl;
import dao.AtividadeDAO;
import dao.AtividadeDAOImpl;
import dao.AulaDAO;
import dao.AulaDAOImpl;
import dao.ComentarioDAO;
import dao.ComentarioDAOImpl;
import dao.DAOException;
import dao.ProfessorDAO;
import dao.ProfessorDAOImpl;
import dao.RespostaDAO;
import dao.RespostaDAOImpl;
import dao.SalaDAO;
import dao.SalaDAOImpl;
import models.Aluno;
import models.Atividade;
import models.Aula;
import models.Comentario;
import models.Professor;
import models.Questao;
import models.Resposta;
import models.Sala;
import models.Subcomentario;

@WebServlet("/aluno")
public class AlunoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlunoController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null || action.equals("login")) {
			try {
				login(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("cadastrarConta")) {
			try {
				cadastrarConta(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("perfil")) {
			try {
				perfil(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarConta")) {
			try {
				editarConta(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarSenha")) {
			try {
				editarSenha(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerConta")) {
			try {
				removerConta(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("index")) {
			try {
				index(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("sala")) {
			try {
				sala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("entrarNovaSala")) {
			try {
				entrarNovaSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("cancelarEntradaSala")) {
			try {
				cancelarEntradaSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("aula")) {
			try {
				aula(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("comentar")) {
			try {
				comentar(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarComentario")) {
			try {
				editarComentario(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerComentario")) {
			try {
				removerComentario(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("subcomentar")) {
			try {
				subcomentar(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarSubcomentario")) {
			try {
				editarSubcomentario(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerSubcomentario")) {
			try {
				removerSubcomentario(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("atividade")) {
			try {
				atividade(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("responderAtividade")) {
			try {
				responderAtividade(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("logout")) {
			try {
				logout(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		AlunoDAO dao_aluno = new AlunoDAOImpl();
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		HttpSession session = request.getSession();
		Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
		Professor professorLogado = (Professor) session.getAttribute("professorLogado");

		if (alunoLogado != null) {
			response.sendRedirect("aluno?action=index");
		} else if (professorLogado != null) {
			response.sendRedirect("professor?action=index");
		} else if (email != null && senha != null) {
			if (dao_aluno.login(email, senha) == true) {
				alunoLogado = dao_aluno.buscarAlunoPorEmail(email);
				session = request.getSession(true);
				session.setAttribute("alunoLogado", alunoLogado);
				request.setAttribute("aluno", alunoLogado);

				response.sendRedirect("aluno?action=index");
			} else {
				request.setAttribute("ok", false);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/login.jsp");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/login.jsp");
			rd.forward(request, response);
		}
	}

	private void cadastrarConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		AlunoDAO dao_aluno = new AlunoDAOImpl();

		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");

		if (email != null && nome != null && senha != null) {
			if (dao_aluno.buscarAlunoPorEmail(email) == null) {
				Aluno aluno = new Aluno(email, nome, senha, null, 0, null);
				dao_aluno.cadastrarAluno(aluno);
				request.setAttribute("ok", true);
			} else {
				request.setAttribute("ok", false);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/cadastrar.jsp");
		rd.forward(request, response);
	}

	private void perfil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		AlunoDAO dao_aluno = new AlunoDAOImpl();
		SalaDAO dao_sala = new SalaDAOImpl();
		RespostaDAO dao_resposta = new RespostaDAOImpl();
		HttpSession session = request.getSession(true);
		Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");

		int num_salas = dao_aluno.quantidadeSalasAtivas(alunoLogado.getEmail());
		int num_aulas = dao_aluno.quantidadeAulas(alunoLogado.getEmail());
		int num_atividades = dao_aluno.quantidadeAtividades(alunoLogado.getEmail());
		
		int num_atividades_respondidas = 0;
		List<Sala> salas = dao_aluno.salasAluno(alunoLogado.getEmail());
		for(Sala sala: salas) {
			List<Atividade> atividades = dao_sala.atividadesSala(sala.getId());
			for(Atividade atividade: atividades) {
				if(dao_resposta.verificarAtividadeRespondida(atividade.getId_atividade(), alunoLogado.getEmail())) {
					num_atividades_respondidas++;
				}
			}
		}
		
		request.setAttribute("num_salas", num_salas);
		request.setAttribute("num_aulas", num_aulas);
		request.setAttribute("num_atividades", num_atividades);
		request.setAttribute("num_atividades_respondidas", num_atividades_respondidas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/perfil.jsp");
		rd.forward(request, response);
	}

	private void editarConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String nome = request.getParameter("nome");

		if (nome != null) {
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			HttpSession session = request.getSession(true);
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			alunoLogado.setNome(nome);
			dao_aluno.atualizarAluno(alunoLogado);
			request.setAttribute("editar", true);
		}
		response.sendRedirect("aluno?action=perfil");
	}

	private void editarSenha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String senha = request.getParameter("senha");

		if (senha != null) {
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			HttpSession session = request.getSession(true);
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			alunoLogado.setSenha(senha);
			dao_aluno.atualizarAluno(alunoLogado);
			request.setAttribute("senha", true);
		}
		response.sendRedirect("aluno?action=perfil");
	}

	private void removerConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		HttpSession session = request.getSession(true);
		Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");

		AlunoDAO dao_aluno = new AlunoDAOImpl();
		dao_aluno.removerAluno(alunoLogado.getEmail());

		if (session.getAttribute("alunoLogado") != null) {
			session.invalidate();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/login.jsp");
		rd.forward(request, response);
	}

	private void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		HttpSession session = request.getSession();
		Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");

		AlunoDAO dao_aluno = new AlunoDAOImpl();
		List<Sala> salas = dao_aluno.salasAluno(alunoLogado.getEmail());
		request.setAttribute("salas", salas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/index.jsp");
		rd.forward(request, response);
	}

	private void sala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();

			if (dao_aluno.verificarAlunoSala(Integer.parseInt(id), alunoLogado.getEmail()) != null) {
				SalaDAO dao_sala = new SalaDAOImpl();
				Sala sala = dao_sala.buscarSalaPeloId(Integer.parseInt(id));
				sala.setSituacao(dao_aluno.verificarAlunoSala(Integer.parseInt(id), alunoLogado.getEmail()));
				List<Aula> aulas = dao_sala.aulasSala(Integer.parseInt(id));
				List<Atividade> atividades = dao_sala.atividadesSala(Integer.parseInt(id));

				request.setAttribute("sala", sala);
				request.setAttribute("aulas", aulas);
				request.setAttribute("atividades", atividades);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/sala.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void entrarNovaSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			SalaDAO dao_sala = new SalaDAOImpl();
			if (dao_sala.buscarSalaPeloId(Integer.parseInt(id)) != null) {
				HttpSession session = request.getSession();
				Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");

				AlunoDAO dao_aluno = new AlunoDAOImpl();
				if (dao_aluno.verificarAlunoSala(Integer.parseInt(id), alunoLogado.getEmail()) == null) {
					dao_aluno.entrarNovaSala(Integer.parseInt(id), alunoLogado.getEmail());
					response.sendRedirect("aluno?action=index");
				} else {
					response.sendRedirect("aluno?action=sala&id=" + id);
				}
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void cancelarEntradaSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");

			AlunoDAO dao_aluno = new AlunoDAOImpl();
			if (dao_aluno.verificarAlunoSala(Integer.parseInt(id), alunoLogado.getEmail()) != null) {
				dao_aluno.cancelarEntradaSala(Integer.parseInt(id), alunoLogado.getEmail());
				response.sendRedirect("aluno?action=index");
			} else {
				response.sendRedirect("aluno?action=sala&id=" + id);
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void aula(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(Integer.parseInt(id));

			if (dao_aluno.verificarAlunoSala(aula.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				SalaDAO dao_sala = new SalaDAOImpl();
				Sala sala = dao_sala.buscarSalaPeloId(aula.getId_sala());

				ProfessorDAO dao_professor = new ProfessorDAOImpl();
				ComentarioDAO dao_comentario = new ComentarioDAOImpl();
				List<Comentario> comentarios = dao_comentario.comentariosSala(Integer.parseInt(id));
				for (Comentario comentario : comentarios) {
					if (comentario.getTipo().equals("Aluno")) {
						Aluno aluno = dao_aluno.buscarAlunoPorEmail(comentario.getEmail());
						comentario.setNome(aluno.getNome());
					} else {
						Professor professor = dao_professor.buscarProfessorPorEmail(comentario.getEmail());
						comentario.setNome(professor.getNome());
					}
					comentario
							.setSubcomentarios(dao_comentario.subcomentariosComentario(comentario.getId_comentario()));
					for (Subcomentario subcomentario : comentario.getSubcomentarios()) {
						if (subcomentario.getTipo().equals("Aluno")) {
							Aluno aluno = dao_aluno.buscarAlunoPorEmail(comentario.getEmail());
							subcomentario.setNome(aluno.getNome());
						} else {
							Professor professor = dao_professor.buscarProfessorPorEmail(comentario.getEmail());
							subcomentario.setNome(professor.getNome());
						}
					}
				}

				request.setAttribute("sala", sala);
				request.setAttribute("aula", aula);
				request.setAttribute("comentarios", comentarios);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/aula.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void comentar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("comentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(Integer.parseInt(id));

			if (dao_aluno.verificarAlunoSala(aula.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				ComentarioDAO dao_comentario = new ComentarioDAOImpl();
				Comentario comentario = new Comentario(0, texto, null, null, alunoLogado.getEmail(), "Aluno",
						Integer.parseInt(id), null);
				dao_comentario.cadastrarComentario(comentario);
				response.sendRedirect("aluno?action=aula&id=" + id);
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void editarComentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("comentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Comentario comentario = dao_comentario.buscarComentarioPeloId(Integer.parseInt(id));
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_aluno.verificarAlunoSala(aula.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				if (comentario.getEmail().equals(alunoLogado.getEmail())) {
					comentario.setComentario(texto);
					dao_comentario.atualizarComentario(comentario);
					response.sendRedirect("aluno?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("aluno?action=index");
				}
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void removerComentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Comentario comentario = dao_comentario.buscarComentarioPeloId(Integer.parseInt(id));
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_aluno.verificarAlunoSala(aula.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				if (comentario.getEmail().equals(alunoLogado.getEmail())) {
					dao_comentario.removerComentario(comentario.getId_comentario());
					response.sendRedirect("aluno?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("aluno?action=index");
				}
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void subcomentar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("subcomentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Comentario comentario = dao_comentario.buscarComentarioPeloId(Integer.parseInt(id));
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_aluno.verificarAlunoSala(aula.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				Subcomentario subcomentario = new Subcomentario(0, texto, null, null, alunoLogado.getEmail(), "Aluno",
						Integer.parseInt(id));
				dao_comentario.cadastrarSubcomentario(subcomentario);
				response.sendRedirect("aluno?action=aula&id=" + comentario.getId_aula());
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void editarSubcomentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("subcomentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Subcomentario subcomentario = dao_comentario.buscarSubcomentarioPeloId(Integer.parseInt(id));
			Comentario comentario = dao_comentario.buscarComentarioPeloId(subcomentario.getId_comentario());
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_aluno.verificarAlunoSala(aula.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				if (subcomentario.getEmail().equals(alunoLogado.getEmail())) {
					subcomentario.setSubcomentario(texto);
					dao_comentario.atualizarSubcomentario(subcomentario);
					response.sendRedirect("aluno?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("aluno?action=index");
				}
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void removerSubcomentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Subcomentario subcomentario = dao_comentario.buscarSubcomentarioPeloId(Integer.parseInt(id));
			Comentario comentario = dao_comentario.buscarComentarioPeloId(subcomentario.getId_comentario());
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_aluno.verificarAlunoSala(aula.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				if (subcomentario.getEmail().equals(alunoLogado.getEmail())) {
					dao_comentario.removerSubcomentario(subcomentario.getId_subcomentario());
					response.sendRedirect("aluno?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("aluno?action=index");
				}
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void atividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));

			if (dao_aluno.verificarAlunoSala(atividade.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				RespostaDAO dao_resposta = new RespostaDAOImpl();

				List<Questao> questoes = dao_atividade.questoesAtividade(Integer.parseInt(id));
				int ordem = 1;
				for (Questao questao : questoes) {
					questao.setOrdem(ordem);
					ordem++;
				}

				SalaDAO dao_sala = new SalaDAOImpl();
				Sala sala = dao_sala.buscarSalaPeloId(atividade.getId_sala());

				request.setAttribute("sala", sala);
				request.setAttribute("atividade", atividade);
				request.setAttribute("questoes", questoes);

				if (dao_resposta.verificarAtividadeRespondida(Integer.parseInt(id), alunoLogado.getEmail()) == false) {
					request.setAttribute("realizado", false);
				} else {
					for (Questao questao : questoes) {
						Resposta resposta = dao_resposta.buscarRespostaPeloIdQuestaoEmail(questao.getId_questao(),
								alunoLogado.getEmail());
						questao.setResposta(resposta);
					}
					int discursivas = dao_resposta.quantidadeQuestoesDiscursivas(Integer.parseInt(id));
					int discursivas_acertadas = dao_resposta
							.quantidadeQuestoesDiscursivasAcertadas(Integer.parseInt(id), alunoLogado.getEmail());
					int objetivas = dao_resposta.quantidadeQuestoesObjetivas(Integer.parseInt(id));
					int objetivas_acertadas = dao_resposta.quantidadeQuestoesObjetivasAcertadas(Integer.parseInt(id),
							alunoLogado.getEmail());

					float nota = 0;
					if ((discursivas + objetivas) == 0) {
						nota = 0;
					} else {
						nota = ((discursivas_acertadas + objetivas_acertadas) * 100) / (discursivas + objetivas);
					}
					request.setAttribute("nota", nota);
					request.setAttribute("realizado", true);
				}

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/atividade.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else

		{
			response.sendRedirect("aluno?action=index");
		}
	}

	private void responderAtividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));

			if (dao_aluno.verificarAlunoSala(atividade.getId_sala(), alunoLogado.getEmail()).equals("Ativo")) {
				RespostaDAO dao_resposta = new RespostaDAOImpl();
				List<Questao> questoes = dao_atividade.questoesAtividade(Integer.parseInt(id));

				if (dao_resposta.verificarAtividadeRespondida(Integer.parseInt(id), alunoLogado.getEmail()) == false) {
					int ordem = 1;
					for (Questao questao : questoes) {
						String resp = request.getParameter("resposta" + ordem);
						Resposta resposta = new Resposta(0, resp, null, null, alunoLogado.getEmail(),
								questao.getId_questao());
						if (questao.getTipo().equals("Discursiva")) {
							resposta.setCorrecao("Pendente");
						}
						dao_resposta.cadastrarResposta(resposta);
						ordem++;
					}

					response.sendRedirect("aluno?action=atividade&id=" + id);
				} else {
					response.sendRedirect("aluno?action=index");
				}
			} else {
				response.sendRedirect("aluno?action=index");
			}
		} else {
			response.sendRedirect("aluno?action=index");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("alunoLogado") != null) {
			session.invalidate();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/login.jsp");
		rd.forward(request, response);
	}
}