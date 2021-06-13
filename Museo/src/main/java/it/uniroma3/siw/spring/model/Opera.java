package it.uniroma3.siw.spring.model;


import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Opera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String titolo;
	
	@Column(nullable = false)
	private String anno;
	
	private String descrizione;
	
	//ASSOCIAZIONI
	
	@OneToOne
	private Collezione collezione;
	
	@OneToOne
	private Artista artista;
	
}
