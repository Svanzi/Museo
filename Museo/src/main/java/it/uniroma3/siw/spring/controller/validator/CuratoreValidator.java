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
	
	public Boolean alreadyExist(Curatore curatore) {
		Boolean res = false;
		Curatore temp = this.curatoreService.findByNomeAndCognome(curatore.getNome(),curatore.getCognome());
		if(temp != null)
			res = true;
		return res;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		 return Collezione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
