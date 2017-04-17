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
 *Class to read and handle the requirements databae
 *</pre>
 */
public class Requirements {
	private Map<String,String> requirements = new Hashtable<String,String>();
	public Requirements(String[] requirementIDs){
		for (String r : requirementIDs) {
		    if (requirements.get(r) == null) { // if the requirement is not in the map - need to read off of disk
		    	update(r);
		    }
		}
	}
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
	public Map<String,String> getRequirements() {
		return requirements;
	}
}
