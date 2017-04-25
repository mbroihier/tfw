package tools;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating Results objects
 * </pre>
 */
public class Results extends DatabaseComponent {
	/** 
	 * Constructor for creating Results objects
	 * 
	 * @param category name that maps to 'category'.testDbResults files
	 * 
	 */
	public Results(String category) {
		super(category+".testDbResults");
	}
	/**
	 * Method to get the results text for a particular testID
	 * @param testID test case ID
	 * @return getComponentText(testID)
	 */
	public String getResultsText(String testID) {
		return getComponentText(testID);
	}

}
