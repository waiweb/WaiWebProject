package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Tool_TimeStamp {
	
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
			e.printStackTrace();
		}
		long time = date.getTime();
		
		Timestamp timestamp = new Timestamp(time);
		
		return timestamp;
		
		
    }
    
    

    

}
