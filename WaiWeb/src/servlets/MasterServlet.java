package servlets;

//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UserNotFoundExecption;
import model.Cam;
import model.ImageItem;
import model.User;
//import utils.Tool_ImageProcessing;
import utils.Tool_Security;
import utils.Tool_TimeStamp;
import Dao.CamDaoImpl;
import Dao.DatabaseControllerImpl;
import Dao.ImageDaoImpl;
import Dao.UserCamMappingImpl;
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
        
        try {
			beispiele();
		} catch (UserNotFoundExecption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
    
	public void beispiele() throws UserNotFoundExecption{
    	
		DatabaseControllerImpl db = new DatabaseControllerImpl();
		db.createDatabase();
		
		UserDaoImpl udb = new UserDaoImpl();
		UserCamMappingImpl usercammapping = new UserCamMappingImpl();
		
		//User anlegen einmal mit und einmal ohne gehashtem password (mit ist besser !):
		udb.createUserInDatabase(new User("admin",new String(Tool_Security.hashFromString("admin")),1,Tool_TimeStamp.getTimeStampString(),"Admin"));
		udb.createUserInDatabase(new User("user",new String(Tool_Security.hashFromString("user")),0,Tool_TimeStamp.getTimeStampString(),"User"));
		udb.createUserInDatabase(new User("a",new String(Tool_Security.hashFromString("a")),1,Tool_TimeStamp.getTimeStampString(),"User a"));
		udb.createUserInDatabase(new User("b",new String(Tool_Security.hashFromString("b")),0,Tool_TimeStamp.getTimeStampString(),"User b"));

		
		//Test ob userlogin korrekt ist einmal mit fehlerfall
		System.out.println("Existing: "+udb.isUsernameExisting("UserB"));
		System.out.println("Validlogin: "+udb.isUserLoginValid("admin",new String(Tool_Security.hashFromString("admin"))));
		
		//ausgabe aller user
		ArrayList<User>list = (ArrayList<User>) udb.getAllUsers();
		for(int i=0; i< list.size();i++){
			System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		}
		
		//Create cams
		CamDaoImpl camdao = new CamDaoImpl();
		
		camdao.createCamInDatabase(new Cam("Wasserturm", "https://www.mvv-energie.de/webcam_maritim/MA-Wasserturm.jpg", Tool_TimeStamp.getTimeStampString(), "/camimages", ""));
		camdao.createCamInDatabase(new Cam("East", "http://my.dal.biz/cgi-bin/webcam/getpics.cgi?Cam=east", Tool_TimeStamp.getTimeStampString(), "/camimages", ""));
		camdao.createCamInDatabase(new Cam("Big", "http://www.mpc-it.de/webcam/big.jpg", Tool_TimeStamp.getTimeStampString(), "/camimages", ""));
		camdao.createCamInDatabase(new Cam("See", "http://www.die-ersten-am-see.de/webcam/camluzo.jpg", Tool_TimeStamp.getTimeStampString(), "/camimages", ""));
		camdao.createCamInDatabase(new Cam("Fuessen", "http://webcamfuessen.de/webcam/webcamfuessen.jpg", Tool_TimeStamp.getTimeStampString(), "/camimages", ""));
		camdao.createCamInDatabase(new Cam("Neustadthambach", "http://www.neustadthambach.de/pics/schloss.jpg", Tool_TimeStamp.getTimeStampString(), "/camimages", ""));

		ArrayList<Cam>camlist = (ArrayList<Cam>) camdao.getAllCams();
		for(Cam cam : camlist){
			System.out.println("Id: "+cam.getCamname() + " url: "+cam.getUrl()+" erstellt am: "+cam.getTimeOfCreation()+ " directory: "+cam.getPathOriginalImageDirectory()+" kommentare: "+cam.getKommentar());
		}
		
		
		long user = udb.getUserIdFromDatabaseByName("user");
		if(user != 0){
			System.out.println("User != null yes");
		}
		else{
			System.out.println("User == null sorry");
		}
		
		usercammapping.setUserCamMapping(user,(ArrayList<Cam>)camdao.getAllCams());	
		
		System.out.println(usercammapping.getUserCamMapping(udb.getUserIdFromDatabaseByName("a")));	
		Date now= new Date();
		ImageDaoImpl image= new ImageDaoImpl();
		ImageItem item= new ImageItem(1,"test.jpg" , 1, new Timestamp(now.getTime()),"D:/Box/Box Sync/Box Sync Folder/FH/7 sem/WAI - Damm/WaiWebProject/WaiWeb/WebContent/camimages/" , "hallo");
		ImageItem item2= new ImageItem(1,"Koala.jpg" , 1, new Timestamp(now.getTime()),"D:/Box/Box Sync/Box Sync Folder/FH/7 sem/WAI - Damm/WaiWebProject/WaiWeb/WebContent/camimages/" , "hallo");
		ImageItem item3= new ImageItem(1,"Desert.jpg" , 1, new Timestamp(now.getTime()),"D:/Box/Box Sync/Box Sync Folder/FH/7 sem/WAI - Damm/WaiWebProject/WaiWeb/WebContent/camimages/" , "hallo");
		ImageItem item4= new ImageItem(1,"Tulips.jpg" , 1, new Timestamp(now.getTime()),"D:/Box/Box Sync/Box Sync Folder/FH/7 sem/WAI - Damm/WaiWebProject/WaiWeb/WebContent/camimages/" , "hallo");
		ImageItem item5= new ImageItem(1,"Penguins.jpg" , 1, new Timestamp(now.getTime()),"D:/Box/Box Sync/Box Sync Folder/FH/7 sem/WAI - Damm/WaiWebProject/WaiWeb/WebContent/camimages/" , "hallo");
		image.addImage(item);
		image.addImage(item2);
		image.addImage(item3);
		image.addImage(item4);
		image.addImage(item5);
		
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
