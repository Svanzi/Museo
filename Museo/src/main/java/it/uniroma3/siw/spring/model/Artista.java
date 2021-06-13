package it.uniroma3.siw.spring.model;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	private String dataNascita;
	
	private String luogoNascita;
	
	private String dataMorte;
	
	private String luogoMorte;
	
	private String nazionalita;
	
	//ASSOCIAZIONI
	
	@OneToMany(mappedBy = "artista")
	private List<Opera> opere;
	
}
