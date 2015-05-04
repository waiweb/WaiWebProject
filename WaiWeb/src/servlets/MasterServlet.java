package servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import utils.Tool_ImageProcessing;
import utils.Tool_Security;
import utils.Tool_TimeStamp;
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
     */
    public MasterServlet() {
        super();
        

        beispiele();
		

    }
    
    
    public void beispiele(){
    	
		DatabaseControllerImpl db = new DatabaseControllerImpl();
		db.createDatabase();
		
		
		UserDaoImpl udb = new UserDaoImpl();
		
		//User anlegen einmal mit und einmal ohne gehashtem password (mit ist besser !)
		udb.createUserInDatabase(new User("UserA",new String(Tool_Security.hashFromString("meinpass")),1,Tool_TimeStamp.getTimeStampString(),""));
		udb.createUserInDatabase(new User("UserB","meinpass",1,Tool_TimeStamp.getTimeStampString(),""));
		
		//Test ob userlogin korrekt ist einmal mit fehlerfall
		System.out.println("Existing: "+udb.isUsernameExisting("UserB"));
		System.out.println("Validlogin: "+udb.isUserLoginValid("UserA",new String(Tool_Security.hashFromString("Meinpass"))));
		System.out.println("Validlogin: "+udb.isUserLoginValid("UserA",new String(Tool_Security.hashFromString("meinpass"))));
		
		//ausgabe aller user
		ArrayList<User>list = (ArrayList<User>) udb.getAllUsers();
		for(int i=0; i< list.size();i++){
			System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		}
		
		//Änderung username und passwort, einsetzen neuen zeitstempel
		udb.updateUser(new User(1,"UseraaaA","meinyoooolo",1,Tool_TimeStamp.getTimeStampString(),""));
		udb.updateUser(new User(2,"UseraaaaaaA","meinyoooolo",1,Tool_TimeStamp.getTimeStampString(),""));

		//ausgabe aller user
		list = (ArrayList<User>) udb.getAllUsers();
		for(int i=0; i< list.size();i++){
			System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		}
		
		
		//User id holen, dann damit user löschen		
		long userid = udb.getUserIdFromDatabaseByName("UseraaaaaaA");
		System.out.println("Gesuchte userid: "+userid);
		udb.deleteUserInDatabase(userid);
		
		
		//ausgabe aller user
		list = (ArrayList<User>) udb.getAllUsers();
		for(int i=0; i< list.size();i++){
			System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		}
		

    	
    }
    
    // ToDo    Servlet um Liste für cams und benutzer an Auswahl.jsp zu senden
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
