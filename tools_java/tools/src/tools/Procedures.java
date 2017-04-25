package tools;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating Procedures objects
 * </pre>
 */
public class Procedures extends DatabaseComponent {
	/** 
	 * Constructor for creating Procedures objects
	 * 
	 * @param category name that maps to 'category'.testDbProcedures files
	 * 
	 */
	public Procedures(String category) {
		super(category+".testDbProcedures");
	}
	/**
	 * Method to get the procedure text for a particular testID
	 * @param testID test case ID
	 * @return getComponentText(testID)
	 */
	public String getProcedureText(String testID) {
		return getComponentText(testID);
	}
}
