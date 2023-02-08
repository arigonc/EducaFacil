function validarCadastrarUsuario() {
	var erro = 0;
	var formato = /\S+@\S+\.\S+/;
	var nome = document.getElementById('nome');
	var email = document.getElementById('email');
	var senha = document.getElementById('senha');
	var erro_nome = document.getElementById('erro_nome');
	var erro_email = document.getElementById('erro_email');
	var erro_senha = document.getElementById('erro_senha');

	if (nome.value == "") {
		nome.classList.add("is-invalid");
		erro_nome.innerHTML = "Informe o seu nome.";
		erro++;
	} else if (nome.value.length < 3 || nome.value.lenth > 250) {
		nome.classList.add("is-invalid");
		erro_nome.innerHTML = "O minímo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		nome.classList.remove("is-invalid");
		nome.classList.add("is-valid");
	}

	if (email.value == "") {
		email.classList.add("is-invalid");
		erro_email.innerHTML = "Informe o seu email.";
		erro++;
	} else if (formato.test(email.value) == false) {
		email.classList.add("is-invalid");
		erro_email.innerHTML = "Informe um email válido.";
		erro++;
	} else if (email.value.length < 3 || email.value.lenth > 250) {
		email.classList.add("is-invalid");
		erro_email.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		email.classList.remove("is-invalid");
		email.classList.add("is-valid");
	}

	if (senha.value == "") {
		senha.classList.add("is-invalid");
		erro_senha.innerHTML = "Informe a sua senha.";
		erro++;
	} else if (senha.value.length < 8 || senha.value.length > 15) {
		senha.classList.add("is-invalid");
		erro_senha.innerHTML = "Sua senha deve ter de 8 a 15 caracteres.";
		erro++;
	} else {
		senha.classList.remove("is-invalid");
		senha.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formCadastrarUsuario').submit();
	}

}

function validarLoginUsuario() {
	var erro = 0;
	var email = document.getElementById('email');
	var senha = document.getElementById('senha');
	var erro_email = document.getElementById('erro_email');
	var erro_senha = document.getElementById('erro_senha');

	if (email.value == "") {
		email.classList.add("is-invalid");
		erro_email.innerHTML = "Informe o seu email.";
		erro++;
	} else {
		email.classList.remove("is-invalid");
		email.classList.add("is-valid");
	}

	if (senha.value == "") {
		senha.classList.add("is-invalid");
		erro_senha.innerHTML = "Informe a sua senha.";
		erro++;
	} else {
		senha.classList.remove("is-invalid");
		senha.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formLoginUsuario').submit();
	}

}

function validarPerfilContaUsuario() {
	var erro = 0;
	var nome = document.getElementById('nome');
	var erro_nome = document.getElementById('erro_nome');

	if (nome.value == "") {
		nome.classList.add("is-invalid");
		erro_nome.innerHTML = "Informe o seu nome.";
		erro++;
	} else if (nome.value.length < 3 || nome.value.lenth > 250) {
		nome.classList.add("is-invalid");
		erro_nome.innerHTML = "O minímo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		nome.classList.remove("is-invalid");
		nome.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formPerfilContaUsuario').submit();
	}

}

function validarPerfilSenhaUsuario() {
	var erro = 0;
	var senha = document.getElementById('senha');
	var erro_senha = document.getElementById('erro_senha');

	if (senha.value == "") {
		senha.classList.add("is-invalid");
		erro_senha.innerHTML = "Informe a sua senha.";
		erro++;
	} else if (senha.value.length < 8 || senha.value.length > 15) {
		senha.classList.add("is-invalid");
		erro_senha.innerHTML = "Sua senha deve ter de 8 a 15 caracteres.";
		erro++;
	} else {
		senha.classList.remove("is-invalid");
		senha.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formPerfilSenhaUsuario').submit();
	}

}

function validarSala() {
	var erro = 0;
	var titulo = document.getElementById('tituloSala');
	var descricao = document.getElementById('descricaoSala');
	var materia = document.getElementById('materia');
	var cor = document.getElementById('cor');
	var cor_back = document.getElementById('cor_back');
	var erro_titulo = document.getElementById('erro_tituloSala');
	var erro_descricao = document.getElementById('erro_descricaoSala');
	var erro_materia = document.getElementById('erro_materia');
	var erro_cor = document.getElementById('erro_cor');
	var erro_cor_back = document.getElementById('erro_cor_back');

	if (titulo.value == "") {
		titulo.classList.add("is-invalid");
		erro_titulo.innerHTML = "Informe um título.";
		erro++;
	} else if (titulo.value.length < 3 || titulo.value.lenth > 250) {
		titulo.classList.add("is-invalid");
		erro_titulo.innerHTML = "O minímo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		titulo.classList.remove("is-invalid");
		titulo.classList.add("is-valid");
	}

	if (descricao.value == "") {
		descricao.classList.add("is-invalid");
		erro_descricao.innerHTML = "Informe uma descrição.";
		erro++;
	} else if (descricao.value.length < 3 || descricao.value.lenth > 250) {
		descricao.classList.add("is-invalid");
		erro_descricao.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		descricao.classList.remove("is-invalid");
		descricao.classList.add("is-valid");
	}

	if (materia.value == "") {
		materia.classList.add("is-invalid");
		erro_materia.innerHTML = "Informe uma matéria.";
		erro++;
	} else if (materia.value.length < 3 || materia.value.lenth > 250) {
		materia.classList.add("is-invalid");
		erro_materia.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		materia.classList.remove("is-invalid");
		materia.classList.add("is-valid");
	}

	if (cor.value == "") {
		cor.classList.add("is-invalid");
		erro_cor.innerHTML = "Informe uma cor de leitura.";
		erro++;
	} else {
		cor.classList.remove("is-invalid");
		cor.classList.add("is-valid");
	}

	if (cor_back.value == "") {
		cor_back.classList.add("is-invalid");
		erro_cor_back.innerHTML = "Informe uma cor de fundo.";
		erro++;
	} else {
		cor_back.classList.remove("is-invalid");
		cor_back.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formSala').submit();
	}

}

function validarAula() {
	var erro = 0;
	var titulo = document.getElementById('tituloAula');
	var descricao = document.getElementById('descricaoAula');
	var video = document.getElementById('video');
	var inicio = document.getElementById('inicioAula');
	var fim = document.getElementById('fimAula');
	var erro_titulo = document.getElementById('erro_tituloAula');
	var erro_descricao = document.getElementById('erro_descricaoAula');
	var erro_video = document.getElementById('erro_video');
	var erro_inicio = document.getElementById('erro_inicioAula');
	var erro_fim = document.getElementById('erro_fimAula');

	if (titulo.value == "") {
		titulo.classList.add("is-invalid");
		erro_titulo.innerHTML = "Informe um título.";
		erro++;
	} else if (titulo.value.length < 3 || titulo.value.lenth > 250) {
		titulo.classList.add("is-invalid");
		erro_titulo.innerHTML = "O minímo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		titulo.classList.remove("is-invalid");
		titulo.classList.add("is-valid");
	}

	if (descricao.value == "") {
		descricao.classList.add("is-invalid");
		erro_descricao.innerHTML = "Informe uma descrição.";
		erro++;
	} else if (descricao.value.length < 3 || descricao.value.lenth > 250) {
		descricao.classList.add("is-invalid");
		erro_descricao.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		descricao.classList.remove("is-invalid");
		descricao.classList.add("is-valid");
	}

	if (video.value == "") {
		video.classList.add("is-invalid");
		erro_video.innerHTML = "Informe o código de um vídeo.";
		erro++;
	} else if (video.value.length != 11) {
		video.classList.add("is-invalid");
		erro_video.innerHTML = "O código tem 11 caracteres. Informe um código válido.";
		erro++;
	} else {
		video.classList.remove("is-invalid");
		video.classList.add("is-valid");
	}

	if (inicio.value == "") {
		inicio.classList.add("is-invalid");
		erro_inicio.innerHTML = "Informe uma data de início.";
		erro++;
	} else {
		inicio.classList.remove("is-invalid");
		inicio.classList.add("is-valid");
	}

	if (fim.value == "") {
		fim.classList.add("is-invalid");
		erro_fim.innerHTML = "Informe uma data final.";
		erro++;
	} else {
		fim.classList.remove("is-invalid");
		fim.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formAula').submit();
	}

}

function validarAtividade() {
	var erro = 0;
	var titulo = document.getElementById('tituloAtividade');
	var descricao = document.getElementById('descricaoAtividade');
	var inicio = document.getElementById('inicioAtividade');
	var fim = document.getElementById('fimAtividade');
	var erro_titulo = document.getElementById('erro_tituloAtividade');
	var erro_descricao = document.getElementById('erro_descricaoAtividade');
	var erro_inicio = document.getElementById('erro_inicioAtividade');
	var erro_fim = document.getElementById('erro_fimAtividade');

	if (titulo.value == "") {
		titulo.classList.add("is-invalid");
		erro_titulo.innerHTML = "Informe um título.";
		erro++;
	} else if (titulo.value.length < 3 || titulo.value.lenth > 250) {
		titulo.classList.add("is-invalid");
		erro_titulo.innerHTML = "O minímo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		titulo.classList.remove("is-invalid");
		titulo.classList.add("is-valid");
	}

	if (descricao.value == "") {
		descricao.classList.add("is-invalid");
		erro_descricao.innerHTML = "Informe uma descrição.";
		erro++;
	} else if (descricao.value.length < 3 || descricao.value.lenth > 250) {
		descricao.classList.add("is-invalid");
		erro_descricao.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		descricao.classList.remove("is-invalid");
		descricao.classList.add("is-valid");
	}

	if (inicio.value == "") {
		inicio.classList.add("is-invalid");
		erro_inicio.innerHTML = "Informe uma data de início.";
		erro++;
	} else {
		inicio.classList.remove("is-invalid");
		inicio.classList.add("is-valid");
	}

	if (fim.value == "") {
		fim.classList.add("is-invalid");
		erro_fim.innerHTML = "Informe uma data final.";
		erro++;
	} else {
		fim.classList.remove("is-invalid");
		fim.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formAtividade').submit();
	}

}

function validarQuestaoObjetiva(i) {
	var erro = 0;
	var pergunta = document.getElementById('perguntaObjetiva' + i);
	var altA = document.getElementById('altA' + i);
	var altB = document.getElementById('altB' + i);
	var altC = document.getElementById('altC' + i);
	var altD = document.getElementById('altD' + i);
	var altE = document.getElementById('altE' + i);
	var gabarito = document.getElementById('gabaritoObjetiva' + i);
	var erro_pergunta = document.getElementById('erro_perguntaObjetiva' + i);
	var erro_altA = document.getElementById('erro_altA' + i);
	var erro_altB = document.getElementById('erro_altB' + i);
	var erro_altC = document.getElementById('erro_altC' + i);
	var erro_altD = document.getElementById('erro_altD' + i);
	var erro_altE = document.getElementById('erro_altE' + i);
	var erro_gabarito = document.getElementById('erro_gabaritoObjetiva' + i);

	if (pergunta.value == "") {
		pergunta.classList.add("is-invalid");
		erro_pergunta.innerHTML = "Informe uma pergunta.";
		erro++;
	} else if (pergunta.value.length < 3 || pergunta.value.lenth > 600) {
		pergunta.classList.add("is-invalid");
		erro_pergunta.innerHTML = "O minímo de caracteres são 3 e o máximo são 600.";
		erro++;
	} else {
		pergunta.classList.remove("is-invalid");
		pergunta.classList.add("is-valid");
	}

	if (altA.value == "") {
		altA.classList.add("is-invalid");
		erro_altA.innerHTML = "Informe uma alternativa A.";
		erro++;
	} else if (altA.value.length < 3 || altA.value.lenth > 250) {
		altA.classList.add("is-invalid");
		erro_altA.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		altA.classList.remove("is-invalid");
		altA.classList.add("is-valid");
	}

	if (altB.value == "") {
		altB.classList.add("is-invalid");
		erro_altB.innerHTML = "Informe uma alternativa B.";
		erro++;
	} else if (altB.value.length < 3 || altB.value.lenth > 250) {
		altB.classList.add("is-invalid");
		erro_altB.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		altB.classList.remove("is-invalid");
		altB.classList.add("is-valid");
	}

	if (altC.value == "") {
		altC.classList.add("is-invalid");
		erro_altC.innerHTML = "Informe uma alternativa C.";
		erro++;
	} else if (altC.value.length < 3 || altC.value.lenth > 250) {
		altC.classList.add("is-invalid");
		erro_altC.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		altC.classList.remove("is-invalid");
		altC.classList.add("is-valid");
	}

	if (altD.value == "") {
		altD.classList.add("is-invalid");
		erro_altD.innerHTML = "Informe uma alternativa D.";
		erro++;
	} else if (altD.value.length < 3 || altD.value.lenth > 250) {
		altD.classList.add("is-invalid");
		erro_altD.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		altD.classList.remove("is-invalid");
		altD.classList.add("is-valid");
	}

	if (altE.value == "") {
		altE.classList.add("is-invalid");
		erro_altE.innerHTML = "Informe uma alternativa E.";
		erro++;
	} else if (altE.value.length < 3 || altE.value.lenth > 250) {
		altE.classList.add("is-invalid");
		erro_altE.innerHTML = "O mínimo de caracteres são 3 e o máximo são 250.";
		erro++;
	} else {
		altE.classList.remove("is-invalid");
		altE.classList.add("is-valid");
	}

	if (gabarito.value == "") {
		gabarito.classList.add("is-invalid");
		erro_gabarito.innerHTML = "Informe um gabarito.";
		erro++;
	} else {
		gabarito.classList.remove("is-invalid");
		gabarito.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formQuestaoObjetiva' + i).submit();
	}

}

function validarQuestaoDiscursiva(i) {
	var erro = 0;
	var pergunta = document.getElementById('perguntaDiscursiva' + i);
	var gabarito = document.getElementById('gabaritoDiscursiva' + i);
	var erro_pergunta = document.getElementById('erro_perguntaDiscursiva' + i);
	var erro_gabarito = document.getElementById('erro_gabaritoDiscursiva' + i);

	if (pergunta.value == "") {
		pergunta.classList.add("is-invalid");
		erro_pergunta.innerHTML = "Informe uma pergunta.";
		erro++;
	} else if (pergunta.value.length < 3 || pergunta.value.lenth > 600) {
		pergunta.classList.add("is-invalid");
		erro_pergunta.innerHTML = "O minímo de caracteres são 3 e o máximo são 600.";
		erro++;
	} else {
		pergunta.classList.remove("is-invalid");
		pergunta.classList.add("is-valid");
	}

	if (gabarito.value == "") {
		gabarito.classList.add("is-invalid");
		erro_gabarito.innerHTML = "Informe um gabarito.";
		erro++;
	} else if (gabarito.value.length < 3 || gabarito.value.lenth > 600) {
		gabarito.classList.add("is-invalid");
		erro_gabarito.innerHTML = "O minímo de caracteres são 3 e o máximo são 600.";
		erro++;
	} else {
		gabarito.classList.remove("is-invalid");
		gabarito.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formQuestaoDiscursiva' + i).submit();
	}

}

function validarComentario(i) {
	var erro = 0;
	var comentario = document.getElementById('comentario' + i);
	var erro_comentario = document.getElementById('erro_comentario' + i);

	if (comentario.value == "") {
		comentario.classList.add("is-invalid");
		erro_comentario.innerHTML = "Informe um comentário.";
		erro++;
	} else if (comentario.value.length < 3 || comentario.value.lenth > 600) {
		comentario.classList.add("is-invalid");
		erro_comentario.innerHTML = "O minímo de caracteres são 3 e o máximo são 600.";
		erro++;
	} else {
		comentario.classList.remove("is-invalid");
		comentario.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formComentario' + i).submit();
	}

}

function validarSubcomentario(i) {
	var erro = 0;
	var resposta = document.getElementById('resposta' + i);
	var erro_resposta = document.getElementById('erro_resposta' + i);

	if (resposta.value == "") {
		resposta.classList.add("is-invalid");
		erro_resposta.innerHTML = "Informe uma resposta.";
		erro++;
	} else if (resposta.value.length < 3 || resposta.value.lenth > 600) {
		resposta.classList.add("is-invalid");
		erro_resposta.innerHTML = "O minímo de caracteres são 3 e o máximo são 600.";
		erro++;
	} else {
		resposta.classList.remove("is-invalid");
		resposta.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formSubcomentario' + i).submit();
	}

}

function validarEntradaSala() {
	var erro = 0;
	var codigo = document.getElementById('codigo');
	var erro_codigo = document.getElementById('erro_codigo');

	if (codigo.value == "") {
		codigo.classList.add("is-invalid");
		erro_codigo.innerHTML = "Informe um código.";
		erro++;
	} else {
		resposta.classList.remove("is-invalid");
		resposta.classList.add("is-valid");
	}

	if (erro == 0) {
		document.getElementById('formEntradaSala').submit();
	}

}

function validarResponderAtividade(i) {
	var erro = 0;

	for (j = 1; j <= i; j++) {
		var resposta = document.getElementById('resposta' + j);
		var erro_resposta = document.getElementById('erro_resposta' + j);
		var altA = document.getElementById('altA' + j);
		var altB = document.getElementById('altB' + j);
		var altC = document.getElementById('altC' + j);
		var altD = document.getElementById('altD' + j);
		var altE = document.getElementById('altE' + j);
		var erro_altA = document.getElementById('erro_altA' + j);
		var erro_altB = document.getElementById('erro_altB' + j);
		var erro_altC = document.getElementById('erro_altC' + j);
		var erro_altD = document.getElementById('erro_altD' + j);
		var erro_altE = document.getElementById('erro_altE' + j);

		if (resposta != null) {
			if (resposta.value == "") {
				resposta.classList.add("is-invalid");
				erro_resposta.innerHTML = "Informe uma resposta.";
				erro++;
			} else if (resposta.value.length < 3 || resposta.value.lenth > 600) {
				resposta.classList.add("is-invalid");
				erro_resposta.innerHTML = "O minímo de caracteres são 3 e o máximo são 600.";
				erro++;
			} else {
				resposta.classList.remove("is-invalid");
				resposta.classList.add("is-valid");
			}
		} else {
			if (altA.checked == false && altB.checked == false && altC.checked == false && altD.checked == false && altE.checked == false) {
				altA.classList.add("is-invalid");
				altB.classList.add("is-invalid");
				altC.classList.add("is-invalid");
				altD.classList.add("is-invalid");
				altE.classList.add("is-invalid");
				erro_altA.innerHTML = "Selecione alguma alternativa.";
				erro_altB.innerHTML = "Selecione alguma alternativa.";
				erro_altC.innerHTML = "Selecione alguma alternativa.";
				erro_altD.innerHTML = "Selecione alguma alternativa.";
				erro_altE.innerHTML = "Selecione alguma alternativa.";
				erro++;
			} else {
				altA.classList.remove("is-invalid");
				altA.classList.add("is-valid");
				altB.classList.remove("is-invalid");
				altB.classList.add("is-valid");
				altC.classList.remove("is-invalid");
				altC.classList.add("is-valid");
				altD.classList.remove("is-invalid");
				altD.classList.add("is-valid");
				altE.classList.remove("is-invalid");
				altE.classList.add("is-valid");
			}
		}
	}

	if (erro == 0) {
		document.getElementById('formResponderAtividade').submit();
	}

}

function validarCorrecao(i) {
	var erro = 0;

	for (j = 1; j <= i; j++) {
		var correcao = document.getElementById('correcao' + j);
		var erro_correcao = document.getElementById('erro_correcao' + j);

		if (correcao != null) {
			if (correcao.value == "") {
				correcao.classList.add("is-invalid");
				erro_correcao.innerHTML = "Informe uma correção.";
				erro++;
			} else {
				correcao.classList.remove("is-invalid");
				correcao.classList.add("is-valid");
			}
		}
	}

	if (erro == 0) {
		document.getElementById('formCorrecao').submit();
	}

}