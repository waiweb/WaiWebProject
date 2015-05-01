package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Dao.Interface.UserInterface;
import exception.UserNotAddedExecption;
import model.User;
import jndi.JndiFactory;

public class UserDaoImpl implements UserInterface {
	
	
	final JndiFactory jndi = JndiFactory.getInstance(); //ich hole mir die instanz hier heraus.


	@Override
	public int createUserInDatabase(User user) {

		
		if (user == null){
			throw new IllegalArgumentException("User can not be null");
		}

		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
			
			//hier user implemntierung.

				
		} catch (Exception e) {
			throw new UserNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
		
		
		return 0;
	}



	@Override
	public User getUserFromDatabase() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteUserInDatabase(int userId) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteUserInDatabase(User user) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
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
