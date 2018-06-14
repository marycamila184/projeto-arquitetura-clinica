package com.up.clinica.servico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.up.clinica.model.Especie;
import com.up.clinica.model.dal.EspecieDAO;
import com.up.clinica.servico.converter.EspecieJsonConverter;

@WebServlet(name = "Especies", urlPatterns = { "/Especies" })
public class EspecieService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected Services servico;
	
	public EspecieService() {
		// inicio o serviço com as dependencias
		servico = new Services(new EspecieDAO(), new EspecieJsonConverter());
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

	private class Services extends AbstractService<EspecieJsonConverter, EspecieDAO, Especie, Long> {

		public Services(EspecieDAO dao, EspecieJsonConverter converter) {
			super(dao, converter);
		}

		@Override
		public boolean validatorModel(Especie objeto) {
			if (objeto.getNome().equals("") 
					|| objeto.getTipoAnimal().getAcronimo().equals("")) {
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
