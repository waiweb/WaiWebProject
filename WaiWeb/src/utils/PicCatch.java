package utils;

/*
 * Catch.java
 * Purpose: Holt ein Bild von einer URL und speichert es in den angegebenen lokalen Pfad.
 * Benutzung: Der Konstruktor wird mit den Daten:
 * URL, KameraName, Lokaler Speicherplatz, BildFormat aufgerufen.
 * Mit dem Aufruf der Funktion GetCurrentPic wird dann das aktuelle Bild geholt und abgespeichert.
 * Alternativer Aufruf des Konstruktors mit dem Kameraobjekt möglich
 *
 * @author Andreas Wachter
 * @version 1.0 12/05/2015
 */

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import com.github.sarxos.webcam.util.ImageUtils;

import model.Cam;
import jndi.JndiFactory;;

public class PicCatch {
	private static Logger log = Logger.getLogger(JndiFactory.class);   

	
	
/**
 * Graps the image from the given url and returns it as BufferedImage
 * @param camera
 * @return
 */
	public static BufferedImage PicCatch(Cam camera){
		
		BufferedImage originalImage = null;
		String strURL_Dir = camera.getUrl();

	    URL url;
	    
		try {
			
			url = new URL(strURL_Dir);
			originalImage = ImageIO.read(url);
			
		} catch (MalformedURLException e) {
			log.error("Error: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Error: "+e.getMessage());
			e.printStackTrace();
		}
		
		return originalImage;
		

	}
	
	/**
	 * Die funktion holt aus der cam die url heraus, verbindet sich mit ihr, erzeugt ein bild und speichert es unter
	 * dem argument "fullname". 
	 * Das arguemnt path wird benötigt um einen pfad anzulegen, sofern noch nicht erzeugt.
	 * @param cam
	 * @param path
	 * @param fullname
	 */
	 public static void streamCapture(Cam cam,String path,String fullname){
	    	
			IpCamDeviceRegistry.unregisterAll();

	    	boolean pathGenerated = (new File(path)).mkdirs();
	
	    	
	    	if(pathGenerated){
	    		System.out.println("Path generated: yes");
				log.info("Path generated: yes");

	    	}
	    	else{
	    		System.out.println("Path generated: no");
				log.info("Path generated: no");

	    	}

	    	
		    String name = String.valueOf(cam.getId_Cam());
	        String url = cam.getUrl();
	        IpCamMode mode = IpCamMode.PUSH;

	        IpCamDevice ipCamDevice =null;
	        try {
	        	//if(!IpCamDeviceRegistry.isRegistered(name))
	        	ipCamDevice = IpCamDeviceRegistry.register(name, url, mode);
			} catch (MalformedURLException e) {
				log.error("Error: "+e.getMessage());

				e.printStackTrace();
			}
	        
	        try{
				WebcamUtils.capture(Webcam.getWebcams().get(0), fullname, ImageUtils.FORMAT_JPG);
				Webcam.getWebcams().get(0).close(); //superwichtig sonst kann der stream nicht mehr wieder verwendet werden.
	
				IpCamDeviceRegistry.unregisterAll();
	        }
	        catch(Exception e){
	        	log.info("Webcam: "+cam.getId_Cam() + " reagiert nicht bzw. ist bereits beschäftigt");
				log.error("Error: "+e.getMessage());
	        	
	        }
	    	
	    }

	 public static void restAllCameras(){
		 
		 for(Webcam cam : Webcam.getWebcams()){
			 cam.close();
		 }
		 
		 
	 }
    // WIRD NICHT MEHR VERWENDET !!! Die funktion geht zwar aber sie ist ziemlich langsam. Daher verwenden wir
	 // die eigene Implementierung.
	@Deprecated
    public static void imageCapture(Cam cam,String path,String fullname){
    	
    	(new File(path)).mkdirs();

    	
		//Saxros Webcam Capture tool
    	
	    String name = String.valueOf(cam.getId_Cam());
        String url = cam.getUrl();
        IpCamMode mode = IpCamMode.PULL;

        try {
			IpCamDeviceRegistry.register(name, url, mode);
		} catch (MalformedURLException e) {
			log.error("Error: "+e.getMessage());
			e.printStackTrace();
		}
        Webcam.getWebcams().get(0).open();
		WebcamUtils.capture(Webcam.getWebcams().get(0), fullname, ImageUtils.FORMAT_JPG);
		IpCamDeviceRegistry.unregisterAll();
    	
    	
    }
	


}
