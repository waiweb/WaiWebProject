package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DatabaseControllerImpl;

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		
        if (session != null){
        	if ((int) session.getAttribute("rechte") == 1) {
	        	System.out.println("Session mit User=" + session.getAttribute("username") 
	        			+ " und Rechte=" + session.getAttribute("rechte") + " bestätigt.");
			
				if(action.equals("deleteDatabase")){
					//Komplette Datenbank zurücksetzen TODO: Funktion zum rücksetzen der Datenbanken ohne Admin zu löschen!
					DatabaseControllerImpl databaseDelete = new DatabaseControllerImpl();
					databaseDelete.deleteDatabase();
					
					backToAuswahl(request, response);
					
				} else if(action.equals("deletePictures")){
					//Alle gespeicherten Bilder löschen: TODO: Funktion um alle Bilder zu löschen!
					backToAuswahl(request, response);
					
				} else if (action.equals("Back")) {
					backToAuswahl(request, response);
				} 
        	} else {
        		System.out.println("ERROR! Keine ausreichenden Rechte, Administrator-Rechte erforderlich!");
        		response.sendRedirect(request.getContextPath() + "/master");
        	}
			
        } else {
        	System.out.println("ERROR! Keine aktive Session oder ausrechende Rechte gefunden!");
        	response.sendRedirect(request.getContextPath() + "/master");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	//Funktion um auf Auswahlmöglichkeiten zurückzukehren:
	void backToAuswahl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/auswahl");
	}

}
