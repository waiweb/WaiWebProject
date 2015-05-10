package model;

import java.util.HashMap;

public class ImageItem {
	
	private long Id_Image;  
	private long Id_CamSource;
	private String year;
	private String month;
	private String week;
	private String day;
	private String hour;
	private String minute;
	private String second;
	
	private String Kommentar;
	
	public ImageItem(){
	};

	public ImageItem(long id_Image, long id_CamSource, String year,
			String month, String week, String day, String hour, String minute,
			String second, String kommentar) {
		Id_Image = id_Image;
		Id_CamSource = id_CamSource;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		Kommentar = kommentar;
	}

	public ImageItem(long id_CamSource, String year, String month, String week,
			String day, String hour, String minute, String second,
			String kommentar) {
		super();
		Id_CamSource = id_CamSource;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		Kommentar = kommentar;
	}
	
	public ImageItem(long id_CamSource, HashMap<String,String> timeHashMap, String kommentar) {
		Id_CamSource = id_CamSource;
		this.year = timeHashMap.get("year");
		this.month = timeHashMap.get("month");
		this.week = timeHashMap.get("week");
		this.day = timeHashMap.get("day");
		this.hour = timeHashMap.get("hour");
		this.minute = timeHashMap.get("minute");
		this.second = timeHashMap.get("second");
		Kommentar = kommentar;
	}
	
	public ImageItem(long id_Image, long id_CamSource, HashMap<String,String> timeHashMap, String kommentar) {
		this.Id_Image = id_Image;
		this.Id_CamSource = id_CamSource;
		this.year = timeHashMap.get("year");
		this.month = timeHashMap.get("month");
		this.week = timeHashMap.get("week");
		this.day = timeHashMap.get("day");
		this.hour = timeHashMap.get("hour");
		this.minute = timeHashMap.get("minute");
		this.second = timeHashMap.get("second");
		Kommentar = kommentar;
	}
	
	
	

}
