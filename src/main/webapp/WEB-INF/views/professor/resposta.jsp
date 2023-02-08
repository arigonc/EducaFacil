<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../header.jsp"></c:import>

<section style="min-height: 100vh;" class="m-4">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb border rounded p-3">
			<li class="breadcrumb-item"><a href="professor?action=index">Minhas
					salas</a></li>
			<li class="breadcrumb-item"><a
				href="professor?action=sala&id=${sala.id}">Sala ${sala.titulo}</a></li>
			<li class="breadcrumb-item"><a
				href="professor?action=atividade&id=${atividade.id_atividade}">Atividade
					${atividade.titulo}</a></li>
			<li class="breadcrumb-item active" aria-current="page">Resposta
				do aluno ${aluno.nome}</li>
		</ol>
	</nav>
	<c:if test="${realizado = false}">
		<div class="alert alert-danger d-flex align-items-center mt-3"
			role="alert">
			<i class="bi bi-lock-fill mx-1"></i>
			<div>
				<strong>Atenção!</strong> Esse aluno ainda não respondeu essa
				atividade.
			</div>
		</div>
	</c:if>
	<c:if test="${realizado = true}">
		<form
			action="professor?action=corrigirAtividade&id=${atividade.id_atividade}&email=${aluno.email}"
			method="post" id="formCorrecao">
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
									<div class="form-floating">
										<div class="form-floating mt-3">
											<select class="form-select" id="correcao${questao.ordem}"
												name="correcao${questao.ordem}">
												<option></option>
												<option value="Correto">Resposta correta</option>
												<option value="Errado">Resposta errada</option>
											</select> <label for="correcao${questao.ordem}">Correção</label>
											<div class="invalid-feedback"
												id="erro_correcao${questao.ordem}"></div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${questao.resposta.correcao == 'Correto'}">
								<div class="alert alert-success mt-3" role="alert">
									<i class="bi bi-check-lg mx-1"></i>Resposta correta!
									<div class="form-floating">
										<div class="form-floating mt-3">
											<select class="form-select" id="correcao${questao.ordem}"
												name="correcao${questao.ordem}">
												<option></option>
												<option value="Correto" selected>Resposta correta</option>
												<option value="Errado">Resposta errada</option>
											</select> <label for="correcao${questao.ordem}">Correção</label>
											<div class="invalid-feedback"
												id="erro_correcao${questao.ordem}"></div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${questao.resposta.correcao == 'Errado'}">
								<div class="alert alert-danger mt-3" role="alert">
									<i class="bi bi-x-lg mx-1"></i>Resposta incorreta! Gabarito:
									${questao.gabarito}
									<div class="form-floating">
										<div class="form-floating mt-3">
											<select class="form-select" id="correcao${questao.ordem}"
												name="correcao${questao.ordem}">
												<option></option>
												<option value="Correto">Resposta correta</option>
												<option value="Errado" selected>Resposta errada</option>
											</select> <label for="correcao${questao.ordem}">Correção</label>
											<div class="invalid-feedback"
												id="erro_correcao${questao.ordem}"></div>
										</div>
									</div>
								</div>
							</c:if>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</form>
		<button class="btn btn-sm btn-primary my-2"
			onclick="validarCorrecao(${questoes.size()})">Corrigir</button>
	</c:if>
</section>
<c:import url="../footer.jsp"></c:import>