package com.up.clinica.servico.converter;

import java.util.List;

public interface IJsonConverter<T> {

	public String convertToJsonString(List<T> objetos);

	public String convertToJsonString(T objeto);

}
