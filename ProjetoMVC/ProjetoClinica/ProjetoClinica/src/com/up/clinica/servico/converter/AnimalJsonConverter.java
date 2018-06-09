package com.up.clinica.servico.converter;

import com.up.clinica.model.Animal;

public class AnimalJsonConverter extends JsonConverter<Animal> {

	@Override
	protected String tipoClasseString() {
		return "animais";
	}
	
	@Override
	public Animal convertToObject(String jsonString) {
		return gson.fromJson(jsonString, Animal.class);
	}
}
