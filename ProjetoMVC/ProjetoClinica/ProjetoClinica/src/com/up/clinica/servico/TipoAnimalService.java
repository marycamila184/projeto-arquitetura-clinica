package com.up.clinica.servico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.up.clinica.model.Animal;
import com.up.clinica.servico.converter.AnimalJsonConverter;

@WebServlet(name = "GetTipoAnimais", urlPatterns = { "/GetTipoAnimais" })
public class TipoAnimalService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String servico = request.getParameter("servico");
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			ServletOutputStream out = response.getOutputStream();
			List<Animal> animais = new ArrayList<>();
			Animal a1 = new Animal();
			a1.setId(1L);
			a1.setNascimento(new Date());
			a1.setNome("Totó");
			Animal a2 = new Animal();
			a2.setId(2L);
			a2.setNascimento(new Date());
			a2.setNome("Cachorro");
			animais.add(a1);
			animais.add(a2);
			AnimalJsonConverter converter = new AnimalJsonConverter();
		//	String output = converter.convertToList(animais);
		//	out.print(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
