$(document).ready(
		function() {

			var idRow = "";
			var dataRowName = "";

			$.get("/ProjetoClinica/Animais?servico=listar", function(data) {
				$.each(data.animais, function(key, value) {
					$('#table').append(
							'<tr id="' + value.id + '" data-row-name="'
									+ value.nome + '"><td>' + value.id
									+ '</td><td>' + value.nome + '</td><td>'
									+ value.nascimento + '</td><td>'
									+ value.especie.nome + '</td></tr>');
				});
			});

			$('#table').click(function(event) {

				$('.selected').removeClass('selected');
				$(this).addClass("selected");

				idRow = $(this).attr('id');
				dataRowName = $(this).attr('data-row-name');
			});

			$("#btn-excluir").click(
					function() {
						if (confirm("Deseja deletar o animal " + dataRowName
								+ "?")) {
							$.get("/ProjetoClinica/Animais?servico=remover&id="
									+ idRow, function(data) {
								$('#' + idRow).remove();
							});

						}
					});

			$('#btn-cadastrar').click(function(event) {
					
			});

			$('#btn-atualizar').click(function(event) {

			});
		});