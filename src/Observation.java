/**
 * Observation class 
 * 
 * @author Tim Weaver
 * @version 2018-10-03
 * 
 */

public class Observation extends AbstractObservation
{
	/**
	 * The value of an object 
	 */
	private double value = 0;    
	
	/**
	 * A flag to check if objects are valid
	 */
	private boolean valid;
	
	/**
	 * Holds the station name
	 */
	private String stid = "";
	
	/**
	 *  Observation Constructor setting all fields
	 *  
	 *  @param value The value of an object
	 *  @param stid The station name
	 */
	public Observation(double value, String stid) 
	{
		this.stid = stid;
		this.value = value;
	}
	
	/**
	 * Checks to make sure the data is within set bounds
	 * 
	 * @return boolean Returns false if data is outside of the bounds
	 */
	public boolean isValid()
	{
		//abnormally small number 
		int min = -900;  
		//abnormally large number
		int max = 900;
				
		//Check lower bound
		if (value < min) 
		{
			return false; 
		}	
		//Check upper bound
		else if (value > max)
		{
			return false;
		}
		//Within the boundaries
		else 
		{
			return true;
		}
	}

	/**
	 * Returns the value
	 * 
	 * @return value
	 */
	public double getValue() {
		return value;  
	}
	
	/**
	 * Returns the station name
	 * 
	 * @return station name
	 */
	public String getStid() {
		return stid;
	}
	
	public String toString()
	{
	    return "";
	}
}
