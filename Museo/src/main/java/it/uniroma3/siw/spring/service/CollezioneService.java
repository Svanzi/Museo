package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public Collezione inserisci(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}
	
	@Transactional
	public void elimina(String nome) {
		collezioneRepository.deleteByNome(nome);
	}
	
	@Transactional
	public List<Collezione> allCollezioni(){
		return (List<Collezione>) collezioneRepository.findAll();
	}

	public Collezione findByNome(String nome){
		return collezioneRepository.findByNome(nome);
	}
	
	@Transactional
	public Boolean alreadyExist(Collezione collezione) {
		Boolean res = false;
		Collezione temp = this.collezioneRepository.findByNome(collezione.getNome());
		if(temp != null)
			res = true;
		return res;
	}

}
