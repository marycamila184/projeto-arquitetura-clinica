package com.up.clinica.servico.converter;

import com.up.clinica.model.TipoAnimal;

public class TipoAnimalConverter extends JsonConverter<TipoAnimal> {

	@Override
	protected String tipoClasse() {
		return "tipoanimais";
	}

}
