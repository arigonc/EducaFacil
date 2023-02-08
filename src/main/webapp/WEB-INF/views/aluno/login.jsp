<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../header.jsp"></c:import>

<section style="min-height: 100vh;" class="bg-dark">
	<div class="container col-xl-10 col-xxl-8 px-4">
		<div class="row align-items-center g-lg-5 py-5">
			<div class="col-lg-7 text-center text-lg-start">
				<h2 class="display-4 fw-bold lh-1 mb-3 text-white">EducaFácil</h2>
				<h2 class="fw-bold lh-1 mb-3 text-white">Para alunos</h2>
				<p class="col-lg-10 fs-4 text-white">Acesse aulas e atividades
					nas salas virtuais dos seus professores! Faça parte da melhor
					plataforma educacional brasileira!</p>
			</div>
			<div class="col-md-10 mx-auto col-lg-5">
				<div class="card border-0 shadow rounded-3 my-3">
					<div class="card-body p-4 p-sm-5 p-3">
						<h4 class="card-title text-center mb-4 fs-5">Login</h4>
						<form action="aluno?action=login" method="post"
							id="formLoginUsuario">
							<div class="form-floating mb-3">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="Email..."> <label for="email">Email</label>
								<div class="invalid-feedback" id="erro_email"></div>
							</div>
							<div class="form-floating mb-3">
								<input type="password" class="form-control" id="senha"
									name="senha" placeholder="Senha..."> <label for="senha">Senha</label>
								<div class="invalid-feedback" id="erro_senha"></div>
							</div>
							<div class="form-check p-1 mb-3">
								<a href="aluno?action=cadastrarConta">Ainda não sou
									cadastrado</a>
							</div>
						</form>
						<button class="btn btn-dark" onclick="validarLoginUsuario()">Login</button>
						<c:if test="${ok == false}">
							<div
								class="alert alert-danger d-flex align-items-center alert-dismissible fade show mt-3"
								role="alert">
								<i class="bi bi-exclamation-triangle-fill flex-shrink-0 me-1"></i>
								<div>Email e/ou senha incorretos!</div>
								<button type="button" class="btn-close" data-bs-dismiss="alert"
									aria-label="Close"></button>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="../footer.jsp"></c:import>