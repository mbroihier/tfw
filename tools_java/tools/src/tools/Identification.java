package tools;

import java.util.ArrayList;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating Identification objects
 * </pre>
 */
public class Identification extends DatabaseComponent{
	/** 
	 * Constructor for creating Identification objects
	 * 
	 * @param category name that maps to 'category'.testDbId files
	 * 
	 */
	public Identification(String category) {
		super(category+".testDbID");
	}
	/**
	 * Method to get the test case IDs for a test category
	 * @return getIds()
	 */
	public ArrayList<String> getIdentificationIDs() {
		return getIds();
	}
	/**
	 * Method to get the identification text for a particular testID
	 * @param id test case ID
	 * @return getComponentText(testID)
	 */
	public String getIdentificationText(String id) {
		return getComponentText(id);
	}
	
}
