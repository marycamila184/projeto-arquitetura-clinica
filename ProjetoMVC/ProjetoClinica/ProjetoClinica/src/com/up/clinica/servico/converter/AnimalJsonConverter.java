package com.up.clinica.servico.converter;

import com.up.clinica.model.Animal;

public class AnimalJsonConverter extends JsonConverter<Animal> {

	@Override
	protected String tipoClasse() {
		return "animais";
	}

}
