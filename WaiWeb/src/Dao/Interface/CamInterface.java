package Dao.Interface;

import java.util.List;

import model.Cam;
import model.User;

public interface CamInterface {
	
	
	public void createCamInDatabase(Cam cam);
	public Cam getCamFromDatabase(long camId);
	public void deleteCamInDatabase(int camId);
	public void deleteCamInDatabase(Cam cam);
	public List<Cam> getAllCams();
	public void updateUser(Cam cam);
	public long getCamIdFromDatabaseByName(String name);
	public boolean isCamNameExisting(String name);


}
