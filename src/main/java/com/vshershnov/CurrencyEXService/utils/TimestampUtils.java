package com.vshershnov.CurrencyEXService.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

/**
 * Methods for dealing with timestamps
 */
@Service
public class TimestampUtils {

	/**
	 * Return an ISO 8601 combined date and time string for current date/time
	 * 
	 * @return String with format "yyyy-MM-dd'T'HH:mm'Z'"
	 */
	public String getISO8601StringForCurrentDate() {
		Date now = new Date();
		return getISO8601StringForDate(now);
	}

	/**
	 * Return an ISO 8601 combined date and time string for specified date/time
	 * 
	 * @param Date date 
	 * @return String with format "yyyy-MM-dd'T'HH:mm'Z'"
	 */
	public String getISO8601StringForDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.GERMAN);
		String srtDate = dateFormat.format(date);
		return srtDate;
	}
	
	/**
	 * Return a Date from ISO 8601 combined date and time string
	 * 
	 * @param String dateStr
	 * @return Date
	 */
	public Date getDateForISO8601String(String dateStr) {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.GERMAN);
		
		try {
			Date date = dateFormat.parse(dateStr);			
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Return a Date from ISO 8601 combined date and time string
	 * 
	 * @param String dateStr
	 * @return Date
	 */
	public Date getDateForUrlString(String dateStr) {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
		
		try {
			Date date = dateFormat.parse(dateStr);			
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return an ISO 8601 combined date and time string for NBU Date text "dd.MM.yyyy"
	 * 
	 * @param asText 
	 * @return String with format "yyyy-MM-dd'T'HH:mm'Z'"
	 * @throws ParseException 
	 */
	public String getISOStringForNBUDate(String asText) throws ParseException {
		// input format: MM/yy
		SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
		// output format: yyyy-MM-dd
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.GERMAN);
		Date date = parser.parse(asText);
		date.setTime(new Date().getTime());
		return formatter.format(date); // 2017-11-01		
	}
	
	public Date setTime2359(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 23);  
        cal.set(Calendar.MINUTE, 59);  
        cal.set(Calendar.SECOND, 59); 
        return cal.getTime(); 
    }
}
