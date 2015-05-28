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
	


}
