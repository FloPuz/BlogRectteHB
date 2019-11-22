package com.blogrecette.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idCategorie;
	
	@Column(name = "nomCategorie")
	private String nom;

	
	
	public Categorie() {
		super();
	}
	public Categorie(String nom) {
		super();
		this.nom = nom;
	}
	
	public Categorie(int idCategorie, String nom) {
		super();
		this.idCategorie = idCategorie;
		this.nom = nom;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nom=" + nom + "]";
	}

	
	
}
