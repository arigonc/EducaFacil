<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../header.jsp"></c:import>
<section style="min-height: 100vh;" class="m-4">
	<div class="row align-middle">
		<div class="col">
			<p class="fs-5">Minhas salas</p>
		</div>
		<div class="col text-end">
			<button type="button" class="btn btn-sm btn-dark"
				data-bs-toggle="modal" data-bs-target="#criarNovaSala">
				<i class="bi bi-plus-circle mx-1"></i>Criar nova sala
			</button>
			<button type="button" class="btn btn-sm btn-dark"
				data-bs-toggle="modal" data-bs-target="#entrarNovaSala">
				<i class="bi bi-plus-circle mx-1"></i>Entrar em nova sala
			</button>
		</div>
	</div>
	<div class="row row-cols-1 row-cols-md-3 g-4 mt-2">
		<c:forEach var="sala" items="${salas}">
			<a href="professor?action=sala&id=${sala.id}"
				class="text-black text-decoration-none">
				<div class="col">
					<div class="card h-100"
						style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
						<div class="card-body">
							<div class="row align-middle">
								<div class="col-md-9">
									<h5 class="card-title">
										<i class="bi bi-journal mx-2"></i></i>${sala.titulo}</h5>
								</div>
								<div class="col-md-3 text-end">
									<c:if test="${sala.situacao == 'Pendente'}">
										<span class="badge bg-warning">Pendente</span>
									</c:if>
									<c:if test="${sala.situacao == 'Removido'}">
										<span class="badge bg-danger">Removido</span>
									</c:if>
									<c:if test="${sala.situacao == 'Ativo'}">
										<span class="badge bg-success">Ativo</span>
									</c:if>
									<c:if test="${sala.situacao == 'Admin'}">
										<span class="badge bg-dark">Admin</span>
									</c:if>
								</div>
							</div>
							<p class="card-text">${sala.descricao}</p>
						</div>
					</div>
				</div>
			</a>
		</c:forEach>
	</div>
	<div class="modal modal-lg fade" id="criarNovaSala" tabindex="-1"
		aria-labelledby="criarNovaSala" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Criar nova sala</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Complete os campos abaixos relacionados a sua nova sala.</p>
					<form id="formSala" action="professor?action=criarNovaSala"
						method="post">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="tituloSala"
								name="titulo" placeholder="Título..."> <label
								for="titulo">Título</label>
							<div class="invalid-feedback" id="erro_tituloSala"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="descricaoSala"
								name="descricao" placeholder="Descrição..."> <label
								for="descricao">Descrição</label>
							<div class="invalid-feedback" id="erro_descricaoSala"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="materia"
								name="materia" placeholder="Matéria..."> <label
								for="materia">Matéria</label>
							<div class="invalid-feedback" id="erro_materia"></div>
						</div>
						<p class="my-3">Que tal personalizar a sua sala? Escolha uma
							cor de leitura e uma cor de fundo.</p>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating mt-3">
									<select class="form-select" id="cor" name="cor">
										<option selected></option>
										<option value="#fff">Branco</option>
										<option value="#000">Preto</option>
									</select> <label for="cor">Cor de leitura</label>
									<div class="invalid-feedback" id="erro_cor"></div>
								</div>
							</div>
							<div class="col-md-6">
								<label for="cor_back" class="form-label">Cor de fundo</label> <input
									type="color" class="form-control form-control-color"
									id="cor_back" name="cor_back" value="#212529">
								<div class="invalid-feedback" id="erro_cor_back"></div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button class="btn btn-primary" onclick="validarSala()">Criar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal modal-lg fade" id="entrarNovaSala" tabindex="-1"
		aria-labelledby="entrarNovaSala" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Entrar em nova sala</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Adicione abaixo o código da sala.</p>
					<form id="formEntradaSala" action="professor?action=entrarNovaSala"
						method="post">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="codigo" name="id"
								placeholder="Código..."> <label for="codigo">Código</label>
							<div class="invalid-feedback" id="erro_codigo"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button class="btn btn-primary" onclick="validarEntradaSala()">Entrar
					</button>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="../footer.jsp"></c:import>