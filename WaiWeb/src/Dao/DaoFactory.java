package Dao;

import Dao.Interface.CamDao;
import Dao.Interface.DatabaseControlIDao;
import Dao.Interface.ImageDao;
import Dao.Interface.UserCamMappingDao;
import Dao.Interface.UserDao;

public class DaoFactory {
	
	private static DaoFactory instance = new DaoFactory();
	
	private DaoFactory() {		
	}
	
	public static DaoFactory getInstance() {
		return instance;
	}
	
	public CamDao getCamDao() {
		return new CamDaoImpl();
	}
	
	public DatabaseControlIDao getDatabaseControllDao() {
		return new DatabaseControllerImpl();
	}
	
	public ImageDao getIamgeDao() {
		return new ImageDaoImpl();
	}
	
	public UserCamMappingDao getUserCamMappingdao(){
		return new UserCamMappingImpl();
	}
	
	public UserDao getUserDao(){
		return new UserDaoImpl();
	}
}
