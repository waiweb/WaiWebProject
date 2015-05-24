package model;

import java.sql.Timestamp;
import java.util.HashMap;

import utils.Tool_TimeStamp;

public class ImageItem {
	
	private long Id_Image;  
	private long Id_CamSource;
	private Timestamp timestamp;
	private String name;
	private String path;
	private String Kommentar;
	
	public ImageItem(){
	};

	/**
	 * Dies ist der empfohlene Konstruktor.
	 * @param id_Image
	 * @param id_CamSource
	 * @param timestamp
	 * @param kommentar
	 */
	public ImageItem(long id_Image,String name, long id_CamSource, Timestamp timestamp,String path, String kommentar) {
		this.Id_Image = id_Image;
		this.name = name;
		this.Id_CamSource = id_CamSource;
		this.timestamp = timestamp;
		this.path = path+name;
		this.Kommentar = kommentar;
	}

	/**
	 * Der Konstruktor erzeugt aus dem timestamp und der cam_id den namen und path zum bild.
	 * @param id_CamSource
	 * @param timestamp
	 * @param kommentar
	 */
	public ImageItem(long id_CamSource, Timestamp timestamp, String kommentar) {
		this.Id_CamSource = id_CamSource;
		this.timestamp = timestamp;
		this.Kommentar = kommentar;
		
		//Zeit- und Namen- Erzeugung
		HashMap<String,String> hashmapTime = Tool_TimeStamp.getTimeStampSetFromSQLDateFormat(timestamp);	
		String imagename = 
		  hashmapTime.get("minute") + "_"
		+ hashmapTime.get("second") + "_"
		+ hashmapTime.get("millisecond") + ".jpg";
						
		String imagepath = 
		  String.valueOf(id_CamSource) + "/"
		+ hashmapTime.get("year") + "/"
		+ hashmapTime.get("month") + "/"
		+ hashmapTime.get("day") + "/"
		+ hashmapTime.get("hour") + "/";
		
		this.path = imagepath+ imagename;
		this.name = imagename;
	
	}

	public long getId_Image() {
		return Id_Image;
	}

	public void setId_Image(long id_Image) {
		Id_Image = id_Image;
	}

	public long getId_CamSource() {
		return Id_CamSource;
	}

	public void setId_CamSource(long id_CamSource) {
		Id_CamSource = id_CamSource;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getKommentar() {
		return Kommentar;
	}

	public void setKommentar(String kommentar) {
		Kommentar = kommentar;
	}
	
	


	

	

}
