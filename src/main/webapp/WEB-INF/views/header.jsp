<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EducaFácil</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="css/estilo.css">
</head>
<body>
	<c:if test="${empty sessionScope.alunoLogado}">
		<c:if test="${empty sessionScope.professorLogado}">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">EducaFácil</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
						aria-controls="navbarNavAltMarkup" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end"
						id="navbarNavAltMarkup">
						<div class="navbar-nav">
							<a class="nav-link active" aria-current="page"
								href="aluno?action=login">Sou aluno</a> <a
								class="nav-link active" href="professor?action=login">Sou
								professor</a>
						</div>
					</div>
				</div>
			</nav>
		</c:if>
	</c:if>
	<c:if test="${not empty sessionScope.alunoLogado}">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">EducaFácil</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link dropdown active dropdown-toggle" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-person-fill mx-2"></i>${sessionScope.alunoLogado.nome}
						</a>
						<ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end">
							<li><a class="dropdown-item" href="aluno?action=index">Minhas
									salas</a></li>
							<li><a class="dropdown-item" href="aluno?action=perfil">Meu
									perfil</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="aluno?action=logout">Sair</a></li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</c:if>
	<c:if test="${not empty sessionScope.professorLogado}">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">EducaFácil</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-link dropdown active dropdown-toggle" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-person-fill mx-2"></i>${sessionScope.professorLogado.nome}
						</a>
						<ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end">
							<li><a class="dropdown-item" href="professor?action=index">Minhas
									salas</a></li>
							<li><a class="dropdown-item" href="professor?action=perfil">Meu
									perfil</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="professor?action=logout">Sair</a></li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</c:if>