package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Voorstelling;

/**
 * Servlet implementation class Voorstellingen
 */
@WebServlet("/voorstellingen")
public class VoorstellingenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Iterable<Voorstelling> voorstellingen;
	private static final String VIEW = "/WEB-INF/JSP/voorstellingen.jsp";
	private final VoorstellingDAO voorstellingDAO = new VoorstellingDAO();
	private final GenreDAO genreDAO = new GenreDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int nummer = Integer.parseInt(request.getParameter("nummer"));
		String naam = request.getParameter("naam");
		List<Voorstelling> voorstellingen= (List<Voorstelling>) voorstellingDAO.findByGenre(nummer);//new ArrayList<>();
		
		
		//voorstellingen.addAll(voorstellingDAO.findByGenre(nummer));
		request.setAttribute("naam", naam);
		request.setAttribute("nummer", nummer);
		request.setAttribute("genres", genreDAO.findAll());
		request.setAttribute("voorstellingen", voorstellingen);
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
		
	}

}
