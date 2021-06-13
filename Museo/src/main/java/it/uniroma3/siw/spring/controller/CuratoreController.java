package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CuratoreService;

@Controller
public class CuratoreController {
	
	@Autowired
	private CuratoreService curatoreService;
	
	@Autowired
	private CuratoreValidator curatoreValidator;
	
	@RequestMapping(value = "/addCuratore", method = RequestMethod.GET)
	public String addCuratoreGet (Model model) {
		model.addAttribute("curatore", new Curatore());
		return "/artista/curatoreForm.html";
	}
	
	@RequestMapping(value = "/addCuratore", method = RequestMethod.POST)
	public String addCuratorePost (@ModelAttribute("curatore") Curatore curatore, Model model) {
//		se non esiste già l'artista
		if(!this.curatoreValidator.alreadyExist(curatore)) {
			this.curatoreService.inserisci(curatore);
			model.addAttribute("curatori", this.curatoreService.allCuratori());
			model.addAttribute("curatore", new Curatore());
			return "/curatore/listCuratori.html";
		}
		else {
			model.addAttribute("curatore", new Curatore());
			return "/curatore/curatoreForm.html";
		}
	}

}
