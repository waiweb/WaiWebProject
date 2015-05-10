package Dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import model.ImageItem;
import Dao.Interface.ImageItemInterface;

public class ImageDaoImpl implements ImageItemInterface{
	
	public static final String homeDir = "./Images";

	@Override
	public void addImage(BufferedImage bufferedimage, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addImage(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImageItem getImageItem(HashMap<String, String> imageHashMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteImage(HashMap<String, String> imageHashMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteImage(ImageItem imageItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ImageItem> getAllImageItems() {
		// TODO Auto-generated method stub
		return null;
	}




}
