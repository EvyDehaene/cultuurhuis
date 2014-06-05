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
import javax.servlet.http.HttpSession;

import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Klant;
import be.vdab.entities.ReservatieMandjeItem;

/**
 * Servlet implementation class OverzichtServlet
 */
@WebServlet("/overzicht")
public class OverzichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/overzicht.jsp";
	private final VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<ReservatieMandjeItem> gelukteReservaties = new ArrayList<>();
		List<ReservatieMandjeItem> mislukteReservaties = new ArrayList<>();
		if (session!=null){
			@SuppressWarnings("unchecked")
			Map<Integer, Integer> mandje = (HashMap<Integer, Integer>) session.getAttribute("mandje");
			Klant sessieKlant = (Klant) session.getAttribute("klant");
			if (mandje!=null){
				List<ReservatieMandjeItem> reservaties = new ArrayList<>();
				for (Map.Entry<Integer, Integer> entry : mandje.entrySet()){
					int nummer = entry.getKey();
					int plaatsen = entry.getValue();
					reservaties.add(new ReservatieMandjeItem(plaatsen, voorstellingDAO.findByNumber(nummer)));
				}
				for (ReservatieMandjeItem item:reservaties){
					int plaatsen = item.getPlaatsen();
					int vrijePlaatsen = voorstellingDAO.findByNumber(item.getNummer()).getVrijePlaatsen();
					if (plaatsen <= vrijePlaatsen) {
						voorstellingDAO.update(plaatsen, item.getNummer());;
						voorstellingDAO.createReservatie(sessieKlant.getKlantNr(), item.getNummer(), plaatsen);
						
						gelukteReservaties.add(item);
						request.setAttribute("gelukt", gelukteReservaties);
					} else {
						mislukteReservaties.add(item);
						request.setAttribute("mislukt", mislukteReservaties);
					}
				}
			}
			
			//request.setAttribute("klant", sessieKlant);
			session.removeAttribute("klant");
			session.removeAttribute("mandje");
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
			dispatcher.forward(request, response);
		}
	}

}
