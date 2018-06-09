package com.up.clinica.servico;

import com.up.clinica.model.dal.AbstractDAO;
import com.up.clinica.servico.converter.JsonConverter;

public abstract class AbstractService<T, U> implements IService<T, U> {

	@Override
	public String listar() throws Exception {
		return tipoJsonConverter().convertToJsonString(tipoDao().listar());
	}

	@Override
	public String buscar(U id) throws Exception {
		return tipoJsonConverter().convertToJsonString(tipoDao().buscar(id));
	}

	@Override
	public void remover(U id) throws Exception {
		tipoDao().remover(id);
	}

	@Override
	public void alterar(T objeto) throws Exception {
		tipoDao().atualizar(objeto);
	}

	@Override
	public void cadastrar(T objeto) throws Exception {
		tipoDao().persistir(objeto);
	}

	protected abstract AbstractDAO tipoDao();

	protected abstract JsonConverter tipoJsonConverter();
}
