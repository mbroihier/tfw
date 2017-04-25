package tools;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating Setup objects
 * </pre>
 */
public class Setup extends DatabaseComponent{
	/** 
	 * Constructor for creating Setup objects
	 * 
	 * @param category name that maps to 'category'.testDbPre files
	 * 
	 */
	public Setup(String category) {
		super(category+".testDbPre");
	}
	/**
	 * Method to get the setup text for a particular testID
	 * @param testID test case ID
	 * @return getComponentText(testID)
	 */
	public String getSetupText(String testID) {
		return getComponentText(testID);
	}
}
