package tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating category information objects
 * </pre>
 */
public class CategoryInformation {
		private String categoryDescriptionText = "";
		private String categoryTitle = "";
		/** 
		 * Constructor for creating category information objects
		 * 
		 * <pre>
		 * Pseudo-code:
		 * {@code
		 * open the testDbCategoryTitle file and read the category title;
		 * open the testDbCategoryDescription file and read the category category description;
		 * 
		 * }
		 * </pre>
		 * @param category name that maps to 'category'.testDbCategoryTitle/Description files
		 * 
		 */
		public CategoryInformation(String category) {
			Path filePath = Paths.get(category+".testDbCategoryTitle");
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
						categoryTitle = line.trim();						
					}
				} catch (Exception e) {
					System.out.println("Error while reading Category title database: "+e);
				}
			}
			filePath = Paths.get(category+".testDbCategoryDescription");
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
						categoryDescriptionText = line.trim();						
					}
				} catch (Exception e) {
					System.out.println("Error while reading Category description database: "+e);
				}
			}
		}
		/**
		 * Method to get the category title
		 * @return categoryTitle - getter
		 */
		public String getCategoryTitle() {
			return categoryTitle;
		}
		/**
		 * Method to get the category description
		 * @return categoryDescriptionText - getter
		 */
		public String getCategoryDescriptionText() {
			return categoryDescriptionText;
		}

}
