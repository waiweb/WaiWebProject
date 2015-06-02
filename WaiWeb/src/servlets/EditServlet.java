package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Tool_PathEdit;
import utils.Tool_Security;
import utils.Tool_TimeStamp;
import exception.UserNotFoundExecption;
import Dao.CamDaoImpl;
import Dao.ImageDaoImpl;
import Dao.UserCamMappingImpl;
import Dao.UserDaoImpl;
import model.Cam;
import model.ImageItem;
import model.User;


public class EditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	final UserDaoImpl daoImp = new UserDaoImpl();
	final CamDaoImpl camDaoImp = new CamDaoImpl();
	private ImageDaoImpl imgDaoImp = new ImageDaoImpl();
	final UserCamMappingImpl ucDaoImp= new UserCamMappingImpl();
	private User user;
	private Cam cam;
	private int rechte, id;
	private String username, passwort, kommentar, camname, url, rechteToString;	
	
	//GET:
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		//Aktive Session überprüfen:
        if(session != null && session.getAttribute("rechte") != null){
        	//Rechte überprüfen: (ADMINISTRATOR)
        		if((int) session.getAttribute("rechte") == 1){
        		System.out.println("Session mit User=" + session.getAttribute("username") 
        			+ " und Rechte=" + session.getAttribute("rechte") + " bestätigt.(Edit)");	
        	
				if(action == null){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/User.jsp");
					dispatcher.forward(request, response);	
					
				} else if(action.equals("addUser")){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Add_User.jsp");
					dispatcher.forward(request, response);	
					
		 		} else if(action.equals("addCam")){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Add_Cam.jsp");
					dispatcher.forward(request, response);	
					
		 		} else if(action.equals("back")){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
					dispatcher.forward(request, response);	
		 		}
			//Für normale User kein Zugriff!
        	} else {
            	System.out.println("ERROR! Keine ausreichenden Rechte, Administrator-Rechte erforderlich!");
        		backToAuswahl(request, response);
        	}
        } else {
        	System.out.println("ERROR! Keine aktive Session gefunden!");
        	response.sendRedirect(request.getContextPath() + "/master");
		}
	}
	
	//POST:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		initVar();
		
 		/** User Editierung: **/
		//User auswaehlen zum editieren:
		if(action.equals("editUser")){
			checkUserId(request);
			
			List<Cam> cams = new ArrayList<Cam>();
			List<Cam> checkedCams = new ArrayList<Cam>();
			List<Cam> tempCheckedCams = new ArrayList<Cam>();
			
			try {
				UserDaoImpl daoImp = new UserDaoImpl();
				user = daoImp.getUserFromDatabase(id);
			    cams = camDaoImp.getAllCams();
			    tempCheckedCams = ucDaoImp.getUserCamMapping(user);
			    
			    for(int i=0;i<cams.size();i++){
			    	for(int j=0;j<tempCheckedCams.size();j++){
			    		if(cams.get(i).getId_Cam()==tempCheckedCams.get(j).getId_Cam()){
			    			checkedCams.add(cams.get(i));
			    		}
			    	}
			    }
			    
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			  }
			
			//Die gecheckten cams aus der liste allcams loeschen
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
			
 		//Loescht den ausgewählten Nutzer in der Datenbank und kehrt zur User Liste zurück:
 		} else if(action.equals("deleteUser")){
			checkUserId(request);

			UserDaoImpl daoImp = new UserDaoImpl();
 			daoImp.deleteUserInDatabase(id);
 			
 			System.out.println("User mit der ID: " + id + " erfolgreich geloescht!");
 			backToAuswahl(request, response);
 		
 		//Aenderungen eines Nutzers sichern und in Datenbank updaten:
 		} else if(action.equals("saveUser")){
			checkUserId(request);
 			
 	 		if (request.getParameter("username") != null && request.getParameter("rechte") != null
 	 				&& request.getParameter("kommentar") != null) {
 	 			rechteToString = request.getParameter("rechte");
 	 			if (rechteToString.length() != 0 && rechteToString.matches("1") || rechteToString.matches("0")) {
 	 				rechte = Integer.valueOf(request.getParameter("rechte"));
 	 	 			username = request.getParameter("username");
 	 	 			kommentar = request.getParameter("kommentar");
 	 	 
 	 	 	 		//Versucht den User in der Datenbank upzudaten:
 	 				try {
 	 					UserDaoImpl daoImp = new UserDaoImpl();
 	 					user = daoImp.getUserFromDatabase(id);
 	 					user.setUsername(username);
 	 					user.setRechte(rechte);
 	 					user.setKommentar(kommentar);
 	 					daoImp.updateUser(user);
 	 			 		System.out.println("User mit der ID: " + id + " erfolgreich geupdatet!");
 	 				} catch (UserNotFoundExecption e) {
 	 					e.printStackTrace();
 	 				}
 	 	             
 	 				//Die angehakten Checkboxen werden rausgelesen und in die User_cam tabelle geschrieben
 	 				String[] checkbox=request.getParameterValues("checked");
 	 				
 	 				if (checkbox != null){
 	 					
 	 			    	ArrayList<Cam> camList = new ArrayList<Cam>();
 	 			    	List<Cam> cams= new ArrayList<Cam>();
 	 					cams=camDaoImp.getAllCams();

 	 			    	for(int i=0;i<cams.size();i++){
 	 				    	for(int j=0;j<checkbox.length;j++){
 	 				    		if(cams.get(i).getId_Cam()==Integer.valueOf(checkbox[j])){
 	 				    			System.out.println ("Die cam ID: "+checkbox[j]+" wurde dem User: "+ user.getUsername()+" hinzugefuegt");
 	 				    			camList.add(cams.get(i));
 	 				    		}
 	 				    	}
 	 				    }
 	 			    	
 	 			    	ucDaoImp.setUserCamMapping(user, camList);
 	 			    	
 	 			     } else if(checkbox == null){
 	 			    	
 	 			    	ArrayList<Cam> camList = new ArrayList<Cam>();
 	 			    	ucDaoImp.setUserCamMapping(user, camList);
 	 			    	System.out.println("Es wurden alle Bilder fuer den User: "+ user.getUsername() + " aus der Bezieungstabelle entfernt");
 	 			    }
 	 				
 	 	 			backToAuswahl(request, response);
 	 	 			
 	 	 			
 	 			} else {
 	 	 			System.out.println("Ungültige Rechte!");
 	 	 			backToAuswahl(request, response);
 	 	 		}
 	 		} else {
 	 			System.out.println("Ungültige Eingabe der Nutzerdaten!");
 	 			backToAuswahl(request, response);
 	 		}
 	 		
 			
 		//Neuen Nutzer in die Datenbank hinzufuegen:
 		} else if(action.equals("addNewUser")){
 	 		if (request.getParameter("username") != null && request.getParameter("passwort") != null 
 	 				&& request.getParameter("rechte") != null && request.getParameter("kommentar") != null) {
 	 			username = request.getParameter("username");
 	 			passwort = request.getParameter("passwort");
 	 			rechteToString = request.getParameter("rechte");
 	 			
 	 			if (rechteToString.length() != 0 && rechteToString.matches("1") || rechteToString.matches("0")) {
 	 				rechte = Integer.valueOf(request.getParameter("rechte"));
 	 	 			kommentar = request.getParameter("kommentar");
 	 	 			
 	 	 	 		if (daoImp.isUsernameExisting(username) == false) {
 	 	 	 			if((daoImp.createUserInDatabase(new User(username,new String(Tool_Security.hashFromString(passwort)),rechte,Tool_TimeStamp.getTimeStampString(),kommentar)) == true)){
 	 	 	 	 			System.out.println("Neuer User: " + username + " erfolgreich hinzugefuegt!");
 	 	 	 	 			backToAuswahl(request, response);
 	 	 	 			} else {
 	 	 	 	 			backToAuswahl(request, response);
 	 	 	 	 		}
 	 	 	 		} else {
 	 	 	 			System.out.println("User mit dem Namen: " + username + " bereits vorhanden!");
 	 	 	 			backToAuswahl(request, response);
 	 	 	 		}
 	 	 			
 	 			} else {
 	 	 			System.out.println("Ungültige Rechte!");
 	 	 			response.sendRedirect(request.getContextPath() + "/auswahl?action=user");
 	 	 		}
 	 		} else {
 	 			System.out.println("Falsche Eingabe der Daten! Felder dürfen nicht NULL sein!");
 	 			response.sendRedirect(request.getContextPath() + "/auswahl?action=user");
 	 		}
 		}
		
		/** Cam Editierung: **/
		//Cam auswählen zum editieren, nur Administrator:
		if(action.equals("editCam")){
			checkUserId(request);
			cam = camDaoImp.getCamFromDatabase(id);
			
			request.setAttribute("cam", cam);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Edit_Cam.jsp");
			dispatcher.forward(request, response);	
		
		} else if(action.equals("deleteCam")){
			checkUserId(request);
			
 			camDaoImp.deleteCamInDatabase(id);
 			
 			System.out.println("Cam mit der ID: " + id + " erfolgreich geloescht!");
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
 	
 		//Neue Cam in der Datenbank hinzufuegen:
 		} else if(action.equals("addNewCam")){
 	 		if (request.getParameter("camname") != null && request.getParameter("url") != null 
 	 				&& request.getParameter("kommentar") != null) {
 	 			camname = request.getParameter("camname");
 	 			url = request.getParameter("url");
 	 			kommentar = request.getParameter("kommentar");
 	 		}
 	 		
 	 		//TODO: Check if URL is valid!
 	 		if (camDaoImp.isCamNameExisting(camname) == false) {
 	 				camDaoImp.createCamInDatabase(new Cam(camname, url, Tool_TimeStamp.getTimeStampString(), "/camimages", kommentar));
 	 	 			System.out.println("Neue Cam: " + camname + " erfolgreich hinzugefuegt!");
 	 	 			
 	 		} else {
 	 			System.out.println("Cam mit dem Namen: " + camname + " ist bereits vorhanden!");
 	 		}
 	 		response.sendRedirect(request.getContextPath() + "/auswahl?action=cam");
 			
 		//Cam Images der jeweiligen Cam anzeigen: TODO: Bilder jeweiligen Cams in Liste speichern und an JSP senden!
 		} else if (action.equals("showImages")){
			checkUserId(request);
			Date date= new Date();
			Timestamp now = new Timestamp(date.getTime());
			
			//Wieviele Minuten angezeigt werden sollen:
			Timestamp five= new Timestamp(now.getYear(), now.getMonth(), now.getDay(),now.getHours(), now.getMinutes()-15, 0, 0);
			ArrayList<ImageItem> images= imgDaoImp.getImageItemsOfCam(id,five, now);
			cam = camDaoImp.getCamFromDatabase(id);
			
		    List<ImageItem> path= Tool_PathEdit.editImageListToOriginalImagePathJSP(images);
		    
			//Überabe an die Show_Images.jsp:
			request.setAttribute("cam", cam);
			request.setAttribute("path", path);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Show_Images.jsp");
			dispatcher.forward(request, response);	
		
		//Cam Refresh:
		} else if(action.equals("refresh")){
			checkUserId(request);
			String dateStart=request.getParameter("startDate");
			String dateEnd=request.getParameter("endDate");
			String timeStart=request.getParameter("startTime");
			String timeEnd=request.getParameter("endTime");
			System.out.println(" Refresh gedrueckt");
			Timestamp timestampStart = convertStringToTimestamp(dateStart, timeStart);
			Timestamp timestampEnd = convertStringToTimestamp(dateEnd, timeEnd);
			ArrayList<ImageItem> images= imgDaoImp.getImageItemsOfCam(id,timestampStart, timestampEnd);
			cam = camDaoImp.getCamFromDatabase(id);
			List<ImageItem> path= Tool_PathEdit.editImageListToOriginalImagePathJSP(images);
			
			System.out.println("Bilder vom "+timestampStart+" bis "+ timestampEnd);
			
			if(dateStart!=""&&dateEnd!="" && dateStart!=null && dateEnd!=null
		       && timeStart!=""&&timeEnd!="" &&timeStart!=null && timeEnd!=null
		       &&timestampStart.before(timestampEnd)){
				
				request.setAttribute("cam", cam);
				request.setAttribute("path", path);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Show_Images.jsp");
				dispatcher.forward(request, response);
				
			} else if(dateStart!=""&&dateEnd!="" && dateStart!=null && dateEnd!=null){
				   Date timeNow= new Date();
				   dateStart=request.getParameter("startDate");
				   dateEnd=request.getParameter("endDate");
				   timestampStart = convertStringToTimestamp(dateStart,String.valueOf(timeNow.getHours())+":"+String.valueOf(timeNow.getMinutes()-10));
				   timestampEnd = convertStringToTimestamp(dateEnd, String.valueOf(timeNow.getHours())+":"+String.valueOf(timeNow.getMinutes()));
				   images= imgDaoImp.getImageItemsOfCam(id,timestampStart, timestampEnd);
				   cam = camDaoImp.getCamFromDatabase(id);
				   path= Tool_PathEdit.editImageListToOriginalImagePathJSP(images);
				   System.out.println("Keine korrekte Kalender eingabe ! Es wurden die letzten 10 min ausgegeben");
				   request.setAttribute("cam", cam);
				   request.setAttribute("path", path);
				   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Show_Images.jsp");
				   dispatcher.forward(request, response);
				}else{
					System.out.println("Keine korrekte Kalender eingabe! Ausgabe nicht moeglich");
				}
		 }	
	}
	
	//Funktion um auf die Auswahlmoeglichkeiten zurueckzukehren:
	private void backToAuswahl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/auswahl");
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
		rechteToString = null;
	}
	
	//Check ID, nur da verwenden wo auch gebraucht:
	private void checkUserId(HttpServletRequest request) {
 		if (request.getParameter("id") != null) {
			id = Integer.valueOf(request.getParameter("id"));
 		}
	}
	
	private static Timestamp convertStringToTimestamp(String date,String time) {
		Timestamp timestamp = null;
		
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		    Date parsedDate = dateFormat.parse(date+" "+time);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){//this generic but you can control another types of exception
		 System.out.println("Fehler bei der Konvertierung von String in Timestamp !!");
		}
		
		return timestamp;
	  }

}
