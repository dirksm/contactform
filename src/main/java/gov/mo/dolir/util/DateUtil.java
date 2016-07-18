package gov.mo.dolir.util;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

public static final int CURRENT_CENTURY = 2000;
	
	public static String getJulianDate(Date dt)
	{
		String str = "";
		if (dt == null) {
			return str;
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		int day_of_year = gc.get(Calendar.DAY_OF_YEAR);
		int year = gc.get(Calendar.YEAR);
		year -= CURRENT_CENTURY;
		str += StringUtils.leftPad(String.valueOf(year), 2, '0');
		str += StringUtils.leftPad(String.valueOf(day_of_year), 3, '0');
		return str;
	}

	public static String getLongJulianDate(Date dt)
	{
		String str = "";
		if (dt == null) {
			return str;
		}
		str = getJulianDate(dt);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		str += StringUtils.leftPad(String.valueOf(gc.get(Calendar.HOUR_OF_DAY)), 2, '0');
		str += StringUtils.leftPad(String.valueOf(gc.get(Calendar.MINUTE)), 2, '0');
		str += StringUtils.leftPad(String.valueOf(gc.get(Calendar.SECOND)), 2, '0');
		return str;
	}
	
	
	/** 
	 * Add a <code>time</code> component of a Date to a Date.
	 * 
	 * <p>This method adds a time component to
	 * a Date object.
	 * @param {@link Date} object.
	 * @param {@link Date} object. Its time component will be added.
	 * @return Date object - the input Date with a time component added.
	 */
	public static Date addTime(Date dt, Date tm)
	{
		int hrs = 0;
		int mins = 0;
		int secs = 0;
		if(dt == null)
			return null;
		
		if(tm != null){
			GregorianCalendar tgc = new GregorianCalendar();
			tgc.setTime(tm);	
			hrs = tgc.get(Calendar.HOUR_OF_DAY);
			mins = tgc.get(Calendar.MINUTE);
			secs = tgc.get(Calendar.SECOND);
		}
			
		GregorianCalendar dgc = new GregorianCalendar();
		dgc.setTime(dt);
			
		GregorianCalendar gc = new GregorianCalendar( dgc.get(Calendar.YEAR),
		dgc.get(Calendar.MONTH),dgc.get(Calendar.DATE), hrs, mins, secs);
		
		return new Date(gc.getTime().getTime());
	}

	/** 
	 * Get a <code>Time</code> value from a Date value.
	 * 
	 * <p>This method gets a <code>Time</code> component from
	 * a Date object.
	 * @param {@link Date} object.
	 * @return Time object - the time component.
	 */
	public static Time getTime(Date dt)
	{
		if(dt == null)
			return null;
		GregorianCalendar gc = new GregorianCalendar();
  		gc.setTime(dt);
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.clear();
		gc1.set(Calendar.HOUR_OF_DAY, gc.get(Calendar.HOUR_OF_DAY));
		gc1.set(Calendar.MINUTE, gc.get(Calendar.MINUTE) );
		gc1.set(Calendar.SECOND, gc.get(Calendar.SECOND));
		long time = gc1.getTime().getTime();
		return new Time(time);
	}
	
	/**
	 * Gets the millisecond value for a given date at midnight
	 * @return number of milliseconds of dt at midnight
	 */
	public static long getMidnight(Date dt)
	{
		if(dt == null)
			return 0;
			
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime().getTime();
	}
	
	/**
	 * Resets the date object to midnight
	 * @param dt Date object
	 * @return Date object with the time set to midnight
	 */
	public static Date resetTime(Date dt)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime();
	}
	
	/** 
	 * Add the specified (signed) number of months to the given date
	 * @param dt - base date
	 * @param numOfMonths - number of months to add (or to subtract if numOfDays < 0)
	 * @return a Date object
	 */ 
	public static Date addMonths(Date dt, int numofMonths)
	{
		if(dt == null) return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(Calendar.MONTH, numofMonths);
		return gc.getTime();
	}


	/** 
	 * Add the specified (signed) number of days to the given date
	 * @param dt - base date
	 * @param numOfDays - number of days to add (or to subtract if numOfDays < 0)
	 * @return a Date object
	 */ 
	public static Date addDays(Date dt, int numOfDays)
	{
		if(dt == null) return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(Calendar.DAY_OF_MONTH, numOfDays);
		return gc.getTime();
	}
	
	/**
	 * Add the specified (signed) number of working days to a given date.
	 * This assumes that Saturday and Sunday are non-working days.
	 * This does not take holidays into effect
	 * @param dt Base Date
	 * @param numOfDays - Number of working days to add (or to subtract if numOfDays < 0)
	 * @return {@link Date} object representing the new date.
	 */
	public static Date addWorkingDays(Date dt, int numOfDays)
	{
		if(dt == null) return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		
		int loop = 0;
		while(loop < numOfDays)
		{
			gc.add(Calendar.DAY_OF_MONTH, 1);
			if (Calendar.SATURDAY != gc.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != gc.get(Calendar.DAY_OF_WEEK))
			{
				loop++;
			}
		}
		return gc.getTime();
	}
	
	/**
	 * Add the specified (signed) number of minutes to the given date
	 * @param dt
	 * @param numOfMin number of minutes to add (or to subtract if numOfMin < 0)
	 * @return Date object
	 */
	public static Date addMinutes(Date dt, int numOfMin)
	{
		if(dt == null) return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(Calendar.MINUTE, numOfMin);
		return gc.getTime();
	}

	public static Date getFirstDateOfCurrentMonth( Date dt)
	{
		if(dt == null) return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.set( Calendar.DAY_OF_MONTH, gc.getActualMinimum(Calendar.DAY_OF_MONTH));
		return gc.getTime();
	}
	
	public static Date getLastDateOfCurrentMonth( Date dt)
	{
		if(dt == null) return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.set( Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
		return gc.getTime();
	}

	public static String toString(Date dt){
		return toString(dt, "MM/dd/yyyy hh:mm a");
	}
	/**
	 * Format a date using format mask
	 * @param dt
	 * @param formatMask
	 * @return
	 */
	public static String toString(Date dt, String formatMask){
		if(dt == null) return "null";
		
		if(formatMask == null)
			return  toString(dt, "MM/dd/yyyy hh:mm a");
		
		SimpleDateFormat sdFormat = new SimpleDateFormat(formatMask);
		return sdFormat.format(dt);
	}
	
	/**
	 * Parses a date string and converts to a date
	 * @param dt input string to parse
	 * @param pattern date pattern to parse from
	 * @return input string as a {@link Date} object
	 */
	public static Date parse(String dt, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(dt);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}
	
	/**
	 * Formats a {@link Date} object into a {@link String} object following the specified pattern
	 * @param dt {@link Date} object to be formatted
	 * @param pattern Pattern used to format the date into a string
	 * @return {@link String} representation of the {@link Date} based on the pattern.
	 */
	public static String format(Date dt, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = "";
		try {
			date = sdf.format(dt);
		} catch (Exception e) {
			date = "";
		}
		return date;
	}
	
	/**
	 * Determines if compare date is between start and end.
	 * @param start Start Date
	 * @param end End Date
	 * @param compare Date that is being compared
	 * @return true if the compare date is between the start and end date
	 */
	public static boolean between(Date start, Date end, Date compare)
	{
		boolean isBetween = false;
		if ((start == null && end == null) || compare == null) {
			return false;
		}
		if (start == null) {
			isBetween = compare.before(end);
		}
		else if (end == null) {
			isBetween = compare.after(start);
		}
		else {
			isBetween = compare.after(start) && compare.before(end);
		}
		return isBetween;
	}
	
	/**
	 * Returns the year from a java.util.Date object
	 * @param date Date object
	 * @return year of the Date object represented as an int.
	 */
	public static int getYear(Date date)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * Sets the year in a {@link Date} object
	 * @param date {@link Date} object to be set
	 * @param value year value to set it to
	 * @return {@link Date} object with the new year
	 */
	public static Date setYear(Date date, int value)
	{
		return set(Calendar.YEAR, date, value);
	}

	/**
	 * Returns the month from a java.util.Date object
	 * @param date Date object
	 * @return month of the Date object represented as an int.
	 */
	public static int getMonth(Date date)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int month = gc.get(Calendar.MONTH);
		return month;
	}
	
	/**
	 * Sets the month in a {@link Date} object
	 * @param date {@link Date} object to be set
	 * @param value month value to set it to
	 * @return {@link Date} object with the new month
	 */
	public static Date setMonth(Date date, int value)
	{
		return set(Calendar.MONTH, date, value);
	}

	/**
	 * Returns the day of the month from a java.util.Date object
	 * @param date Date object
	 * @return day of the month of the Date object represented as an int.
	 */
	public static int getDay(Date date)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int day = gc.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * Returns the day of the week from a {@link Date} object
	 * @param date {@link Date} object
	 * @return day of the week with Sunday equalling zero
	 */
	public static int getDayOfWeek(Date date)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int day = gc.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	/**
	 * Sets the day of the month in a {@link Date} object
	 * @param date {@link Date} object to be set
	 * @param value day of the month value to set it to
	 * @return {@link Date} object with the new day of the month
	 */
	public static Date setDay(Date date, int value)
	{
		return set(Calendar.DAY_OF_MONTH, date, value);
	}

	public static int get(int field, Date dt)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		return gc.get(field);
	}
	
	public static Date set(int field, Date dt, int value)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.set(field, value);
		return gc.getTime();
	}

	/**
	 * Compares two dates to see if they are on the same date.
	 * This method performs the check only on the mm/dd/yyyy, no 
	 * time aspect is involved.
	 * @param first {@link Date} object to check
	 * @param second {@link Date} object to check
	 * @return true if the dates are equal; false otherwise.  
	 * Will return false if either or both dates are null.
	 */
	public static boolean compareDates(Date first, Date second)
	{
		try
		{
			Date fst = resetTime(first);
			Date sec = resetTime(second);
			return fst.equals(second);
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Retrieves the month name for a number.
	 * This assumes that January starts with 1.
	 * It doesn't follow the java Date functionality for month
	 * @param month Month Number (January equals 1)
	 * @return String object representing the month name
	 */
	public static String getMonthName(Integer month)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.set(Calendar.MONTH, month.intValue()-1);
		SimpleDateFormat sd = new SimpleDateFormat("MMMM");
		return sd.format(gc.getTime());
	}
	
	/**
	 * Returns the number of days between first and last.
	 * Subtracts first from last to determine how many days are between them.
	 * This method assumes last is greater than or equal to first.
	 * @param first The Date object that is subtracting from
	 * @param last The Date object that is being subtracted from
	 * @return int value determining the number of days elapsed
	 */
	public static int getNumDays(Date first, Date last)
	{
		long diff = last.getTime() - first.getTime();

		// 1000 msec per sec
		// 60 sec per min
		// 60 min per hour
		// 24 hr per day
		int conv = 1000 * 60 * 60 * 24;
		return (int)(diff/conv);
	}
	
	/**
	 * Returns a new {@link Date} object with set month, year, and day values
	 * @param month Month of the year with January equaling 1
	 * @param day Day of the month
	 * @param year Year
	 * @return {@link Date} object
	 */
	public static Date createDate(Integer month, Integer day, Integer year)
	{
		Date dt = new Date();
		dt = setMonth(dt, month.intValue() - 1);
		dt = setDay(dt, day.intValue());
		dt = setYear(dt, year.intValue());
		return resetTime(dt);
	}
	
}
