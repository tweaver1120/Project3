import org.junit.Assert;
import org.junit.Test;
/**
 * Testing class for the Observation class
 * @author Tim Weaver
 * @version 2018-10-03
 *
 */

public class ObservationTest { 

	/** 
	 * Test full constructor and getters
	 */
	@Test
	public void testObservation() {
		//create new object
		Observation test = new Observation(10.5, "test");    
		
		//assign expected
		double expectedA = 10.5;
		
		//assign actual
		double actualA = test.getValue(); 
		
		//assign expected
		String expectedB = "test";
		
		//assign actual
		String actualB = test.getStid();
		
		//test that expected = actual
		Assert.assertEquals(expectedA, actualA, .01); 
		Assert.assertEquals(expectedB, actualB);
	} 
	
	/** 
	 * Test that input is within the bounds
	 */
	@Test
	public void testIsValid() {
		//create new object
		Observation test = new Observation(10.5, ""); 
		
		//assign expected
		boolean expected = true;
		//assign actual
		boolean actual = test.isValid();
		
		//test that expected = actual
		Assert.assertEquals(expected, actual);
	}
}
