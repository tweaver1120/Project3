import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
/** 
 * Tests the Map Data Class
 * 
 * @author Tim Weaver
 * @version 2018-10-03
 */
public class MapDataTest {  

	/** 
	 * Test full constructor and getters
	 */
	@Test
	public void testMapData() {
		MapData test = new MapData(2018, 8, 30, 17, 45, "data/");  	
		GregorianCalendar gc = new GregorianCalendar(2018, 8, 30, 0, 0, 0);
		
		
		
		Assert.assertEquals(gc.get(GregorianCalendar.YEAR), test.getDateTime().get(GregorianCalendar.YEAR));
		Assert.assertEquals(gc.get(GregorianCalendar.MONTH), test.getDateTime().get(GregorianCalendar.MONTH));
		Assert.assertEquals(gc.get(GregorianCalendar.DAY_OF_MONTH), test.getDateTime().get(GregorianCalendar.DAY_OF_MONTH));
		Assert.assertEquals(gc.get(GregorianCalendar.HOUR), test.getDateTime().get(GregorianCalendar.HOUR));
		Assert.assertEquals(gc.get(GregorianCalendar.MINUTE), test.getDateTime().get(GregorianCalendar.MINUTE));
		Assert.assertEquals(gc.get(GregorianCalendar.SECOND), test.getDateTime().get(GregorianCalendar.SECOND));   
	}
	
	/** 
	 * Test if file is parsing correctly
	 * @throws IOException Make sure inputs are valid
	 * @throws ParseException Checks for errors when parsing the file
	 */
	@Test
	public void testParseFile() throws IOException, ParseException
	{
		MapData test = new MapData(2018, 8, 30, 17, 45, "data/");
		
		String path = test.createFileName(2018, 8, 30, 17, 45, "data/");
		test.parseFile(path);
		
		Assert.assertFalse(test.sradData == null);   
		Assert.assertFalse(test.tairData == null); 
		Assert.assertFalse(test.ta9mData == null); 
	}
	
	/**
	 * Test the positions of the data is being
	 * correctly assigned
	 * @throws IOException Make sure inputs are valid
	 */
	@Test
	public void testParamHeader() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data/");
        
        int[] positions = test.getPositions();
        
        Assert.assertFalse(positions[0] == -1);   
        Assert.assertFalse(positions[1] == -1); 
        Assert.assertFalse(positions[2] == -1); 
    }
	
	/** 
	 * Test air temperature statistics at 1.5 meters,
	 * air temperature statistics at 1.5 meters, and solar radiation statistics
	 */
	@Test
	public void testCalculateStatistics() {
		MapData test = new MapData(2018, 8, 30, 17, 45, "data/"); 
		
		Assert.assertEquals(20.8, test.getTairMin().getValue(), .1); 
		Assert.assertEquals(36.5, test.getTairMax().getValue(), .1); 
		Assert.assertEquals(32.4, test.getTairAverage().getValue(), .1);   
		
		Assert.assertEquals(20.7, test.getTa9mMin().getValue(), .1); 
        Assert.assertEquals(34.9, test.getTa9mMax().getValue(), .1); 
        Assert.assertEquals(31.6, test.getTa9mAverage().getValue(), .1);
        
        Assert.assertEquals(163.0, test.getSradMin().getValue(), .1); 
        Assert.assertEquals(899.0, test.getSradMax().getValue(), .1); 
        Assert.assertEquals(820.5, test.getSradAverage().getValue(), .1);
	}
	
	
	/** 
	 * Make sure file name is being created correctly 
	 */
	@Test
	public void testCreateFileName() {
		MapData test = new MapData(2018, 8, 30, 17, 45, "data/"); 
		
		String expected = "201811201035.mdf";
		String actual = test.createFileName(2018, 11, 20, 10, 35, ""); 
		
		Assert.assertEquals(expected, actual);
	} 
}
