package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
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

import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Ingredient;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.CommentaireService;
import com.blogrecette.services.IngredientService;
import com.blogrecette.services.MembreService;
import com.blogrecette.services.RecetteService;


/**
 * Servlet implementation class Recette
 */
@WebServlet(name = "recette", urlPatterns = {"/recette"})
public class Recette extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recette() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		List<com.blogrecette.model.Recette> recettesCategorie1 =new ArrayList<>();
		List<com.blogrecette.model.Recette> recettesCategorie2 =new ArrayList<>();
		List<Commentaire>allCommentaires = new ArrayList<Commentaire>();
		List<com.blogrecette.model.Categorie> categories =new ArrayList<>();
		List<Ingredient> allIngredients = new ArrayList<Ingredient>();
		
		int noteMoyenneByRecette;
		
		com.blogrecette.model.Recette recette = new com.blogrecette.model.Recette();
		

		try {
			
			RecetteService recetteservice = new RecetteService();
			
			CommentaireService commentaireService = new CommentaireService();
			
			CategorieService categorieservice = new CategorieService();
			IngredientService ingredientService = new IngredientService();
			
			categories = categorieservice.getAllCategories();
			
			
			allIngredients = ingredientService.selectIngredientByIdRecette(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("Ingredients", allIngredients);
			
			recette = recetteservice.getRecetteById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("recette", recette);
			
			recettesCategorie2 = recetteservice.selectAllRecettesByCategories(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("recettesCat2", recettesCategorie2);
			
			allCommentaires = commentaireService.getCommentairesByIdRecette(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("allCommentaires", allCommentaires);
			
			noteMoyenneByRecette = recetteservice.moyNoteRecetteByRecette(Integer.parseInt(request.getParameter("id")));
			recette.setMoyenneNote(noteMoyenneByRecette);
			request.setAttribute("Notemoyenne", noteMoyenneByRecette);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		//response.sendRedirect("recette?id="+request.getParameter("id"));
		this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);//Return like

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession session = request.getSession();
		String info = "";

		String auteur = request.getParameter("auteur");
		if (auteur.isEmpty()) {
			info+="Veuillez saisir un nom<br>";
		}
		
		String contenu = request.getParameter("contenu");
		if (contenu.isEmpty()) {
			info+="Veuillez saisir un commentaire avant de valider<br>";
		}
		
		int note =  Integer.parseInt(request.getParameter("note"));
		
		int idRecette = Integer.parseInt(request.getParameter("id"));/*JE DOIS RECUPER L'ID DANS L'URL*/;
		
		request.setAttribute("info", info);
		
		RecetteService recetteService = new RecetteService();
		com.blogrecette.model.Recette recette = new com.blogrecette.model.Recette();
		recette= recetteService.getRecetteById(idRecette);
		
		
		Commentaire commentaire = new Commentaire();
		commentaire.setRecettes(recette);
		commentaire.setAuteur(auteur);
		commentaire.setContenu(contenu);
		commentaire.setNote(note);
		commentaire.setDateCreation(new java.util.Date());
		
		if (info.trim().isEmpty()) {
			try {
			
				CommentaireService commentaireService = new CommentaireService();
				commentaireService.createCommentaire(commentaire);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				
			}
			
			
			request.setAttribute("commentaire", commentaire);
			//response.sendRedirect("recette?id="+request.getParameter("id"));
			this.doGet(request, response);
			//this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);//Return like

		}else {
			request.setAttribute("commentaire", commentaire);
			//response.sendRedirect("recette?id="+request.getParameter("id"));
			this.doGet(request, response);
			//this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);//Return like

		}

	}

}
