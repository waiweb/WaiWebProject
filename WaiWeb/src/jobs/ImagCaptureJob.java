package jobs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

import jndi.JndiFactory;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import com.github.sarxos.webcam.util.ImageUtils;

import Dao.CamDaoImpl;
import Dao.ImageDaoImpl;
import utils.PicCatch;
import utils.SystemStartup;
import utils.Tool_ImageProcessing;
import utils.Tool_TimeStamp;
import model.Cam;
import model.ImageItem;
import utils.PicCatch;

public class ImagCaptureJob implements Job {
	
    private static Logger log = Logger.getLogger(JndiFactory.class);   

    final CamDaoImpl camdaoImp= new CamDaoImpl();
    final ImageDaoImpl imageDaoImp = new ImageDaoImpl();
    public static final String originalImageType = ".jpg";
    public static final String thumbnailImageType = "_thumbnail.jpg";
    public String baseImagePath;

    
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			//wenn man in den einstellungen context.xml auf false stellt ignoriert diese funktion den quarz.
			if (JndiFactory.getInstance().getEnvironmentAsBoolean("enableQuarz").booleanValue() == true){
				capture();	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("Enter ImagCaptureJob - execute job");
		System.out.println("Enter ImagCaptureJob - execute job");
		
	}
	

    
	public ImagCaptureJob(){
	};
	
	
	/**
	 * Die Funktion holt sich alle Cam's aus der DB und startet für jede einzeln das "capturen".
	 */
	public void capture(){
 
		try {
		baseImagePath = JndiFactory.getInstance().getImageDirectoryPath();
		} catch (NamingException e) {
			log.error("Error: ImagCaptureJob: JndiFactory.getInstance().getImageDirectoryPath()");
			System.out.println("Error: ImagCaptureJob: JndiFactory.getInstance().getImageDirectoryPath() "+e.getMessage());
		}

	//Holt sich die Cams
	ArrayList<Cam> camList = (ArrayList<Cam>) camdaoImp.getAllCams();
	
		for(Cam webcam : camList){
			
			if(webcam != null){
				captureSingelCam(webcam);
			}
			else{
	    		System.out.println("System Cam: "+webcam.getId_Cam()+ " was null!");
	    		log.error("System Cam: "+webcam.getId_Cam()+ " was null!");

			}
	
		}

	}
	

	/**
	 * Die Funktion entscheidet für jede cam ob die URL dort drin zu einem "bild" oder einem "videostream" führt. 
	 * Für den Fall des "videos" wird die sarxos api zur hilfe genommen um aus dem Videostream ein Bild zu extrahieren.
	 * Im Fall des Bildes werden standart Java-Methoden verwendet um das bild zu speichern. 
	 * Es außerdem eine kleinere Kopie des Bildes (="thumbnail") erzeugt und im gleichen Ordner gespeichert. 
	 * Metainformationen sowie der Pfad zum Bild (ohne Basispfad und typ-endung) wird zusätzlich in der Datenbank mit einem
	 * Timestamp gespeichert.
	 * @param cam
	 */
    public void captureSingelCam(Cam cam){
    	
    	//Erzeugt einen Zeitstempel sowie eine handlichere Hashmap
    	Timestamp timestamp = Tool_TimeStamp.getTimeStampSQLDateFormat();
    	HashMap<String,String> hashmapTimeStamp = Tool_TimeStamp.getTimeStampSetFromSQLDateFormat(timestamp);
    	

    	
    	//Erzeugt die Pfade anhand von Zeitwerten und der Cam-ID
    	String basePath = baseImagePath;
    	//String basePath ="/WaiWebProject3/WaiWebProject/WaiWeb/WebContent/camimages";
    	String imageDirectoryPath ="";
    	String imageName ="";
    	
    	imageDirectoryPath = cam.getId_Cam() + "/"
    			+ hashmapTimeStamp.get("year") + "/"
    			+ hashmapTimeStamp.get("month") + "/"
    			+ hashmapTimeStamp.get("day") + "/"
    			+ hashmapTimeStamp.get("hour");
    	
    	
    	imageName = hashmapTimeStamp.get("minute") + "_minute_"
    			+ hashmapTimeStamp.get("second") + "_second";
	
    	String path = basePath+"/"+imageDirectoryPath;
    	String fullname = path+"/"+imageName;
					
		//Hier wird entschieden ob es ein Image ist oder Videostream.	
			if(Tool_ImageProcessing.isImage(cam.getUrl())){
				System.out.println("Cam: "+cam.getCamname()+ " is image");
				
				//Holt das Bild mit dem Tool PicCatch.
				BufferedImage image = PicCatch.PicCatch(cam);

				//Legt die Ordnerstruktur an sofern noch nicht vorhanden.
				(new File(basePath+"/"+imageDirectoryPath)).mkdirs();
	    	
		    	//Speichert das Originalbild im Filesystem
		        try {
		    		ImageIO.write(image, "jpg",new File(basePath+"/"+imageDirectoryPath+"/"+imageName+originalImageType));
		    	} catch (IOException e) {
		    		log.error("Error while writing original-image to Disk of cam: "+cam.getId_Cam());
		    		log.error("Erromessage: "+e.getMessage());

		    	}
		        
		        
		        //Erzeugt und speichert die Thumbnail im Filesystem
		        try {
		    		ImageIO.write(Tool_ImageProcessing.getThumbnailOfImage(image), "jpg",new File(basePath+"/"+imageDirectoryPath+"/"+imageName+thumbnailImageType));

		    	} catch (IOException e) {
		    		log.error("Error while writing thumbnail-image to Disk of cam: "+cam.getId_Cam());
		    		log.error("Erromessage: "+e.getMessage());
		    	}

		    	//Speichert die Metainformationen des Dildes in der Datenbank.
		    	ImageItem item = new ImageItem(imageName,cam.getId_Cam(),timestamp,imageDirectoryPath,"");
		    	imageDaoImp.addImage(item);
		    	
		    	
		    				

			}
			else if(Tool_ImageProcessing.isStream(cam.getUrl())){
				System.out.println("Cam: "+cam.getCamname() + " is a stream");
				log.info("Cam: "+cam.getCamname() + " is a stream");
				PicCatch.streamCapture(cam,path,fullname);

		    	System.out.println("Dateisystempfad zum Bild: "+path);
		    	log.info("Dateisystempfad zum Bild: "+path);

		        
		        BufferedImage image=null;
				try {
					image = Tool_ImageProcessing.readImageFromPath(fullname+originalImageType);
				} catch (IOException e1) {
					log.error("Error while reading original-image from disk: "+cam.getId_Cam());
		    		log.error("Erromessage: "+e1.getMessage());
		    		System.out.println("Errormessage: "+e1.getMessage());
				}
				
		        if(image != null){

			        //Speichert die Thumbnail im Filesystem
			        try {
			    		ImageIO.write(Tool_ImageProcessing.getThumbnailOfImage(image), "jpg",new File(fullname+thumbnailImageType));
			
			    	} catch (IOException e) {
			    		log.error("Error while writing thumbnail-image to disk: "+cam.getId_Cam());
			    		log.error("Erromessage: "+e.getMessage());
			    		e.printStackTrace();
			    	}
			        
			    	
			    	//Speichert die metainformationen des bildes in der Datenbank.
			    	ImageItem item = new ImageItem(imageName,cam.getId_Cam(),timestamp,imageDirectoryPath,"");
			    	imageDaoImp.addImage(item);
		    	
		        }
		        else
					System.out.println("image of cam: "+cam.getId_Cam()+ " was null!");
			        log.error("Error: Image of cam: "+cam.getId_Cam()+ " was null!");
			
		    	}
				
			
				
			}
			

  	
    	



    
    	
    	
    	
    

	

	


}
