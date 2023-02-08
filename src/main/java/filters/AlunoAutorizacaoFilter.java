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

@WebFilter("/aluno")
public class AlunoAutorizacaoFilter implements Filter {

	private final String[] aluno_action = { "perfil", "editarConta", "editarSenha", "removerConta", "index", "sala",
			"entrarNovaSala", "cancelarEntradaSala", "aula", "comentar", "editarComentario", "removerComentario",
			"subcomentar", "editarSubcomentario", "removerSubcomentario", "atividade", "responderAtividade", "logout" };

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String action = ((HttpServletRequest) request).getParameter("action");
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (action != null && Arrays.asList(aluno_action).contains(action)) {
			if (session.getAttribute("alunoLogado") != null) {
				chain.doFilter(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aluno/login.jsp");
				rd.forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
