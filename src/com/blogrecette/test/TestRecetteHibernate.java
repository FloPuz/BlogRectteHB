package com.blogrecette.test;

import com.blogrecette.services.*;

import java.sql.SQLException;
import java.util.Date;

import com.blogrecette.model.*;

public class TestRecetteHibernate {

	public static void main(String[] args) throws SQLException, Exception {
		CategorieService categorieService = new CategorieService();
        Categorie categorie = new Categorie("Dessert");
        Categorie categorieCreated = categorieService.createCategorie(categorie);
        
        MembreService membreService = new MembreService();
        Membre membre = new Membre("Hanitra A.", "hanitra", "mdp123", "h@yahoo.com", new Date());
        Membre membreCreated = membreService.addMembre(membre);
        
        RecetteService recetteService = new RecetteService();
        Recette recette = new Recette("Tartiflette", "Description de la tartiflette", "tartiflette.png", new Date());
        
        recette.setCategorie(categorieCreated);
        recette.setMembre(membreCreated);
        
        Recette recetteCreated = recetteService.addRecette(recette);
        
        CommentaireService commentaireService = new CommentaireService();
        Commentaire commentaire = new Commentaire("Auteur", "Contenu", 3, new Date());
        
        commentaire.setRecettes(recetteCreated);
        System.out.println(commentaire.toString());
        
        recetteCreated.addCommentaires(commentaire);
        
        Commentaire commentaireCreated = commentaireService.createCommentaire(commentaire);
        
        System.out.println(recetteCreated.toString());
        System.out.println(commentaireCreated.toString());
        
        recetteCreated.addCommentaires(commentaireCreated);
        

        System.out.println(recetteCreated.getCommentaires().toString());
	}

}
