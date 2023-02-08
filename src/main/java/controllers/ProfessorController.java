package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProfessorDAO;
import dao.ProfessorDAOImpl;
import dao.QuestaoDAO;
import dao.QuestaoDAOImpl;
import dao.RespostaDAO;
import dao.RespostaDAOImpl;
import dao.SalaDAO;
import dao.SalaDAOImpl;
import dao.AlunoDAO;
import dao.AlunoDAOImpl;
import dao.AtividadeDAO;
import dao.AtividadeDAOImpl;
import dao.AulaDAO;
import dao.AulaDAOImpl;
import dao.ComentarioDAO;
import dao.ComentarioDAOImpl;
import dao.DAOException;
import models.Aluno;
import models.Atividade;
import models.Aula;
import models.Comentario;
import models.Professor;
import models.Questao;
import models.Resposta;
import models.Sala;
import models.Subcomentario;

@WebServlet("/professor")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfessorController() {
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
		} else if (action.equals("aceitarAlunoSala")) {
			try {
				aceitarAlunoSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerAlunoSala")) {
			try {
				removerAlunoSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("aceitarProfessorSala")) {
			try {
				aceitarProfessorSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerProfessorSala")) {
			try {
				removerProfessorSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("criarNovaSala")) {
			try {
				criarNovaSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarSala")) {
			try {
				editarSala(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerSala")) {
			try {
				removerSala(request, response);
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
		} else if (action.equals("criarNovaAula")) {
			try {
				criarNovaAula(request, response);
			} catch (ServletException | IOException | DAOException | ParseException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarAula")) {
			try {
				editarAula(request, response);
			} catch (ServletException | IOException | DAOException | ParseException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerAula")) {
			try {
				removerAula(request, response);
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
		} else if (action.equals("criarNovaAtividade")) {
			try {
				criarNovaAtividade(request, response);
			} catch (ServletException | IOException | DAOException | ParseException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarAtividade")) {
			try {
				editarAtividade(request, response);
			} catch (ServletException | IOException | DAOException | ParseException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerAtividade")) {
			try {
				removerAtividade(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("criarNovaQuestaoObjetiva")) {
			try {
				criarNovaQuestaoObjetiva(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("criarNovaQuestaoDiscursiva")) {
			try {
				criarNovaQuestaoDiscursiva(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarQuestaoObjetiva")) {
			try {
				editarQuestaoObjetiva(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("editarQuestaoDiscursiva")) {
			try {
				editarQuestaoDiscursiva(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("removerQuestao")) {
			try {
				removerQuestao(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("resposta")) {
			try {
				resposta(request, response);
			} catch (ServletException | IOException | DAOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("corrigirAtividade")) {
			try {
				corrigirAtividade(request, response);
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
		ProfessorDAO dao_professor = new ProfessorDAOImpl();
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		HttpSession session = request.getSession();
		Professor professorLogado = (Professor) session.getAttribute("professorLogado");
		Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");

		if (professorLogado != null) {
			response.sendRedirect("professor?action=index");
		} else if (alunoLogado != null) {
			response.sendRedirect("aluno?action=index");
		} else if (email != null && senha != null) {
			if (dao_professor.login(email, senha) == true) {
				professorLogado = dao_professor.buscarProfessorPorEmail(email);
				session = request.getSession(true);
				session.setAttribute("professorLogado", professorLogado);
				request.setAttribute("professor", professorLogado);

				response.sendRedirect("professor?action=index");
			} else {
				request.setAttribute("ok", false);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/login.jsp");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/login.jsp");
			rd.forward(request, response);
		}
	}

	private void cadastrarConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		ProfessorDAO dao_professor = new ProfessorDAOImpl();

		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");

		if (email != null && nome != null && senha != null) {
			if (dao_professor.buscarProfessorPorEmail(email) == null) {
				Professor professor = new Professor(email, nome, senha, null, null);
				dao_professor.cadastrarProfessor(professor);
				request.setAttribute("ok", true);
			} else {
				request.setAttribute("ok", false);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/cadastrar.jsp");
		rd.forward(request, response);
	}

	private void perfil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		ProfessorDAO dao_professor = new ProfessorDAOImpl();
		HttpSession session = request.getSession(true);
		Professor professorLogado = (Professor) session.getAttribute("professorLogado");

		int num_salas = dao_professor.quantidadeSalasAtivas(professorLogado.getEmail());
		int num_alunos = dao_professor.quantidadeAlunosSalas(professorLogado.getEmail());
		int num_aulas = dao_professor.quantidadeAulas(professorLogado.getEmail());
		int num_atividades = dao_professor.quantidadeAtividades(professorLogado.getEmail());
		request.setAttribute("num_salas", num_salas);
		request.setAttribute("num_alunos", num_alunos);
		request.setAttribute("num_aulas", num_aulas);
		request.setAttribute("num_atividades", num_atividades);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/perfil.jsp");
		rd.forward(request, response);
	}

	private void editarConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String nome = request.getParameter("nome");

		if (nome != null) {
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			HttpSession session = request.getSession(true);
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			professorLogado.setNome(nome);
			dao_professor.atualizarProfessor(professorLogado);
			request.setAttribute("editar", true);
		}
		response.sendRedirect("professor?action=perfil");
	}

	private void editarSenha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String senha = request.getParameter("senha");

		if (senha != null) {
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			HttpSession session = request.getSession(true);
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			professorLogado.setSenha(senha);
			dao_professor.atualizarProfessor(professorLogado);
			request.setAttribute("senha", true);
		}
		response.sendRedirect("professor?action=perfil");
	}

	private void removerConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		HttpSession session = request.getSession(true);
		Professor professorLogado = (Professor) session.getAttribute("professorLogado");

		ProfessorDAO dao_professor = new ProfessorDAOImpl();
		dao_professor.removerProfessor(professorLogado.getEmail());

		if (session.getAttribute("professorLogado") != null) {
			session.invalidate();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/login.jsp");
		rd.forward(request, response);
	}

	private void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		HttpSession session = request.getSession();
		Professor professorLogado = (Professor) session.getAttribute("professorLogado");

		ProfessorDAO dao_professor = new ProfessorDAOImpl();
		List<Sala> salas = dao_professor.salasProfessor(professorLogado.getEmail());
		request.setAttribute("salas", salas);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/index.jsp");
		rd.forward(request, response);
	}

	private void sala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();

			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()) != null) {
				SalaDAO dao_sala = new SalaDAOImpl();
				Sala sala = dao_sala.buscarSalaPeloId(Integer.parseInt(id));
				sala.setSituacao(
						dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()));
				List<Aula> aulas = dao_sala.aulasSala(Integer.parseInt(id));
				List<Atividade> atividades = dao_sala.atividadesSala(Integer.parseInt(id));
				List<Aluno> alunos = dao_sala.alunosSala(Integer.parseInt(id));
				List<Professor> professores = dao_sala.professoresSala(Integer.parseInt(id));

				request.setAttribute("sala", sala);
				request.setAttribute("aulas", aulas);
				request.setAttribute("atividades", atividades);
				request.setAttribute("alunos", alunos);
				request.setAttribute("professores", professores);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/sala.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void criarNovaSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String materia = request.getParameter("materia");
		String cor = request.getParameter("cor");
		String cor_back = request.getParameter("cor_back");

		if (titulo != null && descricao != null && materia != null && cor != null && cor_back != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");

			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			SalaDAO dao_sala = new SalaDAOImpl();

			Sala sala = new Sala(0, titulo, descricao, materia, cor, cor_back, null, null);
			int id = dao_sala.cadastrarSala(sala);
			dao_professor.entrarNovaSala(id, professorLogado.getEmail(), "Admin");
			response.sendRedirect("professor?action=sala&id=" + id);
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void editarSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String materia = request.getParameter("materia");
		String cor = request.getParameter("cor");
		String cor_back = request.getParameter("cor_back");

		if (id != null && titulo != null && descricao != null && materia != null && cor != null && cor_back != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");

			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				SalaDAO dao_sala = new SalaDAOImpl();
				Sala sala = new Sala(Integer.parseInt(id), titulo, descricao, materia, cor, cor_back, null, null);
				dao_sala.atualizarSala(sala);
				response.sendRedirect("professor?action=sala&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");

			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				SalaDAO dao_sala = new SalaDAOImpl();
				dao_sala.removerSala(Integer.parseInt(id));
				response.sendRedirect("professor?action=index");
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void entrarNovaSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			SalaDAO dao_sala = new SalaDAOImpl();
			if (dao_sala.buscarSalaPeloId(Integer.parseInt(id)) != null) {
				HttpSession session = request.getSession();
				Professor professorLogado = (Professor) session.getAttribute("professorLogado");

				ProfessorDAO dao_professor = new ProfessorDAOImpl();
				if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()) == null) {
					dao_professor.entrarNovaSala(Integer.parseInt(id), professorLogado.getEmail(), "Pendente");
					response.sendRedirect("professor?action=index");
				} else {
					response.sendRedirect("professor?action=sala&id=" + id);
				}
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void cancelarEntradaSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");

			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()) != null) {
				dao_professor.cancelarEntradaSala(Integer.parseInt(id), professorLogado.getEmail());
				response.sendRedirect("professor?action=index");
			} else {
				response.sendRedirect("professor?action=sala&id=" + id);
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void aula(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				SalaDAO dao_sala = new SalaDAOImpl();
				Sala sala = dao_sala.buscarSalaPeloId(aula.getId_sala());

				AlunoDAO dao_aluno = new AlunoDAOImpl();
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

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/aula.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void aceitarAlunoSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		if (id != null && email != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();

			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				AlunoDAO dao_aluno = new AlunoDAOImpl();
				dao_aluno.atualizarEntradaSala(Integer.parseInt(id), email, "Ativo");

				response.sendRedirect("professor?action=sala&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerAlunoSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		if (id != null && email != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();

			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				AlunoDAO dao_aluno = new AlunoDAOImpl();
				dao_aluno.atualizarEntradaSala(Integer.parseInt(id), email, "Removido");

				response.sendRedirect("professor?action=sala&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void aceitarProfessorSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		if (id != null && email != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();

			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				dao_professor.atualizarEntradaSala(Integer.parseInt(id), email, "Ativo");

				response.sendRedirect("professor?action=sala&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerProfessorSala(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		if (id != null && email != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();

			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				dao_professor.atualizarEntradaSala(Integer.parseInt(id), email, "Removido");

				response.sendRedirect("professor?action=sala&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void criarNovaAula(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException, ParseException {
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String video = request.getParameter("video");
		String data_inicio = request.getParameter("data_inicio");
		String data_fim = request.getParameter("data_fim");
		String id = request.getParameter("id");

		if (titulo != null && descricao != null && video != null && data_inicio != null && data_fim != null
				&& id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();

			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date inicio = formatter.parse(data_inicio);
				Date fim = formatter.parse(data_fim);

				AulaDAO dao_aula = new AulaDAOImpl();
				Aula aula = new Aula(0, titulo, descricao, video, inicio, fim, null, Integer.parseInt(id));
				int id_aula = dao_aula.cadastrarAula(aula);
				response.sendRedirect("professor?action=aula&id=" + id_aula);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void editarAula(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException, ParseException {
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String video = request.getParameter("video");
		String data_inicio = request.getParameter("data_inicio");
		String data_fim = request.getParameter("data_fim");
		String id = request.getParameter("id");

		if (titulo != null && descricao != null && video != null && data_inicio != null && data_fim != null
				&& id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date inicio = formatter.parse(data_inicio);
				Date fim = formatter.parse(data_fim);

				aula.setTitulo(titulo);
				aula.setDescricao(descricao);
				aula.setVideo(video);
				aula.setData_inicio(inicio);
				aula.setData_fim(fim);
				dao_aula.atualizarAula(aula);

				response.sendRedirect("professor?action=aula&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerAula(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				dao_aula.removerAula(Integer.parseInt(id));
				response.sendRedirect("professor?action=sala&id=" + aula.getId_sala());
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void comentar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("comentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				ComentarioDAO dao_comentario = new ComentarioDAOImpl();
				Comentario comentario = new Comentario(0, texto, null, null, professorLogado.getEmail(), "Professor",
						Integer.parseInt(id), null);
				dao_comentario.cadastrarComentario(comentario);
				response.sendRedirect("professor?action=aula&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void editarComentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("comentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Comentario comentario = dao_comentario.buscarComentarioPeloId(Integer.parseInt(id));
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				if (comentario.getEmail().equals(professorLogado.getEmail())) {
					comentario.setComentario(texto);
					dao_comentario.atualizarComentario(comentario);
					response.sendRedirect("professor?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("professor?action=index");
				}
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerComentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Comentario comentario = dao_comentario.buscarComentarioPeloId(Integer.parseInt(id));
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				if (comentario.getEmail().equals(professorLogado.getEmail())) {
					dao_comentario.removerComentario(comentario.getId_comentario());
					response.sendRedirect("professor?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("professor?action=index");
				}
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void subcomentar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("subcomentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Comentario comentario = dao_comentario.buscarComentarioPeloId(Integer.parseInt(id));
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				Subcomentario subcomentario = new Subcomentario(0, texto, null, null, professorLogado.getEmail(),
						"Professor", Integer.parseInt(id));
				dao_comentario.cadastrarSubcomentario(subcomentario);
				response.sendRedirect("professor?action=aula&id=" + comentario.getId_aula());
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void editarSubcomentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String texto = request.getParameter("subcomentario");
		String id = request.getParameter("id");

		if (texto != null && id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Subcomentario subcomentario = dao_comentario.buscarSubcomentarioPeloId(Integer.parseInt(id));
			Comentario comentario = dao_comentario.buscarComentarioPeloId(subcomentario.getId_comentario());
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				if (subcomentario.getEmail().equals(professorLogado.getEmail())) {
					subcomentario.setSubcomentario(texto);
					dao_comentario.atualizarSubcomentario(subcomentario);
					response.sendRedirect("professor?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("professor?action=index");
				}
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerSubcomentario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			ComentarioDAO dao_comentario = new ComentarioDAOImpl();
			Subcomentario subcomentario = dao_comentario.buscarSubcomentarioPeloId(Integer.parseInt(id));
			Comentario comentario = dao_comentario.buscarComentarioPeloId(subcomentario.getId_comentario());
			AulaDAO dao_aula = new AulaDAOImpl();
			Aula aula = dao_aula.buscarAulaPeloId(comentario.getId_aula());

			if (dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(aula.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				if (subcomentario.getEmail().equals(professorLogado.getEmail())) {
					dao_comentario.removerSubcomentario(subcomentario.getId_subcomentario());
					response.sendRedirect("professor?action=aula&id=" + comentario.getId_aula());
				} else {
					response.sendRedirect("professor?action=index");
				}
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void atividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			RespostaDAO dao_resposta = new RespostaDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				List<Questao> questoes = dao_atividade.questoesAtividade(Integer.parseInt(id));
				int ordem = 1;
				for (Questao questao : questoes) {
					questao.setOrdem(ordem);
					ordem++;
				}

				SalaDAO dao_sala = new SalaDAOImpl();
				Sala sala = dao_sala.buscarSalaPeloId(atividade.getId_sala());

				List<Aluno> alunos = dao_sala.alunosSala(atividade.getId_sala());
				for (Aluno aluno : alunos) {
					if (dao_resposta.verificarAtividadeRespondida(atividade.getId_atividade(),
							aluno.getEmail()) == true) {
						if (dao_resposta.verificarAtividadeDiscursivaPendente(atividade.getId_atividade(),
								aluno.getEmail()) == true) {
							aluno.setSituacao("Pendente");
						} else {
							aluno.setSituacao("Concluída");
						}
					} else {
						aluno.setSituacao("Não realizado");
					}
					int discursivas = dao_resposta.quantidadeQuestoesDiscursivas(Integer.parseInt(id));
					int discursivas_acertadas = dao_resposta
							.quantidadeQuestoesDiscursivasAcertadas(Integer.parseInt(id), aluno.getEmail());
					int objetivas = dao_resposta.quantidadeQuestoesObjetivas(Integer.parseInt(id));
					int objetivas_acertadas = dao_resposta.quantidadeQuestoesObjetivasAcertadas(Integer.parseInt(id),
							aluno.getEmail());

					float nota = 0;
					if ((discursivas + objetivas) == 0) {
						nota = 0;
					} else {
						nota = ((discursivas_acertadas + objetivas_acertadas) * 100) / (discursivas + objetivas);
					}

					aluno.setNota(nota);
				}

				request.setAttribute("sala", sala);
				request.setAttribute("atividade", atividade);
				request.setAttribute("questoes", questoes);
				request.setAttribute("alunos", alunos);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/atividade.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void criarNovaAtividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException, ParseException {
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String data_inicio = request.getParameter("data_inicio");
		String data_fim = request.getParameter("data_fim");
		String id = request.getParameter("id");

		if (titulo != null && descricao != null && data_inicio != null && data_fim != null && id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();

			if (dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(Integer.parseInt(id), professorLogado.getEmail())
							.equals("Admin")) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date inicio = formatter.parse(data_inicio);
				Date fim = formatter.parse(data_fim);

				AtividadeDAO dao_atividade = new AtividadeDAOImpl();
				Atividade atividade = new Atividade(0, titulo, descricao, inicio, fim, null, Integer.parseInt(id));
				int id_atividade = dao_atividade.cadastrarAtividade(atividade);
				response.sendRedirect("professor?action=atividade&id=" + id_atividade);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void editarAtividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException, ParseException {
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String data_inicio = request.getParameter("data_inicio");
		String data_fim = request.getParameter("data_fim");
		String id = request.getParameter("id");

		if (titulo != null && descricao != null && data_inicio != null && data_fim != null && id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date inicio = formatter.parse(data_inicio);
				Date fim = formatter.parse(data_fim);

				atividade.setTitulo(titulo);
				atividade.setDescricao(descricao);
				atividade.setData_inicio(inicio);
				atividade.setData_fim(fim);
				dao_atividade.atualizarAtividade(atividade);
				response.sendRedirect("professor?action=atividade&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerAtividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				dao_atividade.removerAtividade(Integer.parseInt(id));
				response.sendRedirect("professor?action=sala&id=" + atividade.getId_sala());
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void criarNovaQuestaoObjetiva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String pergunta = request.getParameter("pergunta");
		String altA = request.getParameter("altA");
		String altB = request.getParameter("altB");
		String altC = request.getParameter("altC");
		String altD = request.getParameter("altD");
		String altE = request.getParameter("altE");
		String gabarito = request.getParameter("gabarito");

		if (id != null && pergunta != null && altA != null && altB != null && altC != null && altD != null
				&& altE != null && gabarito != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				QuestaoDAO dao_questao = new QuestaoDAOImpl();
				Questao questao = new Questao(0, pergunta, altA, altB, altC, altD, altE, gabarito, "Objetiva", null,
						Integer.parseInt(id), 0, null);
				dao_questao.cadastrarQuestao(questao);
				response.sendRedirect("professor?action=atividade&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void criarNovaQuestaoDiscursiva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String pergunta = request.getParameter("pergunta");
		String gabarito = request.getParameter("gabarito");

		if (id != null && pergunta != null && gabarito != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				QuestaoDAO dao_questao = new QuestaoDAOImpl();
				Questao questao = new Questao(0, pergunta, null, null, null, null, null, gabarito, "Discursiva", null,
						Integer.parseInt(id), 0, null);
				dao_questao.cadastrarQuestao(questao);
				response.sendRedirect("professor?action=atividade&id=" + id);
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void editarQuestaoObjetiva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String pergunta = request.getParameter("pergunta");
		String altA = request.getParameter("altA");
		String altB = request.getParameter("altB");
		String altC = request.getParameter("altC");
		String altD = request.getParameter("altD");
		String altE = request.getParameter("altE");
		String gabarito = request.getParameter("gabarito");

		if (id != null && pergunta != null && altA != null && altB != null && altC != null && altD != null
				&& altE != null && gabarito != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			QuestaoDAO dao_questao = new QuestaoDAOImpl();
			Questao questao = dao_questao.buscarQuestaoPeloId(Integer.parseInt(id));
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(questao.getId_atividade());

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				questao.setPergunta(pergunta);
				questao.setAltA(altA);
				questao.setAltB(altB);
				questao.setAltC(altC);
				questao.setAltD(altD);
				questao.setAltE(altE);
				questao.setGabarito(gabarito);
				dao_questao.atualizarQuestao(questao);
				response.sendRedirect("professor?action=atividade&id=" + questao.getId_atividade());
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void editarQuestaoDiscursiva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String pergunta = request.getParameter("pergunta");
		String gabarito = request.getParameter("gabarito");

		if (id != null && pergunta != null && gabarito != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			QuestaoDAO dao_questao = new QuestaoDAOImpl();
			Questao questao = dao_questao.buscarQuestaoPeloId(Integer.parseInt(id));
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(questao.getId_atividade());

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				questao.setPergunta(pergunta);
				questao.setGabarito(gabarito);
				dao_questao.atualizarQuestao(questao);
				response.sendRedirect("professor?action=atividade&id=" + questao.getId_atividade());
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void removerQuestao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");

		if (id != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			QuestaoDAO dao_questao = new QuestaoDAOImpl();
			Questao questao = dao_questao.buscarQuestaoPeloId(Integer.parseInt(id));
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(questao.getId_atividade());

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				dao_questao.removerQuestao(Integer.parseInt(id));
				response.sendRedirect("professor?action=atividade&id=" + questao.getId_atividade());
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void resposta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		if (id != null && email != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));
			RespostaDAO dao_resposta = new RespostaDAOImpl();

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				Aluno aluno = dao_aluno.buscarAlunoPorEmail(email);

				if (aluno != null) {
					request.setAttribute("aluno", aluno);
					request.setAttribute("atividade", atividade);

					if (dao_resposta.verificarAtividadeRespondida(Integer.parseInt(id), email) == true) {
						List<Questao> questoes = dao_atividade.questoesAtividade(Integer.parseInt(id));
						int ordem = 1;
						for (Questao questao : questoes) {
							questao.setOrdem(ordem);
							ordem++;
							Resposta resposta = dao_resposta.buscarRespostaPeloIdQuestaoEmail(questao.getId_questao(),
									email);
							questao.setResposta(resposta);
						}

						request.setAttribute("questoes", questoes);
						request.setAttribute("realizado", true);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/resposta.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("realizado", false);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/resposta.jsp");
						rd.forward(request, response);
					}
				} else {
					response.sendRedirect("professor?action=index");
				}
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void corrigirAtividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		if (id != null && email != null) {
			HttpSession session = request.getSession();
			Professor professorLogado = (Professor) session.getAttribute("professorLogado");
			ProfessorDAO dao_professor = new ProfessorDAOImpl();
			AlunoDAO dao_aluno = new AlunoDAOImpl();
			AtividadeDAO dao_atividade = new AtividadeDAOImpl();
			Atividade atividade = dao_atividade.buscarAtividadePeloId(Integer.parseInt(id));
			RespostaDAO dao_resposta = new RespostaDAOImpl();

			if (dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail()).equals("Ativo")
					|| dao_professor.verificarProfessorSala(atividade.getId_sala(), professorLogado.getEmail())
							.equals("Admin")) {
				Aluno aluno = dao_aluno.buscarAlunoPorEmail(email);

				if (aluno != null) {
					if (dao_resposta.verificarAtividadeRespondida(Integer.parseInt(id), email) == true) {
						List<Questao> questoes = dao_atividade.questoesAtividade(Integer.parseInt(id));
						int ordem = 1;
						for (Questao questao : questoes) {
							if (questao.getTipo().equals("Discursiva")) {
								String correcao = request.getParameter("correcao" + ordem);
								Resposta resposta = dao_resposta
										.buscarRespostaPeloIdQuestaoEmail(questao.getId_questao(), email);
								resposta.setCorrecao(correcao);
								dao_resposta.atualizarResposta(resposta);
							}
							ordem++;
						}
						response.sendRedirect("professor?action=resposta&id=" + id + "&email=" + email);
					} else {
						response.sendRedirect("professor?action=index");
					}
				} else {
					response.sendRedirect("professor?action=index");
				}
			} else {
				response.sendRedirect("professor?action=index");
			}
		} else {
			response.sendRedirect("professor?action=index");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("professorLogado") != null) {
			session.invalidate();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/login.jsp");
		rd.forward(request, response);
	}

}
