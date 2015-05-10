package model;

public class Cam {

	private long Id_Cam;
	private String Camname;
	private String Url;
	private String TimeOfCreation;
	private String PathOriginalImageDirectory;
	private String Kommentar;
	
	public Cam(){
		
	}
	
	

	public Cam(long id_Cam, String camname, String url, String timeOfCreation,
		String pathOriginalImageDirectory, String kommentar) {
		
		this.Id_Cam = id_Cam;
		this.Camname = camname;
		this.Url = url;
		this.TimeOfCreation = timeOfCreation;
		this.PathOriginalImageDirectory = pathOriginalImageDirectory;
		this.Kommentar = kommentar;
	}
	
	public Cam(String camname, String url, String timeOfCreation,
			String pathOriginalImageDirectory, String kommentar) {
			
			this.Camname = camname;
			this.Url = url;
			this.TimeOfCreation = timeOfCreation;
			this.PathOriginalImageDirectory = pathOriginalImageDirectory;
			this.Kommentar = kommentar;
		}



	public long getId_Cam() {
		return Id_Cam;
	}

	public void setId_Cam(long id_Cam) {
		Id_Cam = id_Cam;
	}

	public String getCamname() {
		return Camname;
	}

	public void setCamname(String camname) {
		Camname = camname;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getTimeOfCreation() {
		return TimeOfCreation;
	}

	public void setTimeOfCreation(String timeOfCreation) {
		TimeOfCreation = timeOfCreation;
	}

	public String getPathOriginalImageDirectory() {
		return PathOriginalImageDirectory;
	}

	public void setPathOriginalImageDirectory(String pathOriginalImageDirectory) {
		PathOriginalImageDirectory = pathOriginalImageDirectory;
	}

	public String getKommentar() {
		return Kommentar;
	}

	public void setKommentar(String kommentar) {
		Kommentar = kommentar;
	}
	
	
	
}

