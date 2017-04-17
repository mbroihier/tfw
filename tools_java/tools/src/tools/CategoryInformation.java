package tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CategoryInformation {
		private String categoryDescriptionText = "";
		private String categoryTitle = "";
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
		public String getCategoryTitle() {
			return categoryTitle;
		}
		public String getCategoryDescriptionText() {
			return categoryDescriptionText;
		}

}
