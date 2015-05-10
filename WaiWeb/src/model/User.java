package model;

import javax.servlet.http.HttpServletRequest;



public class User {
	
	private long Id_User;
	private String Username;
	private String Password;
	private int rechte;
	private String timeOfCreation;
	private String kommentar;
	boolean processError = false;
	
	public User(){
		
	}
	

	public User(long id_User, String username, String password, int rechte,
			String timeOfCreation, String kommentar) {
		this.Id_User = id_User;
		this.Username = username;
		this.Password = password;
		this.rechte = rechte;
		this.timeOfCreation = timeOfCreation;
		this.kommentar = kommentar;
	}
	
	public User(String username, String password, int rechte,
			String timeOfCreation, String kommentar) {
		this.Username = username;
		this.Password = password;
		this.rechte = rechte;
		this.timeOfCreation = timeOfCreation;
		this.kommentar = kommentar;
	}




	public long getId_User() {
		return Id_User;
	}
	public void setId_User(long l) {
		Id_User = l;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getRechte() {
		return rechte;
	}
	public void setRechte(int rechte) {
		this.rechte = rechte;
	}
	public String getTimeOfCreation() {
		return timeOfCreation;
	}
	public void setTimeOfCreation(String timeOfCreation) {
		this.timeOfCreation = timeOfCreation;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
    public boolean getProcessError () {
		    return this.processError;
	  }
	
    // hier sollte ich alle infos ueber ein servlet erhalten und dem benutzer mit daten fuellen 
	 public void processRequest (HttpServletRequest request) {
		    // Get the name and the other stuff
		     this.processError = false;
		    if (!(Username == null || Username.equals("")||Password == null || Password.equals(""))){ 
		    	setUsername(request.getParameter ("username"));
		    	setPassword(request.getParameter ("password"));}  
		    else{
		      this.processError = true;
		      return;
		    }
     }
	
	 

}

