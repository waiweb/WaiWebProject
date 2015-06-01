package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import jndi.JndiFactory;

import org.apache.log4j.Logger;

public class Tool_TimeStamp {
	
	private static Logger log = Logger.getLogger(JndiFactory.class);   

	
	//@Deprecated 
    public static String getTimeStampString(){

        long date = System.currentTimeMillis();
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_string = sDate.format(date);
        return date_string;
    }

    public static String transformTimeStampToString(long date){

        SimpleDateFormat sDate = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        String date_string = sDate.format(date);
        return date_string;
    }
    
    /**
     * Generates a timestamp and returns a hashmap of strings of the timestamp 
     * @return
     */
	//@Deprecated 
    public static HashMap<String,String> getTimeStampSet(){
    	
    	HashMap<String,String> timstampHashMap = new HashMap<String,String>();
        long date = System.currentTimeMillis();
        
        SimpleDateFormat dateYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateMonth = new SimpleDateFormat("MM");
        SimpleDateFormat dateDay = new SimpleDateFormat("dd");
        SimpleDateFormat dateHour = new SimpleDateFormat("HH");
        SimpleDateFormat dateMinute = new SimpleDateFormat("mm");
        SimpleDateFormat dateSecond = new SimpleDateFormat("ss");
        SimpleDateFormat dateMilliSecond = new SimpleDateFormat("SSS");
        
        timstampHashMap.put("year", dateYear.format(date));
        timstampHashMap.put("month", dateMonth.format(date));
        timstampHashMap.put("day", dateDay.format(date));
        timstampHashMap.put("hour", dateHour.format(date));
        timstampHashMap.put("minute", dateMinute.format(date));
        timstampHashMap.put("second", dateSecond.format(date));
        timstampHashMap.put("millisecond", dateMilliSecond.format(date));

        return timstampHashMap;

    }
    
    
    public static Timestamp getTimeStampSQLDateFormat(){
    	return   new Timestamp(System.currentTimeMillis());

    }
    
    
    public static HashMap<String,String> getTimeStampSetFromSQLDateFormat(Timestamp date){
    	
    	HashMap<String,String> timstampHashMap = new HashMap<String,String>();
        
        SimpleDateFormat dateYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateMonth = new SimpleDateFormat("MM");
        SimpleDateFormat dateDay = new SimpleDateFormat("dd");
        SimpleDateFormat dateHour = new SimpleDateFormat("HH");
        SimpleDateFormat dateMinute = new SimpleDateFormat("mm");
        SimpleDateFormat dateSecond = new SimpleDateFormat("ss");
        SimpleDateFormat dateMilliSecond = new SimpleDateFormat("SSS");
        
        timstampHashMap.put("year", dateYear.format(date));
        timstampHashMap.put("month", dateMonth.format(date));
        timstampHashMap.put("day", dateDay.format(date));
        timstampHashMap.put("hour", dateHour.format(date));
        timstampHashMap.put("minute", dateMinute.format(date));
        timstampHashMap.put("second", dateSecond.format(date));
        timstampHashMap.put("millisecond", dateMilliSecond.format(date));

        return timstampHashMap;

    }
    
    public static Timestamp generateTimeStamp(String year,String month,String day, String hour){
    	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM/HH");
		String timeString = new String(year+"/"+day+"/"+month+"/" +hour);

		java.util.Date date = null;
		try {
			date =  dateFormat.parse(timeString);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("Error: "+e.getMessage());

			e.printStackTrace();
		}
		long time = date.getTime();
		
		Timestamp timestamp = new Timestamp(time);
		
		return timestamp;
		
		
    }
    
    
	public static Timestamp convertStringToTimestamp(String date,String time) {
		Timestamp timestamp = null;
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		    Date parsedDate = dateFormat.parse(date+" "+time);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){//this generic but you can control another types of exception
			log.error("Error: "+e.getMessage());
		 System.out.println("Fehler bei der Konvertierung von String in Timestamp !!");
		}
		
		return timestamp;
	  }
    
    

    

}
