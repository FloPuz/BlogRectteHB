package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Membre;
import com.blogrecette.services.MembreService;


/**
 * Servlet implementation class inscription
 */
@WebServlet(name = "inscription", urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);//Return like

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		


		String info = "";
		String success="Bienvenu";
		Date dateInscription = null;


		String nom = request.getParameter("nom");
		if (nom.isEmpty()) {
			info+="Veuillez saisir un nom<br>";
		}

		String pseudo = request.getParameter("pseudo");
		if (pseudo.isEmpty()) {
			info+="Veuillez saisir un prenom<br>";
		}

		String email= request.getParameter("email");
		if (email.isEmpty()) {
			info+="Veuillez saisir un email<br>";
		}



		try {
			dateInscription = new Date();
		}
		catch (IllegalArgumentException e) {
			info += "Veuillez saisir une date <br>";
		}

		String passwd = request.getParameter("mdp");
		if (passwd.isEmpty()) {
			info+="Veuillez saisir un mot de passe<br>";
		}
		String passwdconf = request.getParameter("mdpconf");
		if (passwd==passwdconf ) {
			info+="Veuillez saisir le même mot de passe<br>";
		}
		if (passwdconf.isEmpty()) {
			info+="Vous n'avez pas confirmez votre mot de passe<br>";
		}




		request.setAttribute("info", info);








		Membre member=new Membre();
		member.setNom(nom);
		member.setPseudo(pseudo);
		member.setEmail(email);
		member.setMdp(passwd);
		member.setDateInscription(dateInscription);







		if (info.trim().isEmpty()) {
			try {
				MembreService membreservice = new MembreService();
				membreservice.addMembre(member);
			} catch (Exception e) {
				// TODO: handle exception
			}
			request.setAttribute("success", success);
			session.setAttribute("member", member);

			response.sendRedirect("index");
		}else {
			request.setAttribute("member", member);
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);

		}









		//this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);//Return like



	}
}


