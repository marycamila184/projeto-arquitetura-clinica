package com.up.clinica.servico.converter;

import java.util.List;

public interface IJsonConverter<T> {

	public String convertToList(List<T> objetos);
	
	public String convertToObject(T objeto);
	
}
