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
import be.vdab.entities.ReservatieMandjeItem;
import be.vdab.entities.Voorstelling;


@WebServlet("/reservatiemandje")
public class ReservatieMandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/reservatiemandje.jsp";
	private final VoorstellingDAO voorstellingDAO = new VoorstellingDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		double totaal = 0;
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Integer, Integer> mandje =  (HashMap<Integer, Integer>) session.getAttribute("mandje"); 
			if (mandje != null) { 
				List<ReservatieMandjeItem> reservaties = new ArrayList<>();
				for (Map.Entry<Integer, Integer> entry : mandje.entrySet()) { 
					int nummer = entry.getKey();
					int plaatsen = entry.getValue();
					ReservatieMandjeItem reservatie = new ReservatieMandjeItem(plaatsen, voorstellingDAO.findByNumber(nummer));
					
					reservaties.add(reservatie);
					double tussenResultaat = plaatsen * voorstellingDAO.findByNumber(nummer).getPrijs().doubleValue();
					totaal = totaal + tussenResultaat;

				}
				request.setAttribute("reservaties", reservaties);
				request.setAttribute("totaalTeBetalen", totaal);
			}
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double totaal = 0;
		if (!request.getParameterMap().isEmpty()){
		HttpSession session = request.getSession(false);
		  if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Integer, Integer> mandje =  (HashMap<Integer, Integer>) session.getAttribute("mandje"); 
			
			List<ReservatieMandjeItem> reservaties = new ArrayList<>();
			if (mandje != null) {
				for (Map.Entry<Integer, Integer> entry : mandje.entrySet()) { 
					int nummer = entry.getKey();
					int plaatsen = entry.getValue();
					ReservatieMandjeItem reservatie = new ReservatieMandjeItem(plaatsen, voorstellingDAO.findByNumber(nummer));
					reservaties.add(reservatie);
					double tussenResultaat = plaatsen * voorstellingDAO.findByNumber(nummer).getPrijs().doubleValue();
					totaal = totaal + tussenResultaat;
					request.setAttribute("reservaties", reservaties);
					request.setAttribute("totaalTeBetalen", totaal);
				}
				if (!request.getParameter("verwijderNr").isEmpty()){
					int nr=Integer.parseInt(request.getParameter("verwijderNr"));
					for (ReservatieMandjeItem reservatie : reservaties){
						if (reservatie.getNummer()==nr){
							reservaties.remove(reservatie);
							double tussenResultaat = reservatie.getPlaatsen() * voorstellingDAO.findByNumber(reservatie.getNummer()).getPrijs().doubleValue();
							totaal = totaal - tussenResultaat;
							mandje.remove(nr);
						}
					}
					request.setAttribute("reservaties", reservaties);
					request.setAttribute("totaalTeBetalen", totaal);
				}
				
			}	
			
		  }
		  RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		  dispatcher.forward(request, response);
		  
		} else {
			
		}
		
	}

}
