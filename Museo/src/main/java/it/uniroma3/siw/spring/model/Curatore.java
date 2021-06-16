package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.*;


import lombok.Data;

@Data
@Entity
public class Curatore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private String dataNascita;
	
	@Column(nullable = false)
	private String luogoNascita;
	
	private String email;
	
	private String numeroTelefono;
	
	//ASSOCIAZIONI
	
	@OneToMany(mappedBy = "curatore")
	private List<Collezione> collezioni;

}
