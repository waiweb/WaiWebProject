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
	
	

	public static BufferedImage PicCatch(Cam camera){
		
		BufferedImage originalImage = null;
		String strURL_Dir = camera.getUrl();

	    URL url;
	    
		try {
			
			url = new URL(strURL_Dir);
			originalImage = ImageIO.read(url);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return originalImage;
		

	}
	
	
	 public static void streamCapture(Cam cam,String path,String fullname){
	    	
			IpCamDeviceRegistry.unregisterAll();

		
		  // Webcam.setDriver(new IpCamDriver());

		   
	    	boolean pathGenerated = (new File(path)).mkdirs();
	    	
		
	    	
	    	
	    	if(pathGenerated){
	    		System.out.println("Path generated: yes");
	    	}
	    	else{
	    		System.out.println("Path generated: no");

	    	}

	    	
		    String name = String.valueOf(cam.getId_Cam());
	        String url = cam.getUrl();
	        IpCamMode mode = IpCamMode.PUSH;

	        IpCamDevice ipCamDevice =null;
	        try {
	        	if(!IpCamDeviceRegistry.isRegistered(name))
	        	ipCamDevice = IpCamDeviceRegistry.register(name, url, mode);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			WebcamUtils.capture(Webcam.getWebcams().get(0), fullname, ImageUtils.FORMAT_JPG);
			Webcam.getWebcams().get(0).close();
			


			IpCamDeviceRegistry.unregisterAll();
	    	
	    }

    
    public static void imageCapture(Cam cam,String path,String fullname){
    	
    	(new File(path)).mkdirs();

    	
		//Saxros Webcam Capture tool
    	
	    String name = String.valueOf(cam.getId_Cam());
        String url = cam.getUrl();
        IpCamMode mode = IpCamMode.PULL;

        try {
			IpCamDeviceRegistry.register(name, url, mode);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Webcam.getWebcams().get(0).open();

		WebcamUtils.capture(Webcam.getWebcams().get(0), fullname, ImageUtils.FORMAT_JPG);


		IpCamDeviceRegistry.unregisterAll();
    	
    	
    }
	


}
