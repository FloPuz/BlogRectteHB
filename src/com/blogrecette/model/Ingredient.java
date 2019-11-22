package com.blogrecette.model;

import java.util.Collection;

import javax.persistence.*;
@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idIngredient;

	@Column(name = "nomIngredient")
	private String nomIngredient;

	@Column(name = "quantite")
	private int quantite;

	@Column(name = "unit")
	private String unit;

	/**********************DEBUT SECTION RECETTES**********************/

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,optional = false,targetEntity = Recette.class)
	@JoinColumn(name="idRecette")
	private Recette recette;

	public Recette getRecettes(){
		return recette;
	}

	public void setRecettes(Recette recette) {
		this.recette = recette;
	}

	/**********************FIN SECTION RECETTES**********************/






	public Ingredient() {
		super();
	}

	public Ingredient(String nomIngredient, int quantite, String unit, Recette recette) {
		super();
		this.nomIngredient = nomIngredient;
		this.quantite = quantite;
		this.unit = unit;
		this.recette = recette;
	}

	public int getIdIngredient() {
		return idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	public String getNomIngredient() {
		return nomIngredient;
	}
	public void setNomIngredient(String nomIngredient) {
		this.nomIngredient = nomIngredient;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}





}
