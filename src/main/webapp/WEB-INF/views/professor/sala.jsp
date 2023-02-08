<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="hoje" value="${now}" pattern="yyyy-MM-dd" />

<c:import url="../header.jsp"></c:import>

<section style="min-height: 100vh;" class="m-4">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb border rounded p-3">
			<li class="breadcrumb-item"><a href="professor?action=index">Minhas
					salas</a></li>
			<li class="breadcrumb-item active" aria-current="page">Sala
				${sala.titulo}</li>
		</ol>
	</nav>
	<c:if test="${sala.situacao == 'Ativo' || sala.situacao == 'Admin'}">
		<div class="card mb-4"
			style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
			<div class="card-body">
				<div class="row align-middle">
					<div class="col-md-9">
						<h5 class="card-title">
							<i class="bi bi-journal mx-2"></i>${sala.titulo}</h5>
					</div>
					<div class="col-md-3 text-end">
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
		<div class="row align-middle">
			<div class="col">
				<p class="fs-5">Aulas</p>
			</div>
			<div class="col text-end">
				<button type="button" class="btn btn-sm btn-dark"
					data-bs-toggle="modal" data-bs-target="#criarNovaAula">
					<i class="bi bi-plus-circle mx-1"></i>Criar nova aula
				</button>
			</div>
		</div>
		<c:if test="${empty aulas}">
			<p class="fs-6">Nenhuma aula cadastrada ainda!</p>
		</c:if>
		<c:forEach var="aula" items="${aulas}">
			<a href="professor?action=aula&id=${aula.id_aula}"
				class="text-black text-decoration-none">
				<div class="card w-100 mb-3"
					style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
					<div class="card-body">
						<div class="row align-middle">
							<div class="col-md-9">
								<h5 class="card-title">
									<i class="bi bi-play-circle mx-2"></i>${aula.titulo}</h5>
							</div>
							<div class="col-md-3 text-end">
								<c:if test="${hoje < aula.data_inicio}">
									<span class="badge bg-primary">Atribuído</span>
								</c:if>
								<c:if
									test="${hoje >= aula.data_inicio && hoje <= aula.data_fim}">
									<span class="badge bg-success">Ativo</span>
								</c:if>
								<c:if test="${hoje > aula.data_fim}">
									<span class="badge bg-danger">Inativo</span>
								</c:if>
							</div>
						</div>
						<p class="card-text">${aula.descricao}</p>
						<p class="card-text">
							<small>Avaliado de <fmt:formatDate
									value="${aula.data_inicio}" pattern="dd/MM/yyyy" /> a <fmt:formatDate
									value="${aula.data_fim}" pattern="dd/MM/yyyy" /></small>
						</p>
					</div>
				</div>
			</a>
		</c:forEach>
		<div class="row align-middle mt-5">
			<div class="col">
				<p class="fs-5">Atividades</p>
			</div>
			<div class="col text-end">
				<button type="button" class="btn btn-sm btn-dark"
					data-bs-toggle="modal" data-bs-target="#criarNovaAtividade">
					<i class="bi bi-plus-circle mx-1"></i>Criar nova atividade
				</button>
			</div>
		</div>
		<c:if test="${empty atividades}">
			<p class="fs-6">Nenhuma atividade cadastrada ainda!</p>
		</c:if>
		<c:forEach var="atividade" items="${atividades}">
			<a href="professor?action=atividade&id=${atividade.id_atividade}"
				class="text-black text-decoration-none">
				<div class="card w-100 mb-3"
					style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
					<div class="card-body">
						<div class="row align-middle">
							<div class="col-md-9">
								<h5 class="card-title">
									<i class="bi bi-card-text mx-2"></i>${atividade.titulo}</h5>
							</div>
							<div class="col-md-3 text-end">
								<c:if test="${hoje < atividade.data_inicio}">
									<span class="badge bg-primary">Atribuído</span>
								</c:if>
								<c:if
									test="${hoje >= atividade.data_inicio && hoje <= atividade.data_fim}">
									<span class="badge bg-success">Ativo</span>
								</c:if>
								<c:if test="${hoje > atividade.data_fim}">
									<span class="badge bg-danger">Inativo</span>
								</c:if>
							</div>
						</div>
						<p class="card-text">${atividade.descricao}</p>
						<p class="card-text">
							<small>Avaliado de <fmt:formatDate
									value="${atividade.data_inicio}" pattern="dd/MM/yyyy" /> a <fmt:formatDate
									value="${atividade.data_fim}" pattern="dd/MM/yyyy" /></small>
						</p>
					</div>
				</div>
			</a>
		</c:forEach>
		<div class="row align-middle mt-5">
			<div class="col">
				<p class="fs-5">Configurações avançadas</p>
			</div>
			<div class="col text-end">
				<div class="btn-group">
					<button class="btn btn-dark btn-sm dropdown-toggle" type="button"
						data-bs-toggle="dropdown" aria-expanded="false">
						<i class="bi bi-gear-fill"></i>
					</button>
					<ul class="dropdown-menu dropdown-menu-dark">
						<li><button type="button" class="dropdown-item"
								data-bs-toggle="modal" data-bs-target="#editarSala">
								<i class="bi bi-pencil-fill mx-1"></i>Editar sala
							</button></li>
						<li><button type="button" class="dropdown-item"
								data-bs-toggle="modal" data-bs-target="#removerSala">
								<i class="bi bi-trash-fill mx-1"></i>Remover sala
							</button></li>
					</ul>
				</div>
			</div>
			<p class="fs-6">Participantes da sala</p>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead
						style="background-color: ${sala.cor_back}; color: ${sala.cor};">
						<tr>
							<th scope="col">Aluno</th>
							<th scope="col">Situação</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty alunos}">
							<td colspan="3" class="text-center">Nenhum aluno participa
								da sala ainda!</td>
						</c:if>
						<c:forEach var="aluno" items="${alunos}">
							<tr>
								<td scope="row">${aluno.nome}</td>
								<td><c:if test="${aluno.situacao == 'Pendente'}">
										<span class="badge bg-warning">Pendente</span>
									</c:if> <c:if test="${aluno.situacao == 'Removido'}">
										<span class="badge bg-danger">Removido</span>
									</c:if> <c:if test="${aluno.situacao == 'Ativo'}">
										<span class="badge bg-success">Ativo</span>
									</c:if></td>
								<td><c:if
										test="${aluno.situacao == 'Pendente' || aluno.situacao == 'Removido'}">
										<a type="button" class="btn btn-primary"
											href="professor?action=aceitarAlunoSala&id=${sala.id}&email=${aluno.email}"><i
											class="bi bi-person-fill-add"></i></a>
									</c:if><a type="button" class="btn btn-danger"
									href="professor?action=removerAlunoSala&id=${sala.id}&email=${aluno.email}"><i
										class="bi bi-person-x"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead
						style="background-color: ${sala.cor_back}; color: ${sala.cor};">
						<tr>
							<th scope="col">Professor</th>
							<th scope="col">Situação</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty professores}">
							<td colspan="3">Nenhum professor participa da sala ainda!</td>
						</c:if>
						<c:forEach var="professor" items="${professores}">
							<tr>
								<td scope="row">${professor.nome}</td>
								<td><c:if test="${professor.situacao == 'Pendente'}">
										<span class="badge bg-warning">Pendente</span>
									</c:if> <c:if test="${professor.situacao == 'Removido'}">
										<span class="badge bg-danger">Removido</span>
									</c:if> <c:if test="${professor.situacao == 'Ativo'}">
										<span class="badge bg-success">Ativo</span>
									</c:if> <c:if test="${professor.situacao == 'Admin'}">
										<span class="badge bg-dark">Admin</span>
									</c:if></td>
								<td><c:if
										test="${professor.situacao == 'Pendente' || professor.situacao == 'Removido'}">
										<a type="button" class="btn btn-primary"
											href="professor?action=aceitarProfessorSala&id=${sala.id}&email=${professor.email}"><i
											class="bi bi-person-fill-add"></i></a>
									</c:if> <c:if
										test="${sessionScope.professorLogado.email != professor.email && professor.situacao != 'Admin'}">
										<a type="button" class="btn btn-danger"
											href="professor?action=removerProfessorSala&id=${sala.id}&email=${professor.email}"><i
											class="bi bi-person-x"></i></a>
									</c:if> <c:if
										test="${professor.situacao == 'Admin' || sessionScope.professorLogado.email == professor.email}">
										Nenhuma ação disponível
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="modal fade" id="criarNovaAula" tabindex="-1"
			aria-labelledby="criarNovaAula" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Criar nova aula</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Complete os campos abaixos relacionados a sua nova aula.</p>
						<form id="formAula"
							action="professor?action=criarNovaAula&id=${sala.id}"
							method="post">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="tituloAula"
									name="titulo" placeholder="Título..."> <label
									for="tituloAula">Título</label>
								<div class="invalid-feedback" id="erro_tituloAula"></div>
							</div>
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="descricaoAula"
									name="descricao" placeholder="Descrição..."> <label
									for="descricaoAula">Descrição</label>
								<div class="invalid-feedback" id="erro_descricaoAula"></div>
							</div>
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="video" name="video"
									placeholder="Código do vídeo no YouTube..." maxlength="255"
									required> <label for="video">Código do vídeo no
									YouTube</label>
								<div class="invalid-feedback" id="erro_video"></div>
							</div>
							<small>Exemplo: https://www.youtube.com/watch?v=<b>ngORmvyvAaI</b>
								(parte negritada é o código do vídeo)
							</small>
							<div class="form-floating my-3">
								<input type="date" class="form-control" id="inicioAula"
									name="data_inicio" placeholder="Data de início..." required>
								<label for="inicioAula">Data de início</label>
								<div class="invalid-feedback" id="erro_inicioAula"></div>
							</div>
							<div class="form-floating">
								<input type="date" class="form-control" id="fimAula"
									name="data_fim" placeholder="Data final..." required> <label
									for="fimAula">Data final</label>
								<div class="invalid-feedback" id="erro_fimAula"></div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<button class="btn btn-primary" onclick="validarAula()">Criar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="criarNovaAtividade" tabindex="-1"
			aria-labelledby="criarNovaAtividade" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Criar nova atividade</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Complete os campos abaixos relacionados a sua nova
							atividade.</p>
						<form id="formAtividade"
							action="professor?action=criarNovaAtividade&id=${sala.id}"
							method="post">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="tituloAtividade"
									name="titulo" placeholder="Título..."> <label
									for="tituloAtividade">Título</label>
								<div class="invalid-feedback" id="erro_tituloAtividade"></div>
							</div>
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="descricaoAtividade"
									name="descricao" placeholder="Descrição..."> <label
									for="descricaoAtividade">Descrição</label>
								<div class="invalid-feedback" id="erro_descricaoAtividade"></div>
							</div>
							<div class="form-floating mb-3">
								<input type="date" class="form-control" id="inicioAtividade"
									name="data_inicio" placeholder="Data de início..."> <label
									for="inicioAtividade">Data de início</label>
								<div class="invalid-feedback" id="erro_inicioAtividade"></div>
							</div>
							<div class="form-floating">
								<input type="date" class="form-control" id="fimAtividade"
									name="data_fim" placeholder="Data final..."> <label
									for="fimAtividade">Data final</label>
								<div class="invalid-feedback" id="erro_fimAtividade"></div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<button class="btn btn-primary" onclick="validarAtividade()">Criar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="editarSala" tabindex="-1"
			aria-labelledby="editarSala" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Editar sala</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Edite os campos que deseja</p>
						<form id="formSala"
							action="professor?action=editarSala&id=${sala.id}" method="post">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="tituloSala"
									name="titulo" placeholder="Título..." value="${sala.titulo}">
								<label for="titulo">Título</label>
								<div class="invalid-feedback" id="erro_tituloSala"></div>
							</div>
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="descricaoSala"
									name="descricao" placeholder="Descrição..."
									value="${sala.descricao}"> <label for="descricao">Descrição</label>
								<div class="invalid-feedback" id="erro_descricaoSala"></div>
							</div>
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="materia"
									name="materia" placeholder="Matéria..." value="${sala.materia}">
								<label for="materia">Matéria</label>
								<div class="invalid-feedback" id="erro_materia"></div>
							</div>
							<p class="my-3">Que tal personalizar a sua sala? Escolha uma
								cor de leitura e uma cor de fundo.</p>
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating mt-3">
										<select class="form-select" id="cor" name="cor">
											<option></option>
											<option value="#fff"
												<c:if test="${sala.cor == '#fff'}">selected</c:if>>Branco</option>
											<option value="#000"
												<c:if test="${sala.cor == '#000'}">selected</c:if>>Preto</option>
										</select> <label for="cor">Cor de leitura</label>
										<div class="invalid-feedback" id="erro_cor"></div>
									</div>
								</div>
								<div class="col-md-6">
									<label for="cor_back" class="form-label">Cor de fundo</label> <input
										type="color" class="form-control form-control-color"
										id="cor_back" name="cor_back" value="${sala.cor_back}"
										required>
									<div class="invalid-feedback" id="erro_cor_back"></div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<button class="btn btn-primary" onclick="validarSala()">Editar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal modal-lg fade" id="removerSala" tabindex="-1"
			aria-labelledby="removerSala" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Remover sala</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Você tem certeza de que deseja remover a sala
							${sala.titulo}? Essa ação não pode ser desfeita.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<a type="button" class="btn btn-danger"
							href="professor?action=removerSala&id=${sala.id}">Remover</a>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${sala.situacao == 'Pendente'}">
		<div class="card mb-4"
			style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
			<div class="card-body">
				<div class="row align-middle">
					<div class="col-md-9">
						<h5 class="card-title">
							<i class="bi bi-journal mx-2"></i>${sala.titulo}</h5>
					</div>
					<div class="col-md-3 text-end">
						<span class="badge bg-warning">Pendente</span>
					</div>
				</div>
				<p class="card-text">${sala.descricao}</p>
			</div>
		</div>
		<div class="alert alert-danger d-flex align-items-center mt-3"
			role="alert">
			<i class="bi bi-lock-fill mx-1"></i>
			<div>
				<strong>Atenção!</strong> Sua solicitação para entrar nessa aula
				está pendente. O(a) professor(a) administrador(a) deve aceitar a sua
				entrada na sala. Certifique-se com ele(a) sobre sua solicitação ou <a
					href="professor?action=cancelarEntradaSala&id=${sala.id}">cancele
					sua entrada nessa sala</a>.
			</div>
		</div>
	</c:if>
	<c:if test="${sala.situacao == 'Removido'}">
		<div class="card mb-4"
			style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
			<div class="card-body">
				<div class="row align-middle">
					<div class="col-md-9">
						<h5 class="card-title">
							<i class="bi bi-journal mx-2"></i>${sala.titulo}</h5>
					</div>
					<div class="col-md-3 text-end">
						<span class="badge bg-danger">Removido</span>
					</div>
				</div>
				<p class="card-text">${sala.descricao}</p>
			</div>
		</div>
		<div class="alert alert-danger d-flex align-items-center mt-3"
			role="alert">
			<i class="bi bi-lock-fill mx-1"></i>
			<div>
				<strong>Atenção!</strong> Sua solicitação para entrar nessa aula foi
				rejeitada. Comunique com o professor administrador se isso for um
				erro ou <a href="professor?action=cancelarEntradaSala&id=${sala.id}">cancele
					sua entrada nessa sala</a>.
			</div>
		</div>
	</c:if>
</section>

<c:import url="../footer.jsp"></c:import>