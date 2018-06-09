package com.up.clinica.servico;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.up.clinica.model.Animal;
import com.up.clinica.model.Especie;
import com.up.clinica.model.dal.AbstractDAO;
import com.up.clinica.model.dal.AnimalDAO;
import com.up.clinica.servico.converter.AnimalJsonConverter;
import com.up.clinica.servico.converter.JsonConverter;

@WebServlet(name = "Animais", urlPatterns = { "/Animais" })
public class AnimalService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AnimalDAO animalDAO = new AnimalDAO();
	private AnimalJsonConverter jsonConverter = new AnimalJsonConverter();
	private Services service = new Services();
	
	public AnimalService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = "";
		try {
			String servico = request.getParameter("servico");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			ServletOutputStream out = response.getOutputStream();

			if (servico.equals("listar")) {
				// Listando os animais
				output = service.listar();

			} else if (servico.equals("buscar")) {
				// Buscando um animal
				Long id = Long.parseLong(request.getParameter("id"));
				output = service.buscar(id);

			} else if (servico.equals("cadastrar")) {
				// Fazendo o parser para animal
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date dataNascimento = formatter.parse(request.getParameter("datanascimento"));
				String nome = request.getParameter("nome");
				Animal novoAnimal = new Animal();
				novoAnimal.setNome(nome);
				novoAnimal.setNascimento(dataNascimento);

				// Setando a espécie
				Especie e = new Especie();
				e.setId(Long.parseLong(request.getParameter("idespecie")));
				novoAnimal.setEspecie(e);

				// Cadastrando o animal
				service.cadastrar(novoAnimal);

			} else if (servico.equals("remover")) {
				// Removendo o animal
				service.remover(Long.parseLong(request.getParameter("id")));

			} else if (servico.equals("alterar")) {
				// Alterando o animal
				Long id = Long.parseLong(request.getParameter("id"));
				String nome = request.getParameter("nome");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date dataNascimento = formatter.parse(request.getParameter("datanascimento"));
				
				Animal animal = new Animal();
				animal.setNome(nome);
				animal.setId(id);
				animal.setNascimento(dataNascimento);
				
				// Setando a espécie
				Especie e = new Especie();
				e.setId(Long.parseLong(request.getParameter("idespecie")));
				animal.setEspecie(e);
				
				//Alterando o animal
				service.alterar(animal);

			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}

			out.print(output);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	private class Services extends AbstractService<Animal, Long> {

		@Override
		protected AbstractDAO tipoDao() {
			return animalDAO;
		}

		@Override
		protected JsonConverter tipoJsonConverter() {
			return jsonConverter;
		}
	}
}
