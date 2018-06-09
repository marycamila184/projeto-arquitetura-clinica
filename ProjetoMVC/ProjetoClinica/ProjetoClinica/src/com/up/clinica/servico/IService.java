package com.up.clinica.servico;

public interface IService<T, U> {
	
	public String listar() throws Exception;
	
	public String buscar(U id) throws Exception;
	
	public void remover(U id) throws Exception;
	
	public void alterar(T objeto) throws Exception;
	
	public void cadastrar(T objeto) throws Exception;

}
