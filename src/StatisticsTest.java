import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;


/**
 * Statistics Test class 
 * 
 * @author Tim Weaver
 * @version 2018-10-03
 * 
 */

public class StatisticsTest
{

    /**
     * Tests the Statistics constructor with the date 
     * as a string
     * @throws ParseException Checks for errors when parsing the file
     */
    @Test
    public void testStatisticsStringDate() throws ParseException
    {
        Statistics test = new Statistics(10.5, "ACEME", "2018-08-30 17:45:00 UTC", 5, StatsType.MAXIMUM);
        
        double value = test.getValue();
        String stid = test.getStid();
        String date = test.getUTCDateTimeString();
        int station = test.getNumberOfReportingStations();
        
        assertEquals(10.5, value, .1);
        assertEquals("ACEME", stid);
        assertEquals("2018-08-30 17:45:00 UTC", date);
        assertEquals(5, station);
    }
    
    /**
     * Tests the Statistics constructor with the date 
     * as a calendar 
     * @throws ParseException Checks for errors when parsing the file
     */
    @Test
    public void testStatisticsCalDate() throws ParseException
    {
        Statistics test = new Statistics(10.5, "ACEME", "2018-08-30 17:45:00 UTC", 5, StatsType.MAXIMUM);
        
        double value = test.getValue();
        String stid = test.getStid();
        String date = test.getUTCDateTimeString();
        int station = test.getNumberOfReportingStations();
        
        assertEquals(10.5, value, .1);
        assertEquals("ACEME", stid);
        assertEquals("2018-08-30 17:45:00 UTC", date);
        assertEquals(5, station);
    }
    
    /**
     * Tests the string from date method
     * @throws ParseException Checks for errors when parsing the file
     */
    @Test
    public void testStringFromDate() throws ParseException
    {
        GregorianCalendar gc = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        String expected = "2018-08-30 17:45:00 UTC";
        String actual = Statistics.createStringFromDate(gc);
        
        assertEquals(expected,actual);
    }
    
    /**
     * Tests the date from string method
     * @throws ParseException Checks for errors when parsing the file
     */
    @Test
    public void testDateFromString() throws ParseException
    {
        String testStr = "2018-08-30 17:45:00 UTC";
        GregorianCalendar expected = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        GregorianCalendar actual = Statistics.createDateFromString(testStr);
        
        assertEquals(expected.get(GregorianCalendar.HOUR_OF_DAY), actual.get(GregorianCalendar.HOUR_OF_DAY));
        assertEquals(expected.get(GregorianCalendar.YEAR), actual.get(GregorianCalendar.YEAR));
        assertEquals(expected.get(GregorianCalendar.DAY_OF_MONTH), actual.get(GregorianCalendar.DAY_OF_MONTH));
        assertEquals(expected.get(GregorianCalendar.SECOND), actual.get(GregorianCalendar.SECOND));
        assertEquals(expected.get(GregorianCalendar.MINUTE), actual.get(GregorianCalendar.MINUTE));
        assertEquals(expected.get(GregorianCalendar.MONTH), actual.get(GregorianCalendar.MONTH));
    }
    
    /**
     * Tests the newer than method
     * @throws ParseException Checks for errors when parsing the file
     */
    @Test
    public void testNewerThan() throws ParseException
    {
        boolean flag;
        Statistics test = new Statistics(10.5, "ACEME", "2018-08-30 17:45:00 UTC", 5, StatsType.MAXIMUM);
        
        int year = 2018;
        int month = 8;
        int day = 30;
        int hour = 17;
        int minute = 45;
        
        GregorianCalendar testCal = new GregorianCalendar();
        testCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        testCal.set(GregorianCalendar.YEAR,  year);
        testCal.set(GregorianCalendar.MONTH, month);
        testCal.set(GregorianCalendar.DAY_OF_MONTH, day);
        testCal.set(GregorianCalendar.HOUR_OF_DAY, hour);
        testCal.set(GregorianCalendar.MINUTE, minute);
        testCal.set(GregorianCalendar.SECOND, 0);
        
        flag = test.newerThan(testCal);
        
        assertEquals(true, flag);
    }
    
    /**
     * Tests the older than method
     * @throws ParseException Checks for errors when parsing the file
     */
    @Test
    public void testOlderThan() throws ParseException
    {
        boolean flag;
        Statistics test = new Statistics(10.5, "ACEME", "2018-08-30 17:45:00 UTC", 5, StatsType.MAXIMUM);
        
        int year = 2018;
        int month = 8;
        int day = 30;
        int hour = 17;
        int minute = 45;
        
        GregorianCalendar testCal = new GregorianCalendar();
        testCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        testCal.set(GregorianCalendar.YEAR,  year);
        testCal.set(GregorianCalendar.MONTH, month);
        testCal.set(GregorianCalendar.DAY_OF_MONTH, day);
        testCal.set(GregorianCalendar.HOUR_OF_DAY, hour);
        testCal.set(GregorianCalendar.MINUTE, minute);
        testCal.set(GregorianCalendar.SECOND, 0);
        
        flag = test.newerThan(testCal);
        
        assertEquals(true, flag);
    }
    
    /**
     * Tests the same as method
     * @throws ParseException Checks for errors when parsing the file
     */
    @Test
    public void testSameAs() throws ParseException
    {
        boolean flag;
        Statistics test = new Statistics(10.5, "ACEME", "2018-08-30 17:45:00 UTC", 5, StatsType.MAXIMUM);
        
        int year = 2018;
        int month = 8;
        int day = 30;
        int hour = 17;
        int minute = 45;
        
        GregorianCalendar testCal = new GregorianCalendar();
        testCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        testCal.set(GregorianCalendar.YEAR,  year);
        testCal.set(GregorianCalendar.MONTH, month);
        testCal.set(GregorianCalendar.DAY_OF_MONTH, day);
        testCal.set(GregorianCalendar.HOUR_OF_DAY, hour);
        testCal.set(GregorianCalendar.MINUTE, minute);
        testCal.set(GregorianCalendar.SECOND, 0);
        
        flag = test.newerThan(testCal);
        
        assertEquals(true, flag);
    }   
}
