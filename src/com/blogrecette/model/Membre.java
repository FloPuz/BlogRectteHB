package com.blogrecette.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sun.awt.datatransfer.DataTransferer.ReencodingInputStream;

@Entity
@Table(name = "membre")
public class Membre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idMembre;


	/**********************DEBUT SECTION RECETTES**********************/
	@OneToMany(mappedBy = "membre")
	private Collection<Recette> recettes;

	public void AddRecette(Recette recette) {
		recettes.add(recette);
		recette.setMembre(this);
	}

	public void DeleteRecette(Recette recette) {
		recettes.remove(recette);
		recette.setMembre(null);
	}
	/**********************FIN SECTION RECETTES**********************/


	@Column(name = "nomMembre")
	private String nom;

	@Column(name ="pseudo")
	private String pseudo;

	@Column(name = "mdp")
	private String mdp;

	@Column(name = "email")
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateInscription")
	private Date dateInscription;

	public Membre() {
		super();
		this.recettes = new ArrayList<Recette>();
	}
	public Membre(String nom, String pseudo, String mdp, String email, Date dateInscription) {
		super();
		this.recettes = new ArrayList<Recette>();
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscription = dateInscription;
	}

	public int getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}



	@Override
	public String toString() {
		return "Membre [idMembre=" + idMembre + ", nom=" + nom + ", pseudo="
				+ pseudo + ", mdp=" + mdp + ", email=" + email + ", dateInscription=" + dateInscription + "]";
	}

}
