package tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;
/**
 * 
 * @author broihier
 *<pre>
 *Class to read and handle the requirements database
 *</pre>
 */
public class Requirements {
	private Map<String,String> requirements = new Hashtable<String,String>();
	/** 
	 * Constructor for creating Requirements objects
	 * <pre>
	 * foreach requirement id in the array {
	 *     if the id isn't in the requirements map {
	 *         update the map;
	 *     }
	 * }
	 * </pre>
	 * @param requirementIDs an array of IDs to put into a requirements database
	 * 
	 */
	public Requirements(String[] requirementIDs){
		for (String r : requirementIDs) {
		    if (requirements.get(r) == null) { // if the requirement is not in the map - need to read off of disk
		    	update(r);
		    }
		}
	}
	/**
	 * Method to return the text associated with a requirements ID
	 * @param id key for the text
	 * @return requirements text from the map indexed by the key
	 */
	public String getRequirementsText(String id) {
		if (requirements.get(id) == null) {
			update(id);
		}
		if (requirements.get(id) == null) {
			return "Requirement ID missing from requirements database " + id;
		} else {
			return requirements.get(id);
		}
		
	}
	/**
	 * Method to add requirement to the requirements map
	 * <pre>
	 * {@code
	 * using the characters leading up to the first "-", open the REQ.<characters> file;
	 * if file exists {
	 *     while file not all read {
	 *         read a map key (requirement ID);
	 *         read the next line and store the line in the requirements map at the map key;
	 *     }
	 * } else {
	 *     write out an error and return;
	 * }
	 * </pre>
	 * @param r requirement or prefix for accessing the requirements database file
	 */
	private void update(String r) {
		Path filePath = Paths.get("REQ." + r.split("-")[0]);
		InputStream in = null;
		try {
			in = Files.newInputStream (filePath);
		} catch (Exception e) {
			System.out.println("Input stream creation error: " + e);
	 	}
		if (in != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			try {
				String line = null;
				while ((line = reader.readLine()) != null) {
					String id = line.trim();
					requirements.put(id,reader.readLine().trim());
				}
			} catch (Exception e) {
				System.out.println("Error while reading Requirements database: "+e);
			}
		}
	}
	/**
	 * Method that returns the requirements map
	 * @return requirements - map of requirements
	 */
	public Map<String,String> getRequirements() {
		return requirements;
	}
}
