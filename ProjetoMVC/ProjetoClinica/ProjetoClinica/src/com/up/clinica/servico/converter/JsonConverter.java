package com.up.clinica.servico.converter;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class JsonConverter<T> implements IJsonConverter<T> {

	protected Gson gson = null;

	public JsonConverter() {
		gson = new GsonBuilder().create();
	}

	private final String convertToJson(JsonElement jsonElement) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.add(tipoClasseString(), gson.toJsonTree(jsonElement));
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
	
	public abstract T convertToObject(String jsonString);

	protected abstract String tipoClasseString();
}
