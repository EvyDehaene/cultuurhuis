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

import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Voorstelling;

/**
 * Servlet implementation class VoorstellingServlet
 */
@WebServlet("/voorstelling")
public class VoorstellingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/voorstelling.jsp";
	private static final String SUCCESSFUL_VIEW = "/reservatiemandje";
	private static final String MANDJE = "mandje";
	private final VoorstellingDAO voorstellingDAO = new VoorstellingDAO();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<Integer,Integer> mandje = (Map<Integer,Integer>)request.getSession().getAttribute(MANDJE);
		int nummer = 0;
		try {
			nummer = Integer.parseInt(request.getParameter("nummer")); // nummer van de voorstelling                                                                              
			Voorstelling voorstelling = voorstellingDAO.findByNumber(nummer );
			request.setAttribute("voorstelling", voorstelling);
			if (mandje!=null){
				for (Map.Entry<Integer, Integer> entry : mandje.entrySet()){
					if (nummer==entry.getKey()){
						int plaatsen=entry.getValue();
						request.setAttribute("gereserveerdePlaatsen", plaatsen);
					}
				}
			}
		} catch (NumberFormatException ex) {
			// we doen niets, de pagina blijft staan
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Voorstelling voorstelling = null;
		try {
			int nummer = Integer.parseInt(request.getParameter("nummer"));
			voorstelling = voorstellingDAO.findByNumber(nummer);
		} catch (NumberFormatException ex) {
			// we doen niets-> de pagina blijft staan
		}
		Map<String, String> fouten = new HashMap<>();
		if (!request.getParameter("plaatsen").isEmpty()) {
			int plaatsenTeReserveren = 0;
			try {
				plaatsenTeReserveren = Integer.parseInt(request.getParameter("plaatsen"));
				if (plaatsenTeReserveren>voorstelling.getVrijePlaatsen()|| plaatsenTeReserveren < 1) {
					fouten.put("plaatsen","Tik een getal tussen 1 en "+voorstelling.getVrijePlaatsen());
				}
			} catch (Exception ex) {
				fouten.put("plaatsen", "Tik een getal");
			}
			if (fouten.isEmpty()) {
				@SuppressWarnings("unchecked")
				Map<Integer,Integer> mandje = (Map<Integer,Integer>)request.getSession().getAttribute(MANDJE);
				if(mandje==null){
					mandje = new HashMap<Integer,Integer>();
				} else if (mandje!=null){
					for (Map.Entry<Integer, Integer> entry : mandje.entrySet()){
						if (voorstelling.getVoorstellingsNr()==entry.getKey()){
							plaatsenTeReserveren = entry.getValue()+plaatsenTeReserveren;
						} 
					}
				}
				mandje.put(voorstelling.getVoorstellingsNr(), plaatsenTeReserveren);
				request.getSession().setAttribute(MANDJE, mandje);
				response.sendRedirect(request.getContextPath()+SUCCESSFUL_VIEW/*+voorstelling.getGenreNr()*/);
			} else {
				request.setAttribute("fouten", fouten);
				request.setAttribute("voorstelling", voorstelling);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		} else {
			// aantal plaatsen in niet ingevuld
			fouten.put("plaatsen","Tik een getal of ga terug naar voorstellingen");
			request.setAttribute("voorstelling", voorstelling);
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
