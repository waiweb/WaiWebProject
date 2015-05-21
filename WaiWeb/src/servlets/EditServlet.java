package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.CHECKCAST;

import utils.Tool_Security;
import utils.Tool_TimeStamp;
import exception.UserNotFoundExecption;
import Dao.CamDaoImpl;
import Dao.UserCamMappingImpl;
import Dao.UserDaoImpl;
import model.Cam;
import model.User;


public class EditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	final UserDaoImpl daoImp = new UserDaoImpl();
	final CamDaoImpl camDaoImp = new CamDaoImpl();
	final UserCamMappingImpl ucDaoImp= new UserCamMappingImpl();
	private User user;
	private Cam cam;
	private int rechte, id;
	private String username, passwort, kommentar, camname, url;
	
	
	//GET:
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String action = request.getParameter("action");
		
		if(action.equals("addUser")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Add_User.jsp");
			dispatcher.forward(request, response);	
			
 		} else if(action.equals("addCam")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Add_Cam.jsp");
			dispatcher.forward(request, response);	
			
 		} else if(action.equals("back")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
			dispatcher.forward(request, response);	
 		}
	}
	
	//POST:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		initVar();
		
 		/** User Editierung: **/
		//User auswählen zum editieren:
		if(action.equals("editUser")){
			checkUserId(request);
			List<Cam> cams= new ArrayList<Cam>();
			List<Cam> checkedCams=new ArrayList<Cam>();
			List<Cam> tempCheckedCams=new ArrayList<Cam>();
			
			try {
				user = daoImp.getUserFromDatabase(id);
			    cams=camDaoImp.getAllCams();
			    tempCheckedCams=ucDaoImp.getUserCamMapping(daoImp.getUserFromDatabase(id));
			    
			    for(int i=0;i<tempCheckedCams.size();i++){
			    	long id= tempCheckedCams.get(i).getId_Cam();
			    	checkedCams.add(camDaoImp.getCamFromDatabase(id));
			    	
			    }
			    
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
			
			// die gecheckten cams aus der liste allcams l�schen
			for(int i=cams.size()-1;i>=0;i--){
				for(int j=checkedCams.size()-1; j>=0; j--){
				  if(cams.get(i).getId_Cam()==checkedCams.get(j).getId_Cam()){
				       cams.remove(i);
				        break;}
			     }
			}
			

			
			
			request.setAttribute("checkedCams",checkedCams );
			request.setAttribute("cams", cams);
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
		//Cam auswählen zum editieren:
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
 	
 		//Neue Cam in der Datenbank hinzufügen:
 		} else if(action.equals("addNewCam")){
 	 		if (request.getParameter("camname") != null && request.getParameter("url") != null 
 	 				&& request.getParameter("kommentar") != null) {
 	 			camname = request.getParameter("camname");
 	 			url = request.getParameter("url");
 	 			kommentar = request.getParameter("kommentar");
 	 		}
 	 		
 	 		//Überprüfung ob Name bereits vergeben oder die Rechte im zulässigen Bereich sind! 0 = User, 1 = Admin:
 	 		//TODO: Check if URL is valid!
 	 		if (camDaoImp.isCamNameExisting(camname) == false) {
 	 				camDaoImp.createCamInDatabase(new Cam(camname, url, Tool_TimeStamp.getTimeStampString(), "/camimages", kommentar));
 	 	 			System.out.println("Neue Cam: " + camname + " erfolgreich hinzugefügt!");
 	 		} else {
 	 			System.out.println("Cam mit dem Namen: " + camname + " ist bereits vorhanden!");
 	 		}
 			backToAuswahl(request, response);
 		}
		   // die angehackten checkboxen werden rausgelesen und in die User_cam tabelle geschrieben
		String[] checkbox=request.getParameterValues("checked");
		
		if(checkbox!=null){
			checkUserId(request);
	    	ArrayList<Cam> camList = new ArrayList<Cam>();
	    	try {
				user=daoImp.getUserFromDatabase(id);
			} catch (UserNotFoundExecption e1) {
				e1.printStackTrace();
			}
	    	for (int i = 0; i < checkbox.length; i++) 
	        {  System.out.println ("cam: "+checkbox[i]+ " wurde zu user: "+user.getUsername()+" hinzugef�gt");
	          camList.add(camDaoImp.getCamFromDatabase(Integer.valueOf(checkbox[i])));
	        }
	    
	    	ucDaoImp.setUserCamMapping(user, camList);
	    }else if(checkbox==null){
	    	try {
				user=daoImp.getUserFromDatabase(id);
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
	    	ArrayList<Cam> camList = new ArrayList<Cam>();
	    	ucDaoImp.setUserCamMapping(user, camList);
	    	
	    }
	}
	
	//Funktion um auf die User Liste zurückzukehren:
	private void backToAuswahl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
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
