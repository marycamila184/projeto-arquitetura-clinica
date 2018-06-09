package com.up.clinica.servico;

public interface IService<T, U> {

	String listar() throws Exception;

	void alterar(T objeto) throws Exception;

	void cadastrar(T objeto) throws Exception;

	void remover(String id) throws Exception;

	String buscar(String id) throws Exception;
}
