package com.up.clinica.servico.converter;

import com.up.clinica.model.Especie;

public class EspecieJsonConverter extends JsonConverter<Especie> {

	@Override
	protected String tipoClasseString() {
		return "especies";
	}
	
	@Override
	public Especie convertToObject(String jsonString) {
		return gson.fromJson(jsonString, Especie.class);
	}

}
