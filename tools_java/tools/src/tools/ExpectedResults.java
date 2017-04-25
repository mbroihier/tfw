package tools;

/**
 * 
 * @author broihier
 * <pre>
 * Class for creating ExpectedResults objects
 * </pre>
 */
public class ExpectedResults extends DatabaseComponent {
	/** 
	 * Constructor for creating ExpectedResults objects
	 * 
	 * @param category name that maps to 'category'.testDbExpectedResults files
	 * 
	 */
	public ExpectedResults(String category) {
		super(category+".testDbExpectedResults");
	}
	/**
	 * Method to get the expected results text for a particular testID
	 * @param testID test case ID
	 * @return getComponentText(testID)
	 */
	public String getExpectedResultsText(String testID) {
		return getComponentText(testID);
	}
}
