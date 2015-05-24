package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CamNotAddedExecption;
import exception.CamNotFoundExecption;
import model.Cam;
import Dao.Interface.CamInterface;


import jndi.JndiFactory;

public class CamDaoImpl implements CamInterface{
	
final JndiFactory jndi = JndiFactory.getInstance(); //ich hole mir die instanz hier heraus.
	
	@Override
	public void createCamInDatabase(Cam cam) {
		

		
		if (cam == null){
			throw new IllegalArgumentException("Cam can not be null");
		}

		Connection connection = null;		
			try {
				connection = jndi.getConnection("jdbc/libraryDB");	
				
		
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO cams_table (Camname,Url,TimeOfCreation,PathOriginalImageDirectory,Kommentar) Values(?,?,?,?,?)");
				pstmt.setString(1, cam.getCamname() );
				pstmt.setString(2, cam.getUrl());
				pstmt.setString(3, cam.getTimeOfCreation());
				pstmt.setString(4, cam.getPathOriginalImageDirectory());
				pstmt.setString(5, cam.getKommentar());
				pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());

			throw new CamNotAddedExecption(cam.getCamname());
		} finally {
			closeConnection(connection);
		}
		
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




	@Override
	public Cam getCamFromDatabase(long camId) {
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			PreparedStatement pstmt = connection.prepareStatement("select Id_Cam,Camname,Url,TimeOfCreation,PathOriginalImageDirectory,Kommentar from cams_table where Id_Cam = ?");
			pstmt.setLong(1, camId);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next()){
				Cam cam = new Cam();
				cam.setId_Cam(rs.getLong("Id_cam"));
				cam.setCamname(rs.getString("camname"));
				cam.setUrl(rs.getString("Url"));
				cam.setTimeOfCreation(rs.getString("TimeOfCreation"));
				cam.setPathOriginalImageDirectory(rs.getString("PathOriginalImageDirectory"));
				cam.setKommentar(rs.getString("Kommentar"));
				return cam;
			}
			else {
			throw new CamNotFoundExecption(camId);
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
	public void deleteCamInDatabase(int camId) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			PreparedStatement pstmt = connection.prepareStatement("delete from cams_table where Id_Cam =?");
			pstmt.setLong(1, camId);
			pstmt.executeQuery();		
			

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
	}

	@Override
	public void deleteCamInDatabase(Cam cam) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			PreparedStatement pstmt = connection.prepareStatement("delete from cams_table where Id_Cam =?");
			pstmt.setLong(1, cam.getId_Cam());
			pstmt.executeQuery();		
			

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
	}

	@Override
	public List<Cam> getAllCams() {
		
		ArrayList<Cam> camlist = new ArrayList<Cam>();
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			PreparedStatement pstmt = connection.prepareStatement("select * from cams_table");
			ResultSet rs =  pstmt.executeQuery();
			
			while(rs.next()){
				Cam cam = new Cam();
				cam.setId_Cam(rs.getLong("Id_cam"));
				cam.setCamname(rs.getString("camname"));
				cam.setUrl(rs.getString("Url"));
				cam.setTimeOfCreation(rs.getString("TimeOfCreation"));
				cam.setPathOriginalImageDirectory(rs.getString("PathOriginalImageDirectory"));
				cam.setKommentar(rs.getString("Kommentar"));
				camlist.add(cam);
			}

			

		} catch (Exception e) {
			//throw new UserNotFoundExecption(userId);
			//database fehler
		} finally {
			closeConnection(connection);
		}
		return camlist;
	}

	@Override
	public void updateUser(Cam cam) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
						
			PreparedStatement pstmt = connection.prepareStatement("update cams_table set Camname = ?, Url = ?, TimeOfCreation = ?, PathOriginalImageDirectory = ?, Kommentar = ? where Id_Cam = ?");
			pstmt.setString(1, cam.getCamname() );
			pstmt.setString(2, cam.getUrl());
			pstmt.setString(3, cam.getTimeOfCreation());
			pstmt.setString(4, cam.getPathOriginalImageDirectory());
			pstmt.setString(5, cam.getKommentar());
			pstmt.setLong(6, cam.getId_Cam());
			
			pstmt.executeUpdate();
			

	} catch (Exception e) {
		System.out.println("Updatefehler: "+e.getMessage());
	} finally {
		closeConnection(connection);
	}
		
	}

	@Override
	public long getCamIdFromDatabaseByName(String name) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select Id_Cam,camname from cams_table where camname = ?") ;
			pstmt.setString(1, name);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next()){
				return rs.getLong("Id_Cam");

			}
			else {
			throw new CamNotFoundExecption(name);
			}
			

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
		return 0;
	}

	@Override
	public boolean isCamNameExisting(String name) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select Id_Cam,camname from cams_table where camname = ?") ;
			pstmt.setString(1, name);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
			else {
				return false;
			}
			

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
		return false;
	}

	//TODO: cheken ob man von der kamera noch bilder beziehen kann.
	@Override
	public boolean isCamAlive(Cam cam) {
		// TODO Auto-generated method stub
		return false;
	}

}
