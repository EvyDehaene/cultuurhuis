package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * Servlet implementation class NieuweKlantServlet
 */
@WebServlet("/nieuweklant")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/nieuweklant.jsp";
	private static final String SUCCESS_VIEW="/WEB-INF/JSP/bevestiging.jsp";
	private final KlantDAO klantDAO = new KlantDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		List<String> nietIngevuld = new ArrayList<>();
		if (!request.getParameterMap().isEmpty()){
			String voornaam = request.getParameter("voornaam");
			String familienaam = request.getParameter("familienaam");
			String straat = request.getParameter("straat");
			String huisNr = request.getParameter("huisNr");
			String postcode = request.getParameter("postcode");
			String gemeente = request.getParameter("gemeente");
			String gebruikersnaam = request.getParameter("gebruikersnaam");
			String paswoord = request.getParameter("paswoord");
			String herhaalPaswoord = request.getParameter("herhaalPaswoord");
			if (voornaam==null||voornaam.isEmpty()) {
				nietIngevuld.add("Voornaam");
			}
			if (familienaam==null||familienaam.isEmpty()) {
				nietIngevuld.add("Familienaam");
			}
			if (straat==null||straat.isEmpty()){
				nietIngevuld.add("Straat");
			}
			if (huisNr==null||huisNr.isEmpty()) {
				nietIngevuld.add("Huisnr.");
			}
			if (postcode==null||postcode.isEmpty()) {
				nietIngevuld.add("Postcode");
			}
			if (gemeente==null||gemeente.isEmpty()) {
				nietIngevuld.add("Gemeente");
			}
			if (gebruikersnaam==null||gebruikersnaam.isEmpty()){
				nietIngevuld.add("Gebruikersnaam");
			}
			if (paswoord==null||paswoord.isEmpty()){
				nietIngevuld.add("Paswoord");
			}
			if (herhaalPaswoord==null||herhaalPaswoord.isEmpty()) {
				nietIngevuld.add("Herhaal paswoord");
			}
			if (!paswoord.equals(herhaalPaswoord)){
				fouten.put("paswoord", "Paswoord en Herhaal paswoord zijn verschillend.");
			}
			if(klantDAO.gebruikersnaamBestaatAl(gebruikersnaam)==true){
				fouten.put("gebruiker", "Gebruikersnaam komt al voor in de database.");
			}
			if (fouten.isEmpty() && nietIngevuld.isEmpty()){
				Klant nieuweKlant = klantDAO.klantToegevoegd(voornaam, familienaam, straat, huisNr, postcode, gemeente, gebruikersnaam, paswoord);
				request.setAttribute("klant", nieuweKlant);
				RequestDispatcher dispatcher = request.getRequestDispatcher(SUCCESS_VIEW);
				dispatcher.forward(request, response);
			} else {
				if (!fouten.isEmpty()){
					request.setAttribute("fouten", fouten);
				}
				if (!nietIngevuld.isEmpty()) {
					request.setAttribute("nietIngevuld", nietIngevuld);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
				dispatcher.forward(request, response);
			}
		}
	}

}
