package com.blogrecette.servlets;

import java.io.IOException;
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
 * Servlet implementation class EditRecette
 */
@WebServlet(name = "recettesEdit", urlPatterns = {"/recettesEdit"})
public class EditRecette extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditRecette() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("recette").equals(null)) {
				session.removeAttribute("recette");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}


		List<com.blogrecette.model.Recette> recettesByMembre =new ArrayList<>();

		try {

			RecetteService recetteservice = new RecetteService();
			recettesByMembre = recetteservice.selectAllRecettesByMembre(Integer.parseInt(request.getParameter("idMembre")));
			session.setAttribute("recettesByMembre", recettesByMembre);

		} catch (Exception e) {
			// TODO: handle exception
		}


		
		this.getServletContext().getRequestDispatcher("/WEB-INF/editrecette.jsp").forward(request, response);//Return like

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
