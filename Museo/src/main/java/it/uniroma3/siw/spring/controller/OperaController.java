package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController {

	@Autowired
	private OperaService operaService;
	
	@Autowired
	private ArtistaService artistaService;
	
	@RequestMapping(value = "/cercaArtista", method = RequestMethod.GET)
	public String cercaArtista (Model model) {
		model.addAttribute("artista", new Artista());
		return "/opera/cercaArtista.html";
	}
	
	@RequestMapping(value = "/inizializzazioneArtista", method = RequestMethod.GET)
	public String inizializzazioneArtista (@RequestParam(value = "nome", required = false) String nome,
										   @RequestParam(value = "cognome", required = false) String cognome, 
										   Model model) {
		String result;
		Artista artista = this.artistaService.findByNomeAndCognome(nome, cognome);
//		se esiste l'artista
		if(artista != null) {
			model.addAttribute("opera", new Opera());
			model.addAttribute("artista", artista);
			result = "/opera/operaForm.html";
		}
		else {
			model.addAttribute("artista", new Artista());
			result = "/artista/artistaForm.html";
		}
		return result;
	}
	
	@RequestMapping(value = "/addOpera", method = RequestMethod.POST)
	public String addOperaPost (@ModelAttribute("opera") Opera opera, 
								@ModelAttribute("artista") Artista artista,
								@RequestParam(value = "titolo", required = false) String titolo,
								Model model) {
		String result;
		Opera operaTemp = this.operaService.findByTitolo(titolo);
//		Se l'opera non esiste già
		if(operaTemp == null) {
			opera.setArtista(artista);
			this.operaService.inserisci(opera);
			model.addAttribute("opere", this.operaService.allOpere());
			result =  "/opera/listOpere.html";
		}
		else {
			model.addAttribute("opera", new Opera());
			result = "/opera/operaForm.html";
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteOpera", method = RequestMethod.GET)
	public String chooseOpera (Model model) {
		model.addAttribute("opere", this.operaService.allOpere());
		model.addAttribute("opera", new Opera());
		return "/opera/deleteOpera.html";
	}
	
	@RequestMapping(value = "/deleteOpera", method = RequestMethod.POST)
	public String deleteOpera (@ModelAttribute("opera") Opera opera) {
		this.operaService.elimina(opera.getTitolo());
		return "/opera/deleteOperaSuccessful.html";
	}
	
	@RequestMapping(value = "/cercaOperaUser", method = RequestMethod.GET)
	public String cercaOperaUserGet (Model model) {
		model.addAttribute("opera", new Opera());
		return "/opera/cercaOperaUser.html";
	}
	
	@RequestMapping(value = "/cercaOperaUser", method = RequestMethod.POST)
	public String cercaOperaUserPost (@ModelAttribute("opera") Opera opera, 
									  Model model) {
		model.addAttribute("opera", this.operaService.findByTitolo(opera.getTitolo()));
		return "/opera/opera.html";
	}
	
	@RequestMapping(value = "/listOpere")
	public String listOpere (Model model) {
		model.addAttribute("opere", this.operaService.allOpere());
		return "/opera/listOpere.html";
	}
	
	@RequestMapping(value ="/opera/{id}", method = RequestMethod.GET)
	public String opera (@PathVariable("id") Long id,
						 Model model) {
		model.addAttribute("opera", this.operaService.findById(id));
		return "/opera/opera.html";
	}
	
}
