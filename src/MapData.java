import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map Data class takes a file and parses it based on white space
 * then calculates air temperature min, max, and average at 1.5 meters and 9 meters
 * as well as solar radiation min, max, and average. This is then printed out.
 * 
 * @author Tim Weaver
 * @version 2018-10-23
 * 
 */

public class MapData 
{
    HashMap<String, ArrayList<Observation>> dataCatalog = new HashMap<String, ArrayList<Observation>>();
    
    EnumMap<StatsType, TreeMap<String, Statistics>> statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>();
    
    TreeMap<String, Integer> paramPositions = new TreeMap<String, Integer>();
	
	/**
     * String containing TA9M
     */
	private String TA9M = "TA9M";
	
	/**
     * String containing TAIR
     */
	private String TAIR = "TAIR";
	
	/**
     * String containing SRAD
     */
	private String SRAD = "SRAD";
	
	/**
     * String containing STID
     */
	private String STID = "STID";
	
	/**
	 * Contains the word "Mesonet" which is used
	 * to identify the station
	 */
	private String MESONET = "Mesonet";
	
	/**
	 * The File Name provided by user
	 */
	private String fileName = "";
	
	/**
     * Stores the date 
     */
	private GregorianCalendar utcDateTime;
	
	/**
	 * assigns the positions of tair, ta9m, srad, and stid
	 * @param inParamStr Checks for errors when parsing the file
	 */
	private void parseParamHeader(String inParamStr)
	{
	    ArrayList<String> position = new ArrayList<>();
	    
	    //Trims the string by whitespace
	    String[] id = (inParamStr.trim().split("\\s+"));
	    
	    // Add the array string to arrayList
	    position.addAll(Arrays.asList(id));
	    
	    //Set the positions based on index
	    tairPosition = position.indexOf(TAIR);
	    ta9mPosition = position.indexOf(TA9M);
	    sradPosition = position.indexOf(SRAD);
	    stidPosition = position.indexOf(STID);
	}
	
	public Integer getIndexOf()
	{
	    return 0;
	}
	
	private void calculateAllStatistics()
	{
	    
	}
	
	private void prepareDataCatalog()
	{
	    
	}
	
	public Statistics getStatistics(StatsType type, String paramId)
    {
        
    }
	
	/**
	 * Calculates the min, max, and average for ta9m, tair, and srad
	 * as well as the station Ids for each indicator
	 * @param inData array list of all the data 
	 * @param paramId parameter id
	 */
	private void calculateStatistics(ArrayList<Observation> inData, String paramId)
	{
		double total = 0.0;
        int count = 0;
        double average = 0.0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        String stationId = null;
        
        //Loop through data
        for(int i = 0; i < inData.size(); i++)
        {
            //Check bounds
            if (inData.get(i).isValid())
            {
                //increment counter
                count ++;
                total += inData.get(i).getValue();
                
                //check for min value
                if (inData.get(i).getValue() < min)
                {
                    stationId = inData.get(i).getStid();
                    min = inData.get(i).getValue();
                }
                //check for max value
                if (inData.get(i).getValue() > max)
                {
                    stationId = inData.get(i).getStid();
                    max = inData.get(i).getValue();
                }
            }
            //calculate average          
            average = total/count;
        }
        //assign tair data if parameter id matches tair string
        if (paramId == TAIR)
        {
        	tairMin = new Statistics(min, stationId, utcDateTime, count, StatsType.MINIMUM);
	        tairMax = new Statistics(max, stationId, utcDateTime, count, StatsType.MAXIMUM);
	        tairAverage = new Statistics(average, MESONET, utcDateTime, count, StatsType.AVERAGE);
        }
      //assign srad data if parameter id matches srad string
        else if(paramId == SRAD)
        {
        	sradMin = new Statistics(min, stationId, utcDateTime, count, StatsType.MINIMUM);
	        sradMax = new Statistics(max, stationId, utcDateTime, count, StatsType.MAXIMUM);
	        sradAverage = new Statistics(average, MESONET, utcDateTime, count, StatsType.AVERAGE);
        }
      //assign ta9m data if parameter id matches ta9m string
        else if(paramId == TA9M)
        {
        	ta9mMin = new Statistics(min, stationId, utcDateTime, count, StatsType.MINIMUM);
	        ta9mMax = new Statistics(max, stationId, utcDateTime, count, StatsType.MAXIMUM);
	        ta9mAverage = new Statistics(average, MESONET, utcDateTime, count, StatsType.AVERAGE);
        }      
	}
	
	/**
	 * Map Constructor setting all the fields. Also, reads in a file and calls on parse file method
	 * and calls on calculation methods for Air Temperature at 1.5 meters, Solar Radiation and
	 * Air Temperature at 9 meters 
	 * 
	 * @param year The current year set by the user.
	 * @param month The current month set by the user.
	 * @param day The current day set by the user.
	 * @param hour The current hour set by the user.
	 * @param minute The current minute set by the user.
	 * @param directory The given directory set by the user.
	 */
	public MapData(int year, int month, int day, int hour, int minute, String directory) 
	{
		//Location of the file 
		String path = createFileName(year, month, day, hour, minute, directory); 
	
		//Try to read the file
		try
		{
			parseFile(path);  
		}
		//Print error message if file can not be read
		catch(Exception e)
		{
			System.out.println("Error reading from file!\n"); 
		}	
		
		calculateStatistics(sradData, SRAD);  
		calculateStatistics(ta9mData, TA9M);
		calculateStatistics(tairData, TAIR);	
	} 
	
	/**
	 * returns utc Date Time
	 * @return date time
	 */
	public GregorianCalendar getDateTime()
	{
	    return utcDateTime;
	}
	  
	/**
	 * Creates a file name based on year, month, day, hour, and minute.
	 * 
	 * @param year The current year set by the user.
	 * @param month The current month set by the user.
	 * @param day The current day set by the user.
	 * @param hour The current hour set by the user.
	 * @param minute The current minute set by the user.
	 * @param directory The given directory set by the user.
	 * @return The string representation of create file name method, formatted as:
	 * 			(year)(month)(day)(hour)(minute) ex: 201811200324.mdf
	 */
	public String createFileName(int year, int month, int day, int hour, int minute, String directory)
	{
		return String.format("%s%04d%02d%02d%02d%02d.mdf", directory, year, month, day, hour, minute);
	}

	/**
	 * Parses out a given file based on white space
	 * 
	 * @param path Imports the set file path location
	 * @throws IOException Checks to make sure there are valid inputs.
	 * @throws ParseException Checks for errors when parsing the file
	 */
	public void parseFile(String path) throws IOException, ParseException
	{		
	    /**
	     * Holds the number of stations which gets incremented 
	     */
	    Integer numberOfStations = null;  
	    
		//Begin reading file
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String strg = br.readLine();
		
		//Parse the date and time
		String dateTime = br.readLine();		
		utcDateTime = Statistics.createDateFromString(dateTime.trim());
		
		//Set header indexes
		String headers = br.readLine();
	    parseParamHeader(headers);
		
		//Initialize variable to 0
		numberOfStations = 0;
		
		//Read new line
		strg = br.readLine();
		
        //Initialize data arrays
        sradData = new ArrayList<Observation>();
        tairData = new ArrayList<Observation>();
        ta9mData = new ArrayList<Observation>();
		
		//Loops through an array while the string is not empty
		while (strg != null)
		{
			if (!strg.trim().isEmpty())
			{		
				//Parse the file based on white space
				String[] array  = strg.split("\\s+");	
							
				sradData.add(new Observation(Double.parseDouble(array[sradPosition + 1]), array[stidPosition + 1]));
				tairData.add(new Observation(Double.parseDouble(array[tairPosition + 1]), array[stidPosition + 1]));
				ta9mData.add(new Observation(Double.parseDouble(array[ta9mPosition + 1]), array[stidPosition + 1]));
				
				//Increment number of stations;
				numberOfStations++;
			}
			
			//Read new line
			strg = br.readLine();
		}
		//Close the file
		br.close();
	}
	
	/**
	 * Prints out all of the information gathered and calculated by the program including
	 * year, month, day, hour, minute, max air temp at 1.5m, min air temp at 1.5m, average air temp at 1.5m,
	 * max air temp at 9m, min air temp at 9m, average air temp at 9m, max solar radiation, min solar radiation, and average solar radiation
	 * 
	 * @return The string representation of the MapData class, formatted as:
	 * 			 =========================================================
				 === 2018-08-30 17:45 ===
				 =========================================================
				 Maximum Air Temperature[1.5m] = 21.7 C at MEDI 
				 Minimum Air Temperature[1.5m] = 13.8 C at EVAX 
				 Average Air Temperature[1.5m] = 18.0 C at Mesonet 
				 =========================================================
				 =========================================================
				 Maximum Air Temperature[9.0m] = 23.3 C at MARE 
				 Minimum Air Temperature[9.0m] = 15.8 C at COOK 
				 Average Air Temperature[9.0m] = 19.7 C at Mesonet 
				 =========================================================
				 =========================================================
				 Maximum Solar Radiation[1.5m] = 0.0 W/m^2 at  
				 Minimum Air Temperature[1.5m] = 0.0 W/m^2 at ACME 
				 Average Solar Radiation[1.5m] = 0.0 W/m^2 at Mesonet 
				 =========================================================
	 * 
	 */ 
	public String toString()
	{ 
		String lineBreak = createLineBreak();
		
		String header = String.format("\n" + "=== " + "%02d-%02d-%02d %02d:%02d:%02d" + " ===" + "\n", 
				utcDateTime.get(GregorianCalendar.YEAR), utcDateTime.get(GregorianCalendar.MONTH), utcDateTime.get(GregorianCalendar.DAY_OF_MONTH), 
				utcDateTime.get(GregorianCalendar.HOUR), utcDateTime.get(GregorianCalendar.MINUTE), utcDateTime.get(GregorianCalendar.SECOND)); 
		String tairMaxStrg = String.format("Maximum Air Temperature[1.5m] = %.1f C at %s \n", 
				tairMax.getValue(), tairMax.getStid()); 
		String tairMinStrg = String.format("Minimum Air Temperature[1.5m] = %.1f C at %s \n", tairMin.getValue(), tairMin.getStid());
		String tairAverageStrg = String.format("Average Air Temperature[1.5m] = %.1f C at %s \n", 
				tairAverage.getValue(), MESONET);
		String ta9mMaxStrg = String.format("Maximum Air Temperature[9.0m] = %.1f C at %s \n", ta9mMax.getValue(), ta9mMax.getStid());
		String ta9mMinStrg = String.format("Minimum Air Temperature[9.0m] = %.1f C at %s \n", ta9mMin.getValue(), ta9mMin.getStid());
		String ta9mAverageStrg = String.format("Average Air Temperature[9.0m] = %.1f C at %s \n", 
				ta9mAverage.getValue(),MESONET);
		String sradMaxStrg = String.format("Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s \n", sradMax.getValue(), sradMax.getStid());
		String sradMinStrg = String.format("Minimum Air Temperature[1.5m] = %.1f W/m^2 at %s \n", sradMin.getValue(), sradMin.getStid());
		String sradAverageStrg = String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s \n", 
				sradAverage.getValue(), MESONET);
		
		String format = lineBreak + header + lineBreak + "\n" + tairMaxStrg + tairMinStrg + tairAverageStrg + lineBreak + "\n" + lineBreak
				+ "\n" + ta9mMaxStrg + ta9mMinStrg + ta9mAverageStrg + lineBreak + "\n" + lineBreak + "\n" + sradMaxStrg + sradMinStrg + 
				sradAverageStrg + lineBreak;
		
		return format;
	}

	/**
	 * Creates a line of 57 "=" signs to separate 
	 * information for the toString method
	 * 
	 * @return "=" 57 times to create a line break
	 */
	private String createLineBreak()
	{
		String returnStr = "";
		for (int i = 0; i < 57; i++)
		{
			returnStr += "=";
		}
		
		return returnStr;
	}
	
}
