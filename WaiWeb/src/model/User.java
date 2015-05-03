package model;

import javax.servlet.http.HttpServletRequest;

public class User {
	
	private int Id_User;
	private String Username;
	private String Password;
	private int rechte;
	private String timeOfCreation;
	private String kommentar;
	boolean processError = false;
	
	public User(){
		
	}
	
	
	public int getId_User() {
		return Id_User;
	}
	public void setId_User(int id_User) {
		Id_User = id_User;
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
	
    // hier sollte ich alle infos über ein servlet erhalten und dem benutzer mit daten füllen 
	 public void processRequest (HttpServletRequest request) {
		    // Get the name and the other stuff
		     this.processError = false;
		    if (!(Username == null || Username.equals("")||Password == null || Password.equals(""))){ 
		    	setUsername(request.getParameter ("username"));
		    	setEmail(request.getParameter ("password"));}  
		    else{
		      this.processError = true;
		      return;
		    }
     }
	
	 

}

