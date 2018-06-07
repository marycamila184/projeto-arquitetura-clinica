package com.up.clinica.servico.converter;

import com.up.clinica.model.Especie;

public class EspecieJsonConverter extends JsonConverter<Especie> {

	@Override
	protected String tipoClasse() {
		return "especies";
	}

}
