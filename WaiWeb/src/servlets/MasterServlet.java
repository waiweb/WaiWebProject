package servlets;

//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import job.CaptureImages;

import org.apache.catalina.startup.HomesUserDatabase;

import model.Cam;
import model.ImageItem;
import model.User;
import utils.Tool_CamCapture;
//import utils.Tool_ImageProcessing;
import utils.Tool_Security;
import utils.Tool_TimeStamp;
import Dao.CamDaoImpl;
import Dao.DatabaseControllerImpl;
import Dao.ImageDaoImpl;
import Dao.UserDaoImpl;

/**
 * Servlet implementation class MasterServlet
 */
@WebServlet("/MasterServlet")
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String homeDir = "./Images";

    /**
     * @see HttpServlet#HttpServlet()
     * Servlet zur Weiterleitung auf Startseite, erste Instanz:
     */
    public MasterServlet() {
        super();
       

        beispiele();
    }  
        
    public void beispiele(){
    	
		DatabaseControllerImpl db = new DatabaseControllerImpl();
		ImageDaoImpl imageDaoImp = new ImageDaoImpl();
		db.createDatabase();
		
		UserDaoImpl udb = new UserDaoImpl();
		
		//User anlegen einmal mit und einmal ohne gehashtem password (mit ist besser !):
		udb.createUserInDatabase(new User("admin",new String(Tool_Security.hashFromString("admin")),1,Tool_TimeStamp.getTimeStampString(),""));
		udb.createUserInDatabase(new User("UserB","meinpass",1,Tool_TimeStamp.getTimeStampString(),""));
		
		//Test ob userlogin korrekt ist einmal mit fehlerfall
		System.out.println("Existing: "+udb.isUsernameExisting("UserB"));
		System.out.println("Validlogin: "+udb.isUserLoginValid("admin",new String(Tool_Security.hashFromString("admin"))));
		
		//ausgabe aller user
		ArrayList<User>list = (ArrayList<User>) udb.getAllUsers();
		for(int i=0; i< list.size();i++){
			System.out.println("Id: "+list.get(i).getId_User()+ " name: "+list.get(i).getUsername()+ " password: "+list.get(i).getPassword()+" Lastupdate: "+list.get(i).getTimeOfCreation());
		}
		
		//create cams
		
		CamDaoImpl camDaoImp = new CamDaoImpl();
		
		camDaoImp.createCamInDatabase(new Cam("cam_bildQuelle_1", "../Wai_Semesterproject/WaiWebProject/WaiWeb/webcamQuelleSimulation/bild1.png", Tool_TimeStamp.getTimeStampString(),homeDir, "unnoetiges kommentar"));
		camDaoImp.createCamInDatabase(new Cam("cam_bildQuelle_2", "../Wai_Semesterproject/WaiWebProject/WaiWeb/webcamQuelleSimulation/bild2.png", Tool_TimeStamp.getTimeStampString(),homeDir, "unnoetiges kommentar"));
		camDaoImp.createCamInDatabase(new Cam("cam_bildQuelle_3", "../Wai_Semesterproject/WaiWebProject/WaiWeb/webcamQuelleSimulation/bild3.png", Tool_TimeStamp.getTimeStampString(),homeDir, "unnoetiges kommentar"));

		ArrayList<Cam>camlist = (ArrayList<Cam>) camDaoImp.getAllCams();
		
		if(camlist != null){
			for(Cam cam : camlist){
				System.out.println("Id_cam: "+cam.getId_Cam()+" camname: "+cam.getCamname() + " url: "+cam.getUrl()+" erstellt am: "+cam.getTimeOfCreation()+ " directory: "+cam.getPathOriginalImageDirectory()+" kommentare: "+cam.getKommentar());
			}
		}
		
		CaptureImages.captureCam(camlist.get(0));
		CaptureImages.captureCam(camlist.get(1));
		CaptureImages.captureCam(camlist.get(2));
		 
		
		

		/*
		System.out.println("file da?: "+new File("../Wai_Semesterproject/WaiWebProject/WaiWeb/webcamQuelleSimulation/bild1.png").exists());
		System.out.println("file da?: "+new File("/Wai_Semesterproject/WaiWebProject/WaiWeb/webcamQuelleSimulation/bild1.png").exists());
		System.out.println("file da?: "+new File("../WaiWebProject/WaiWeb/webcamQuelleSimulation/bild1.png").exists());
		System.out.println("file da?: "+new File("/WaiWebProject/WaiWeb/webcamQuelleSimulation/bild1.png").exists());
		System.out.println("file da?: "+new File("D:/xWAI/Wai_Semesterproject/WaiWebProject/WaiWeb/webcamQuelleSimulation/bild1.png").exists());
		 */
		
		
		ImageItem item = imageDaoImp.getImageItem(1);
		System.out.println("Name Imageitem: "+item.getImageName());
		
		ArrayList<ImageItem> imageItemList = (ArrayList<ImageItem>) imageDaoImp.getAllImageItems();
		
		System.out.println("Alle images"); 
		for(int i=0; i<imageItemList.size();i++){
			System.out.println("Name Imageitem: "+imageItemList.get(i).getImageName());

		}
		
		
		//Beispiel selektion bilder in einer bestimmten uhrzeit:
		
		HashMap<String, String> imageRequestHashMap = new HashMap<String, String>();
		imageRequestHashMap.put("Year",String.valueOf(2015));
		imageRequestHashMap.put("Month","05");
		imageRequestHashMap.put("Day","12");
		imageRequestHashMap.put("Hour","15");

		
		
		ArrayList<ImageItem> selectedList = imageDaoImp.getImageItem(imageRequestHashMap);
		System.out.println("size: "+selectedList.size()); 

		
		System.out.println("Selection images");
		for(int i=0; i<selectedList.size();i++){
			System.out.println("Name Imageitem: "+selectedList.get(i).getImageName());

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
		
		//Direkte Weiterleitung auf Login Bildschirm bei erstem Start:
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/login.html");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
