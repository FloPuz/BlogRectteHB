package com.blogrecette.model;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String nom;

	/**********************DEBUT SECTION RECETTES**********************/
	@ManyToMany(mappedBy = "tags", targetEntity = Recette.class)
	private Collection<Recette> recettes;

	public void AddRecette(Recette recette) {
		recettes.add(recette);
		recette.addTag(this);
	}

	public void DeleteRecette(Recette recette) {
		recettes.remove(recette);
		recette.setMembre(null);
	}
	
	public Collection<Recette> getRecettes() {
		return recettes;
	}

	/**********************FIN SECTION RECETTES**********************/

	
	
	
	
	
	
	
	public Tag(String nom) {
		super();
		this.nom = nom;
	}
	
	public Tag() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}
	
	
}
