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

public abstract class DatabaseComponent {
	private ArrayList<String> ids = new ArrayList<String>();
	private Map<String,String> componentText = new Hashtable<String,String>();
	private Map<String,String> traceKeys = new Hashtable<String,String>();
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
	 * 
	 * @return identification IDs array list
	 */
	public ArrayList<String> getIds() {
		return ids;
	}
	/**
	 * 
	 * @param id index for the component text requested
	 * @return identification text
	 */
	public String getComponentText(String id) {
		return componentText.get(id);
	}
	/**
	 * 
	 * @param id index for the trace text requested
	 * @return trace information text
	 */
	public String getTraceKeys(String id) {
		return traceKeys.get(id);
	}
	public Map<String,String> getAllTraceKeys() {
		return traceKeys;
	}
}
