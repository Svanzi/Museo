package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Transactional
	public Artista inserisci(Artista artista) {
		return artistaRepository.save(artista);
	}
	
	@Transactional
	public void elimina(Long id) {
		artistaRepository.deleteById(id);
	}
	
	@Transactional
	public Artista findByNomeAndCognome(String nome, String cognome){
		return artistaRepository.findByNomeAndCognome(nome,cognome);
	}
	
	@Transactional
	public List<Artista> allArtisti(){
		return (List<Artista>) artistaRepository.findAll();
	}

	@Transactional
	public Artista findById(Long id) {
		Optional<Artista> optional = artistaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Boolean alreadyExist(Artista artista) {
		Boolean res = false;
		Artista temp = this.artistaRepository.findByNomeAndCognome(artista.getNome(),artista.getCognome());
		if(temp != null)
			res = true;
		return res;
	}
	
}
