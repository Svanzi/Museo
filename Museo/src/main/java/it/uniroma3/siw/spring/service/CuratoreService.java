package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CuratoreRepository;

@Service
public class CuratoreService {
	
	@Autowired
	private CuratoreRepository curatoreRepository;
	
	@Transactional
	public Curatore inserisci(Curatore curatore) {
		return curatoreRepository.save(curatore);
	}
	
	@Transactional
	public void elimina(Long id) {
		curatoreRepository.deleteById(id);
	}
	
	@Transactional
	public List<Curatore> allCuratori(){
		return (List<Curatore>) curatoreRepository.findAll();
	}
	
	@Transactional
	public Curatore findByNameAndCognome(String nome, String cognome) {
		return curatoreRepository.findByNameAndCognome(nome, cognome);
	}
	
	@Transactional
	public Boolean alreadyExist(Curatore curatore) {
		Boolean res = false;
		Curatore temp = this.curatoreRepository.findByNameAndCognome(curatore.getName(),curatore.getCognome());
		if(temp != null)
			res = true;
		return res;
	}

	
}
