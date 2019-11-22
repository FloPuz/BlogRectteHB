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
import com.blogrecette.model.Tag;
import com.blogrecette.services.RecetteService;
import com.blogrecette.services.TagService;

/**
 * Servlet implementation class DeleteTag
 */
@WebServlet(name = "delete-tag", urlPatterns = {"/delete-tag"})

public class DeleteTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTag() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		TagService tagService = new TagService();
		Tag tag = new Tag();
		try {
			tag = tagService.getTagById(Integer.parseInt(request.getParameter("id")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tagService.deleteTag(tag);
		
		response.sendRedirect("tag");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
