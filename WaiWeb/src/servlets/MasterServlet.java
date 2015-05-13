package servlets;

//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cam;
import model.User;
//import utils.Tool_ImageProcessing;
import utils.Tool_Security;
import utils.Tool_TimeStamp;
import Dao.CamDaoImpl;
import Dao.DatabaseControllerImpl;
import Dao.UserDaoImpl;

/**
 * Servlet implementation class MasterServlet
 */
@WebServlet("/MasterServlet")
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * Servlet zur Weiterleitung auf Startseite, erste Instanz:
     */
    public MasterServlet() {
        super();
       
        /**	
        //WICHTIG!!!
         * Auskommentieren für Beispielnutzer usw. bei Start!
         * Momentan genutzt für Tests: USERNAME: admin PW: admin
         */
        
        beispiele();		
    }
    
    public void beispiele(){
    	
		DatabaseControllerImpl db = new DatabaseControllerImpl();
		db.createDatabase();
		
		UserDaoImpl udb = new UserDaoImpl();
		
		//User anlegen, einmal mit Adminrechte, einmal ohne:
		udb.createUserInDatabase(new User("admin",new String(Tool_Security.hashFromString("admin")),1,Tool_TimeStamp.getTimeStampString(),""));
		udb.createUserInDatabase(new User("user",new String(Tool_Security.hashFromString("user")),0,Tool_TimeStamp.getTimeStampString(),""));
		
		//Test ob userlogin korrekt ist einmal mit fehlerfall
		System.out.println("Existing: "+udb.isUsernameExisting("UserB"));
		System.out.println("Validlogin: "+udb.isUserLoginValid("admin",new String(Tool_Security.hashFromString("admin"))));
		
		//ausgabe aller user
		ArrayList<User>list = (ArrayList<User>) udb.getAllUsers();
		for(int i=0; i< list.size();i++){
			System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		}
		
		//create cams
		
		CamDaoImpl dao = new CamDaoImpl();
		
		dao.createCamInDatabase(new Cam("Wiese", "www.spielgel.de", Tool_TimeStamp.getTimeStampString(), "/camimages", "yolo"));
		dao.createCamInDatabase(new Cam("Fluss", "www.natur.de", Tool_TimeStamp.getTimeStampString(), "/camimages", "fluesse"));
		dao.createCamInDatabase(new Cam("Berg", "www.berg.de", Tool_TimeStamp.getTimeStampString(), "/camimages", "berg"));

		ArrayList<Cam>camlist = (ArrayList<Cam>) dao.getAllCams();
		for(Cam cam : camlist){
			System.out.println("Id: "+cam.getCamname() + " url: "+cam.getUrl()+" erstellt am: "+cam.getTimeOfCreation()+ " directory: "+cam.getPathOriginalImageDirectory()+" kommentare: "+cam.getKommentar());
		}
		
		//Aenderung username und passwort, einsetzen neuen zeitstempel
		//udb.updateUser(new User(1,"UseraaaA","meinyoooolo",1,Tool_TimeStamp.getTimeStampString(),""));
		//udb.updateUser(new User(2,"UseraaaaaaA","meinyoooolo",1,Tool_TimeStamp.getTimeStampString(),""));

		//Ausgabe aller user
		//list = (ArrayList<User>) udb.getAllUsers();
		//for(int i=0; i< list.size();i++){
		//	System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		//}
		
		
		//User id holen, dann damit user loeschen		
		//long userid = udb.getUserIdFromDatabaseByName("UseraaaaaaA");
		//System.out.println("Gesuchte userid: "+userid);
		//udb.deleteUserInDatabase(userid);
		
		
		//ausgabe aller user
		//list = (ArrayList<User>) udb.getAllUsers();
		//for(int i=0; i< list.size();i++){
		//	System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		//}	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		//Direkte Weiterleitung auf Login Bildschirm bei erstem Start:
		if (action == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/login.html");
			dispatcher.forward(request, response);
		}
		
		else if (action.equals("edit")) {
			//Auf JPS für Einstellungen weiterleiten:
		}
		
		else if (action.equals("logout")) {
			//Initiiere Logout-Prozess für den angemeldeten User:
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
