package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.IngredientService;
import com.blogrecette.services.MembreService;
import com.blogrecette.services.RecetteService;

/**
 * Servlet implementation class AjoutRecette
 */
@WebServlet(name = "ajoutRecette", urlPatterns = {"/ajoutRecette"})
public class AjoutRecette extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutRecette() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		CategorieService categorieService = new CategorieService();
		List<Categorie> allCategories = new ArrayList<Categorie>();
	
		
		try {
			allCategories = categorieService.getAllCategories();
			request.setAttribute("allCategories", allCategories);
		
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutrecette.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		RecetteService recetteService = new RecetteService();
		CategorieService categorieService = new CategorieService();
		MembreService membreService = new MembreService();
		
		
		String info = "";
		
		String titre = request.getParameter("titre");
		if (titre.isEmpty()) {
			info+="Veuillez saisir un titre<br>";
		}
		
		String photo = request.getParameter("photo");
		if (photo.isEmpty()) {
			info +="Veuillez renseigner une image<br>";
		}
		
		String description = request.getParameter("description");
		if (description.isEmpty()) {
			info +="Veuillez saisir une description<br>";
		}

		request.setAttribute("info", info);
		
		int idCategorie = Integer.parseInt(request.getParameter("categorie"));
		Membre membre = new Membre();
		membre = (Membre)session.getAttribute("membre");
		
		
		
		
		if (info.isEmpty()) {
			
			
			
			//J'ajoute la recette avec sa catégorie et son membre
			Categorie categorie= new Categorie();
			
			
				try {
					categorie = categorieService.getCategorieFromId(idCategorie);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Recette recette = new Recette();
				recette.setCategorie(categorie);
				recette.setMembre(membre);
				recette.setDateCreation(new Date());
				recette.setDescription(description);
				recette.setPhoto(photo);
				recette.setTitre(titre);
				recetteService.addRecette(recette);
				session.setAttribute("recette", recette);
				
				
					
					
					
				
			
			
			
			
			this.doGet(request, response);
			
			
		}else {
			
			this.doGet(request, response);
		}
		/************************INGREDIENTS***************************************/
		
		
		
		
		
		
		
		
		this.doGet(request, response);
	
	}
	
}
