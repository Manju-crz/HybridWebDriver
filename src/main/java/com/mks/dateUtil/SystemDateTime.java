package com.mks.dateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemDateTime {
	
	
	public String getCurrentSystemDate() {
		
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return sdf.format(date);
	}
	

	public String getCurrentSystemTime() {
		
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public String getCurrentSystemDateAndTime() {
		
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
	

	public String getCurrentSystemDate(String reqFormat) {
		
		DateFormat sdf = new SimpleDateFormat(reqFormat);
		Date date = new Date();
		return sdf.format(date);
	}
	

	public String getCurrentSystemTime(String reqFormat) {
		
		DateFormat sdf = new SimpleDateFormat(reqFormat);
		Date date = new Date();
		return sdf.format(date);
	}
	
	public String getCurrentSystemDateAndTime(String reqFormat) {
		
		DateFormat sdf = new SimpleDateFormat(reqFormat);
		Date date = new Date();
		return sdf.format(date);
	}
	
	
	public String getSystemDateWithDiff(int withDateDiff) {
		
		return null;
	}
	
	public String getCurrentDayName() {
		
		return null;
	}
	
	public String getCurrentMonthName() {
		
		return null;
	}

	public int getCurrentDate() {
		
		return Integer.parseInt(getCurrentSystemDate("dd"));
	}
	
	public int getCurrentMonthValue() {
		
		return Integer.parseInt(getCurrentSystemDate("MM"));
	}
	
	public int getCurrentYear() {
		
		return Integer.parseInt(getCurrentSystemDate("yyyy"));
	}
	
	
	
	
}
