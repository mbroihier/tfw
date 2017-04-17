package tools;

import java.io.File;

public class BuildHTMLSection {

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
