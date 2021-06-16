package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

@Component
public class OperaValidator implements Validator{
	
	@Autowired
	private OperaService operaService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Opera opera= (Opera) o;
		String titolo = opera.getTitolo().trim();
		String data = opera.getAnno().trim();
		
		if(titolo.isEmpty())
			errors.rejectValue("titolo", "required");
		if(data.isEmpty())
			errors.rejectValue("data", "required");
		
		if(this.operaService.alreadyExist(opera))	
			errors.reject("opera", "duplicate");
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		 return Collezione.class.equals(clazz);
	}

}
