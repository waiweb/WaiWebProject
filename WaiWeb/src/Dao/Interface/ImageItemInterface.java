package Dao.Interface;

import java.util.List;

import model.Cam;
import model.ImageItem;

public interface ImageItemInterface {
	

	
	public void addImage(ImageItem imageItem);
	public ImageItem getImage(int imageId);
	public void deleteImageItemFromDatabase(int imageId);
	public void deleteImageItemFromDatabase(ImageItem imageItem);
	public List<ImageItem> getAllImageItems();
	

}
