package Dao.Interface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import model.Cam;
import model.ImageItem;

public interface ImageItemInterface {
	

	
	public void addImage(BufferedImage bufferedimage, String );
	public void addImage(File file);
	
	//Das Argument ist eine Hashmap mit den Angaben Year, Month, Day, Hour, Minute
	public ImageItem getImageItem(HashMap<String,String> imageHashMap);
	public void deleteImage(HashMap<String,String> imageHashMap);
	
	public void deleteImage(ImageItem imageItem);
	public List<ImageItem> getAllImageItems();
	
	

}
