package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

/**
 * Servlet implementation class BevestigingServlet
 */
@WebServlet("/bevestiging")
public class BevestigingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/bevestiging.jsp";
	private final KlantDAO klantDAO = new KlantDAO();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		String paswoord = request.getParameter("paswoord");
		if (gebruikersnaam == null || gebruikersnaam.isEmpty()) {
			fouten.put("gebruikersnaam", "Gebruikersnaam is een verplicht veld");
		}
		if (paswoord == null || paswoord.isEmpty()) {
			fouten.put("paswoord", "Paswoord is een verplicht veld");
		}
		Klant klant = klantDAO.getBestaandeKlant(gebruikersnaam, paswoord);
		if (klant==null){
			fouten.put("klant", "Verkeerde gebruikersnaam of paswoord");
		}
		if (fouten.isEmpty()) {
			request.setAttribute("klant", klant);
			request.getSession().setAttribute("klant", klant);
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
			dispatcher.forward(request, response);
			
		} else {
			request.setAttribute("fouten", fouten);
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
			dispatcher.forward(request, response);
		}
		
	}

}
