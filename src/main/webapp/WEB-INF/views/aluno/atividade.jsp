<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="hoje" value="${now}" pattern="yyyy-MM-dd" />
<c:import url="../header.jsp"></c:import>

<section style="min-height: 100vh;" class="m-4">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb border rounded p-3">
			<li class="breadcrumb-item"><a href="aluno?action=index">Minhas
					salas</a></li>
			<li class="breadcrumb-item"><a
				href="aluno?action=sala&id=${sala.id}">Sala ${sala.titulo}</a></li>
			<li class="breadcrumb-item active" aria-current="page">Atividade
				${atividade.titulo}</li>
		</ol>
	</nav>
	<div class="card mt-4"
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
	<c:if
		test="${hoje >= atividade.data_inicio && hoje <= atividade.data_fim}">
		<c:if test="${realizado == false}">
			<div class="row align-middle mt-4">
				<div class="col">
					<p class="fs-5">Questões</p>
				</div>
			</div>
			<c:if test="${empty questoes}">
				<p class="fs-6">Nenhuma questão cadastrada ainda!</p>
			</c:if>
			<form
				action="aluno?action=responderAtividade&id=${atividade.id_atividade}"
				method="post" id="formResponderAtividade">
				<c:forEach var="questao" items="${questoes}">
					<div class="card w-100 mb-3" style="border-color: ${sala.cor_back}">
						<div class="card-body">
							<div class="row align-middle">
								<div class="col">
									<h5 class="card-title">Questão #${questao.ordem}</h5>
								</div>
							</div>
							<p class="card-text">${questao.pergunta}</p>
							<c:if test="${questao.tipo == 'Objetiva'}">
								<div class="invalid-feedback" id="erro_resposta${questao.ordem}"></div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="resposta${questao.ordem}" id="altA${questao.ordem}"
										value="A"> <label class="form-check-label"
										for="altA${questao.ordem}">a) ${questao.altA}</label>
									<div class="invalid-feedback" id="erro_altA${questao.ordem}"></div>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="resposta${questao.ordem}" id="altB${questao.ordem}"
										value="B"> <label class="form-check-label"
										for="altB${questao.ordem}">b) ${questao.altB}</label>
									<div class="invalid-feedback" id="erro_altB${questao.ordem}"></div>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="resposta${questao.ordem}" id="altC${questao.ordem}"
										value="C"> <label class="form-check-label"
										for="altC${questao.ordem}">c) ${questao.altC}</label>
									<div class="invalid-feedback" id="erro_altC${questao.ordem}"></div>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="resposta${questao.ordem}" id="altD${questao.ordem}"
										value="D"> <label class="form-check-label"
										for="altD${questao.ordem}">d) ${questao.altD}</label>
									<div class="invalid-feedback" id="erro_altD${questao.ordem}"></div>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="resposta${questao.ordem}" id="altE${questao.ordem}"
										value="E"> <label class="form-check-label"
										for="altE${questao.ordem}">e) ${questao.altE}</label>
									<div class="invalid-feedback" id="erro_altE${questao.ordem}"></div>
								</div>
							</c:if>
							<c:if test="${questao.tipo == 'Discursiva'}">
								<div class="form-floating">
									<textarea class="form-control" placeholder="Resposta..."
										id="resposta${questao.ordem}" name="resposta${questao.ordem}"
										maxlength="600" style="height: 120px" required></textarea>
									<label for="resposta${questao.ordem}">Resposta</label>
									<div class="invalid-feedback"
										id="erro_resposta${questao.ordem}"></div>
								</div>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</form>
			<button class="btn btn-sm btn-primary my-2"
				onclick="validarResponderAtividade(${questoes.size()})">Responder</button>
		</c:if>
		<c:if test="${realizado == true}">
			<div class="row align-middle mt-4">
				<div class="col">
					<p class="fs-5">Questões</p>
				</div>
				<div class="col text-end">
					<p class="fs-5">
						<span class="badge bg-dark">Nota: <fmt:formatNumber
								type="number" pattern="###.##" value="${nota}" />%
						</span>
					</p>
				</div>
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
						</div>
						<p class="card-text">${questao.pergunta}</p>
						<c:if test="${questao.tipo == 'Objetiva'}">
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altA${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'A'}">checked</c:if>>
								<label class="form-check-label" for="altA${questao.ordem}">a)
									${questao.altA}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altB${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'B'}">checked</c:if>>
								<label class="form-check-label" for="altB${questao.ordem}">b)
									${questao.altB}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altC${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'C'}">checked</c:if>>
								<label class="form-check-label" for="altC${questao.ordem}">c)
									${questao.altC}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altD${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'D'}">checked</c:if>>
								<label class="form-check-label" for="altD${questao.ordem}">d)
									${questao.altD}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altE${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'E'}">checked</c:if>>
								<label class="form-check-label" for="altE${questao.ordem}">e)
									${questao.altE}</label>
							</div>
							<c:if test="${questao.resposta.resposta == questao.gabarito}">
								<div class="alert alert-success mt-3" role="alert">
									<i class="bi bi-check-lg mx-1"></i>Alternativa correta!
								</div>
							</c:if>
							<c:if test="${questao.resposta.resposta != questao.gabarito}">
								<div class="alert alert-danger mt-3" role="alert">
									<i class="bi bi-x-lg mx-1"></i>Alternativa incorreta! Gabarito:
									Alternativa ${questao.gabarito}
								</div>
							</c:if>
						</c:if>
						<c:if test="${questao.tipo == 'Discursiva'}">
							<div class="form-floating">
								<textarea class="form-control" placeholder="Resposta..."
									id="resposta${questao.ordem}" maxlength="600"
									style="height: 120px" disabled>${questao.resposta.resposta}</textarea>
								<label for="resposta${questao.ordem}">Resposta</label>
							</div>
							<c:if test="${questao.resposta.correcao == 'Pendente'}">
								<div class="alert alert-warning mt-3" role="alert">
									<i class="bi bi-exclamation-triangle-fill mx-1"></i>Correção
									pendente! Aguarde seu(sua) professor(a) corrigir esta questão.
								</div>
							</c:if>
							<c:if test="${questao.resposta.correcao == 'Correto'}">
								<div class="alert alert-success mt-3" role="alert">
									<i class="bi bi-check-lg mx-1"></i>Resposta correta!
								</div>
							</c:if>
							<c:if test="${questao.resposta.correcao == 'Errado'}">
								<div class="alert alert-danger mt-3" role="alert">
									<i class="bi bi-x-lg mx-1"></i>Resposta incorreta! Gabarito:
									${questao.gabarito}
								</div>
							</c:if>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</c:if>
	<c:if test="${hoje > atividade.data_fim}">
		<c:if test="${realizado == false}">
			<div class="alert alert-danger d-flex align-items-center mt-3"
				role="alert">
				<i class="bi bi-lock-fill mx-1"></i>
				<div>
					<strong>Atenção!</strong> Esta atividade está fora do período de
					realização. Ela iniciou em
					<fmt:formatDate value="${atividade.data_inicio}"
						pattern="dd/MM/yyyy" />
					e finalizou em
					<fmt:formatDate value="${atividade.data_fim}" pattern="dd/MM/yyyy" />
					. Além disso, você não respondeu a atividade.
				</div>
			</div>
		</c:if>
		<c:if test="${realizado == true}">
			<c:forEach var="questao" items="${questoes}">
				<div class="card w-100 mb-3" style="border-color: ${sala.cor_back}">
					<div class="card-body">
						<div class="row align-middle">
							<div class="col">
								<h5 class="card-title">Questão #${questao.ordem}</h5>
							</div>
						</div>
						<p class="card-text">${questao.pergunta}</p>
						<c:if test="${questao.tipo == 'Objetiva'}">
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altA${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'A'}">checked</c:if>>
								<label class="form-check-label" for="altA${questao.ordem}">a)
									${questao.altA}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altB${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'B'}">checked</c:if>>
								<label class="form-check-label" for="altB${questao.ordem}">b)
									${questao.altB}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altC${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'C'}">checked</c:if>>
								<label class="form-check-label" for="altC${questao.ordem}">c)
									${questao.altC}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altD${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'D'}">checked</c:if>>
								<label class="form-check-label" for="altD${questao.ordem}">d)
									${questao.altD}</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									id="altE${questao.ordem}" disabled
									<c:if test="${questao.resposta.resposta == 'E'}">checked</c:if>>
								<label class="form-check-label" for="altE${questao.ordem}">e)
									${questao.altE}</label>
							</div>
							<c:if test="${questao.resposta.resposta == questao.gabarito}">
								<div class="alert alert-success mt-3" role="alert">
									<i class="bi bi-check-lg mx-1"></i>Alternativa correta!
								</div>
							</c:if>
							<c:if test="${questao.resposta.resposta != questao.gabarito}">
								<div class="alert alert-danger mt-3" role="alert">
									<i class="bi bi-x-lg mx-1"></i>Alternativa incorreta! Gabarito:
									Alternativa ${questao.gabarito}
								</div>
							</c:if>
						</c:if>
						<c:if test="${questao.tipo == 'Discursiva'}">
							<div class="form-floating">
								<textarea class="form-control" placeholder="Resposta..."
									id="resposta${questao.ordem}" maxlength="600"
									style="height: 120px" disabled>${questao.resposta.resposta}</textarea>
								<label for="resposta${questao.ordem}">Resposta</label>
							</div>
							<c:if test="${questao.resposta.correcao == 'Pendente'}">
								<div class="alert alert-warning mt-3" role="alert">
									<i class="bi bi-exclamation-triangle-fill mx-1"></i>Correção
									pendente!
								</div>
							</c:if>
							<c:if test="${questao.resposta.correcao == 'Correto'}">
								<div class="alert alert-success mt-3" role="alert">
									<i class="bi bi-check-lg mx-1"></i>Resposta correta!
								</div>
							</c:if>
							<c:if test="${questao.resposta.correcao == 'Errado'}">
								<div class="alert alert-danger mt-3" role="alert">
									<i class="bi bi-x-lg mx-1"></i>Resposta incorreta! Gabarito:
									${questao.gabarito}
								</div>
							</c:if>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</c:if>
	<c:if test="${hoje < atividade.data_inicio}">
		<div class="alert alert-danger d-flex align-items-center mt-3"
			role="alert">
			<i class="bi bi-lock-fill mx-1"></i>
			<div>
				<strong>Atenção!</strong> Esta atividade está fora do período de
				realização. Ela inicia em
				<fmt:formatDate value="${atividade.data_inicio}"
					pattern="dd/MM/yyyy" />
				e finaliza em
				<fmt:formatDate value="${atividade.data_fim}" pattern="dd/MM/yyyy" />
				. Após esse perído, a atividade ainda estará visível, porém você não
				poderá responder.
			</div>
		</div>
	</c:if>
</section>

<c:import url="../footer.jsp"></c:import>
