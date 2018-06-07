$(document).ready(function() {
	
	var idRow = "";
	var dataRowName = "";
	
	$('#table tr').click(function(event) {
		
		$('.selected').removeClass('selected');
	    $(this).addClass("selected");
	    
		tid = $(this).attr('id');
		dataRowName = $(this).attr('data-row-name');
	});
	
	$("#btn-excluir").click(function() {
		var tipo = $('#tipo').text();
		
		if (tipo === "Animais") {
			tipo = "o animal";
		} else if (tipo === "Espécies") {
			tipo = "a espécie";
		} else {
			tipo = "o tipo animal";
		}
		
		if (confirm("Deseja deletar " + tipo + " " + dataRowName + "?")) {
			$('#' + tid).remove();
		}
	});
});