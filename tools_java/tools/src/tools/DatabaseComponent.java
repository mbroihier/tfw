package tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
/**
 * 
 * @author broihier
 * <pre>
 * Base/Abstract class for creating database information objects
 * </pre>
 */

public abstract class DatabaseComponent {
	private ArrayList<String> ids = new ArrayList<String>();
	private Map<String,String> componentText = new Hashtable<String,String>();
	private Map<String,String> traceKeys = new Hashtable<String,String>();

	/** 
	 * Constructor for creating database information objects
	 * 
	 * <pre>
	 * Pseudo-code:
	 * {@code
	 * open the component file;
	 * while there is information in the file {
	 *     read an ID line and add it to a persistent list;
	 *     if there is a ":" in the ID line {
	 *         store the trace IDs following the ":" into a traceKeys map by ID;
	 *     }
	 *     read the next line and store this text into a componentText map by ID;
	 * }
	 * }
	 * </pre>
	 * @param componentFilePath database file path that contains the component information
	 * 
	 */

	public DatabaseComponent (String componentFilePath) {
		Path filePath = Paths.get(componentFilePath);
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
					String[] parts = line.trim().split(":");
					String id = parts[0].replace(" ","");
					if (id.equals("")) continue;
					ids.add(id);
					if (parts.length > 1) {
						traceKeys.put(id, parts[1]);
					}
					componentText.put(id,reader.readLine().trim());
				}
			} catch (Exception e) {
				System.out.println("Error while reading " + componentFilePath + " file database: "+e);
			}
		}
	}
	/**
	 * Method to return the test case IDs associated with the test category
	 * @return identification IDs array list
	 */
	public ArrayList<String> getIds() {
		return ids;
	}
	/**
	 * Method to get the componentText associated with the test case ID
	 * @param id index for the component text requested
	 * @return identification text
	 */
	public String getComponentText(String id) {
		return componentText.get(id);
	}
	/**
	 * Method to return the trace keys associated with the test case ID
	 * @param id index for the trace text requested
	 * @return trace information text
	 */
	public String getTraceKeys(String id) {
		return traceKeys.get(id);
	}
	/**
	 * Method to return all the trace keys associated with the test category
	 * @return traceKeys - getter
	 */
	public Map<String,String> getAllTraceKeys() {
		return traceKeys;
	}
}
