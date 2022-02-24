package com.databasepi.databasepi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.databasepi.databasepi.model.Hospede;
import com.databasepi.databasepi.model.Vaga;
import com.databasepi.databasepi.repository.HospedeRepository;
import com.databasepi.databasepi.repository.VagaRepository;

@Controller
public class VagaController {

	@Autowired
	private VagaRepository vr;

	@Autowired
	private HospedeRepository hr;

	// CADASTRA VAGA
	@RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
	public String form() {
		return "vaga/formVaga";

	}

	@RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
	public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarVaga";
		}

		vr.save(vaga);
		attributes.addFlashAttribute("mensagem", "Quarto cadastrado com sucesso!");
		return "redirect:/cadastrarVaga";

	}

	// LISTA VAGAS
	@RequestMapping("/vagas")
	public ModelAndView listaVagas() {
		ModelAndView mv = new ModelAndView("vaga/listaVaga");
		Iterable<Vaga> vagas = vr.findAll();
		mv.addObject("vagas", vagas);
		return mv;
	}

	// pega o código e faz a busca no banco
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
		mv.addObject("vaga", vaga);

		Iterable<Hospede> hospedes = hr.findByVaga(vaga);
		mv.addObject("hospedes", hospedes);

		return mv;
	}

	// DELETA VAGA
	@RequestMapping("/deletarVaga")
	public String deletarVaga(long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		vr.delete(vaga);
		return "redirect:/vagas";
	}

	// ADICIONA CANDIDATO E LISTA TODOS DA VAGA
		@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
		public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Hospede hospede,
				BindingResult result, RedirectAttributes attributes) {

			if (result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos...");
				return "redirect:/{codigo}";
			}
			
			//consistência
			if(hr.findByCpf(hospede.getCpf()) != null ) {
				attributes.addFlashAttribute("mensagem_erro", "CPF duplicado");
				return "redirect:/{codigo}";
			}

			Vaga vaga = vr.findByCodigo(codigo);
			hospede.setVaga(vaga);
			hr.save(hospede);
			attributes.addFlashAttribute("mensagem", "Hospede adicionado com sucesso!");
			return "redirect:/{codigo}";
		}
	
	// alterado para deletar pelo cpf
	@RequestMapping("/deletarHospede")
	public String deletarHospede(String cpf) {
		Hospede hospede = hr.findByCpf(cpf);
		//cr.delete(hospede);

		Vaga vaga = hospede.getVaga();
		//long codigoLong = vaga.getCodigo();
		String codigo = "" + vaga.getCodigo();
		
		hr.delete(hospede);
		
		return "redirect:/" + codigo;
	}

	// MÉTODOS QUE ATUALIZAM vaga
	// Formulario edição vaga
	@RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
	public ModelAndView editarEvent(long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/update-vaga");
		mv.addObject("vaga", vaga);
		return mv;
	}

	// Updating vaga
	@RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
	public String updateEvent(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		vr.save(vaga);
		attributes.addFlashAttribute("success", "Quarto alterado com sucesso!");
		// return "redirect:/";

		long codigoLong = vaga.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}

}
