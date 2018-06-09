package com.up.clinica.servico.converter;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class JsonConverter<T> implements IJsonConverter<T> {

	private Gson gson = null;

	public JsonConverter() {
		gson = new GsonBuilder().create();
	}

	private final String convertToJson(JsonElement jsonElement) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.add(tipoClasse(), gson.toJsonTree(jsonElement));
		return jsonObject.toString();
	}

	@Override
	public String convertToJsonString(List<T> objetos) {
		JsonArray jarray = gson.toJsonTree(objetos).getAsJsonArray();
		return convertToJson(jarray);
	}

	@Override
	public String convertToJsonString(T objeto) {
		JsonObject jsonObject = new JsonObject();
		return convertToJson(jsonObject);
	}

	protected abstract String tipoClasse();
}
