package tools;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating test procedure books
 * </pre>
 */

public class Book {
	private TestDatabase database = null;
	private Chapter chapter = null;
	private BookInformation bookInformation = null;
	private ArrayList<String> categories = new ArrayList<String>();
	private Document doc = null;
	
	/** 
	 * Class constructor for creating test procedure books
	 * 
	 * <pre>
	 * Pseudo-code:
	 * {@code
	 * create a bookInformation object using the bookPath to access the categories/sections/chapters of the book;
	 * create a DOMizedTemplate with the book table of contents;
	 * fill in the title of the book;
	 * foreach chapter of the book {
	 *     create a chapter based database;
	 *     create a chapter of the book;
	 *     based on the test results posted in the chapter, update the table of chapter contents color to display the test name and status;
	 * }
	 * write out the book's table of contents
	 * }
	 * </pre>
	 * @param bookPath path to the book file that defines the contents of the book
	 * 
	 * 
	 */
	public Book (String bookPath) {
		bookInformation = new BookInformation(bookPath);
		categories = bookInformation.getChapters();
		String projectName = bookInformation.getProjectName();
		DOMizeTemplate DOMizedTemplate = new DOMizeTemplate("../tools_configuration/BOOK_TOC_TEMPLATE.html","./"+projectName+"_toc.html");
		this.doc = DOMizedTemplate.getDoc();
		
		// insert book title into DOM
		Element element = Utilities.findElementById(doc,"bookTitle");
		element.appendChild(doc.createTextNode(projectName + " - System Level Test Procedures"));
		
		// insert chapters into book DOM
		element = Utilities.findElementById(doc,"sectionList");
		Node firstListItem = element.getFirstChild();
		for (String chapterName : categories) {
			new File(chapterName).mkdir();
			database = new TestDatabase(chapterName,bookInformation.getProjectAcronym());
			chapter = new Chapter(chapterName,database);
			String status = chapter.getStatus();
			String title = chapter.getTitle();
			Element listItem = doc.createElement("li");
			Element anchor = doc.createElement("a");
			anchor.setAttribute("href", chapterName+"_toc.html");
			if (status.equals("PASSED")) {
				anchor.setAttribute("style", "color: 00F000");
			} else if (status.equals("FAILED")) {
				anchor.setAttribute("style", "color: FF0000");
			}
			anchor.appendChild(doc.createTextNode(chapterName + " - " + title));
			listItem.appendChild(anchor);
			element.insertBefore(listItem,firstListItem);
		}
		DOMizedTemplate.writeDocument();
	}

}
