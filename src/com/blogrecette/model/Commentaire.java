package com.blogrecette.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "commentaire")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int idCommentaire;
	
	
	/**********************DEBUT SECTION RECETTES**********************/
	
	@ManyToOne(fetch = FetchType.EAGER ,  optional = false ,  targetEntity = Recette.class)
	private Recette recette;
	
	public Recette getRecettes(){
		return recette;
	}

	public void setRecettes(Recette recettes) {
		this.recette = recettes;
	}
	
	/**********************FIN SECTION RECETTES**********************/
	
	@Column(name = "auteur")
	private String auteur;
	
	@Column(name = "contenu")
	private String contenu;
	
	@Column(name = "note")
	private int note;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateCreation")
	private Date dateCreation;
	
	
	public Commentaire() {
		super();
		
	}
	
	

	
	public Commentaire(String auteur, String contenu, int note, Date dateCreation) {
		super();
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}

	public Commentaire(Recette recettes, String auteur, String contenu, int note, Date dateCreation) {
		super();
		this.recette = recettes;
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}

	public int getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "Commentaire [idCommentaire=" + idCommentaire + ", recettes=" + recette.getIdRecette() + ", auteur=" + auteur
				+ ", contenu=" + contenu + ", note=" + note + ", dateCreation=" + dateCreation + "]";
	}
	
	

	
	
}
