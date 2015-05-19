package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jndi.JndiFactory;
import model.Cam;
import model.User;
import Dao.Interface.UserCamMappingInterface;

public class UserCamMappingImpl implements UserCamMappingInterface{

	final JndiFactory jndi = JndiFactory.getInstance();

	
	/**
	 * The function inserts a mapping of User and wich cam's he is able to use. 
	 * If the mapping is updated the whole user-camera mapping well be deleted and re-added with the new list. 
	 * Notice - if the camlist is empty the user will have no rights to view cameras at all.
	 */
	@Override
	public void setUserCamMapping(User user, ArrayList<Cam> camList) {
		
		long Id_User = user.getId_User();
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			
			
			PreparedStatement pstmt = connection.prepareStatement("select * from User_Cam_Mapping_Table where Id_User = ?") ;
			pstmt.setLong(1, Id_User);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next()){
				//update does first delete all and insert them new all.
				pstmt = connection.prepareStatement("Delete from User_Cam_Mapping_Table where Id_User = ?") ;
				pstmt.setLong(1, user.getId_User());
				pstmt.executeUpdate();
				}
			
			//insert updated list.
			for(int i=0; i< camList.size();i++){
				PreparedStatement pstmtt = connection.prepareStatement("INSERT INTO User_Cam_Mapping_Table (Id_User,Id_Cam) Values(?,?)");
				pstmtt.setLong(1, user.getId_User());
				pstmtt.setLong(2, camList.get(i).getId_Cam());
				pstmtt.executeUpdate();
			}
			
		} catch (Exception e) {
			//database query feller
		} finally {
			closeConnection(connection);
		}
		
	}
	

	//todo: evtl. ändern, dass die camliste nur aus long besteht.
	/**
	 * Returns a list of the cams wich are mapped to the user. 
	 * Notice: the cam list only keep the id of the cam an no futher informations.
	 */
	@Override
	public ArrayList<Cam> getUserCamMapping(User user) {
		Connection connection = null;		
		ArrayList<Cam> camList  =new ArrayList<Cam>();
		
		try {
		PreparedStatement pstmt = connection.prepareStatement("select * from User_Cam_Mapping_Table where Id_User = ? ");
		pstmt.setLong(1, user.getId_User());
		ResultSet rs = pstmt.executeQuery();		
		System.out.println("pstmt: "+pstmt.getFetchSize());
		
		
			while(rs.next()){
				Cam cam = new Cam();
				cam.setId_Cam(rs.getLong("Id_Cam"));
				camList.add(cam);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return camList;
	}

	@Override
	public ArrayList<Cam> getUserCamMapping(long userId) {
		Connection connection = null;		
		ArrayList<Cam> camList  =new ArrayList<Cam>();
		
		try {
		PreparedStatement pstmt = connection.prepareStatement("select * from User_Cam_Mapping_Table where Id_User = ? ");
		pstmt.setLong(1, userId);
		ResultSet rs = pstmt.executeQuery();		
		System.out.println("pstmt: "+pstmt.getFetchSize());
		
		
			while(rs.next()){
				Cam cam = new Cam();
				cam.setId_Cam(rs.getLong("Id_Cam"));
				camList.add(cam);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return camList;
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
