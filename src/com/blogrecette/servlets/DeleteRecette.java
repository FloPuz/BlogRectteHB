package com.blogrecette.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.blogrecette.services.RecetteService;

/**
 * Servlet implementation class DeleteRecette
 */
@WebServlet(name = "deleteRecette", urlPatterns = {"/deleteRecette"})
public class DeleteRecette extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRecette() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		RecetteService recetteService = new RecetteService();
		Recette recette = new Recette();
		recette = recetteService.getRecetteById(Integer.parseInt(request.getParameter("idRecette")));
		recetteService.deleteRecette(recette);
		Membre membre = new Membre();
		membre = (Membre)session.getAttribute("membre");
		response.sendRedirect("recettesEdit?idMembre="+membre.getIdMembre());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);//Privil�gi�s pour le doPost
	}

}
