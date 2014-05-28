package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.GenreDAO;
import be.vdab.entities.Genre;

/**
 * Servlet implementation class WelkomServlet
 */
@WebServlet("/welkom")
public class WelkomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Iterable<Genre> genres;
	private static final String VIEW = "/WEB-INF/JSP/welkom.jsp";
	private final GenreDAO genreDAO = new GenreDAO();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("genres", genreDAO.findAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
		
	}

	

}
