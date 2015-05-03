package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Tool_TimeStamp {
	
    public static String getTimeStampString(){

        long date = System.currentTimeMillis();
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        String date_string = sDate.format(date);
        return date_string;
    }

    public static String transformTimeStampToString(long date){

        SimpleDateFormat sDate = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        String date_string = sDate.format(date);
        return date_string;
    }
    
    public static HashMap<String,String> getTimeStampSet(){
    	
    	HashMap<String,String> timstampHashMap = new HashMap<String,String>();
        long date = System.currentTimeMillis();
        
        SimpleDateFormat dateYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateMonth = new SimpleDateFormat("MM");
        SimpleDateFormat dateDay = new SimpleDateFormat("dd");
        SimpleDateFormat dateHour = new SimpleDateFormat("HH");
        
        timstampHashMap.put("year", dateYear.format(date));
        timstampHashMap.put("month", dateMonth.format(date));
        timstampHashMap.put("day", dateDay.format(date));
        timstampHashMap.put("hour", dateHour.format(date));

        return timstampHashMap;

    }

}
