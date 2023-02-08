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
			<li class="breadcrumb-item"><a
				href="professor?action=sala&id=${sala.id}">Sala ${sala.titulo}</a></li>
			<li class="breadcrumb-item active" aria-current="page">Aula
				${aula.titulo}</li>
		</ol>
	</nav>
	<div class="row align-items-center mt-3">
		<div class="col-md-8">
			<div class="ratio ratio-16x9">
				<iframe src="https://www.youtube.com/embed/${aula.video}"
					title="${aula.titulo}" allowfullscreen></iframe>
			</div>
		</div>
		<div class="col-md-4 mt-3">
			<div class="card"
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
							<c:if test="${hoje >= aula.data_inicio && hoje <= aula.data_fim}">
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
		</div>
	</div>
	<div class="row align-middle mt-5">
		<div class="col">
			<p class="fw-semibold fs-5">Fórum de mensagens</p>
		</div>
	</div>
	<c:if test="${hoje >= aula.data_inicio && hoje <= aula.data_fim}">
		<form action="professor?action=comentar&id=${aula.id_aula}"
			method="post" id="formComentario0">
			<div class="form-floating">
				<textarea class="form-control" placeholder="Comentário..."
					id="comentario0" name="comentario" style="height: 120px"></textarea>
				<label for="comentario0">Comentário</label>
				<div class="invalid-feedback" id="erro_comentario0"></div>
			</div>
		</form>
		<button class="btn btn-sm btn-primary my-2"
			onclick="validarComentario(0)">Comentar</button>
	</c:if>
	<c:if test="${hoje < aula.data_inicio || hoje > aula.data_fim}">
		<form action="#" method="post">
			<div class="form-floating">
				<textarea class="form-control" placeholder="Comentário..."
					id="comentario" maxlength="600" style="height: 120px" disabled></textarea>
				<label for="comentario">Comentário</label>
			</div>
			<input type="submit" disabled class="btn btn-sm btn-primary my-2"
				value="Comentar">
		</form>
	</c:if>
	<c:forEach var="comentario" items="${comentarios}">
		<div class="card my-2"
			style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
			<div class="card-body">
				<div class="row align-middle">
					<div class="col-md-9">
						<p class="card-text">
							<span class="fw-semibold"><i
								class="bi bi-chat-left-text-fill mx-1"></i>${comentario.nome}:</span>
							${comentario.comentario}
						</p>
						<p class="card-text">
							<small>Comentado em <fmt:formatDate
									value="${comentario.cadastro}" pattern="dd/MM/yyyy" /> às <fmt:formatDate
									value="${comentario.cadastro}" pattern="HH:mm" /></small>
						</p>
					</div>
					<div class="col-md-3 text-end">
						<c:if test="${hoje <= aula.data_fim}">
							<div class="btn-group">
								<button class="btn btn-dark btn-sm dropdown-toggle"
									type="button" data-bs-toggle="dropdown" aria-expanded="false">
									<i class="bi bi-three-dots-vertical"></i>
								</button>
								<ul class="dropdown-menu dropdown-menu-dark">
									<li>
										<button type="button" class="dropdown-item"
											data-bs-toggle="modal"
											data-bs-target="#subcomentar${comentario.id_comentario}">
											<i class="bi bi-chat-left-dots-fill mx-1"></i>Responder
										</button>
									</li>
									<c:if
										test="${sessionScope.professorLogado.email == comentario.email && comentario.tipo == 'Professor'}">
										<li><button type="button" class="dropdown-item"
												data-bs-toggle="modal"
												data-bs-target="#editarComentario${comentario.id_comentario}">
												<i class="bi bi-pencil-fill mx-1"></i>Editar
											</button></li>
										<li><button type="button" class="dropdown-item"
												data-bs-toggle="modal"
												data-bs-target="#removerComentario${comentario.id_comentario}">
												<i class="bi bi-trash-fill mx-1"></i>Remover
											</button></li>
									</c:if>
								</ul>
							</div>
						</c:if>
						<c:if test="${hoje > aula.data_fim}">
							<div class="btn-group">
								<button class="btn btn-dark btn-sm dropdown-toggle"
									type="button" data-bs-toggle="dropdown" aria-expanded="false"
									disabled>
									<i class="bi bi-three-dots-vertical"></i>
								</button>
								<ul class="dropdown-menu dropdown-menu-dark">
									<li>
										<button type="button" class="dropdown-item" disabled>
											<i class="bi bi-chat-left-dots-fill mx-1"></i>Responder
										</button>
									</li>
									<c:if
										test="${sessionScope.professorLogado.email == comentario.email && comentario.tipo == 'Professor'}">
										<li><button type="button" class="dropdown-item" disabled>
												<i class="bi bi-pencil-fill mx-1"></i>Editar
											</button></li>
										<li><button type="button" class="dropdown-item" disabled>
												<i class="bi bi-trash-fill mx-1"></i>Remover
											</button></li>
									</c:if>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<c:forEach var="subcomentario" items="${comentario.subcomentarios}">
			<div class="card my-2 w-75"
				style="border-color: ${sala.cor_back}; left: 25%;">
				<div class="card-body">
					<div class="row align-middle">
						<div class="col-md-9">
							<p class="card-text">
								<span class="fw-semibold"><i
									class="bi bi-chat-left-text-fill mx-1"></i>${subcomentario.nome}:</span>
								${subcomentario.subcomentario}
							</p>
							<p class="card-text">
								<small>Respondido em <fmt:formatDate
										value="${subcomentario.cadastro}" pattern="dd/MM/yyyy" /> às
									<fmt:formatDate value="${subcomentario.cadastro}"
										pattern="HH:mm" /></small>
							</p>
						</div>
						<div class="col-md-3 text-end">
							<c:if
								test="${sessionScope.professorLogado.email == subcomentario.email && subcomentario.tipo == 'Professor'}">
								<c:if test="${hoje <= aula.data_fim}">
									<div class="btn-group">
										<button class="btn btn-dark btn-sm dropdown-toggle"
											type="button" data-bs-toggle="dropdown" aria-expanded="false">
											<i class="bi bi-three-dots-vertical"></i>
										</button>
										<ul class="dropdown-menu dropdown-menu-dark">
											<li><button type="button" class="dropdown-item"
													data-bs-toggle="modal"
													data-bs-target="#editarSubcomentario${subcomentario.id_subcomentario}">
													<i class="bi bi-pencil-fill mx-1"></i>Editar
												</button></li>
											<li><button type="button" class="dropdown-item"
													data-bs-toggle="modal"
													data-bs-target="#removerSubcomentario${subcomentario.id_subcomentario}">
													<i class="bi bi-trash-fill mx-1"></i>Remover
												</button></li>
										</ul>
									</div>
								</c:if>
								<c:if test="${hoje > aula.data_fim}">
									<div class="btn-group">
										<button class="btn btn-dark btn-sm dropdown-toggle"
											type="button" data-bs-toggle="dropdown" aria-expanded="false"
											disabled>
											<i class="bi bi-three-dots-vertical"></i>
										</button>
										<ul class="dropdown-menu dropdown-menu-dark">
											<li><button type="button" class="dropdown-item" disabled>
													<i class="bi bi-pencil-fill mx-1"></i>Editar
												</button></li>
											<li><button type="button" class="dropdown-item" disabled>
													<i class="bi bi-trash-fill mx-1"></i>Remover
												</button></li>
										</ul>
									</div>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade"
				id="editarSubcomentario${subcomentario.id_subcomentario}"
				tabindex="-1"
				aria-labelledby="editarSubcomentario${subcomentario.id_subcomentario}"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Editar resposta</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form id="formSubcomentario${subcomentario.id_subcomentario}"
								action="professor?action=editarSubcomentario&id=${subcomentario.id_subcomentario}"
								method="post">
								<div class="form-floating">
									<textarea class="form-control" placeholder="Resposta..."
										id="resposta${subcomentario.id_subcomentario}"
										name="subcomentario" style="height: 120px">${subcomentario.subcomentario}</textarea>
									<label for="resposta${subcomentario.id_subcomentario}">Resposta</label>
									<div class="invalid-feedback"
										id="erro_resposta${subcomentario.subcomentario}"></div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cancelar</button>
							<button class="btn btn-primary"
								onclick="validarSubcomentario(${subcomentario.id_subcomentario})">Editar</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal modal-lg fade"
				id="removerSubcomentario${subcomentario.id_subcomentario}"
				tabindex="-1"
				aria-labelledby="removerSubcomentario${subcomentario.id_subcomentario}"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Remover resposta</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<p>Você tem certeza de que deseja remover essa resposta? Essa
								ação não pode ser desfeita.</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cancelar</button>
							<a type="button" class="btn btn-danger"
								href="professor?action=removerSubcomentario&id=${subcomentario.id_subcomentario}">Remover</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="modal fade" id="subcomentar${comentario.id_comentario}"
			tabindex="-1"
			aria-labelledby="subcomentar${comentario.id_comentario}"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Responder comentário</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="formSubcomentario0"
							action="professor?action=subcomentar&id=${comentario.id_comentario}"
							method="post">
							<div class="form-floating">
								<textarea class="form-control" placeholder="Resposta..."
									id="resposta0" name="subcomentario" style="height: 120px"></textarea>
								<label for="resposta0">Resposta</label>
								<div class="invalid-feedback" id="erro_resposta0"></div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<button class="btn btn-primary" onclick="validarSubcomentario(0)">Responder</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade"
			id="editarComentario${comentario.id_comentario}" tabindex="-1"
			aria-labelledby="editarComentario${comentario.id_comentario}"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Editar comentário</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="formComentario${comentario.id_comentario}"
							action="professor?action=editarComentario&id=${comentario.id_comentario}"
							method="post">
							<div class="form-floating">
								<textarea class="form-control" placeholder="Comentário..."
									id="comentario${comentario.id_comentario}" name="comentario"
									style="height: 120px">${comentario.comentario}</textarea>
								<label for="comentario${comentario.id_comentario}">Comentário</label>
								<div class="invalid-feedback"
									id="erro_comentario${comentario.id_comentario}"></div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<button class="btn btn-primary"
							onclick="validarComentario(${comentario.id_comentario})">Editar
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal modal-lg fade"
			id="removerComentario${comentario.id_comentario}" tabindex="-1"
			aria-labelledby="removerComentario${comentario.id_comentario}"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Remover comentário</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Você tem certeza de que deseja remover esse comentário?
							Essa ação não pode ser desfeita.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
						<a type="button" class="btn btn-danger"
							href="professor?action=removerComentario&id=${comentario.id_comentario}">Remover</a>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class="row align-middle mt-5">
		<div class="col">
			<p class="fw-semibold fs-5">Configurações avançadas</p>
		</div>
		<div class="col text-end">
			<div class="btn-group">
				<button class="btn btn-dark btn-sm dropdown-toggle" type="button"
					data-bs-toggle="dropdown" aria-expanded="false">
					<i class="bi bi-gear-fill"></i>
				</button>
				<ul class="dropdown-menu dropdown-menu-dark">
					<li><button type="button" class="dropdown-item"
							data-bs-toggle="modal" data-bs-target="#editarAula">
							<i class="bi bi-pencil-fill mx-1"></i>Editar aula
						</button></li>
					<li><button type="button" class="dropdown-item"
							data-bs-toggle="modal" data-bs-target="#removerAula">
							<i class="bi bi-trash-fill mx-1"></i>Remover aula
						</button></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="modal fade" id="editarAula" tabindex="-1"
		aria-labelledby="editarAula" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Editar aula</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Edite os campos que deseja.</p>
					<form id="formAula"
						action="professor?action=editarAula&id=${aula.id_aula}"
						method="post">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="tituloAula"
								name="titulo" placeholder="Título..." value="${aula.titulo}">
							<label for="tituloAula">Título</label>
							<div class="invalid-feedback" id="erro_tituloAula"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="descricaoAula"
								name="descricao" placeholder="Descrição..."
								value="${aula.descricao}"> <label for="descricaoAula">Descrição</label>
							<div class="invalid-feedback" id="erro_descricaoAula"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="video" name="video"
								placeholder="Código do vídeo no YouTube..."
								value="${aula.video}"> <label for="video">Código
								do vídeo no YouTube</label>
							<div class="invalid-feedback" id="erro_video"></div>
						</div>
						<small>Exemplo: https://www.youtube.com/watch?v=<b>ngORmvyvAaI</b>
							(parte negritada é o código do vídeo)
						</small>
						<div class="form-floating my-3">
							<input type="date" class="form-control" id="inicioAula"
								name="data_inicio" placeholder="Data de início..."
								value="${aula.data_inicio}"> <label for="inicioAula">Data
								de início</label>
							<div class="invalid-feedback" id="erro_inicioAula"></div>
						</div>
						<div class="form-floating">
							<input type="date" class="form-control" id="fimAula"
								name="data_fim" placeholder="Data final..."
								value="${aula.data_fim}"> <label for="fimAula">Data
								final</label>
							<div class="invalid-feedback" id="erro_fimAula"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button class="btn btn-primary" onclick="validarAula()">Editar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal modal-lg fade" id="removerAula" tabindex="-1"
		aria-labelledby="removerAula" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Remover aula</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Você tem certeza de que deseja remover a aula
						${aula.titulo}? Essa ação não pode ser desfeita.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<a type="button" class="btn btn-danger"
						href="professor?action=removerAula&id=${aula.id_aula}">Remover</a>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="../footer.jsp"></c:import>
