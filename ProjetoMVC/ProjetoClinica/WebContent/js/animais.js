$(document).ready(
		function() {
			$.get("/ProjetoClinica/Animais?servico=listar", function(data) {
				$.each(data.animais, function(key, value) {
					$('#table').append(
							'<tr id="' + value.id + '" data-row-name="'
									+ value.nome + '"><td>' + value.id
									+ '</td><td>' + value.nome + '</td><td>'
									+ value.nascimento + '</td><td>'
									+ value.especie.nome + '</td></tr>');
				});
				
				$('#table tr').click(function(event) {
					$('.selected').removeClass('selected');
					$(this).addClass("selected");					
				});
				
				$("#btn-excluir").click(function(event) {
					var tid = $('tr.selected').attr('id');
					var dataRowName = $('tr.selected').attr('data-row-name');

					if (confirm("Deseja deletar o animal " + dataRowName + "?")) {
						$.get("/ProjetoClinica/Animais?servico=remover&id=" + tid, function(data) {
							$('#' + tid).remove();
						});
					}
				});
			});
		});

