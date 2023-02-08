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
			<li class="breadcrumb-item active" aria-current="page">Sala
				${sala.titulo}</li>
		</ol>
	</nav>
	<c:if test="${sala.situacao == 'Ativo'}">
		<div class="card mb-4"
			style="color: ${sala.cor}; background-color: ${sala.cor_back}; border-color: ${sala.cor_back};">
			<div class="card-body">
				<div class="row align-middle">
					<div class="col-md-9">
						<h5 class="card-title">
							<i class="bi bi-journal mx-2"></i>${sala.titulo}</h5>
					</div>
					<div class="col-md-3 text-end">
						<span class="badge bg-success">Ativo</span>
					</div>
				</div>
				<p class="card-text">${sala.descricao}</p>
			</div>
		</div>
		<div class="row align-middle">
			<div class="col">
				<p class="fs-5">Aulas</p>
			</div>
		</div>
		<c:if test="${empty aulas}">
			<p class="fs-6">Nenhuma aula cadastrada ainda!</p>
		</c:if>
		<c:forEach var="aula" items="${aulas}">
			<a href="aluno?action=aula&id=${aula.id_aula}"
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
		</div>
		<c:if test="${empty atividades}">
			<p class="fs-6">Nenhuma atividade cadastrada ainda!</p>
		</c:if>
		<c:forEach var="atividade" items="${atividades}">
			<a href="aluno?action=atividade&id=${atividade.id_atividade}"
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
				está pendente. Seu(sua) professor(a) deve aceitar a sua entrada na
				sala. Certifique-se com ele(a) sobre sua solicitação ou <a
					href="aluno?action=cancelarEntradaSala&id=${sala.id}">cancele
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
				rejeitada. Comunique com seu(sua) professor(a) se isso for um erro
				ou <a href="aluno?action=cancelarEntradaSala&id=${sala.id}">cancele
					sua entrada nessa sala</a>.
			</div>
		</div>
	</c:if>
</section>

<c:import url="../footer.jsp"></c:import>