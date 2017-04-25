package tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating book information objects
 * </pre>
 */

public class BookInformation {
	private String projectName;
	private String projectAcronym = "undefined project acronym";
	private ArrayList<String> chapters = new ArrayList<String>();
	
	/** 
	 * Constructor for creating book information objects
	 * 
	 * <pre>
	 * Pseudo-code:
	 * {@code
	 * read the PROJECT.testDb file to get the project name and acronym;
	 * if the bookPath is not empty {
	 *     read in a list of test categories;
	 * }
	 * 
	 * }
	 * </pre>
	 * @param bookPath path to the book file that defines the contents of the book
	 * 
	 * 
	 */

	public BookInformation(String bookPath) {
		Path filePath = Paths.get("PROJECT.testDb");
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
				if ((line = reader.readLine()) != null) {
					projectName = line.trim();
					Pattern acronym = Pattern.compile("[^"+Pattern.quote("(")+"]+"+Pattern.quote("(")+"([^"+Pattern.quote(")")+"]+).+$");
					Matcher matcher = acronym.matcher(projectName);
					if (matcher.matches()) {
					    projectAcronym = matcher.group(1);
					}
				}
			} catch (Exception e) {
				System.out.println("Error while reading project database: "+e);
			}
		}
		if (bookPath == null) return;
		filePath = Paths.get(bookPath);
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
					 chapters.add(line.trim());						
				}
			} catch (Exception e) {
				System.out.println("Error while reading book file: "+e);
			}
		}
	}
	/**
	 * Method to get the project name
	 * @return projectName getter
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * Method to get the project acronnym
	 * @return projectAcronym getter
	 */
	public String getProjectAcronym() {
		return projectAcronym;
	}
	/**
	 * Method to get the list of chapters/test categories/sections 
	 * @return chapters list of chapter names
	 */
	public ArrayList<String> getChapters() {
		return chapters;
	}

}
