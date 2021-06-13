package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista, Long> {
	
	public Artista findByNomeAndCognome(String nome, String cognome);
	
	public void deleteById(Long id);

}