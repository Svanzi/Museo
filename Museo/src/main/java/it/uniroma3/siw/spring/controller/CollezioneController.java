package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;

@Controller
public class CollezioneController {
	
	@Autowired
	private CuratoreService curatoreService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@RequestMapping(value = "/cercaCuratore", method = RequestMethod.GET)
	public String cercaArtista (Model model) {
		model.addAttribute("curatore", new Curatore());
		return "/collezione/cercaCuratore.html";
	}
	
	@RequestMapping(value = "/inizializzazioneCuratore", method = RequestMethod.GET)
	public String inizializzazioneCuratore (@RequestParam(value = "nome", required = false) String nome,
											@RequestParam(value = "cognome", required = false) String cognome,
											Model model){
		String result;
		Curatore curatore = this.curatoreService.findByNomeAndCognome(nome, cognome);
//		se esiste il curatore
		if(curatore != null) {
			model.addAttribute("collezione", new Collezione());
			model.addAttribute("curatore", curatore);
			result = "/collezione/collezioneForm.html";
		}
		else {
			model.addAttribute("curatore", new Curatore());
			result = "/curatore/curatoreForm.html";
		}
		return result;
	}
	
	@RequestMapping(value = "/addCollezione", method = RequestMethod.POST)
	public String addCollezione(@ModelAttribute("collezione") Collezione collezione,
								@ModelAttribute("curatore") Curatore curatore,
								@RequestParam(value = "nome", required = false) String nome,
								Model model) {
		String result;
		Collezione collezioneTemp = this.collezioneService.findByNome(nome);
//		Se la collezione non esiste già
		if(collezioneTemp == null){
//			collezione.setCuratore(curatore);
			this.collezioneService.inserisci(collezione);
			model.addAttribute("collezioni", this.collezioneService.allCollezioni());
			result = "/collezione/listCollezioni.html";
		}
		else {
			model.addAttribute("collezione", new Collezione());
			result = "/collezione/collezioneForm.html";
		}
		return result;
	}
	
	@RequestMapping(value = "/listCollezioni", method = RequestMethod.GET)
	public String listCollezioni(Model model) {
		model.addAttribute("collezioni", this.collezioneService.allCollezioni());
		return "collezione/listCollezioni.html";
	}

	@RequestMapping(value = "/deleteCollezione", method = RequestMethod.GET)
	public String chooseCollezione (Model model) {
		model.addAttribute("collezioni", this.collezioneService.allCollezioni());
		model.addAttribute("collezione", new Collezione());
		return "/collezione/deleteCollezione.html";
	}
	
	@RequestMapping(value = "/deleteCollezione", method = RequestMethod.POST)
	public String deleteCollezione (@ModelAttribute("collezione") Collezione collezione,
									Model model){
		this.collezioneService.elimina(collezione.getNome());
		return "admin/home.html";
	}
	
}
