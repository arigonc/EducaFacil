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
			<li class="breadcrumb-item active" aria-current="page">Atividade
				${atividade.titulo}</li>
		</ol>
	</nav>
	<div class="card mb-4"
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
	<div class="row align-middle">
		<div class="col">
			<p class="fs-5">Questões</p>
		</div>
		<c:if test="${hoje < atividade.data_inicio}">
			<div class="col text-end">
				<button type="button" class="btn btn-sm btn-dark"
					data-bs-toggle="modal" data-bs-target="#criarNovaQuestaoObjetiva">
					<i class="bi bi-plus-circle mx-1"></i>Criar nova questão objetiva
				</button>
				<button type="button" class="btn btn-sm btn-dark"
					data-bs-toggle="modal" data-bs-target="#criarNovaQuestaoDiscursiva">
					<i class="bi bi-plus-circle mx-1"></i>Criar nova questão discursiva
				</button>
			</div>
		</c:if>
		<c:if test="${hoje >= atividade.data_inicio}">
			<div class="col text-end">
				<button type="button" class="btn btn-sm btn-dark" disabled>
					<i class="bi bi-plus-circle mx-1"></i>Criar nova questão objetiva
				</button>
				<button type="button" class="btn btn-sm btn-dark" disabled>
					<i class="bi bi-plus-circle mx-1"></i>Criar nova questão discursiva
				</button>
			</div>
		</c:if>
	</div>
	<c:if test="${empty questoes}">
		<p class="fs-6">Nenhuma questão cadastrada ainda!</p>
	</c:if>
	<c:forEach var="questao" items="${questoes}">
		<div class="card w-100 mb-3" style="border-color: ${sala.cor_back}">
			<div class="card-body">
				<div class="row align-middle">
					<div class="col">
						<h5 class="card-title">Questão #${questao.ordem}</h5>
					</div>
					<div class="col text-end">
						<c:if test="${hoje < atividade.data_inicio}">
							<div class="btn-group">
								<button class="btn btn-dark btn-sm dropdown-toggle"
									type="button" data-bs-toggle="dropdown" aria-expanded="false">
									<i class="bi bi-three-dots-vertical"></i>
								</button>
								<ul class="dropdown-menu dropdown-menu-dark">
									<li><button type="button" class="dropdown-item"
											data-bs-toggle="modal"
											data-bs-target="#editarQuestao${questao.tipo}${questao.id_questao}">
											<i class="bi bi-pencil-fill mx-1"></i>Editar
										</button></li>
									<li><button type="button" class="dropdown-item"
											data-bs-toggle="modal"
											data-bs-target="#removerQuestao${questao.id_questao}">
											<i class="bi bi-trash-fill mx-1"></i>Remover
										</button></li>
								</ul>
							</div>
						</c:if>
						<c:if test="${hoje >= atividade.data_inicio}">
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
					</div>
				</div>
				<div class="modal modal-lg fade"
					id="removerQuestao${questao.id_questao}" tabindex="-1"
					aria-labelledby="removerQuestao${questao.id_questao}"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Remover questão</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<p>Você tem certeza de que deseja remover essa questão? Essa
									ação não pode ser desfeita.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Cancelar</button>
								<a type="button" class="btn btn-danger"
									href="professor?action=removerQuestao&id=${questao.id_questao}">Remover</a>
							</div>
						</div>
					</div>
				</div>
				<p class="card-text">${questao.pergunta}</p>
				<c:if test="${questao.tipo == 'Objetiva'}">
					<p class="card-text">
						<span class="d-block">a) ${questao.altA}</span> <span
							class="d-block">b) ${questao.altB}</span> <span class="d-block">c)
							${questao.altC}</span> <span class="d-block">d) ${questao.altD}</span> <span
							class="d-block">e) ${questao.altE}</span>
					</p>
					<div class="modal modal-lg fade"
						id="editarQuestaoObjetiva${questao.id_questao}" tabindex="-1"
						aria-labelledby="editarQuestaoObjetiva${questao.id_questao}"
						aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Editar questão objetiva</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<p>Edite os campos que deseja.</p>
									<form id="formQuestaoObjetiva${questao.id_questao}"
										action="professor?action=editarQuestaoObjetiva&id=${questao.id_questao}"
										method="post">
										<div class="form-floating mb-3">
											<textarea class="form-control" placeholder="Pergunta..."
												id="perguntaObjetiva${questao.id_questao}" name="pergunta"
												style="height: 200px">${questao.pergunta}</textarea>
											<label for="perguntaObjetiva${questao.id_questao}">Pergunta</label>
											<div class="invalid-feedback"
												id="erro_perguntaObjetiva${questao.id_questao}"></div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="altA${questao.id_questao}" name="altA"
												placeholder="Alternativa A..." value="${questao.altA}">
											<label for="altA${questao.id_questao}">Alternativa A</label>
											<div class="invalid-feedback"
												id="erro_altA${questao.id_questao}"></div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="altB${questao.id_questao}" name="altB"
												placeholder="Alternativa B..." value="${questao.altB}">
											<label for="altB${questao.id_questao}">Alternativa B</label>
											<div class="invalid-feedback"
												id="erro_altB${questao.id_questao}"></div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="altC${questao.id_questao}" name="altC"
												placeholder="Alternativa C..." value="${questao.altC}">
											<label for="altC${questao.id_questao}">Alternativa C</label>
											<div class="invalid-feedback"
												id="erro_altC${questao.id_questao}"></div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="altD${questao.id_questao}" name="altD"
												placeholder="Alternativa D..." value="${questao.altD}">
											<label for="altD${questao.id_questao}">Alternativa D</label>
											<div class="invalid-feedback"
												id="erro_altD${questao.id_questao}"></div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="altE${questao.id_questao}" name="altE"
												placeholder="Alternativa E..." value="${questao.altE}">
											<label for="altE${questao.id_questao}">Alternativa E</label>
											<div class="invalid-feedback"
												id="erro_altE${questao.id_questao}"></div>
										</div>
										<div class="form-floating">
											<select class="form-select"
												id="gabaritoObjetiva${questao.id_questao}" name="gabarito">
												<option></option>
												<option value="A"
													<c:if test="${questao.gabarito == 'A'}">selected</c:if>>A</option>
												<option value="B"
													<c:if test="${questao.gabarito == 'B'}">selected</c:if>>B</option>
												<option value="C"
													<c:if test="${questao.gabarito == 'C'}">selected</c:if>>C</option>
												<option value="D"
													<c:if test="${questao.gabarito == 'D'}">selected</c:if>>D</option>
												<option value="E"
													<c:if test="${questao.gabarito == 'E'}">selected</c:if>>E</option>
											</select> <label for="gabaritoObjetiva${questao.id_questao}">Gabarito</label>
											<div class="invalid-feedback" id="erro_gabaritoObjetiva"></div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Cancelar</button>
									<button class="btn btn-primary"
										onclick="validarQuestaoObjetiva(${questao.id_questao})">Editar</button>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${questao.tipo == 'Discursiva'}">
					<div class="modal modal-lg fade"
						id="editarQuestaoDiscursiva${questao.id_questao}" tabindex="-1"
						aria-labelledby="editarQuestaoDiscursiva${questao.id_questao}"
						aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Editar questão discursiva</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<p>Edite os campos que deseja.</p>
									<form id="formQuestaoDiscursiva${questao.id_questao}"
										action="professor?action=editarQuestaoDiscursiva&id=${questao.id_questao}"
										method="post">
										<div class="form-floating mb-3">
											<textarea class="form-control" placeholder="Pergunta..."
												id="perguntaDiscursiva${questao.id_questao}" name="pergunta"
												style="height: 200px">${questao.pergunta}</textarea>
											<label for="perguntaDiscursiva${questao.id_questao}">Pergunta</label>
											<div class="invalid-feedback"
												id="erro_perguntaDiscursiva${questao.id_questao}"></div>
										</div>
										<div class="form-floating">
											<textarea class="form-control" placeholder="Gabarito..."
												id="gabaritoDiscursiva${questao.id_questao}" name="gabarito"
												style="height: 200px">${questao.gabarito}</textarea>
											<label for="gabaritoDiscursiva${questao.id_questao}">Gabarito</label>
											<div class="invalid-feedback"
												id="erro_gabaritoDiscursiva${questao.id_questao}"></div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Cancelar</button>
									<button class="btn btn-primary"
										onclick="validarQuestaoDiscursiva(${questao.id_questao})">Editar</button>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<p class="card-text">Gabarito: ${questao.gabarito}</p>
			</div>
		</div>
	</c:forEach>
	<div class="row align-middle mt-5">
		<div class="col">
			<p class="fs-5">Respostas</p>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead
				style="background-color: ${sala.cor_back}; color: ${sala.cor};">
				<tr>
					<th scope="col">Aluno</th>
					<th scope="col">Atividade</th>
					<th scope="col">Nota</th>
					<th scope="col">Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty alunos}">
					<td colspan="4" class="text-center">Nenhum aluno participa da
						sala ainda!</td>
				</c:if>
				<c:forEach var="aluno" items="${alunos}">
					<tr>
						<td scope="row">${aluno.nome}</td>
						<td><c:if test="${aluno.situacao == 'Não realizado'}">
								<span class="badge bg-danger">Não realizado</span>
							</c:if> <c:if test="${aluno.situacao == 'Pendente'}">
								<span class="badge bg-warning">Pendente</span>
							</c:if> <c:if test="${aluno.situacao == 'Concluída'}">
								<span class="badge bg-success">Concluída</span>
							</c:if></td>
						<td><span class="badge bg-dark"><fmt:formatNumber
									type="number" pattern="###.##" value="${aluno.nota}" />% </span></td>
						<td><c:if
								test="${aluno.situacao == 'Pendente' || aluno.situacao == 'Concluída'}">
								<a type="button" class="btn btn-primary"
									href="professor?action=resposta&id=${atividade.id_atividade}&email=${aluno.email}"><i
									class="bi bi-eye-fill"></i></a>
							</c:if> <c:if test="${aluno.situacao == 'Não realizado'}">
								Nenhuma ação disponível
							</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
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
							data-bs-toggle="modal" data-bs-target="#editarAtividade">
							<i class="bi bi-pencil-fill mx-1"></i>Editar atividade
						</button></li>
					<li><button type="button" class="dropdown-item"
							data-bs-toggle="modal" data-bs-target="#removerAtividade">
							<i class="bi bi-trash-fill mx-1"></i>Remover atividade
						</button></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="modal modal-lg fade" id="criarNovaQuestaoObjetiva"
		tabindex="-1" aria-labelledby="criarNovaQuestao" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Criar nova questão objetiva</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Complete os campos a seguir.</p>
					<form id="formQuestaoObjetiva0"
						action="professor?action=criarNovaQuestaoObjetiva&id=${atividade.id_atividade}"
						method="post">
						<div class="form-floating mb-3">
							<textarea class="form-control" placeholder="Pergunta..."
								id="perguntaObjetiva0" name="pergunta" style="height: 200px"></textarea>
							<label for="perguntaObjetiva0">Pergunta</label>
							<div class="invalid-feedback" id="erro_perguntaObjetiva0"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="altA0" name="altA"
								placeholder="Alternativa A..."> <label for="altA0">Alternativa
								A</label>
							<div class="invalid-feedback" id="erro_altA0"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="altB0" name="altB"
								placeholder="Alternativa B..."> <label for="altB0">Alternativa
								B</label>
							<div class="invalid-feedback" id="erro_altB0"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="altC0" name="altC"
								placeholder="Alternativa C..."> <label for="altC0">Alternativa
								C</label>
							<div class="invalid-feedback" id="erro_altC0"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="altD0" name="altD"
								placeholder="Alternativa D..."> <label for="altD0">Alternativa
								D</label>
							<div class="invalid-feedback" id="erro_altD0"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="altE0" name="altE"
								placeholder="Alternativa E..."> <label for="altE0">Alternativa
								E</label>
							<div class="invalid-feedback" id="erro_altE0"></div>
						</div>
						<div class="form-floating">
							<select class="form-select" id="gabaritoObjetiva0"
								name="gabarito">
								<option selected></option>
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
								<option value="E">E</option>
							</select> <label for="gabaritoObjetiva0">Gabarito</label>
							<div class="invalid-feedback" id="erro_gabaritoObjetiva0"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button class="btn btn-primary" onclick="validarQuestaoObjetiva(0)">Criar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal modal-lg fade" id="criarNovaQuestaoDiscursiva"
		tabindex="-1" aria-labelledby="criarNovaQuestao" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Criar nova questão discursiva</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Complete os campos a seguir.</p>
					<form id="formQuestaoDiscursiva0"
						action="professor?action=criarNovaQuestaoDiscursiva&id=${atividade.id_atividade}"
						method="post">
						<div class="form-floating mb-3">
							<textarea class="form-control" placeholder="Pergunta..."
								id="perguntaDiscursiva0" name="pergunta" style="height: 200px"></textarea>
							<label for="perguntaDiscursiva0">Pergunta</label>
							<div class="invalid-feedback" id="erro_perguntaDiscursiva0"></div>
						</div>
						<div class="form-floating">
							<textarea class="form-control" placeholder="Gabarito..."
								id="gabaritoDiscursiva0" name="gabarito" style="height: 200px"></textarea>
							<label for="gabaritoDiscursiva0">Gabarito</label>
							<div class="invalid-feedback" id="erro_gabaritoDiscursiva0"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button class="btn btn-primary"
						onclick="validarQuestaoDiscursiva(0)">Criar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="editarAtividade" tabindex="-1"
		aria-labelledby="editarAtividade" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Editar atividade</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Edite os campos que deseja.</p>
					<form id="formAtividade"
						action="professor?action=editarAtividade&id=${atividade.id_atividade}"
						method="post">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="tituloAtividade"
								name="titulo" placeholder="Título..."
								value="${atividade.titulo}"> <label
								for="tituloAtividade">Título</label>
							<div class="invalid-feedback" id="erro_tituloAtividade"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="descricaoAtividade"
								name="descricao" placeholder="Descrição..."
								value="${atividade.descricao}"> <label
								for="descricaoAtividade">Descrição</label>
							<div class="invalid-feedback" id="erro_descricaoAtividade"></div>
						</div>
						<div class="form-floating mb-3">
							<input type="date" class="form-control" id="inicioAtividade"
								name="data_inicio" placeholder="Data de início..."
								value="${atividade.data_inicio}"> <label
								for="inicioAtividade">Data de início</label>
							<div class="invalid-feedback" id="erro_inicioAtividade"></div>
						</div>
						<div class="form-floating">
							<input type="date" class="form-control" id="fimAtividade"
								name="data_fim" placeholder="Data final..."
								value="${atividade.data_fim}"> <label for="fimAtividade">Data
								final</label>
							<div class="invalid-feedback" id="erro_fimAtividade"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button class="btn btn-primary" onclick="validarAtividade()">Editar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal modal-lg fade" id="removerAtividade" tabindex="-1"
		aria-labelledby="removerAtividade" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Remover atividade</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Você tem certeza de que deseja remover a atividade
						${atividade.titulo}? Essa ação não pode ser desfeita.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<a type="button" class="btn btn-danger"
						href="professor?action=removerAtividade&id=${atividade.id_atividade}">Remover</a>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="../footer.jsp"></c:import>
