package Dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jndi.JndiFactory;
import exception.CamNotAddedExecption;
import exception.ImageItemNotFoundExecption;
import exception.ImageNotAddedExecption;
import exception.UserNotFoundExecption;
import utils.Tool_ImageProcessing;
import model.ImageItem;
import model.User;
import Dao.Interface.ImageItemInterface;

public class ImageDaoImpl implements ImageItemInterface{
	
	final JndiFactory jndi = JndiFactory.getInstance(); //ich hole mir die instanz hier heraus.
	public static final String homeDir = "D:/xWAI/Wai_Semesterproject/WaiWebProject/WaiWeb/Images";

	@Override
	public void addImage(BufferedImage bufferedimage, ImageItem item) {
		Connection connection = null;		

		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
		
						
				String pathname = homeDir + "/" 
				+ item.getId_CamSource() + "/"
				+ item.getYear() + "/"
				+ item.getMonth() + "/"
				+ item.getDay() + "/"
				+ item.getHour();
				
				String filename =				
				+ item.getId_CamSource() 
				+ "_"+item.getYear() 
				+ "_"+item.getMonth()
				+ "_"+item.getDay()
				+ "_"+item.getHour()
				+ "_"+item.getMinute()
				+ "_"+item.getSecond()
				+ "_"+item.getMillisecond();
				
				//Im Dateisystem abspeichern	
				new File(pathname).mkdirs();
				Tool_ImageProcessing.writeImageToPath(bufferedimage, pathname+"/"+filename+".jpg");
				Tool_ImageProcessing.writeImageToPath(Tool_ImageProcessing.getThumbnailOfImage(bufferedimage), pathname+"/"+filename+"_thumbnail.jpg");
	
				//In der Datenbank abspeichern
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Cam_Images_Table (Imagename,Id_Cam,Year,Month,Day,Hour,Minute,Second,Millisecond,Basepath,Kommentar) Values(?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, filename);
				pstmt.setLong(2, item.getId_CamSource());
				pstmt.setString(3, item.getYear());
				pstmt.setString(4, item.getMonth());
				pstmt.setString(5, item.getDay());
				pstmt.setString(6, item.getHour());
				pstmt.setString(7, item.getMinute());
				pstmt.setString(8, item.getSecond());
				pstmt.setString(9, item.getMillisecond());
				pstmt.setString(10, homeDir);
				pstmt.setString(11, "");
				pstmt.executeUpdate();
			

		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());

			throw new ImageNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
	}
	
	@Override
	public void addAllImages(ArrayList<BufferedImage> listImages, ArrayList<ImageItem> itemList) {
		
		//Todo Iamge unter pfad abspeichern
		//Todo Image thumbmail unter pfad abspeichern
		//Image Metainfo in die datenbank legen
		Connection connection = null;		

		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
		
			for(int i=0; i<listImages.size();i++){
						
				String pathname = homeDir + "/" 
				+ itemList.get(i).getId_CamSource() + "/"
				+ itemList.get(i).getYear() + "/"
				+ itemList.get(i).getMonth() + "/"
				+ itemList.get(i).getDay() + "/"
				+ itemList.get(i).getHour();
				
				String filename =				
				+ itemList.get(i).getId_CamSource() 
				+ "_"+itemList.get(i).getYear() 
				+ "_"+itemList.get(i).getMonth()
				+ "_"+itemList.get(i).getDay()
				+ "_"+itemList.get(i).getHour()
				+ "_"+itemList.get(i).getMinute()
				+ "_"+itemList.get(i).getSecond()
				+ "_"+itemList.get(i).getMillisecond();
				
				//Im Dateisystem abspeichern		
				Tool_ImageProcessing.writeImageToPath(listImages.get(i), pathname+"/"+filename+".jpg");
				Tool_ImageProcessing.writeImageToPath(Tool_ImageProcessing.getThumbnailOfImage(listImages.get(i)), pathname+"/"+filename+"_thumbnail.jpg");
	
				//In der Datenbank abspeichern
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Cam_Images_Table (Imagename,Id_Cam,Year,Month,Day,Hour,Minute,Second,Millisecond,Basepath,Kommentar) Values(?,?,?,?,?,?,?,?,?,?,2)");
				pstmt.setString(1, filename);
				pstmt.setLong(2, itemList.get(i).getId_CamSource());
				pstmt.setString(3, itemList.get(i).getYear());
				pstmt.setString(4, itemList.get(i).getMonth());
				pstmt.setString(5, itemList.get(i).getDay());
				pstmt.setString(6, itemList.get(i).getHour());
				pstmt.setString(7, itemList.get(i).getMinute());
				pstmt.setString(8, itemList.get(i).getSecond());
				pstmt.setString(9, itemList.get(i).getMillisecond());
				pstmt.setString(10, homeDir);
				pstmt.setString(11, "");
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());

			throw new ImageNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
	
	}
	

	@Override
	public void addImage(File file) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Holt sich alle ImageItems zum zeitpunkt (also innerhalb einer std.) welcher in der Hashmap angegeben wurde.
	 */
	@Override
	public ArrayList<ImageItem> getImageItem(HashMap<String, String> imageHashMap) {
		Connection connection = null;		
		
		ArrayList<ImageItem> imageItemList = new ArrayList<ImageItem> ();
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select id_image,Imagename, Id_Cam,Year,Month,Day,Hour,Minute,Second,Millisecond,Basepath,Kommentar from Cam_Images_Table where"
					+ " Year = ? AND"
					+ " Month = ? AND"
					+ " Day = ? AND"
					+ " Hour = ?");

			pstmt.setString(1, imageHashMap.get("Year"));
			pstmt.setString(2, imageHashMap.get("Month"));
			pstmt.setString(3, imageHashMap.get("Day"));
			pstmt.setString(4, imageHashMap.get("Hour"));

			ResultSet rs =  pstmt.executeQuery();
			
				while(rs.next()){
					ImageItem imageItem = new ImageItem();
					imageItem.setId_Image(rs.getLong("id_image"));
					imageItem.setImageName(rs.getString("Imagename"));
					imageItem.setId_CamSource(rs.getLong("Id_Cam"));
					imageItem.setYear(rs.getString("Year"));
					imageItem.setMonth(rs.getString("Month"));
					imageItem.setDay(rs.getString("Day"));
					imageItem.setHour(rs.getString("Hour"));
					imageItem.setMinute(rs.getString("Minute"));
					imageItem.setSecond(rs.getString("Second"));
					imageItem.setMillisecond(rs.getString("Millisecond"));
					imageItem.setBasepath(rs.getString("Basepath"));
					imageItem.setKommentar(rs.getString("kommentar"));
					
					imageItemList.add(imageItem);
				}

			} catch (Exception e) {
				//throw new UserNotFoundExecption(userId);
				//database fehler
			} finally {
				closeConnection(connection);
			}
		return imageItemList;
	}
	
	
	
	public ImageItem getImageItem(long Id_imageItem){
		
		Connection connection = null;		
			try {
				connection = jndi.getConnection("jdbc/libraryDB");	
				
				
				PreparedStatement pstmt = connection.prepareStatement("select id_image,Imagename, Id_Cam,Year,Month,Day,Hour,Minute,Second,Millisecond,Basepath,Kommentar from Cam_Images_Table where id_image = ?") ;
				pstmt.setLong(1, Id_imageItem);
				ResultSet rs =  pstmt.executeQuery();
				
				if(rs.next()){
					ImageItem imageItem = new ImageItem();
					imageItem.setId_Image(rs.getLong("id_image"));
					imageItem.setImageName(rs.getString("Imagename"));
					imageItem.setId_CamSource(rs.getLong("Id_Cam"));
					imageItem.setYear(rs.getString("Year"));
					imageItem.setMonth(rs.getString("Month"));
					imageItem.setDay(rs.getString("Day"));
					imageItem.setHour(rs.getString("Hour"));
					imageItem.setMinute(rs.getString("Minute"));
					imageItem.setSecond(rs.getString("Second"));
					imageItem.setMillisecond(rs.getString("Millisecond"));
					imageItem.setBasepath(rs.getString("Basepath"));
					imageItem.setKommentar(rs.getString("kommentar"));
					return imageItem;
				}
				else {
				throw new ImageItemNotFoundExecption(Id_imageItem);
				}
				

		} catch (Exception e) {
			//throw new UserNotFoundExecption(userId);
			//database fehler
		} finally {
			closeConnection(connection);
		}
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
		Connection connection = null;		
		ArrayList<ImageItem> imageItemList = new ArrayList<ImageItem>();
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select id_image,Imagename, Id_Cam,Year,Month,Day,Hour,Minute,Second,Millisecond,Basepath,Kommentar from Cam_Images_Table") ;
			ResultSet rs =  pstmt.executeQuery();
			
			while(rs.next()){
				ImageItem imageItem = new ImageItem();
				imageItem.setId_Image(rs.getLong("id_image"));
				imageItem.setImageName(rs.getString("Imagename"));
				imageItem.setId_CamSource(rs.getLong("Id_Cam"));
				imageItem.setYear(rs.getString("Year"));
				imageItem.setMonth(rs.getString("Month"));
				imageItem.setDay(rs.getString("Day"));
				imageItem.setHour(rs.getString("Hour"));
				imageItem.setMinute(rs.getString("Minute"));
				imageItem.setSecond(rs.getString("Second"));
				imageItem.setMillisecond(rs.getString("Millisecond"));
				imageItem.setBasepath(rs.getString("Basepath"));
				imageItem.setKommentar(rs.getString("kommentar"));
				imageItemList.add(imageItem);
			}
		

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
		return imageItemList;
	}



	
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				// nothing to do
				e.printStackTrace();
			}				
		}
	}





}
