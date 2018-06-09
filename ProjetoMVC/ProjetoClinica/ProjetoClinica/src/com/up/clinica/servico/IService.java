package com.up.clinica.servico;

public interface IService<T, U> {

	String listar() throws Exception;

	String buscar(U id) throws Exception;

	void remover(U id) throws Exception;

	void alterar(T objeto) throws Exception;

	void cadastrar(T objeto) throws Exception;
}
