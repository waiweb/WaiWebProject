package Dao.Interface;

import java.util.ArrayList;

import model.Cam;
import model.User;

public interface UserCamMappingInterface {
	
	public void setUserCamMapping(User user, ArrayList<Cam> camList);
	public ArrayList<Cam> getUserCamMapping(User user);
	public ArrayList<Cam> getUserCamMapping(long userId);

}
