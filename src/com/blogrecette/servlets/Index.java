package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.services.IngredientService;
import com.blogrecette.services.RecetteService;
import com.blogrecette.model.Categorie;
import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Ingredient;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.CommentaireService;

/**
 * Servlet implementation class Index
 */
@WebServlet(name = "index", urlPatterns = {"","/index"})
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		

		List<com.blogrecette.model.Recette> Allrecettes =new ArrayList<>();
		List<Categorie> allCategories = new ArrayList<Categorie>();
		List<Ingredient> allIngredients = new ArrayList<Ingredient>();
		int moyenneById=0;

		try {
			RecetteService recetteservice = new RecetteService();
			CategorieService categorieservice = new CategorieService();
			CommentaireService commentaireService = new CommentaireService();
			
			IngredientService ingredientService = new IngredientService();
			
			Allrecettes = recetteservice.getAllRecette();//recetteservice.moyNoteRecetteByRecette(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("Recettes", Allrecettes);
			
			allCategories = categorieservice.getAllCategories();
			session.setAttribute("categories", allCategories);
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		

		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);//Return like
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

	}

}
