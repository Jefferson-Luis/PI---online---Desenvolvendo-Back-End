package com.databasepi.databasepi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.databasepi.databasepi.model.Hospede;
import com.databasepi.databasepi.model.Dependentes;
import com.databasepi.databasepi.model.Funcionario;
import com.databasepi.databasepi.model.Vaga;

import com.databasepi.databasepi.repository.HospedeRepository;
import com.databasepi.databasepi.repository.DependentesRepository;
import com.databasepi.databasepi.repository.FuncionarioRepository;
import com.databasepi.databasepi.repository.VagaRepository;

@Controller
public class IndexController {

	@Autowired
	private FuncionarioRepository fr;

	@Autowired
	private DependentesRepository dr;

	@Autowired
	private HospedeRepository hr;

	@Autowired
	private VagaRepository vr;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/")
	public ModelAndView pesquisarFuncionario(@RequestParam("nome") String nome, @RequestParam("busca") String busca) {

		String contador = "Resultado: ";
		int contadorint = 0;

		ModelAndView mv = new ModelAndView("index");

		if (busca.equals("nomefuncionario") || busca.equals("todos")) {

			Iterable<Funcionario> lstfuncionarios = fr.findByNomes(nome);

			mv.addObject("funcionario", lstfuncionarios);

			int qtdfunc = 0;
			for (@SuppressWarnings("unused") Funcionario funcionario : lstfuncionarios)
				qtdfunc++;

			if (qtdfunc == 1)
				imprimirDepententeUmFuncionario(nome, mv);

			contadorint = contadorint + qtdfunc;

			mv.addObject("isfunc", ((contadorint > 0) ? true : false));

		}

		if (busca.equals("nomedependente") || busca.equals("todos")) {

			List<Dependentes> dependentes2 = dr.findByNomes(nome);

			mv.addObject("dependentes2", dependentes2);
			
			contadorint = contadorint + (dependentes2.size());

			mv.addObject("isdependentes2", (dependentes2.size() > 0) ? true : false);

		}

		if (busca.equals("nomehospede") || busca.equals("todos")) {

			List<Hospede> hospedes = hr.findByNomesHospedes(nome);

			contadorint = contadorint + (hospedes.size());
			
			mv.addObject("hopedes", hospedes);

			mv.addObject("ishospedes", (hospedes.size() > 0) ? true : false);
		}

		if (busca.equals("tipovaga") || busca.equals("todos")) {

			List<Vaga> vagas = vr.findByQuartos(nome);

			mv.addObject("vagas", vagas);

			contadorint = contadorint + (vagas.size());

			mv.addObject("isvagas", (vagas.size() > 0) ? true : false);
		}

		if (!busca.equals("todos"))
			busca = (busca.indexOf("nome") >= 0) ? busca.replace("nome", "nome do ") : "tÃ­tulo da vaga";
		
		busca = "Resultados da pesquisa por " + busca;
		mv.addObject("busca", busca);

		contador = contador + "" + contadorint;
		
		mv.addObject("contadormsg", contador);
		mv.addObject("contador", contadorint);

		return mv;
	}

	private void imprimirDepententeUmFuncionario(String nome, ModelAndView mv) {
		Iterable<Dependentes> dependentes = dr.findByFuncionario(fr.findByNome(nome));

		if (dependentes.iterator().hasNext() == false)
			return;

		mv.addObject("dependentes", dependentes);
		mv.addObject("funcionarionome", nome);

	}

}
