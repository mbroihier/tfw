package tools;

import java.util.ArrayList;
/**
 * 
 * @author broihier
 *<pre>
 * Process the identification database
 *</pre>
 */
public class Identification extends DatabaseComponent{
	public Identification(String category) {
		super(category+".testDbID");
	}
	/**
	 * 
	 * @return identification IDs array list
	 */
	public ArrayList<String> getIdentificationIDs() {
		return getIds();
	}
	/**
	 * 
	 * @param id index for the identification text requested
	 * @return identification text
	 */
	public String getIdentificationText(String id) {
		return getComponentText(id);
	}
	
}
