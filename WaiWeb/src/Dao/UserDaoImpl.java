package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.Interface.UserInterface;
import exception.UserNotAddedExecption;
import exception.UserNotFoundExecption;
import model.User;
import jndi.JndiFactory;

public class UserDaoImpl implements UserInterface {
	
	
	final JndiFactory jndi = JndiFactory.getInstance(); //ich hole mir die instanz hier heraus.


	@Override
	public void createUserInDatabase(User user) {

		
		if (user == null){
			throw new IllegalArgumentException("User can not be null");
		}

		Connection connection = null;		
			try {
				connection = jndi.getConnection("jdbc/libraryDB");	
				
		
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users_table (username,password,rechte,timeofcreation,kommentar) Values(?,?,?,?,?)");
				pstmt.setString(1, user.getUsername() );
				pstmt.setString(2, user.getPassword());
				pstmt.setInt(3, user.getRechte());
				pstmt.setString(4, user.getTimeOfCreation());
				pstmt.setString(5, user.getKommentar());
				pstmt.executeUpdate();
				
				
				/*//alternativ
				String createUser = 
			   "INSERT INTO INSERT INTO users_table (username,password,rechte,timeofcreation,kommentar) Values("
			    + "'"+user.getUsername() + "', "
			    + "'"+user.getPassword() + "', "
			    	 +user.getRechte()+", "
			    + "'"+user.getTimeOfCreation()+"',"
			    + "'"+user.getId_User()+"'"
			    +");";
			    
				PreparedStatement pstmt = connection.prepareStatement(createUser); 
				pstmt.executeUpdate();  //oder quey?
				*/
				
				

		} catch (Exception e) {
			System.out.println("Fehler: "+e.getMessage());

			throw new UserNotAddedExecption();
		} finally {
			closeConnection(connection);
		}
		
		
	}
	
	
	



	@Override
	public User getUserFromDatabase(long userId) throws UserNotFoundExecption {

		
		Connection connection = null;		
			try {
				connection = jndi.getConnection("jdbc/libraryDB");	
				
				PreparedStatement pstmt = connection.prepareStatement("select Id_User,username,password,rechte,timeofcreation,kommentar from users_table where Id_User = ?") ;
				pstmt.setLong(1, userId);
				ResultSet rs =  pstmt.executeQuery();
				
				if(rs.next()){
					User user = new User();
					user.setId_User(rs.getLong("Id_User"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRechte(rs.getInt("rechte"));
					user.setTimeOfCreation(rs.getString("timeofcreation"));
					user.setKommentar(rs.getString("kommentar"));
					return user;
				}
				else {
				throw new UserNotFoundExecption(userId);
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
	public void updateUser(User user){
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("update users_table set username = ?, password = ?, rechte = ?, timeofcreation = ?, kommentar = ? where Id_User = ?");
			pstmt.setString(1,user.getUsername() );
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getRechte());
			pstmt.setString(4, user.getTimeOfCreation());
			pstmt.setString(5, user.getKommentar());
			pstmt.setLong(6, user.getId_User());
			pstmt.executeUpdate();
			

	} catch (Exception e) {
		System.out.println("Updatefehler: "+e.getMessage());
	} finally {
		closeConnection(connection);
	}
		
		
	}




	@Override
	public void deleteUserInDatabase(long userId) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			PreparedStatement pstmt = connection.prepareStatement("delete from users_table where Id_User =?");
			pstmt.setLong(1, userId);
			pstmt.executeQuery();		
			

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
		
	}

	
	


	@Override
	public void deleteUserInDatabase(User user) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			PreparedStatement pstmt = connection.prepareStatement("delete from users_table where Id_User =?");
			pstmt.setLong(1, user.getId_User());
			pstmt.executeQuery();		
			

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
		
	}



	@Override
	public List<User> getAllUsers() {
		
		ArrayList<User> userlist = new ArrayList<User>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			PreparedStatement pstmt = connection.prepareStatement("select * from users_table");
			ResultSet rs = pstmt.executeQuery();		
			System.out.println("pstmt: "+pstmt.getFetchSize());
			
			
			while(rs.next()){
				User user = new User();
				user.setId_User(rs.getLong("Id_User"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRechte(rs.getInt("rechte"));
				user.setTimeOfCreation(rs.getString("timeofcreation"));
				user.setKommentar(rs.getString("kommentar"));
				userlist.add(user);
			}

			

	} catch (Exception e) {
		//throw new UserNotFoundExecption(userId);
		//database fehler
	} finally {
		closeConnection(connection);
	}
		
		
		return userlist;
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
	public boolean isUserLoginValid(String username, String password) {
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select Id_User,username,password,rechte,timeofcreation,kommentar from users_table where username =? AND password =?") ;
			pstmt.setString(1, username);
			pstmt.setString(2, password);

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
	
	



	@Override
	public boolean isUserLoginValid(User user) {

		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select Id_User,username,password,rechte,timeofcreation,kommentar from users_table where username =? AND password =?") ;
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());

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



	@Override
	public long getUserIdFromDatabaseByName(String name) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select Id_User,username,password,rechte,timeofcreation,kommentar from users_table where username = ?") ;
			pstmt.setString(1, name);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next()){
				return rs.getLong("Id_User");

			}
			else {
			throw new UserNotFoundExecption(name);
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
	public boolean isUsernameExisting(String name) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select username from users_table where username = ?") ;
			pstmt.setString(1, name);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
			else {
			return false;
			}
		} catch (Exception e) {
			//database query fehler
		} finally {
			closeConnection(connection);
		}

		return false;
	}
	
	
	

}
