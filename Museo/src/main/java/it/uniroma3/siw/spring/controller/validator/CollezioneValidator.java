package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator{
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Collezione collezione = (Collezione) o;
		String nome = collezione.getNome().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		
		if(this.collezioneService.alreadyExist(collezione))
			errors.reject("collezione", "duplicate");
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		 return Collezione.class.equals(clazz);
	}

}
