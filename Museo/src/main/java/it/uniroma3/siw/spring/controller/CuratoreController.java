package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		return "/curatore/curatoreForm.html";
	}

	@RequestMapping(value = "/addCuratore", method = RequestMethod.POST)
	public String addCuratorePost (@ModelAttribute("curatore") Curatore curatore, 
			Model model,
			BindingResult bindingResult) {
		this.curatoreValidator.validate(curatore, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.curatoreService.inserisci(curatore);
			model.addAttribute("curatori", this.curatoreService.allCuratori());
			model.addAttribute("curatore", curatore);
			return "/curatore/listCuratori.html";
		}
		else {
			model.addAttribute("curatore", new Curatore());
			return "/curatore/curatoreForm.html";
		}
	}

	@RequestMapping(value = "/listCuratori", method = RequestMethod.GET)
	public String listCuratori(Model model) {
		model.addAttribute("curatori", this.curatoreService.allCuratori());
		return "curatore/listCuratori.html";
	}

	@RequestMapping(value = "/deleteCuratore", method = RequestMethod.GET)
	public String deleteCuratoreGet (Model model) {
		model.addAttribute("curatori", this.curatoreService.allCuratori());
		model.addAttribute("curatore", new Curatore());
		return "/curatore/deleteCuratore.html";
	}

	@RequestMapping(value = "/deleteCuratore", method = RequestMethod.POST)
	public String deleteCollezionePost (@ModelAttribute("curatore") Curatore curatore,
			Model model){
		this.curatoreService.elimina(curatore.getId());
		return "admin/home.html";
	}

}
