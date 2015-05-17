package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Tool_Security;
import utils.Tool_TimeStamp;
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
	private String username, passwort, kommentar, camname, url;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("addUser")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Add_User.jsp");
			dispatcher.forward(request, response);	
 		}
		
		else if(action.equals("addCam")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Add_Cam.jsp");
			dispatcher.forward(request, response);	
 		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		initVar();
		
 		/** User Editierung: **/
		if(action.equals("editUser")){
			checkUserId(request);
			
			try {
				user = daoImp.getUserFromDatabase(id);
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
			
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Edit_User.jsp");
			dispatcher.forward(request, response);	
			
 		//Löscht den ausgewählten Nutzer in der Datenbank und kehrt zur User Liste zurück:
 		} else if(action.equals("deleteUser")){
			checkUserId(request);

 			daoImp.deleteUserInDatabase(id);
 			
 			System.out.println("User mit der ID: " + id + " erfolgreich gelöscht!");
 			backToAuswahl(request, response);
 		
 		//Änderungen eines Nutzers sichern und in Datenbank updaten:
 		} else if(action.equals("saveUser")){
			checkUserId(request);
 			
 	 		if (request.getParameter("username") != null && request.getParameter("rechte") != null
 	 				&& request.getParameter("kommentar") != null) {
 	 			username = request.getParameter("username");
 	 			rechte = Integer.valueOf(request.getParameter("rechte"));
 	 			kommentar = request.getParameter("kommentar");
 	 		}
 	 		
 	 		//Versucht den User in der Datenbank upzudaten:
			try {
				if ((rechte == 0) || (rechte == 1)) {
					user = daoImp.getUserFromDatabase(id);
					user.setUsername(username);
					user.setRechte(rechte);
					user.setKommentar(kommentar);
					daoImp.updateUser(user);
		 			System.out.println("User mit der ID: " + id + " erfolgreich geupdatet!");
					
	 	 		} else {
	 	 			System.out.println("Falsche Angabe der Rechte! Zulässige Rechte, 0 = User, 1 = Admin!");
	 	 		}
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
 
 			backToAuswahl(request, response);
 			
 		//Neuen Nutzer in die Datenbank hinzufügen:
 		} else if(action.equals("addNewUser")){
 	 		if (request.getParameter("username") != null && request.getParameter("passwort") != null 
 	 				&& request.getParameter("rechte") != null && request.getParameter("kommentar") != null) {
 	 			username = request.getParameter("username");
 	 			passwort = request.getParameter("passwort");
 	 			rechte = Integer.valueOf(request.getParameter("rechte"));
 	 			kommentar = request.getParameter("kommentar");
 	 		}
 	 		
 	 		//Überprüfung ob Name bereits vergeben oder die Rechte im zulässigen Bereich sind! 0 = User, 1 = Admin:
 	 		if (daoImp.isUsernameExisting(username) == false) {
 	 			if ((rechte == 0) || (rechte == 1)) {
 	 	 	 		daoImp.createUserInDatabase(new User(username,new String(Tool_Security.hashFromString(passwort)),rechte,Tool_TimeStamp.getTimeStampString(),kommentar));
 	 	 			System.out.println("Neuer User: " + username + " erfolgreich hinzugefügt!");
 	 			} else {
 	 				System.out.println("Falsche Angabe der Rechte! Zulässige Rechte, 0 = User, 1 = Admin!");
 	 			}
 	 		} else {
 	 			System.out.println("User mit dem Namen: " + username + " bereits vorhanden!");
 	 		}
 			backToAuswahl(request, response);
 		}
		
		/** Cam Editierung: **/
		if(action.equals("editCam")){
			checkUserId(request);
			cam = camDaoImp.getCamFromDatabase(id);
			
			request.setAttribute("cam", cam);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Edit_Cam.jsp");
			dispatcher.forward(request, response);	
		
		//Löscht die ausgewählte Cam und kehrt zum Auswahlbildschirm zurück:	
 		} else if(action.equals("deleteCam")){
			checkUserId(request);
			
 			camDaoImp.deleteCamInDatabase(id);
 			
 			System.out.println("Cam mit der ID: " + id + " erfolgreich gelöscht!");
 			backToAuswahl(request, response);
 		
 		//Änderungen der Cam in der Datenbank updaten:
 		} else if(action.equals("saveCam")){
			checkUserId(request);
			
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
		passwort = null;
		kommentar = null;
		camname = null;
		url = null;
	}
	
	//Check ID, nur da verwenden wo auch gebraucht:
	private void checkUserId(HttpServletRequest request) {
 		if (request.getParameter("id") != null) {
			id = Integer.valueOf(request.getParameter("id"));
 		}
	}
}
