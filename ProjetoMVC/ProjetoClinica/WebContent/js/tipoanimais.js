$(document).ready(
		function() {
			$.get("/ProjetoClinica/TipoAnimais?servico=listar", function(data) {
				$.each(data.tipoanimais, function(key, value) {
					$('#table').append(
							'<tr id="' + value.acronimo + '" data-row-name="'
									+ value.nome + '"><td>' + value.acronimo
									+ '</td><td>' + value.nome + '</td><td>'
									+ value.descrição + '</td></tr>');
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

							if (confirm("Deseja deletar o tipo de animal "
									+ dataRowName + "?")) {
								$.get(
										"/ProjetoClinica/TipoAnimais?servico=remover&id="
												+ tid, function(data) {
											$('#' + tid).remove();
										});
							}
						});
			});
		});
