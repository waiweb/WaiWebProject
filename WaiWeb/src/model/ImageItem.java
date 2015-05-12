package model;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageItem {
	
	private long Id_Image;  
	private String imageName;
	private long Id_CamSource;
	private String year;
	private String month;
	private String week;
	private String day;
	private String hour;
	private String minute;
	private String second;
	private String millisecond;	
	private String basepath;
	private String Kommentar;
	

	
	public ImageItem(){
	};

	public ImageItem(long id_Image, long id_CamSource, String year,
			String month, String week, String day, String hour, String minute,
			String second,String millisecond, String kommentar) {
		Id_Image = id_Image;
		Id_CamSource = id_CamSource;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.millisecond =millisecond;
		Kommentar = kommentar;
	}

	public ImageItem(long id_CamSource, String year, String month, String week,
			String day, String hour, String minute, String second,String millisecond,
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
		this.millisecond = millisecond;
		this.Kommentar = kommentar;
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
		this.millisecond = timeHashMap.get("millisecond");
		
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
		this.millisecond = timeHashMap.get("millisecond");

		Kommentar = kommentar;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(String millisecond) {
		this.millisecond = millisecond;
	}

	public String getKommentar() {
		return Kommentar;
	}

	public void setKommentar(String kommentar) {
		Kommentar = kommentar;
	}

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	


	
	

}
