package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CamDaoImpl;
import Dao.UserCamMappingImpl;
import Dao.UserDaoImpl;
import model.Cam;
import model.User;

public class AuswahlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    final UserDaoImpl daoImp = new UserDaoImpl();
    final CamDaoImpl camdaoImp= new CamDaoImpl();
	final UserCamMappingImpl ucDaoImp= new UserCamMappingImpl();
	private ArrayList<Cam> camList;
    private String username;
    private Long id;
	
    //GET:
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		//Aktive Session überprüfen:
        if(session != null){
        	//Rechte überprüfen: (ADMINISTRATOR)
        	if((int) session.getAttribute("rechte") == 1) {
        		System.out.println("Session mit User=" + session.getAttribute("username") 
        			+ " und Rechte=" + session.getAttribute("rechte") + " bestätigt.");

	        	//Falls Action vorhanden prüfen, ansonsten auf Auswahl-Bildschirm:
				if (action == null) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
					dispatcher.forward(request, response);	
				
				//Alle Cams anzeigen: TODO: Thumbnail anzeigen für die einzelnen Cams!
				} else if (action.equals("cam")) {
					List<Cam> collection = camdaoImp.getAllCams();
					
					request.setAttribute("cams", collection);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Cam.jsp");
					dispatcher.forward(request, response);	
				
				//Alle User anzeigen:
        		} else if (action.equals("user")){
					List<User> collection = daoImp.getAllUsers();
					request.setAttribute("users", collection);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/User.jsp");
					dispatcher.forward(request, response);		

				//Einstellungen anzeigen:
        		} else if(action.equals("settings")){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Einstellungen.jsp");
					dispatcher.forward(request, response);	
					
				}
			
			//Rechte überprüfen: (USER):
        	} else if ((int) session.getAttribute("rechte") == 0) {
	        	//Falls Action vorhanden prüfen, ansonsten auf Auswahl-Bildschirm:
				if (action == null) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
					dispatcher.forward(request, response);	
				
				//Cams für den jeweiligen User anpassen:	
				} else if (action.equals("cam")){
					username = (String) session.getAttribute("username");
					id = daoImp.getUserIdFromDatabaseByName(username);
					camList = ucDaoImp.getUserCamMapping(id);
					
					List<Cam> collection = new ArrayList<Cam>();
					
					for(int i=0;i<camList.size();i++) {
						collection.add(i, (camdaoImp.getCamFromDatabase(camList.get(i).getId_Cam())));
					}
					
					request.setAttribute("cams", collection);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Cam.jsp");
					dispatcher.forward(request, response);		
				
				//Kein Zugriff!
				} else if (action.equals("user")) {
					backToAuswahl(request, response);
				//Kein Zugriff!	
				} else if (action.equals("settings")) {
					backToAuswahl(request, response);
				}
        	}	
        //Keine aktive Session vorhanden:
        } else {
        	System.out.println("ERROR! Keine aktive Session gefunden!");
        	response.sendRedirect(request.getContextPath() + "/master");
        }
	}

	//POST:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	void backToAuswahl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Rechte nicht ausreichend, Administrator-Rechte benötigt!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
		dispatcher.forward(request, response);	
	}
}
