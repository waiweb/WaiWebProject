package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Dao.Interface.DatabaseControlInterface;
import exception.UserNotAddedExecption;
import jndi.JndiFactory;

public class DatabaseControllerImpl implements DatabaseControlInterface {
	
	final JndiFactory jndi = JndiFactory.getInstance(); //ich hole mir die instanz hier heraus.
	


	@Override
	public void createDatabase() {
		
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
			    
			    
			    String createString_CamsTable=
			    		  "CREATE TABLE Cams_Table ("
			    		+ "Id_Cam Serial PRIMARY KEY, "
			    		+ "Camname character varying, "
			    		+ "Url character varying,"
			    		+ "TimeOfCreation character varying,"  //bis ich das mit dem datum raus hab.
			    		+ "PathOriginalImageDirectory character varying,"
			    		+ "PathThumbnailImageDirectory character varying,"
			    		+ "Kommentar character varying);"
			    		;
			    
			    //Zur zuordnung, welche User auf eine CAM zugreifen dürfen.
			    String createString_CamsAccess=
			    		  "CREATE TABLE Cam_Access_Table ("
			    		+ "Id_Cam numeric PRIMARY KEY, "
			    		+ "Camname character varying, "   //redundant
			    		+ "Id_User numeric, "
			    		+ "Username character varying,"  //redundant
			    		+ "Kommentar character varying);"
			    		;
			    
			    
			    String createString_Cam_Images=
			    		  "CREATE TABLE Cam_Images_Table ("
			    		+ "Id_Image numeric PRIMARY KEY,"
			    	    + "Imagename character varying,"
			    		+ "Id_Cam numeric, "
			    		+ "Camname character varying, "   //redundant
			    		+ "TimeOfCreation character varying, " //Todo Date statt character
			    		+ "PathOriginalImage character varying,"
			    		+ "PathThumbnailImage character varying,"
			    		+ "Kommentar character varying);"
			    		;
			    
			    


			    
				PreparedStatement pstmt = connection.prepareStatement(createString_UserTable); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement(createString_CamsTable); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement(createString_CamsAccess); 
				pstmt.executeUpdate();
				
				
				pstmt = connection.prepareStatement(createString_Cam_Images); 
				pstmt.executeUpdate();
				
				

				
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			//throw new UserNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
		
		
		
	}

	/**
	 * Droping evey single Table
	 */
	@Override
	public void deleteDatabase() {
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
			

			    
				PreparedStatement pstmt = connection.prepareStatement("Drop Table Users_Table; "); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement("Drop Table Cams_Table; "); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement("Drop Table Cam_Access_Table; "); 
				pstmt.executeUpdate();
				
				pstmt = connection.prepareStatement("Drop Table Cam_Images_Table; "); 
				pstmt.executeUpdate();
				

				
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			//throw new UserNotAddedExecption();
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
				// nothing to do
				e.printStackTrace();
			}				
		}
	}
	

}
