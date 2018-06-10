$(document).ready(function() {
	//Carregando a tabela
	setTable();
	
	$("#btn-excluir").click(function(event) {
		var dataRowName = $('tr.selected').attr('data-row-name');

		//Espero a confirmação do usuario
		if (confirm("Deseja deletar o animal " + dataRowName + "?")) {

			var id = $('tr.selected').attr('id');

			$.ajax({
				url : "/ProjetoClinica/Animais?servico=remover&id=" + id,
				type : "GET",
				success : function(data, textStatus, xhr) {					
					setTable();
					$('#mensagem').html('<div class="alert alert-success" role="alert"><strong>Concluído!</strong> O animal foi deletado com sucesso.</div>');
				},
				error : function(xhr, textStatus) {
					console.log(xhr.status + " - " + textStatus);
					$('#mensagem').html('<div class="alert alert-danger" role="alert"><strong>Ops!</strong> Ocorreu um erro, tente novamente mais tarde.</div>');
				}
			});
		}
	});
	
	$("#btn-modal-cadastrar").click(function(event) {
		//Altero o titulo da modal
		$('#modal-title').text('Infomações do animal');
		$('#nome').val('');
		$('#nascimento').val('');
		$('#especies').val("-1");	
		$('#nascimento').mask("00/00/0000", {placeholder: "__/__/____"});
	});
	
	$("#btn-modal-alterar").click(function(event) {
		
		//Altero o titulo da modal
		var id = $('tr.selected').attr('id');
		$('#modal-title').text('Informações do animal #'+id);
		
		$('#nascimento').mask("00/00/0000", {placeholder: "__/__/____"});
		
		//Preencho os campos da modal
		$.ajax({
			url : "/ProjetoClinica/Animais?servico=buscar&id=" + id,
			type : "GET",
			success : function(data, textStatus, xhr) {	
				$('#id').val(data.animais.id);
				$('#nome').val(data.animais.nome);
				$('#nascimento').val(data.animais.nascimento);
				$('#especies').val(data.animais.especie.id);
				$('#myModal').modal('show');				
			},
			error : function(xhr, textStatus) {
				console.log(xhr.status + " - " + textStatus);
				$('#myModal').modal('hide');
				$('#mensagem').html('<div class="alert alert-danger" role="alert"><strong>Ops!</strong> Ocorreu um erro, tente novamente mais tarde.</div>');
			}
		});
	});

	$("#btn-cadastrar").click(function(event) {
		var objeto = {
			id : $('#id').val(),
			nome : $('#nome').val(),
			nascimento : $('#nascimento').val(),
			idespecie : $('#especies').val()
		}

		$.ajax({
			type : "POST",
			url : "/ProjetoClinica/Animais?servico=cadastrar",
			data : JSON.stringify(objeto),
			contentType : "application/json; charset=utf-8",
			success : function(data, textStatus, xhr) {
				$('#myModal').modal('hide');
				setTable();
				$('#mensagem').html('<div class="alert alert-success" role="alert"><strong>Concluído!</strong> O animal foi cadastrado com sucesso.</div>');
			},
			error : function(xhr, textStatus) {
				console.log(xhr.status + " - " + textStatus);
				if(xhr.status == 400){
					$('#mensagem-modal').html('<div class="alert alert-danger" role="alert"><strong>Ops!</strong> Preencha todos os campos corretamente.</div>');
				}else{
					$('#mensagem-modal').html('<div class="alert alert-danger" role="alert"><strong>Ops!</strong> Ocorreu um erro, tente novamente mais tarde.</div>');
				}				
			}
		});
	});

	$("#btn-alterar").click(function(event) {
		var objeto = {
			id : $('#id').val(),
			nome : $('#nome').val(),
			nascimento : $('#nascimento').val(),
			idespecie : $('#especies').val()
		}

		$.ajax({
			type : "POST",
			url : "/ProjetoClinica/Animais?servico=alterar",
			data : JSON.stringify(objeto),
			contentType : "application/json; charset=utf-8",
			success : function(data, textStatus, xhr) {
				$('#myModal').modal('hide');
				setTable();
				$('#mensagem-modal').html('<div class="alert alert-success" role="alert"><strong>Concluído!</strong> O animal foi alterado com sucesso.</div>');
			},
			error : function(xhr, textStatus) {
				console.log(xhr.status + " - " + textStatus);
				if(xhr.status == 400){
					$('#mensagem-modal').html('<div class="alert alert-danger" role="alert"><strong>Ops!</strong> Preencha todos os campos corretamente.</div>');
				}else{
					$('#mensagem-modal').html('<div class="alert alert-danger" role="alert"><strong>Ops!</strong> Ocorreu um erro, tente novamente mais tarde.</div>');
				}		
			}
		});
	});
});

$("#especies").ready(function(event) {
	$.get("/ProjetoClinica/Especies?servico=listar", function(data) {
		console.log(data.especies);
		$.each(data.especies, function(key, value) {
			$("#especies").append('<option value="-1" disabled selected>Escolha a espécie</option>');
			$("#especies").append("<option value='"+ value.id+"'>"+ value.nome+"</option>");
		});
	});
});

function setTable() {
	$.get("/ProjetoClinica/Animais?servico=listar", function(data) {
		$("#table tbody tr").remove();
		$.each(data.animais, function(key, value) {			
			$('#table tbody').append(
					'<tr id="' + value.id + '" data-row-name="' + value.nome
							+ '"><td>' + value.id + '</td><td>' + value.nome
							+ '</td><td>' + value.nascimento + '</td><td>'
							+ value.especie.nome + '</td></tr>');
		});
		
		$('#table tr').click(function(event) {
			$('.selected').removeClass('selected');
			$(this).addClass("selected");
			$('#btn-modal-alterar').removeAttr('disabled');
			$('#btn-excluir').removeAttr('disabled');
		});
	});	
}
