package utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import jndi.JndiFactory;

import org.apache.log4j.Logger;

import model.Cam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import com.github.sarxos.webcam.util.ImageUtils;

public class Tool_ImageProcessing {
	
	public static final int IMG_WIDTH_Thumbnail = 100;
	public static final int IMG_HEIGHT_Thumbnail = 100;
	private static Logger log = Logger.getLogger(JndiFactory.class);   


	
	/**
	 * Liest das Originalbild von einem pfad erzeugt eine Thumbnail und schreibt diese in den Zielpfad.
	 * @param pathSource
	 * @param pathTarget
	 * @throws IOException 
	 */
	public static  void generateThumbnailByPathAndSaveToPath(String pathSource, String pathTarget) throws IOException{
		BufferedImage buf = Tool_ImageProcessing.readImageFromPath(pathSource);
		BufferedImage bufThumbnail = Tool_ImageProcessing.getThumbnailOfImage(buf);
		Tool_ImageProcessing.writeImageToPath(bufThumbnail, pathTarget);
	}
	

	
	public static void generateThumbnailFromBufferedImageSaveToPath(BufferedImage bufferedImage, String pathTarget){

		BufferedImage bufThumbnail = Tool_ImageProcessing.getThumbnailOfImage(bufferedImage);
		Tool_ImageProcessing.writeImageToPath(bufThumbnail, pathTarget);
	}
	
	/**
	 * Speichert ein Bild unter dem angegebenen Pfad+Name
	 * @param bufferedImage
	 * @param path
	 * @throws IOException
	 */
	 static void writeImageToPath(BufferedImage bufferedImage, String pathAndName){
		try {
			ImageIO.write(bufferedImage, "jpg", new File(pathAndName));
		} catch (IOException e) {
			log.error("Error: "+e.getMessage());
			e.printStackTrace();
		}  // speichern alle bilder grundsätzlich als jpg
	}
	
	/**
	 * Read Image from Path and returns it as BufferedIamge
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage readImageFromPath(String path) throws IOException{
		BufferedImage originalImage = null;
			originalImage = ImageIO.read(new File(path));
	
		return originalImage;
	}
	
	

	
	
	/**
	 * Returns the Imagetype, this is nessesary for further transformations.
	 * @param bufferedImage
	 * @return
	 */
	private static int getImageType (BufferedImage bufferedImage){
		
		int type ;
		
		if(bufferedImage.getType() == 0){
		type = BufferedImage.TYPE_INT_ARGB;
		}
			else{
			type = bufferedImage.getType();
			}
		
		return type;

	}
	
	 /**
	  * Generate a image in a target size. 
	  * @param bimg
	  * @param imageType
	  * @param dWidth
	  * @param dHeight
	  * @param fWidth
	  * @param fHeight
	  * @return
	  */
	  static BufferedImage scale(BufferedImage bimg, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
		    BufferedImage targetbimg = null;
		    if(bimg != null) {
		    	targetbimg = new BufferedImage(dWidth, dHeight, imageType);
		        Graphics2D g = targetbimg.createGraphics();
		        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
		        g.drawRenderedImage(bimg, at);
		    }
		    return targetbimg;
	 }
	 
	 /**
	  * Creates the fixed thumbnail of a image
	  * @param originalImage
	  * @param type
	  * @return
	  */
	 public static BufferedImage getThumbnailOfImage(BufferedImage originalImage){
	    	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH_Thumbnail, IMG_HEIGHT_Thumbnail,getImageType(originalImage));
	    	Graphics2D g = resizedImage.createGraphics();
	    	g.drawImage(originalImage, 0, 0, IMG_WIDTH_Thumbnail, IMG_HEIGHT_Thumbnail, null);
	    	g.dispose();
	     
	    	return resizedImage;  
	 }
	     
	    
	public static BufferedImage generateThumbnailFromBufferedImageWithHint(BufferedImage originalImage){
	     
	    	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH_Thumbnail, IMG_HEIGHT_Thumbnail,getImageType(originalImage));
	    	Graphics2D g = resizedImage.createGraphics();
	    	g.drawImage(originalImage, 0, 0, IMG_WIDTH_Thumbnail, IMG_HEIGHT_Thumbnail, null);
	    	g.dispose();	
	    	g.setComposite(AlphaComposite.Src);
	     
	    	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	    	RenderingHints.VALUE_RENDER_QUALITY);
	    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	    	RenderingHints.VALUE_ANTIALIAS_ON);
	     
	    	return resizedImage;
	}
	
	
	public static boolean isImage(String path){
		
		if(path.endsWith(".jpg") || path.endsWith(".jpeg"))
			return true;
		
		
		return false;
	}

	
	public static boolean isStream(String path){
		
		if(path.endsWith(".mjpg") || path.endsWith(".cgi"))
			return true;
		
		
		return false;
	}

	
	public static boolean isRightFormat(String path){
		if(path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".mjpg") || path.endsWith(".cgi"))
			return true;
	
		return false;
		
	}
	
	



}
