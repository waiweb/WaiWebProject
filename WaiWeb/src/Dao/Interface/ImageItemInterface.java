package Dao.Interface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Cam;
import model.ImageItem;

public interface ImageItemInterface {
	

	
	public void addImage(ImageItem item);
	
	//Das Argument ist eine Hashmap mit den Angaben Year, Month, Day, Hour, Minute
	public ArrayList<ImageItem> getImageItems(Timestamp begin, Timestamp end);
	public void deleteImage(HashMap<String,String> imageHashMap);
	public ArrayList<ImageItem> getImageItemsOfCam(int Id_cam, Timestamp begin, Timestamp end);
	
	public void deleteImage(ImageItem imageItem);
	public List<ImageItem> getAllImageItems();
	
	

}
