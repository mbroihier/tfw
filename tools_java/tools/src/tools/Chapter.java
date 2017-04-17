package tools;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Chapter {
		private Document doc = null;
		private String status = "UNTESTED";
		private String title;
		public Chapter (String category, TestDatabase database) {
			DOMizeTemplate DOMizedTemplate = new DOMizeTemplate("../tools_configuration/TOC_Template.html","./"+category+"_toc.html"); 
			this.doc = DOMizedTemplate.getDoc();
			// insert section title into test case DOM
			Element element = Utilities.findElementById(doc,"title");
			this.title = database.getCategoryTitle();
			element.appendChild(doc.createTextNode(database.getCategoryTitle()));
			
			// insert section description into test case DOM
			element = Utilities.findElementById(doc,"description");
			element.appendChild(doc.createTextNode(database.getCategoryDescriptionText()));
			
			// insert test cases
			
			element = Utilities.findElementById(doc,"list");
			for (String cases : database.getIdentificationIDs()) {
				String status = (new TestCase(cases,category,database)).getStatus();
				Element listItem = doc.createElement("li");
				Element anchor = doc.createElement("a");
				anchor.setAttribute("href", category+"/"+cases+".html");
				if (status.equals("PASSED")) {
					anchor.setAttribute("style", "color: 00F000");
				} else if (status.equals("FAILED")) {
					anchor.setAttribute("style", "color: FF0000");
				}
				anchor.appendChild(doc.createTextNode(cases + " - " + database.getIdentificationText(cases)));
				listItem.appendChild(anchor);
				element.appendChild(listItem);
				updateChapterStatus(status);
			}
			
			// write TOC DOM
			DOMizedTemplate.writeDocument();
		}
		public String getStatus() {
			return status;
		}
		public String getTitle() {
			return title;
		}
		private void updateChapterStatus (String status) {
			if (status.equals("UNTESTED")) {
				if (! this.status.equals("UNTESTED")){
					this.status = "NOTDONE";
				}
			} else {
				if (status.equals("FAILED")) {
					this.status = "FAILED";
				} else {
					if (this.status.equals("UNTESTED")){
						this.status = "PASSED";
					}
				}
			}
		}
}
