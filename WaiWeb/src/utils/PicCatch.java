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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;

import model.Cam;
import jndi.JndiFactory;;

public class PicCatch {
	Cam cam;
	String strURL_Dir 			= new String("http://www.hrfoto.dunkel.de/webcams/hr3/studio2.jpg");
	String strLoacalDirectory 	= new String("z:\\Desktop\\WAI\\WaiWeb\\WebContent\\camimages\\");
	String strCamName 			= new String("Cam1");
	String strPicFormat			= new String("jpg");	//Das Bildformat muss angegeben werden, da z.T. die Bilter auf den Servern,
														//ohne Format bzw. mit alternativem Format angegeben sind. 
	
	public PicCatch(){}	//Aufruf der Instanz mit Standardwerten(nur zum Testen)
	
	public PicCatch(Cam camera){
		SetURL_Directrory(camera.getUrl());
		SetCamName(camera.getCamname());
		//SetLocalDirectrory(camera.getPathOriginalImageDirectory());
		SetPicFormat(".jpg");
		cam = camera;
	}
	
	public PicCatch(String URL_Dir, String CamName, String LocalDir, String PicFormat){
		SetURL_Directrory(URL_Dir);
		SetCamName(CamName);
		//SetLocalDirectrory(LocalDir);
		SetPicFormat(PicFormat);
	}
	
	public void SetLocalDirectrory(String LocalDir){
		if(LocalDir.lastIndexOf("\\") != LocalDir.length())
			LocalDir += "\\";
		strLoacalDirectory = LocalDir;
	}
	
	public void SetURL_Directrory(String URL_Dir){
		strURL_Dir = URL_Dir;
	}
	
	public void SetCamName(String CamName){
		strCamName = CamName;
	}
	
	public void SetPicFormat(String PicFormat){
		strPicFormat = PicFormat;
	}
		
	public void GetCurrentPic()throws IOException{
		
		String DATE_FORMAT 			= "yyyyMMdd_hh_mm_ss";
		String DATE_FORMAT_FOLDER 	= "yyyy/MM/dd";
	    SimpleDateFormat sdf 		= new SimpleDateFormat(DATE_FORMAT);
	    SimpleDateFormat Fsdf		= new SimpleDateFormat(DATE_FORMAT_FOLDER);
	    Calendar c1 				= Calendar.getInstance(); // today
		
	    String strTime = sdf.format(c1.getTime());
	    String strFolderDate = Fsdf.format(c1.getTime());
	    strFolderDate = strFolderDate.replace("/","\\");
	    String locFileName 	= strLoacalDirectory  + strCamName + "\\" + strFolderDate + "\\";
	    
	    //String locFileName = new String();
	    //JNDIFactory jndiFactory = JNDIFactory.getInstance();
	    //try{
	    //	locFileName = jndiFactory.getEnvironmentAsString("projectPath") + "\\images\\";
	    //}catch(NamingException e) {}
	       
	    (new File(locFileName)).mkdirs();
	    
	    String locThumbFileName	= locFileName + strCamName + "_" + strTime + "_thumb." + strPicFormat;
	    locFileName			+= strCamName + "_" + strTime + "." + strPicFormat;

	    URL url 			= new URL(strURL_Dir);
		InputStream in 		= new BufferedInputStream(url.openStream());
		OutputStream out	= new BufferedOutputStream(new FileOutputStream(locFileName));
		
		for ( int i; (i = in.read()) != -1; ) {
		    out.write(i);
		}
		
		in.close();
		out.close();
				
		utils.Tool_ImageProcessing.generateThumbnailByPathAndSaveToPath(locFileName, locThumbFileName);
	}
}
