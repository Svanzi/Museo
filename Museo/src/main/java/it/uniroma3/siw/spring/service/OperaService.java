package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository;
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public void elimina(String titolo) {
		operaRepository.deleteByTitolo(titolo);
	}
	
	@Transactional
	public Opera findByTitolo(String titolo) {
		return operaRepository.findByTitolo(titolo);
	}
	
	@Transactional
	public List<Opera> allOpere(){
		return (List<Opera>) operaRepository.findAll();
	}
	
	@Transactional
	public Opera findById(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Boolean alreadyExist(Opera opera) {
		Boolean res = false;
		Opera temp = this.operaRepository.findByTitolo(opera.getTitolo());
		if(temp != null)
			res = true;
		return res;
	}
	

}
