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
	
	@Column(nullable = true, length = 64)
	private String photos;
	
	//ASSOCIAZIONI
	
	@OneToOne
	private Collezione collezione;
	
	@OneToOne
	private Artista artista;
	
	@Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
         
        return "/opera-photos/" + id + "/" + photos;
    }
	
}
