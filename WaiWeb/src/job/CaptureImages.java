package job;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Dao.CamDaoImpl;
import Dao.ImageDaoImpl;
import model.Cam;
import model.ImageItem;
import utils.Tool_CamCapture;
import utils.Tool_TimeStamp;

public class CaptureImages {
	
	final static ImageDaoImpl imagedaoImp = new ImageDaoImpl();
	final static CamDaoImpl camDaoImp = new CamDaoImpl();
	
	
	/**
	 * Alle cams werden aus der Datenbank geladen. Jede cam wird abgefragt und das daraus resultierende Bild sowie
	 * weitere Metainformationen der Image DaoImpl. zur Speicherung übergeben.
	 */
	public static void captureAllCams(){

		ArrayList<Cam> camList = (ArrayList<Cam>) camDaoImp.getAllCams();
		ArrayList<BufferedImage> bufferedImageList = new ArrayList<BufferedImage>();
		ArrayList<ImageItem> imageItemList = new ArrayList<ImageItem> ();
		
		for(Cam cam : camList){		
			
			BufferedImage bufferedImg = Tool_CamCapture.captureImage(cam.getUrl());
			bufferedImageList.add(bufferedImg);
			
			ImageItem item = new ImageItem(cam.getId_Cam(),Tool_TimeStamp.getTimeStampSet(),"");
			imageItemList.add(item);
			
			imagedaoImp.addAllImages(bufferedImageList,imageItemList);		
		}
	
	}
	
	public static void captureCam(Cam cam){


			BufferedImage bufferedImg = Tool_CamCapture.captureImage(cam.getUrl());
			
			ImageItem item = new ImageItem(cam.getId_Cam(),Tool_TimeStamp.getTimeStampSet(),"");
			
			imagedaoImp.addImage(bufferedImg,item);		
	
	}

}
