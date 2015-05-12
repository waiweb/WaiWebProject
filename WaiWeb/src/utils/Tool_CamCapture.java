package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import model.Cam;
import model.ImageItem;

public class Tool_CamCapture {
	


	//TODO: Funktion die richtig ein image caputred.
	//TODO: Check ob das Image gecaptured wurde
	public static BufferedImage captureImage(String url){
		

		//nur ein platzhalter test
		
		Image image = null;
		try {
			image = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		return (BufferedImage) image;	
	}

}
