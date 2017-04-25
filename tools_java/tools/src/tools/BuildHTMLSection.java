package tools;

import java.io.File;
/**
 * 
 * @author broihier
 * <pre>
 * Wrapper class for the main program that builds HTML chapters/sections/test categories
 * </pre>
 */

public class BuildHTMLSection {
	/** 
	 * Main program for building HTML chapters
	 * 
	 * <pre>
	 * Pseudo-code:
	 * {@code
	 * create a book information object passing in an empty book file path;
	 * create a chapter object passing args[0] as the name;
	 * }
	 * </pre>
	 * @param args[0] path to a chapter/category/section name
	 * 
	 * 
	 */

	public static void main(String[] args) {
		try {
			String chapterName = args[0];
			new File(chapterName).mkdir();
			BookInformation bookInformation = null;
			try {
				bookInformation = new BookInformation(null);
			} catch (Exception e) {
				System.out.println("While getting book information: " + e);
			}
			if (bookInformation != null) {
			    TestDatabase database = new TestDatabase(chapterName,bookInformation.getProjectAcronym());
			    Chapter chapter = new Chapter(chapterName,database);
			    if (chapter != null) {
				    System.out.println("HTML section successfully built");
			    }
			}
		} catch (Exception e) {
			System.out.println("HTML section failed to build");
		}
	}

}
