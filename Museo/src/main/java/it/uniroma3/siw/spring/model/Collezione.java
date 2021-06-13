package it.uniroma3.siw.spring.model;

import java.util.*;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Collezione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private String descrizione;

	//ASSOCIAZIONI
	
	@OneToOne(mappedBy = "collezione")
	private Curatore curatore;
	
	@OneToMany(mappedBy = "collezione")
	private List<Opera> opere;

}
