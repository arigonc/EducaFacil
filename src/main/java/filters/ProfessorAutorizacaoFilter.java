package filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/professor")
public class ProfessorAutorizacaoFilter implements Filter {

	private final String[] professor_action = { "perfil", "editarConta", "editarSenha", "removerConta", "index", "sala",
			"aceitarAlunoSala", "removerAlunoSala", "aceitarProfessorSala", "removerProfessorSala", "criarNovaSala",
			"editarSala", "removerSala", "entrarNovaSala", "cancelarEntradaSala", "aula", "criarNovaAula", "editarAula",
			"removerAula", "comentar", "editarComentario", "removerComentario", "subcomentar", "editarSubcomentario",
			"removerSubcomentario", "atividade", "criarNovaAtividade", "editarAtividade", "removerAtividade",
			"criarNovaQuestaoObjetiva", "criarNovaQuestaoDiscursiva", "editarQuestaoObjetiva",
			"editarQuestaoDiscursiva", "removerQuestao", "resposta", "corrigirAtividade", "logout" };

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String action = ((HttpServletRequest) request).getParameter("action");
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (action != null && Arrays.asList(professor_action).contains(action)) {
			if (session.getAttribute("professorLogado") != null) {
				chain.doFilter(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/professor/login.jsp");
				rd.forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
