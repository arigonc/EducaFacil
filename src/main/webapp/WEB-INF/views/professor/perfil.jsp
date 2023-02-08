<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../header.jsp"></c:import>

<section style="min-height: 100vh;" class="m-4">
	<p class="fs-5">Meu perfil:</p>
	<p class="fs-6">Resumo:</p>
	<div class="row row-cols-1 row-cols-md-4 g-2 mb-5">
		<div class="col">
			<div class="card text-bg-success mb-3" style="max-width: 18rem;">
				<div class="card-body d-flex">
					<h1 class="m-3">
						<i class="bi bi-journal"></i>
					</h1>
					<p class="card-text text-end">
						<b>${num_salas}</b> salas que você administra ou participa
						ativamente.
					</p>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card text-bg-warning text-white mb-3"
				style="max-width: 18rem;">
				<div class="card-body d-flex">
					<h1 class="m-3">
						<i class="bi bi-people-fill"></i>
					</h1>
					<p class="card-text text-end">
						<b>${num_alunos}</b> alunos participantes em todas as suas salas.
					</p>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card text-bg-primary mb-3" style="max-width: 18rem;">
				<div class="card-body d-flex">
					<h1 class="m-3">
						<i class="bi bi-play-circle"></i>
					</h1>
					<p class="card-text text-end">
						<b>${num_aulas}</b> aulas foram cadastradas em todas as suas
						salas.
					</p>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card text-bg-danger mb-3" style="max-width: 18rem;">
				<div class="card-body d-flex">
					<h1 class="m-3">
						<i class="bi bi-card-text"></i>
					</h1>
					<p class="card-text text-end">
						<b>${num_atividades}</b> atividades foram cadastradas em todas as
						suas salas.
					</p>
				</div>
			</div>
		</div>
	</div>
	<p class="fs-6">Conta:</p>
	<div class="row">
		<div class="col-md-6">
			<div class="card border-0 shadow rounded-3 my-3">
				<div class="card-body p-4 p-sm-5 p-3">
					<h4 class="card-title text-center mb-4 fs-5">Editar conta</h4>
					<form action="professor?action=editarConta" method="post"
						id="formPerfilContaUsuario">
						<div class="form-floating mb-3">
							<input type="email" class="form-control" id="email"
								placeholder="Email..."
								value="${sessionScope.professorLogado.email}" disabled>
							<label for="email">Email</label>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="nome" name="nome"
								placeholder="Nome..."
								value="${sessionScope.professorLogado.nome}"> <label
								for="nome">Nome</label>
							<div class="invalid-feedback" id="erro_nome"></div>
						</div>
					</form>
					<button class="btn btn-dark" onclick="validarPerfilContaUsuario()">Editar</button>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="card border-0 shadow rounded-3 my-3">
				<div class="card-body p-4 p-sm-5 p-3">
					<h4 class="card-title text-center mb-4 fs-5">Editar senha</h4>
					<form action="professor?action=editarSenha" method="post"
						id="formPerfilSenhaUsuario">
						<div class="form-floating mb-3">
							<input type="password" class="form-control" id="senha"
								name="senha" placeholder="Senha..."
								value="${sessionScope.professorLogado.senha}"> <label
								for="senha">Senha</label>
							<div class="invalid-feedback" id="erro_senha"></div>
						</div>
					</form>
					<button class="btn btn-dark" onclick="validarPerfilSenhaUsuario()">Editar</button>
				</div>
			</div>
			<div class="card border-0 shadow rounded-3 my-3">
				<div class="card-body text-center">
					<button type="button" class="btn btn-sm btn-danger"
						data-bs-toggle="modal" data-bs-target="#removerConta">
						<i class="bi bi-person-x mx-1"></i>Deletar conta
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal modal-lg fade" id="removerConta" tabindex="-1"
		aria-labelledby="removerConta" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Remover conta</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Não vá embora! Aproveite as ferramentas que o EducaFácil
						oferece! Você tem certeza de que deseja remover a sua conta? Essa
						ação não pode ser desfeita.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<a type="button" class="btn btn-danger"
						href="professor?action=removerConta">Remover</a>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="../footer.jsp"></c:import>