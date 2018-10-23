import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Statistics class 
 * 
 * @author Tim Weaver
 * @version 2018-10-23
 * 
 */

public class Statistics extends Observation implements DateTimeComparable
{
    protected static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";
    protected DateTimeFormatter format;
    private GregorianCalendar utcDateTime;
    private ZonedDateTime zdtDateTime;
    private int numberOfReportingStations;
    private StatsType statType;
    
    /**
     * Constructor that takes a date as a string 
     * to create statistics
     * 
     * @param value Value
     * @param stid Station Id
     * @param dateTimeStr Date Time as a String
     * @param numberOfValidStations Number of Valid Stations
     * @param inStatType State Type
     * @throws ParseException Checks for errors when parsing the file
     */
    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
            StatsType inStatType) throws ParseException
    {
        super(value, stid);
        this.statType = inStatType;
        this.numberOfReportingStations = numberOfValidStations;
        //this.utcDateTime = createDateFromString(dateTimeStr);
        
    }
    
    /**
     * Constructor that takes a date as a calendar 
     * to create statistics
     * 
     * @param value Value
     * @param stid Station Id
     * @param dateTime Date Time as a Calendar
     * @param numberOfValidStations Number of Valid Stations
     * @param inStatType State Type
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, 
            int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        this.numberOfReportingStations = numberOfValidStations;
        this.statType = inStatType;     
        this.utcDateTime = dateTime;
    }
    
    /**
     * Takes a string and converts it into
     * a Calendar
     * 
     * @param dateTimeStr date and time
     * @return dateTime The Date of Time Parsed correctly 
     * @throws ParseException Checks for errors when parsing the file
     */
    public static GregorianCalendar createDateFromString (String dateTimeStr) throws ParseException
    {
        int offSet = 0;
        if (!dateTimeStr.contains("-"))
        {
            offSet = 1;
        }
        
        String[] dateTimeParts = dateTimeStr.split("-|:|\\s");
              
       
        int day = Integer.parseInt(dateTimeParts[2 + offSet]);
        int year = Integer.parseInt(dateTimeParts[0 + offSet]);
        int month = Integer.parseInt(dateTimeParts[1 + offSet]);
        int hour = Integer.parseInt(dateTimeParts[3 + offSet]);
        int minute = Integer.parseInt(dateTimeParts[4 + offSet]);
        int second = Integer.parseInt(dateTimeParts[5 + offSet]);
        String timeZone = "UTC";   
         
        GregorianCalendar dateTime = new GregorianCalendar(year, month, day, hour, minute, second);
        dateTime.setTimeZone(TimeZone.getTimeZone(timeZone));
        
        return dateTime;
    }
    
    public ZonedDateTime createZDateFromString(String dateTimeStr)
    {
        return zdtDateTime;        
    }
    
    /**
     * Takes a calendar and converts it into
     * a string
     * 
     * @param calendar passes through a calendar 
     * @return dateTime The Date of Time Parsed correctly 
     */
    public static String createStringFromDate(GregorianCalendar calendar)
    {
        String returnString = "";
        DecimalFormat df = new DecimalFormat("00");
        
        returnString += (calendar.get(GregorianCalendar.YEAR) + "-");
        returnString += (df.format(calendar.get(GregorianCalendar.MONTH)) + "-");
        returnString += (df.format(calendar.get(GregorianCalendar.DAY_OF_MONTH)) + " ");
        returnString += (df.format(calendar.get(GregorianCalendar.HOUR_OF_DAY)) + ":");
        returnString += (df.format(calendar.get(GregorianCalendar.MINUTE)) + ":");
        returnString += (df.format(calendar.get(GregorianCalendar.SECOND)) + " ");       
        returnString += "UTC";
        
        return returnString;
    }
    
    public String createStringFromDate(ZonedDateTime calendar)
    {
        return "";
    }

    /**
     * Gets number of reporting stations
     * @return Number of reporting stations
     */
    public int getNumberOfReportingStations()
    {
        return numberOfReportingStations;
    }
    
    /**
     * Gets date string and assigns proper values
     * to the variables
     * @return String of date time
     */
    public String getUTCDateTimeString()
    {
        String returnString = "";
        DecimalFormat df = new DecimalFormat("00");
        
        returnString += (utcDateTime.get(GregorianCalendar.YEAR) + "-");
        returnString += (df.format(utcDateTime.get(GregorianCalendar.MONTH)) + "-");
        returnString += (df.format(utcDateTime.get(GregorianCalendar.DAY_OF_MONTH)) + " ");
        returnString += (df.format(utcDateTime.get(GregorianCalendar.HOUR_OF_DAY)) + ":");
        returnString += (df.format(utcDateTime.get(GregorianCalendar.MINUTE)) + ":");
        returnString += (df.format(utcDateTime.get(GregorianCalendar.SECOND)) + " ");       
        returnString += "UTC";
        
        return returnString;
    }
    
    /**
     * Checks to see if newer than
     * @return date time before
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.before(inDateTime);
    }
    
    /**
     * Checks to see if older than
     * @return date time after
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.after(inDateTime);
    }
    
    /**
     * Checks to see if same as
     * @return date time equals
     */
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        return utcDateTime.equals(inDateTime);
    }
    
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        return utcDateTime.before(inDateTime);
    }
    
    public boolean olderThan(ZonedDateTime inDateTime)
    {
        return utcDateTime.after(inDateTime);
    }
    
    public boolean sameAs(ZonedDateTime inDateTime)
    {
        return utcDateTime.equals(inDateTime);
    }
    
    /**
     * toString method
     * @return empty string
     */
    public String toString()
    {
        return "";
    }
}
