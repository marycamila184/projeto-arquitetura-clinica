$(document).ready(
		function() {
			$.get("/ProjetoClinica/Especies?servico=listar", function(data) {
				$.each(data.especies, function(key, value) {
					$('#table').append(
							'<tr id="' + value.id + '" data-row-name="'
									+ value.nome + '"><td>' + value.id
									+ '</td><td>' + value.nome + '</td><td>'
									+ value.descricao + '</td><td>'
									+ value.tipoAnimal.nome + '</td></tr>');
				});

				$('#table tr').click(function(event) {
					$('.selected').removeClass('selected');
					$(this).addClass("selected");
				});

				$("#btn-excluir").click(
						function(event) {
							var tid = $('tr.selected').attr('id');
							var dataRowName = $('tr.selected').attr(
									'data-row-name');

							if (confirm("Deseja deletar a espécie "
									+ dataRowName + "?")) {
								$.get(
										"/ProjetoClinica/Especies?servico=remover&id="
												+ tid, function(data) {
											$('#' + tid).remove();
										});
							}
						});
			});
		});