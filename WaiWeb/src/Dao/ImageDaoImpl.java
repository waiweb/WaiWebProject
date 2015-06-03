package Dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import utils.Tool_TimeStamp;
import jndi.JndiFactory;
import exception.UserNotAddedExecption;
import model.ImageItem;
import model.User;
import Dao.Interface.ImageDao;

public class ImageDaoImpl implements ImageDao{
	
	public static final String homeDir = "./Images";
	final JndiFactory jndi = JndiFactory.getInstance(); 
    private static Logger log = Logger.getLogger(JndiFactory.class);   



	
	@Override
	public void addImage(ImageItem item) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
		
	
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Cam_Images_Table (Imagename,Id_Cam,Time,Path,Kommentar) Values(?,?,?,?,?)");
			pstmt.setString(1, item.getName());
			pstmt.setLong(2, item.getId_CamSource());
			pstmt.setTimestamp(3, item.getTimestamp());
			pstmt.setString(4, item.getPath()); //path includes the imagename
			pstmt.setString(5, item.getKommentar());
			pstmt.executeUpdate();
			

	} catch (Exception e) {
		System.out.println("Fehler: "+e.getMessage());
		log.error("Error: ImageDaoImpl: addImage failed");
		log.error("Erromessage: "+e.getMessage());

		throw new UserNotAddedExecption();
	} finally {
		closeConnection(connection);
	}
	

	}
	
	


	@Override
	public ArrayList<ImageItem> getImageItems(Timestamp begin, Timestamp end) {
		
		ArrayList<ImageItem> imageItemList = new ArrayList<ImageItem>();
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
	
		PreparedStatement pstmt = connection.prepareStatement("select * FROM Cam_Images_Table where Time >= ? AND Time <= ?");
		pstmt.setTimestamp(1, begin);
		pstmt.setTimestamp(2, end);
		
		ResultSet rs = pstmt.executeQuery();		
	

		while(rs.next()){
			ImageItem item = new ImageItem();
			item.setId_Image(rs.getLong("Id_Image"));
			item.setId_CamSource(rs.getLong("Id_Cam"));
			item.setTimestamp(rs.getTimestamp("Time"));
			item.setPath(rs.getString("Path"));
			item.setKommentar(rs.getString("Kommentar"));
			
			imageItemList.add(item);
		}

		
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			log.error("Error: "+e.getMessage());

			throw new UserNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
		
		
		System.out.println("siez imagearray : "+imageItemList.size());
		log.info("size of selected images: "+imageItemList.size());

		return imageItemList;
	}
	
	
	
	@Override
	public ArrayList<ImageItem> getImageItemsOfCam(int Id_cam, Timestamp begin, Timestamp end) {
		
		ArrayList<ImageItem> imageItemList = new ArrayList<ImageItem>();
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
		
		PreparedStatement pstmt = connection.prepareStatement("select * FROM Cam_Images_Table where Time >= ? AND Time <= ? AND Id_Cam = ?");
		pstmt.setTimestamp(1, begin);
		pstmt.setTimestamp(2, end);
		pstmt.setInt(3, Id_cam);
		
		ResultSet rs = pstmt.executeQuery();		
	
	
		while(rs.next()){
			ImageItem item = new ImageItem();
			item.setId_Image(rs.getLong("Id_Image"));
			item.setId_CamSource(rs.getLong("Id_Cam"));
			item.setTimestamp(rs.getTimestamp("Time"));
			item.setPath(rs.getString("Path"));
			item.setKommentar(rs.getString("Kommentar"));
			

			imageItemList.add(item);
		}

		
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			log.error("Error: "+e.getMessage());

			throw new UserNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
		
		
		System.out.println("siez imagearray : "+imageItemList.size());
		log.info("size of selected images: "+imageItemList.size());


		return imageItemList;
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
		ArrayList<ImageItem> imageItemList = new ArrayList<ImageItem>();
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
		
		
		
		PreparedStatement pstmt = connection.prepareStatement("select * FROM Cam_Images_Table");

		ResultSet rs = pstmt.executeQuery();		
	

		
		while(rs.next()){
			ImageItem item = new ImageItem();
			item.setId_Image(rs.getLong("Id_Image"));
			item.setId_CamSource(rs.getLong("Id_Cam"));
			item.setTimestamp(rs.getTimestamp("Time"));
			item.setPath(rs.getString("Path"));
			item.setKommentar(rs.getString("Kommentar"));
			

			imageItemList.add(item);
		}

		
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			log.error("Error: "+e.getMessage());

			throw new UserNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
		
		
		System.out.println("siez imagearray : "+imageItemList.size());
		log.info("size of selected images: "+imageItemList.size());


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
