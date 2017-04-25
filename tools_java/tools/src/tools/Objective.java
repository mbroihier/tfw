package tools;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating Objective objects
 * </pre>
 */
public class Objective extends DatabaseComponent {
	/** 
	 * Constructor for creating Objective objects
	 * 
	 * @param category name that maps to 'category'.testDbObjective files
	 * 
	 */
	public Objective(String category) {
		super(category + ".testDbObjective");
	}
	/**
	 * Method to get the objective text for a particular testID
	 * @param testID test case ID
	 * @return getComponentText(testID)
	 */
	public String getObjectiveText(String testID) {
		return getComponentText(testID);
	}
	/**
	 * Method to get the trace keys associated with a particular testID
	 * @param testID test case ID
	 * @return getTraceKeys(testID)
	 */
	public String getRequirementsInThisTest(String testID) {
		return getTraceKeys(testID);
	}

}
