package tools;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating Cleanup objects
 * </pre>
 */
public class Cleanup extends DatabaseComponent {
	/** 
	 * Constructor for creating Cleanup objects
	 * 
	 * @param category name that maps to 'category'.testDbPost files
	 * 
	 */
	public Cleanup(String category) {
		super(category + ".testDbPost");
	}
	/**
	 * Method to get the cleanup text for a particular testID
	 * @param testID test case ID
	 * @return getComponentText(testID)
	 */
	public String getCleanupText(String testID) {
		return getComponentText(testID);
	}

}
