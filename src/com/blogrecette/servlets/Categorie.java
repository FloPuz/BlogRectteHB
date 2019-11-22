package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.services.CategorieService;
import com.blogrecette.services.RecetteService;



/**
 * Servlet implementation class Categorie
 */
@WebServlet(name = "categorie", urlPatterns = {"/categorie"})
public class Categorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categorie() {
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
		
		
		List<com.blogrecette.model.Categorie> categories =new ArrayList<>();
		List<com.blogrecette.model.Recette> recettesCategorie1 =new ArrayList<>();

		try {
			CategorieService categorieservice= new CategorieService();
			RecetteService recetteservice = new RecetteService();
			
			categories = categorieservice.getAllCategories();
			session.setAttribute("categories", categories);
		
			recettesCategorie1 = recetteservice.selectAllRecettesByCategories(Integer.parseInt(request.getParameter("idCategorie")));
			session.setAttribute("recettesCategorie1", recettesCategorie1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/categorie.jsp").forward(request, response);//Return like

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/categorie.jsp").forward(request, response);//Return like

	}

}
