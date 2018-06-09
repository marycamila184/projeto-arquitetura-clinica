package com.up.clinica.servico.converter;

import com.up.clinica.model.TipoAnimal;

public class TipoAnimalConverter extends JsonConverter<TipoAnimal> {

	@Override
	protected String tipoClasseString() {
		return "tipoanimais";
	}
	
	@Override
	public TipoAnimal convertToObject(String jsonString) {
		return gson.fromJson(jsonString, TipoAnimal.class);
	}

}
