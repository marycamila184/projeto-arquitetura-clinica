package com.up.clinica.servico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.up.clinica.model.Animal;
import com.up.clinica.model.dal.AnimalDAO;
import com.up.clinica.servico.converter.AnimalJsonConverter;

@WebServlet(name = "Animais", urlPatterns = { "/Animais" })
public class AnimalService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected Services servico;

	public AnimalService() {
		// inicio o serviço com as dependencias
		servico = new Services(new AnimalDAO(), new AnimalJsonConverter());
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			servico.iniciaServico(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			servico.iniciaServico(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	private class Services extends AbstractService<AnimalJsonConverter, AnimalDAO, Animal, Long> {

		public Services(AnimalDAO dao, AnimalJsonConverter converter) {
			super(dao, converter);
		}

		@Override
		public boolean validatorModel(Animal objeto) {
			if (objeto.getNome().equals("") || objeto.getNascimento() == null || objeto.getEspecie().getId() <= 0) {
				return false;
			}
			return true;
		}

		@Override
		public Long converterId(String id) {
			return Long.valueOf(id);
		}
	}
}
