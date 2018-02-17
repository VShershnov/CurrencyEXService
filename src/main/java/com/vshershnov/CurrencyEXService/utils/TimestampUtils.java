package com.vshershnov.CurrencyEXService.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.getDefault());
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(date);
	}
	
	/**
	 * Return a Date from ISO 8601 combined date and time string
	 * 
	 * @param String dateStr
	 * @return Date
	 */
	public Date getDateForISO8601String(String dateStr) {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.getDefault());
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));		

		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
}
