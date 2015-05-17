package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UserNotFoundExecption;
import Dao.CamDaoImpl;
import Dao.UserDaoImpl;
import model.Cam;
import model.User;


public class EditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	final UserDaoImpl daoImp = new UserDaoImpl();
	final CamDaoImpl camDaoImp = new CamDaoImpl();
	private User user;
	private Cam cam;
	private int rechte, id;
	private String username, kommentar, camname, url;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("jakd");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		initVar();
		
 		if (request.getParameter("id") != null) {
			id = Integer.valueOf(request.getParameter("id"));
 		}
		
 		/** User Editierung: **/
		if(action.equals("editUser")){
			try {
				user = daoImp.getUserFromDatabase(id);
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
			
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Edit_User.jsp");
			dispatcher.forward(request, response);	
			
 		} else if(action.equals("deleteUser")){
 			//Löscht den ausgewählten Nutzer in der Datenbank und kehrt zur User Liste zurück:
 			daoImp.deleteUserInDatabase(id);
 			
 			System.out.println("User mit der ID: " + id + " erfolgreich gelöscht!");
 			backToAuswahl(request, response);
 			
 		} else if(action.equals("saveUser")){
 			//Einlesen der benötigen Daten vom request:
 	 		if (request.getParameter("username") != null && request.getParameter("rechte") != null
 	 				&& request.getParameter("kommentar") != null) {
 	 			username = request.getParameter("username");
 	 			rechte = Integer.valueOf(request.getParameter("rechte"));
 	 			kommentar = request.getParameter("kommentar");
 	 		}
 	 		
 	 		//Versucht den User in der Datenbank upzudaten:
			try {
				user = daoImp.getUserFromDatabase(id);
				user.setUsername(username);
				user.setRechte(rechte);
				user.setKommentar(kommentar);
				daoImp.updateUser(user);
				
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
 			
 			System.out.println("User mit der ID: " + id + " erfolgreich geupdatet!");
 			backToAuswahl(request, response);
 		}
		
		/** Cam Editierung: **/
		if(action.equals("editCam")){
			cam = camDaoImp.getCamFromDatabase(id);
			
			request.setAttribute("cam", cam);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Edit_Cam.jsp");
			dispatcher.forward(request, response);	
			
 		} else if(action.equals("deleteCam")){
 			//Löscht die ausgewählte Cam und kehrt zum Auswahlbildschirm zurück:
 			camDaoImp.deleteCamInDatabase(id);
 			
 			System.out.println("Cam mit der ID: " + id + " erfolgreich gelöscht!");
 			backToAuswahl(request, response);
 			
 		} else if(action.equals("addCam")){
 			//TODO: Leitet auf die nächste JSP Seite um eine neue Cam hinzuzufügen:
		
		} else if(action.equals("saveCam")){
 			//Einlesen der benötigen Daten vom request:
 	 		if (request.getParameter("camname") != null && request.getParameter("url") != null
 	 				&& request.getParameter("kommentar") != null) {
 	 			camname = request.getParameter("camname");
 	 			url = request.getParameter("url");
 	 			kommentar = request.getParameter("kommentar");
 	 		}
 	 		
 	 		cam = camDaoImp.getCamFromDatabase(id);
			cam.setCamname(camname);
			cam.setUrl(url);
			cam.setKommentar(kommentar);
			camDaoImp.updateUser(cam);
 			
 			System.out.println("Cam mit der ID: " + id + " erfolgreich geupdatet!");
 			backToAuswahl(request, response);	
 		}
	}
	
	//Funktion um auf die User Liste zurückzukehren:
	private void backToAuswahl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmöglichkeiten.jsp");
		dispatcher.forward(request, response);
	}
	
	//Initialisierung der Variablen:
	private void initVar() {
		user = new User();
		cam = new Cam();
		rechte = 0;
		id = 0; 
		username = null;
		kommentar = null;
		camname = null;
		url = null;
	}
}
