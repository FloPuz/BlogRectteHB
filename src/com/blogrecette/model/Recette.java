package com.blogrecette.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "recette")
public class Recette {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idRecette;




	/**********************DEBUT SECTION MEMBRE**********************/

	@ManyToOne(/*fetch = FetchType.EAGER,*/optional = false, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "idMembre")
	private Membre membre;

	public Membre getMembre() {
		return membre;
	}
	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	/**********************FIN SECTION MEMBRE**********************/




	/*********************DEBUT SECTION INGREDIENTS*************************/

	@OneToMany( mappedBy = "recette"/*,fetch = FetchType.EAGER*/)
	private Collection<Ingredient> ingredients;

	public Collection<Ingredient> getIngredients(){
		return this.ingredients;
	}

	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
		ingredient.setRecettes(this);
	}
	public void removeIngredient(Ingredient ingredient){
		ingredients.remove(ingredient);
		ingredient.setRecettes(null);
	}
	/**********************FIN SECTION INGREDIENTS**********************/





	/**********************DEBUT SECTION COMMENTAIRE**********************/

	@OneToMany( mappedBy = "recette"/*,fetch = FetchType.EAGER*/,targetEntity = Commentaire.class)
	private Collection<Commentaire> commentaires;

	public Collection<Commentaire> getCommentaires(){
		return this.commentaires;
	}

	public void addCommentaires(Commentaire commentaire){
		commentaires.add(commentaire);
		commentaire.setRecettes(this);
	}
	public void removeCommentaire(Commentaire commentaire){
		commentaires.remove(commentaire);
		commentaire.setRecettes(null);
	}
	/**********************FIN SECTION COMMENTAIRE**********************/






	/**********************DEBUT SECTION CATEGORIE**********************/

	@ManyToOne(/*fetch = FetchType.EAGER,*/optional = false, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;

	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**********************FIN SECTION CATEGORIE**********************/

	/**********************DEBUT SECTION TAGS**********************/
	@ManyToMany(/*,fetch = FetchType.EAGER*/)
	private Collection<Tag> tags;

	public Collection<Tag> getTags(){
		return this.tags;
	}
	public void addTag(Tag tag){
		this.tags.add(tag);
		tag.AddRecette(this);
	}
	public void removeTag(Tag tag){
		tags.remove(tag);
		tag.DeleteRecette(this);
	}
	/**********************FIN SECTION TAGS**********************/

	@Column(name = "titre")
	private String titre;

	@Column(name = "description")
	private String description;

	@Column(name = "photo")
	private String photo;

	@Column(name = "dateCreation")
	private Date dateCreation;

	@Transient
	private int moyenneNote;


	public Recette() {
		super();
		this.commentaires= new ArrayList<Commentaire>();
		this.ingredients= new ArrayList<Ingredient>();
		
	}

	public Recette(Membre membre, Collection<Ingredient> ingredients, Collection<Commentaire> commentaires,
			Categorie categorie, String titre, String description, String photo, Date dateCreation, int moyenneNote) {
		super();
		this.membre = membre;
		this.ingredients = new ArrayList<Ingredient>();
		this.commentaires = new ArrayList<Commentaire>();
		this.categorie = categorie;
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
		this.moyenneNote = moyenneNote;
	}
	public Recette(String titre, String description, String photo, Date dateCreation) {
		this.titre=titre;
		this.description=description;
		this.photo=photo;
		this.dateCreation=dateCreation;
		this.ingredients = new ArrayList<Ingredient>();//A ne jamais oublier pour chaque collection un NEW
		this.commentaires = new ArrayList<Commentaire>();
	}
	public int getIdRecette() {
		return idRecette;
	}
	public void setIdRecette(int idRecette) {
		this.idRecette = idRecette;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getMoyenneNote() {
		return moyenneNote;
	}

	public void setMoyenneNote(int moyenneNote) {
		this.moyenneNote = moyenneNote;
	}
	@Override
	public String toString() {
		return "Recette [idRecette=" + idRecette + ", membre=" + membre + ", ingredients=" + ingredients
				+ ", commentaires=" + commentaires.toString() + ", categorie=" + categorie + ", titre=" + titre + ", description="
				+ description + ", photo=" + photo + ", dateCreation=" + dateCreation + ", moyenneNote=" + moyenneNote
				+ "]";
	}





}
