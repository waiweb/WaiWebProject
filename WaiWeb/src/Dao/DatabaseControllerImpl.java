package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Dao.Interface.DatabaseControlInterface;
import exception.UserNotAddedExecption;
import jndi.JndiFactory;

public class DatabaseControllerImpl implements DatabaseControlInterface {
	
	final JndiFactory jndi = JndiFactory.getInstance(); //ich hole mir die instanz hier heraus.
	private static Logger log = Logger.getLogger(JndiFactory.class);   


	@Override
	public void createDatabase() {
		log.info("Enter create Database");

		
		//First delete Database
		deleteDatabase();
		

		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
			
			    String createString_UserTable=
			    		  "CREATE TABLE Users_Table ("
			    		+ "Id_User Serial PRIMARY KEY, "
			    		+ "Username character varying, "
			    		+ "Password character varying," //sollte hier gehased abgelegt werden
			    		+ "Rechte integer ,"
			    		+ "TimeOfCreation character varying,"  //bis ich das mit dem datum raus hab.
			    		+ "Kommentar character varying);"
			    		;
			    
			    //CONSTRAINT userNameUnique UNIQUE (Username) todo
			    
			    String createString_CamsTable=
			    		  "CREATE TABLE Cams_Table ("
			    		+ "Id_Cam Serial PRIMARY KEY, "
			    		+ "Camname character varying, "
			    		+ "Url character varying,"
			    		+ "TimeOfCreation character varying,"  //bis ich das mit dem datum raus hab.
			    		+ "PathOriginalImageDirectory character varying,"
			    //		+ "PathThumbnailImageDirectory character varying,"
			    		+ "Kommentar character varying);"
			    		;
			    
			    //Zur zuordnung, welche User auf eine CAM zugreifen d�rfen.
			    String createString_User_Cam_Access_Table=
			    		  "CREATE TABLE User_Cam_Mapping_Table ("
			    		+ "Id_User numeric,  "
			    		+ "Id_Cam numeric);"
			    		;
			    
			    /*
			    String createString_Cam_Images=
			    		  "CREATE TABLE Cam_Images_Table ("
			    		+ "Id_Image serial PRIMARY KEY,"
			    	    + "Imagename character varying,"
			    		+ "Id_Cam numeric, "
			    	    + "Year character varying, "
			    	    + "Month character varying, "
			    	    + "Day character varying, "
			    	    + "Hour character varying, "
			    	    + "Minute character varying, "
			    	    + "Second character varying, "
			    	    + "Millisecond character varying, "
			    		+ "Basepath character varying,"
			    		+ "Kommentar character varying);"
			    		;
			    */
			    
			    String createString_Cam_Images=
			    		  "CREATE TABLE Cam_Images_Table ("
			    		+ "Id_Image serial PRIMARY KEY,"
			    	    + "Imagename character varying,"
			    		+ "Id_Cam numeric, "
			    	    + "Time Timestamp, "
			    		+ "Path character varying,"
			    		+ "Kommentar character varying);"
			    		;

			    
			    

				PreparedStatement pstmt = connection.prepareStatement(createString_UserTable); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement(createString_CamsTable); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement(createString_User_Cam_Access_Table); 
				pstmt.executeUpdate();
				
				
				pstmt = connection.prepareStatement(createString_Cam_Images); 
				pstmt.executeUpdate();
				
				

				
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			log.error("Error: "+e.getMessage());

		} finally {
			closeConnection(connection);
		}
		
		
		
	}

	/**
	 * Droping evey single Table
	 */
	@Override
	public void deleteDatabase() {
		log.info("Enter deleteDatabase");

		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
			

			    
				PreparedStatement pstmt = connection.prepareStatement("Drop Table Users_Table; "); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement("Drop Table Cams_Table; "); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement("Drop Table User_Cam_Mapping_Table; "); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement("Drop Table Cam_Images_Table; "); 
				pstmt.executeUpdate();
				
				System.out.println("Datenbank wurde erfolgreich zurueckgesetzt!");				
				
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			log.error("Error: "+e.getMessage());
		} finally {
			closeConnection(connection);
		}
		
		
	}

	@Override
	public void clearDatabase() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				log.error("Error: "+e.getMessage());

				e.printStackTrace();
			}				
		}
	}
	

}
