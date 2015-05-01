package Dao.Interface;

import java.util.List;

import model.Cam;

public interface CamInterface {
	
	
	public int createCamInDatabase(Cam cam);
	public Cam getCamFromDatabase();
	public void deleteCamInDatabase(int camId);
	public void deleteCamInDatabase(Cam cam);
	public List<Cam> getAllCams();


}
