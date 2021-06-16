package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CollezioneService;

@Controller
public class CollezioneController {

	@Autowired
	private CollezioneValidator collezioneValidator;
	
	@Autowired
	private CuratoreValidator curatoreValidator;

	@Autowired
	private CollezioneService collezioneService;

	//	@RequestMapping(value = "/cercaCuratore", method = RequestMethod.GET)
	//	public String cercaArtista (Model model) {
	//		model.addAttribute("curatore", new Curatore());
	//		return "/collezione/cercaCuratore.html";
	//	}
	//	
	//	@RequestMapping(value = "/inizializzazioneCuratore", method = RequestMethod.GET)
	//	public String inizializzazioneCuratore (@RequestParam(value = "nome", required = false) String nome,
	//											@RequestParam(value = "cognome", required = false) String cognome,
	//											Model model){
	//		String result;
	//		Curatore curatore = this.curatoreService.findByNomeAndCognome(nome, cognome);
	////		se esiste il curatore
	//		if(curatore != null) {
	//			model.addAttribute("collezione", new Collezione());
	//			model.addAttribute("curatore", curatore);
	//			result = "/collezione/collezioneForm.html";
	//		}
	//		else {
	//			model.addAttribute("curatore", new Curatore());
	//			result = "/curatore/curatoreForm.html";
	//		}
	//		return result;
	//	}

	@RequestMapping(value = "/addCollezione", method = RequestMethod.GET) 
	public String addCollezioneGet(Model model) {
		model.addAttribute("collezione", new Collezione());
		model.addAttribute("curatore", new Curatore());
		return "collezione/collezioneForm.html";
	}

	@RequestMapping(value = "/addCollezione", method = RequestMethod.POST)
	public String addCollezionePost(@ModelAttribute("collezione") Collezione collezione, 
			BindingResult collezioneBindingResult,
			@ModelAttribute("curatore") Curatore curatore, 
			BindingResult curatoreBindingResult,
			Model model) {
		this.collezioneValidator.validate(collezione, collezioneBindingResult);
		this.curatoreValidator.validate(curatore, curatoreBindingResult);
		if(!collezioneBindingResult.hasErrors() && !curatoreBindingResult.hasErrors()){
			collezione.setCuratore(curatore);
			this.collezioneService.inserisci(collezione);
			model.addAttribute("collezioni", this.collezioneService.allCollezioni());
			return "/collezione/listCollezioni.html";
		}
		return "/collezione/collezioneForm.html";
	}

	@RequestMapping(value = "/listCollezioni", method = RequestMethod.GET)
	public String listCollezioni(Model model) {
		model.addAttribute("collezioni", this.collezioneService.allCollezioni());
		return "collezione/listCollezioni.html";
	}

	@RequestMapping(value = "/deleteCollezione", method = RequestMethod.GET)
	public String deleteCollezioneGet (Model model) {
		model.addAttribute("collezioni", this.collezioneService.allCollezioni());
		model.addAttribute("collezione", new Collezione());
		return "/collezione/deleteCollezione.html";
	}

	@RequestMapping(value = "/deleteCollezione", method = RequestMethod.POST)
	public String deleteCollezionePost (@ModelAttribute("collezione") Collezione collezione,
			Model model){
		this.collezioneService.elimina(collezione.getNome());
		return "admin/home.html";
	}

}
