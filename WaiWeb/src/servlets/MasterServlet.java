package servlets;

//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
         * Auskommentieren fÃ¼r Beispielnutzer usw. bei Start!
         * Momentan genutzt fÃ¼r Tests: USERNAME: admin PW: admin
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
		udb.createUserInDatabase(new User("admin",new String(Tool_Security.hashFromString("admin")),1,Tool_TimeStamp.getTimeStampString(),"kommi"));
		udb.createUserInDatabase(new User("UserB","meinpass",0,Tool_TimeStamp.getTimeStampString(),"kommi"));
		udb.createUserInDatabase(new User("a",new String(Tool_Security.hashFromString("aaaaaaaa")),1,Tool_TimeStamp.getTimeStampString(),"kommi"));
		udb.createUserInDatabase(new User("b",new String(Tool_Security.hashFromString("bbbbbbbb")),1,Tool_TimeStamp.getTimeStampString(),"kommi"));

		
		//Test ob userlogin korrekt ist einmal mit fehlerfall
		System.out.println("Existing: "+udb.isUsernameExisting("UserB"));
		System.out.println("Validlogin: "+udb.isUserLoginValid("admin",new String(Tool_Security.hashFromString("admin"))));
		
		//ausgabe aller user
		ArrayList<User>list = (ArrayList<User>) udb.getAllUsers();
		for(int i=0; i< list.size();i++){
			System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		}
		
		//create cams
		
		CamDaoImpl camdao = new CamDaoImpl();
		
		camdao.createCamInDatabase(new Cam("Wiese", "www.spielgel.de", Tool_TimeStamp.getTimeStampString(), "/camimages", "yolo"));
		camdao.createCamInDatabase(new Cam("Fluss", "www.natur.de", Tool_TimeStamp.getTimeStampString(), "/camimages", "fluesse"));
		camdao.createCamInDatabase(new Cam("Berg", "www.berg.de", Tool_TimeStamp.getTimeStampString(), "/camimages", "berg"));

		ArrayList<Cam>camlist = (ArrayList<Cam>) camdao.getAllCams();
		for(Cam cam : camlist){
			System.out.println("Id: "+cam.getCamname() + " url: "+cam.getUrl()+" erstellt am: "+cam.getTimeOfCreation()+ " directory: "+cam.getPathOriginalImageDirectory()+" kommentare: "+cam.getKommentar());
		}
		
		
		long user = udb.getUserIdFromDatabaseByName("a");
		long user1 = udb.getUserIdFromDatabaseByName("admin");
		if(user != 0){
			System.out.println("User != null yes");
		}
		else{
			System.out.println("User == null sorry");
		}
		
		ArrayList<Cam>camList= new ArrayList<Cam>();
		camList.add(camdao.getCamFromDatabase(1));
		camList.add(camdao.getCamFromDatabase(2));
		usercammapping.setUserCamMapping(user,(ArrayList)camdao.getAllCams());
		usercammapping.setUserCamMapping(user1, camList);

		System.out.println(usercammapping.getUserCamMapping(udb.getUserIdFromDatabaseByName("a")));
		
		Timestamp timestamp = Tool_TimeStamp.getTimeStampSQLDateFormat();
		
		System.out.println("Date: "+Tool_TimeStamp.getTimeStampSetFromSQLDateFormat(timestamp));
		
		
		//Diese aufgabe wird später in jobs imagecapture ausgeführt 
		//insert eines images (nur die metainformationen, das eigenliche image muss in einer job-klasse in das Dateisystem gelegt werden.
		//Die ablage hat nun die form:   /camId/jahr/monat/tag/stunde/minute_sekunde_millisekunde.jpg  
		
		ImageDaoImpl imageDao = new ImageDaoImpl();
		
		// eines mit aktuellen zeitstempel
		imageDao.addImage(new ImageItem(2,Tool_TimeStamp.getTimeStampSQLDateFormat(),""));

		
		//ein paar fiktive bilder mit erdachten zeitstempel (später kommt diese information über die auswahl im front-end.
		//die generateTimeStamp generiert ein "Timestamp" objekt welches man zum suchen gut verwenden kann weil die SQL datenbank
		// in diesem format abspeichert und dann einfach verglichen werden kann.
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.generateTimeStamp("2012","4", "05","19"),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.generateTimeStamp("2013","4", "05","10"),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.generateTimeStamp("2013","4", "05","11"),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.generateTimeStamp("2013","4", "05","11"),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.generateTimeStamp("2013","4", "05","12"),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.generateTimeStamp("2013","4", "05","13"),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.generateTimeStamp("2013","4", "05","9"),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.getTimeStampSQLDateFormat(),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.getTimeStampSQLDateFormat(),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.getTimeStampSQLDateFormat(),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.getTimeStampSQLDateFormat(),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.getTimeStampSQLDateFormat(),""));
		imageDao.addImage(new ImageItem(1,Tool_TimeStamp.getTimeStampSQLDateFormat(),""));


		
		//Selektiert alle bilder von den beiden angegebenen zeiträumen
		ArrayList<ImageItem> imageList = imageDao.getImageItems(Tool_TimeStamp.generateTimeStamp("2013","4", "05","10"),Tool_TimeStamp.generateTimeStamp("2013","4", "05","12"));
		for(ImageItem item: imageList){
			System.out.println("Imageitem: "+item.getId_Image() + " path: "+item.getPath());
		}
		
		
		//Weiteres Beispiel: selektierung vom 2013-04-05 14 uhr bis "jetzt" alle bilder:
		imageList = imageDao.getImageItems(Tool_TimeStamp.generateTimeStamp("2013","4", "05","13"),Tool_TimeStamp.getTimeStampSQLDateFormat());
		for(ImageItem item: imageList){
			System.out.println("Imageitem: "+item.getId_Image() + " path: "+item.getPath());
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

}
