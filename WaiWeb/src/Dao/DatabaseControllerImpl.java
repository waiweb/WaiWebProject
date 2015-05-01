package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			    		  "CREATE TABLE Users ("
			    		+ "Id numeric PRIMARY KEY, "
			    		+ "Username character varying, "
			    		+ "Rechte character varying,"
			    		+ "Kommentar character varying);"
			    		;

			    
				PreparedStatement pstmt = connection.prepareStatement(createString_UserTable); 
				pstmt.executeUpdate();
				

				
		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());
			//throw new UserNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
		
		
		
	}

	@Override
	public void deleteDatabase() {
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
			

			    
				PreparedStatement pstmt = connection.prepareStatement("Drop Table Users"); 
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
