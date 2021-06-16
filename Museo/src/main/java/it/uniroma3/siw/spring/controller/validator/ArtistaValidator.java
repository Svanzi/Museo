package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;

@Component
public class ArtistaValidator implements Validator{
	
	@Autowired
	private ArtistaService artistaService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Artista artista = (Artista) o;
		String nome = artista.getNome().trim();
		String cognome = artista.getCognome().trim();
		String dataNascita = artista.getDataNascita().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		if(cognome.isEmpty())
			errors.rejectValue("cognome", "required");
		if(dataNascita.isEmpty())
			errors.rejectValue("dataNascita", "required");
		
		if(this.artistaService.alreadyExist(artista))
			errors.reject("artista", "duplicate");
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		 return Artista.class.equals(clazz);
	}

}
