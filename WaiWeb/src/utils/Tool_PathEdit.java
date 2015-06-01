package utils;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import jndi.JndiFactory;
import model.ImageItem;

public class Tool_PathEdit {
	
    public static final String originalImageType = ".jpg";
    public static final String thumbnailImageType = "_thumbnail.jpg";
	private static Logger log = Logger.getLogger(JndiFactory.class);   

	
    /**
     * Returns a new List with copyed values of the original - and with path to the images and the type of the originalimage.
     * @param list
     * @return
     */
	public static List<ImageItem> editImageListToOriginalImagePath(List<ImageItem> list){
		
		ArrayList<ImageItem> editedList  = new ArrayList<ImageItem>();
		//Collections.copy(editedList, list);
		
		@SuppressWarnings("unused")
		String imageDirctoryPath = null;
		try {
			imageDirctoryPath = JndiFactory.getInstance().getImageDirectoryPath();
		} catch (NamingException e) {
			log.error("Error: "+e.getMessage());
			e.printStackTrace();
		}
		
		for(ImageItem item : list){
			
			ImageItem nItem = new ImageItem(item);
			//Hier der Laufwerk Pfad entfernt! Geht jetzt los ab /CamId/Year/...
			nItem.setPath("/camimages/"+ item.getPath()  + originalImageType);
			editedList.add(nItem);
		}
		
		return editedList;
	}
	
	
public static List<ImageItem> editImageListToOriginalImagePathJSP(List<ImageItem> list){
		
		ArrayList<ImageItem> editedList  = new ArrayList<ImageItem>();
		//Collections.copy(editedList, list);
		
		@SuppressWarnings("unused")
		String imageDirctoryPath = null;
		try {
			imageDirctoryPath = JndiFactory.getInstance().getImageDirectoryPath();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ImageItem item : list){
			
			ImageItem nItem = new ImageItem(item);
			//Hier der Laufwerk Pfad entfernt! Geht jetzt los ab /CamId/Year/...
			nItem.setPath("/camimages/"+ item.getPath());
			editedList.add(nItem);
		}
		
		return editedList;
	}
	
	public static List<ImageItem> editImageListToThumbnailImagePath(List<ImageItem> list){
		
		ArrayList<ImageItem> editedList  = new ArrayList<ImageItem>();
		//Collections.copy(editedList, list);
		
		@SuppressWarnings("unused")
		String imageDirctoryPath = null;
		try {
			imageDirctoryPath = JndiFactory.getInstance().getImageDirectoryPath();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ImageItem item : list){
			
			ImageItem nItem = new ImageItem(item);
			nItem.setPath("/camimages/"+ item.getPath() + thumbnailImageType);
			editedList.add(nItem);
		}
		
		return editedList;
	}
	


}
