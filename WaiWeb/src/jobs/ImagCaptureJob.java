package jobs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import Dao.CamDaoImpl;
import Dao.ImageDaoImpl;
import servlets.BackgroundService;
import utils.PicCatch;
import utils.Tool_ImageProcessing;
import utils.Tool_TimeStamp;
import model.Cam;
import model.ImageItem;
import utils.PicCatch;

public class ImagCaptureJob {
	
    final CamDaoImpl camdaoImp= new CamDaoImpl();
    final ImageDaoImpl imageDaoImp = new ImageDaoImpl();
    public static final String originalImageType = ".jpg";
    public static final String thumbnailImageType = "_thumbnail.jpg";
    public String baseImagePath;

	public ImagCaptureJob(String path){
		baseImagePath = path;
 
	//Democam
	Cam cam = new Cam("cam1", "http://www.hrfoto.dunkel.de/webcams/hr3/studio2.jpg", Tool_TimeStamp.getTimeStampString(), "", "");
	

	//Holt sich die Cams
	ArrayList<Cam> camList = (ArrayList<Cam>) camdaoImp.getAllCams();
	
		for(Cam webcam : camList){
			
			if(webcam != null){
			capture(webcam);
			}
			else{
	    		System.out.println("System Cam: "+cam.getId_Cam()+ " was null!");

			}
	
		}
	
	
	}
	

    public void capture(Cam cam){
    	

    	//Holt das Bild mit dem Pfad aus der Cam
    	BufferedImage image = PicCatch.PicCatch(cam);
    	
    	if(image != null){
    	
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
    		
    	
    	String temp = basePath+"/"+imageDirectoryPath+"/"+imageName+originalImageType;
    	System.out.println("temp: "+temp);
    	
    	
    	//Speicherort Anlegen
	    (new File(basePath+"/"+imageDirectoryPath)).mkdirs();

    	
    	//Speichert das Originalbild im Filesystem
        try {
    		ImageIO.write(image, "jpg",new File(basePath+"/"+imageDirectoryPath+"/"+imageName+originalImageType));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
        
        //Speichert die Thumbnail im Filesystem
        try {
    		ImageIO.write(Tool_ImageProcessing.getThumbnailOfImage(image), "jpg",new File(basePath+"/"+imageDirectoryPath+"/"+imageName+thumbnailImageType));

    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        


    	
    	
    	//Speichert die metainformationen des bildes in der Datenbank.
    	ImageItem item = new ImageItem(imageName,cam.getId_Cam(),timestamp,imageDirectoryPath,"");
    	imageDaoImp.addImage(item);
    	
    	}
    	else{
    		System.out.println("System image of cam: "+cam.getId_Cam()+ " was null!");
    	}
    	

    	
    	
    }
    
    
    	
    	
    	
    

	

	


}
