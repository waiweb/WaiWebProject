package Dao;

import java.util.List;

import model.Cam;
import model.ImageItem;
import model.User;

public interface UserInterface {
	
	
	public int  createUserInDatabase(User user);
	public User getUserFromDatabase();
	public void deleteUserInDatabase(int userId);
	public void deleteUserInDatabase(User user);
	public List<User> getAllUsers();
	


}
