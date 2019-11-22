package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Recette;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.IngredientService;

/**
 * Servlet implementation class AjoutIngredient
 */
@WebServlet(name = "ajoutIngredient", urlPatterns = {"/ajoutIngredient"})
public class AjoutIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutIngredient() {
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
		IngredientService ingredientService = new IngredientService();
		List<Ingredient> allIngredients = new ArrayList<Ingredient>();
		Recette recette = new Recette();
		try {

			recette = (Recette) session.getAttribute("recette");
			allCategories = categorieService.getAllCategories();
			request.setAttribute("allCategories", allCategories);
			allIngredients = ingredientService.selectIngredientByIdRecette(recette.getIdRecette());


		} catch (SQLException e) {

			e.printStackTrace();
		}



		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutingredients.jsp").forward(request, response);//Return like
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		Recette recette = new Recette();
		recette = (Recette) session.getAttribute("recette");
		

		IngredientService ingredientService = new IngredientService();
		String erreur = "";

		String nomIngredient = request.getParameter("nomIngredient");
		if (nomIngredient.isEmpty()) {
			erreur +="Veuillez saisir un nom d'ingredient<br>";
		}
		int quantite =0;
		if (request.getParameter("quantite").isEmpty()) {
			erreur +="Veuillez saisir une quantite<br>";
		}else {
			quantite = Integer.parseInt(request.getParameter("quantite"));
		}


		String unit = request.getParameter("unit");
		if (unit.isEmpty()) {
			erreur +="Veuillez saisir une unite<br>";
		}



		if (erreur.isEmpty()) {
			Ingredient ingredient = new Ingredient();
			ingredient.setNomIngredient(nomIngredient);
			ingredient.setQuantite(quantite);
			ingredient.setUnit(unit);
			ingredient.setRecettes(recette);
			recette.addIngredient(ingredient);
			allIngredients.add(ingredient);
			try {
				ingredientService.addIngredient(ingredient);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("Ingredients", allIngredients);
			this.doGet(request, response);


		}else {

			this.doGet(request, response);
		}
	}

}
