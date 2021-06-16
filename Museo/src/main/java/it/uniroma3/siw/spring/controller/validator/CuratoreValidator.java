package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.CuratoreService;

@Component
public class CuratoreValidator implements Validator{
	
	@Autowired
	private CuratoreService curatoreService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		 return Collezione.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Curatore curatore = (Curatore) o;
		String nome = curatore.getName().trim();
		String cognome = curatore.getCognome().trim();
		String dataNascita = curatore.getDataNascita().trim();
		String luogoNascita = curatore.getLuogoNascita().trim();
		
		if(nome.isEmpty())
		
		if(this.curatoreService.alreadyExist(curatore))
			errors.reject("collezione", "duplicate");
		
	}

}
