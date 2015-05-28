package Dao.Interface;

import java.util.List;

import exception.UserNotFoundExecption;
import model.Cam;
import model.ImageItem;
import model.User;

public interface UserInterface {
	
	
	public Boolean createUserInDatabase(User user);
	public User getUserFromDatabase(long userId) throws UserNotFoundExecption;
	public void updateUser(User user);
	public void deleteUserInDatabase(long userId);
	public void deleteUserInDatabase(User user);
	public List<User> getAllUsers();
	public long getUserIdFromDatabaseByName(String name);
	public boolean isUsernameExisting(String name);
	public boolean isUserLoginValid(String username,String password);
	public boolean isUserLoginValid(User user);

	


}
