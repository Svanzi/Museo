package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.controller.validator.ArtistaValidator;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;


@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaValidator artistaValidator;
	
	@Autowired
	private ArtistaService artistaService;
	
	@RequestMapping(value = "/addArtista", method = RequestMethod.GET)
	public String addArtistaGet (Model model) {
		model.addAttribute("artista", new Artista());
		return "/artista/artistaForm.html";
	}
	
	@RequestMapping(value = "/addArtista", method = RequestMethod.POST)
	public String addArtistaPost (@ModelAttribute("artista") Artista artista, 
								  Model model,
								  BindingResult bindingResult) {
		this.artistaValidator.validate(artista, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.artistaService.inserisci(artista);
			model.addAttribute("artisti", this.artistaService.allArtisti());
			model.addAttribute("artista", new Artista());
			return "/artista/listArtisti.html";
		}
		return "/artista/artistaForm.html";
	}
	
	@RequestMapping(value = "/deleteArtista", method = RequestMethod.GET)
	public String deleteArtistaGet (Model model) {
		model.addAttribute("artisti", this.artistaService.allArtisti());
		model.addAttribute("artista", new Artista());
		return "/artista/deleteArtista.html";
	}
	
	@RequestMapping(value = "/deleteArtista", method = RequestMethod.POST)
	public String deleteArtistaPost (@ModelAttribute("artista") Artista artista) {
			this.artistaService.elimina(artista.getId());
			return "/artista/deleteArtistaSuccessful.html";
	}
	
	@RequestMapping(value = "/cercaArtistaUser", method = RequestMethod.GET)
	public String cercaArtistaUserGet (Model model) {
		model.addAttribute("artista", new Artista());
		return "/artista/cercaArtistaUser.html";
	}
	
	@RequestMapping(value = "/cercaArtistaUser", method = RequestMethod.POST)
	public String cercaArtistaUserPost (@RequestParam(value = "nome", required = false) String nome,
										@RequestParam(value = "cognome", required = false) String cognome,
										Model model) {
		model.addAttribute("artista", this.artistaService.findByNomeAndCognome(nome, cognome));
		return "/artista/artista.html";
	}
	
	@RequestMapping(value = "/listArtisti")
	public String listArtisti (Model model) {
		model.addAttribute("artisti", this.artistaService.allArtisti());
		return "/artista/listArtisti.html";
	}
	
	@RequestMapping(value ="/artista/{id}", method = RequestMethod.GET)
	public String opera (@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.findById(id));
		return "/artista/artista.html";
	}
	
}
