package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jndi.JndiFactory;

import com.sun.istack.internal.logging.Logger;

import utils.Tool_PathEdit;
import Dao.DaoFactory;
import Dao.Interface.CamDao;
import Dao.Interface.ImageDao;
import Dao.Interface.UserCamMappingDao;
import Dao.Interface.UserDao;
import model.Cam;
import model.ImageItem;
import model.User;

public class AuswahlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    final UserDao daoImp = DaoFactory.getInstance().getUserDao();
    final CamDao camdaoImp = DaoFactory.getInstance().getCamDao();
	final UserCamMappingDao ucDaoImp = DaoFactory.getInstance().getUserCamMappingdao();
	private ImageDao imgDaoImp = DaoFactory.getInstance().getIamgeDao();
	private static Logger log = Logger.getLogger(JndiFactory.class);
    private String username;
    private Long id;
	
    //GET:
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		//Aktive Session überprüfen:
        if(session != null && session.getAttribute("rechte") != null){
        	//Rechte überprüfen: (ADMINISTRATOR)
        		if((int) session.getAttribute("rechte") == 1){
        		log.info("Session mit User=" + session.getAttribute("username") 
        			+ " und Rechte=" + session.getAttribute("rechte") + " bestätigt. (Auswahl)");

	        	//Falls Action vorhanden prüfen, ansonsten auf Auswahl-Bildschirm:
				if (action == null) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
					dispatcher.forward(request, response);	
				
				//Alle Cams anzeigen: TODO: Thumbnail anzeigen für die einzelnen Cams!
				} else if (action.equals("cam")) {
					List<Cam> collection = camdaoImp.getAllCams();
					ArrayList<ImageItem> allThumbImages = (ArrayList<ImageItem>) Tool_PathEdit.editImageListToThumbnailImagePath(imgDaoImp.getAllImageItems());
				
					for(int j=0;j<collection.size();j++){
						for(int i=0;i<allThumbImages.size();i++){
							if(collection.get(j).getId_Cam() == allThumbImages.get(i).getId_CamSource()){
								collection.get(j).setPathOriginalImageDirectory(allThumbImages.get(i).getPath());
								break;
							}
						}
					}
					
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
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/AuswahlmoeglichkeitenUSER.jsp");
					dispatcher.forward(request, response);	
				
				//Cams für den jeweiligen User anpassen:	
				} else if (action.equals("cam")){
					username = (String) session.getAttribute("username");
					id = daoImp.getUserIdFromDatabaseByName(username);
					
					//Alle Cams holen:
					List<Cam> newMapping = camdaoImp.getAllCams();
					//Aktuelles Mapping des Users holen:
					ArrayList<Cam> camList = ucDaoImp.getUserCamMapping(id);
					//Temp List:
					ArrayList<Cam> tempList = new ArrayList<Cam>();
					
					//Neues Mapping der User - Cams zuweisen wenn Cams gelöscht wurden:
					if (newMapping.size() < camList.size()) {
						for(int i=0;i<newMapping.size();i++) {
							for(int j=0;j<camList.size();j++){	
								if(camList.get(j).getId_Cam() == newMapping.get(i).getId_Cam()) {	
									tempList.add(camList.get(i));
								}
							}
						}
					//Neues Mapping der User - Cams zuweisen wenn keine Cams gelöscht wurden:
					} else {
						for(int i=0;i<camList.size();i++) {
							for(int j=0;j<newMapping.size();j++){
								if(camList.get(i).getId_Cam() == newMapping.get(j).getId_Cam()) {	
									tempList.add(camList.get(i));
								}
							}
						}
					}
	
					//Vergleichen und ggf. gelöschte Cams entfernen:
					List<Cam> collection = new ArrayList<Cam>();
					Cam tempCam = new Cam();
					long tempID = 0;
					
					for(int i=0;i<tempList.size();i++) {
						tempID = tempList.get(i).getId_Cam();
							for(int j=0;j<newMapping.size();j++){
								if(newMapping.get(j).getId_Cam() == tempID){
									tempCam = newMapping.get(j);
								}
							}				
						collection.add(i, tempCam);
					}
					
					Date date= new Date();
					List<ImageItem> imgTemp = null;
					
					Timestamp now = new Timestamp(date.getTime());
					Timestamp until = new Timestamp(date.getYear(),date.getMonth(),date.getDate(),date.getHours(),date.getMinutes()-10,date.getSeconds(), 0);
					
					try{
						imgTemp = imgDaoImp.getImageItems(until, now);
					}catch(Exception e){
							System.out.println("DB Connection Error!");
						}
					ArrayList<ImageItem> allThumbImages = (ArrayList<ImageItem>) Tool_PathEdit.editImageListToThumbnailImagePath(imgTemp);

					
					for(int j=0;j<collection.size();j++){
						for(int i=0;i<allThumbImages.size();i++){
							if(collection.get(j).getId_Cam() == allThumbImages.get(i).getId_CamSource()){
								collection.get(j).setPathOriginalImageDirectory(allThumbImages.get(i).getPath());
								break;
							}
						}
					}
					
					request.setAttribute("cams", collection);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/CamUSER.jsp");
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
		log.info("Rechte nicht ausreichend, Administrator-Rechte benötigt!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
		dispatcher.forward(request, response);	
	}
}