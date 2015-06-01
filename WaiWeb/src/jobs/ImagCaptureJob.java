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
			capture();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("enter Execute job");
		
	}
	

    
	public ImagCaptureJob(){
		//capture();	
	};
	
	public void capture(){
 
	//Democam
	//Cam cam = new Cam("cam1", "http://www.hrfoto.dunkel.de/webcams/hr3/studio2.jpg", Tool_TimeStamp.getTimeStampString(), "", "");
		try {
		baseImagePath = JndiFactory.getInstance().getImageDirectoryPath();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	//Holt sich die Cams
	ArrayList<Cam> camList = (ArrayList<Cam>) camdaoImp.getAllCams();
	
		for(Cam webcam : camList){
			
			if(webcam != null){
				captureSingelCam(webcam);
			}
			else{
	    		System.out.println("System Cam: "+webcam.getId_Cam()+ " was null!");

			}
	
		}

	}
	

    public void captureSingelCam(Cam cam){
    	
    	//Erzeugt einen Zeitstempel sowie eine handlichere Hashmap
    	Timestamp timestamp = Tool_TimeStamp.getTimeStampSQLDateFormat();
    	HashMap<String,String> hashmapTimeStamp = Tool_TimeStamp.getTimeStampSetFromSQLDateFormat(timestamp);
    	

    	
    	//Erzeugt die Pfade
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
					
			
			if(Tool_ImageProcessing.isImage(cam.getUrl())){
				System.out.println("Cam: "+cam.getCamname()+ " is image");
				
				
				BufferedImage image = PicCatch.PicCatch(cam);

				(new File(basePath+"/"+imageDirectoryPath)).mkdirs();

		    	
		    	//Speichert das Originalbild im Filesystem
		        try {
		    		ImageIO.write(image, "jpg",new File(basePath+"/"+imageDirectoryPath+"/"+imageName+originalImageType));
		    	} catch (IOException e) {
		    		log.error("Error while writing original-image to Disk of cam: "+cam.getId_Cam());
		    		log.error("Erromessage: "+e.getMessage());

		    	}
		        
		        
		        //Speichert die Thumbnail im Filesystem
		        try {
		    		ImageIO.write(Tool_ImageProcessing.getThumbnailOfImage(image), "jpg",new File(basePath+"/"+imageDirectoryPath+"/"+imageName+thumbnailImageType));

		    	} catch (IOException e) {
		    		log.error("Erromessage: "+e.getMessage());
		    	}

		    	//Speichert die metainformationen des bildes in der Datenbank.
		    	ImageItem item = new ImageItem(imageName,cam.getId_Cam(),timestamp,imageDirectoryPath,"");
		    	imageDaoImp.addImage(item);
		    	
		    	
		    				

			}
			else if(Tool_ImageProcessing.isStream(cam.getUrl())){
				System.out.println("Cam: "+cam.getCamname() + " is stream!!!");
				PicCatch.streamCapture(cam,path,fullname);

		    	System.out.println("temp: "+fullname);
    	

		        
		        BufferedImage image=null;
				try {
					image = Tool_ImageProcessing.readImageFromPath(fullname+originalImageType);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		        if(image != null){

			        //Speichert die Thumbnail im Filesystem
			        try {
			    		ImageIO.write(Tool_ImageProcessing.getThumbnailOfImage(image), "jpg",new File(fullname+thumbnailImageType));
			
			    	} catch (IOException e) {
			    		// TODO Auto-generated catch block
			    		e.printStackTrace();
			    	}
			        
			    	
			    	//Speichert die metainformationen des bildes in der Datenbank.
			    	ImageItem item = new ImageItem(imageName,cam.getId_Cam(),timestamp,imageDirectoryPath,"");
			    	imageDaoImp.addImage(item);
		    	
		        }
		        else
					System.out.println("System image of cam: "+cam.getId_Cam()+ " was null!");
			
		    	}
				
			
				
			}
			

  	
    	



    
    	
    	
    	
    

	

	


}
