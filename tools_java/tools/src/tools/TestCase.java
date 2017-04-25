package tools;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author broihier
 *<pre>
 *Class for creating TestCase objects
 *</pre>
 */
public class TestCase {
	private Document doc = null;
	private String status = "UNTESTED";
	private DOMizeTemplate DOMizedTemplate;
	/** 
	 * Constructor for creating Test Case objects
	 * 
	 * <pre>
	 * create a DOM using the TEST_CASE_TEMPLATE.html file;
	 * insert the test case ID into the DOM;
	 * insert the title into the DOM;
	 * foreach requirement associated with this test case {
	 *     put the expanded requirement into the objective requirements list of the DOM;
	 * }
	 * put the objective information in the DOM;
	 * put the setup information into the DOM;
	 * put the procedure steps (including bullets) into the DOM;
	 * put the expected results into the DOM;
	 * put the results into the DOM;
	 * set the test case status to the value of the results;
	 * put the cleanup steps into the DOM;
	 * put the requirement tags into the DOM;
	 * write the expanded DOM to disk;
	 * </pre>
	 * 
	 * @param id test case ID
	 * @param category test category (chapter/section)
	 * @param database test database for the test category
	 * 
	 */
	public TestCase (String id, String category, TestDatabase database) {
		this.DOMizedTemplate = new DOMizeTemplate("../tools_configuration/TEST_CASE_TEMPLATE.html","./"+category+"/"+id+".html");
		this.doc = DOMizedTemplate.getDoc();
		// insert identification into test case DOM
		Element element = Utilities.findElementById(doc,"testCaseId");
		element.appendChild(doc.createTextNode(id));
		
		element = Utilities.findElementById(doc,"title");
		element.appendChild(doc.createTextNode(database.getIdentificationText(id)));
		
		// insert objective into the test case DOM
		element = Utilities.findElementById(doc,"requirementsList");
		for (String requirement : database.getRequirementsInThisTest(id).replaceAll(" ", "").split(",")){
			Element listItemElement = doc.createElement("li");
			listItemElement.appendChild(doc.createTextNode(database.getRequirementsText(requirement)));
			element.appendChild(listItemElement);
		}
		Element paragraphElement = doc.createElement("p");
		paragraphElement.appendChild(doc.createTextNode("This test case is intended to verify the following implied or specified requirements:"));
		element = Utilities.findElementById(doc,"objective");
		element.insertBefore(paragraphElement, element.getFirstChild());
		paragraphElement = doc.createElement("p");
		paragraphElement.appendChild(doc.createTextNode(database.getObjectiveText(id)));
		element.appendChild(paragraphElement);
		
		// insert setup into the test case DOM
		element = Utilities.findElementById(doc,"setup");
		for (String setup : database.getSetupText(id).split("/hr")) {
			Element listItem = doc.createElement("li");
			listItem.appendChild(doc.createTextNode(setup));
			element.appendChild(listItem);
		}
		
		// insert procedure steps into the test case DOM
		element = Utilities.findElementById(doc,"actions");
		for (String step : database.getProcedureText(id).split("/step")){
			Element listItem = doc.createElement("li");
			Element unOrderedList = doc.createElement("ul");
			if (step.equals("")) continue;
			boolean procedureStep = true;
			for (String bulletText : step.split("/bullet")) {
				if (procedureStep) {
					// this is the procedure step - add it in
					listItem.appendChild(doc.createTextNode(bulletText.replace("\\step", "")));
					procedureStep = false;
				} else {
					Element bulletListItem = doc.createElement("li");
					bulletListItem.appendChild(doc.createTextNode(bulletText.replace("\\bullet","").replace("\\step", "")));
					unOrderedList.appendChild(bulletListItem);
				}
			}
			if (unOrderedList.hasChildNodes()) {
				unOrderedList.setAttribute("type", "square");
				listItem.appendChild(unOrderedList);
			}
			element.appendChild(listItem);
		}
		
		// insert expected results into the test case DOM
		element = Utilities.findElementById(doc,"expected");
		element.appendChild(doc.createTextNode(database.getExpectedResultsText(id)));
		
		// insert results into the test case DOM
		element = Utilities.findElementById(doc,"results");
		this.status = database.getResultsText(id);
		element.appendChild(doc.createTextNode(this.status));
		
		// insert cleanup into the test case DOM
		element = Utilities.findElementById(doc,"cleanup");
		for (String setup : database.getCleanupText(id).split("/hr")) {
			Element listItem = doc.createElement("li");
			listItem.appendChild(doc.createTextNode(setup));
			element.appendChild(listItem);
		}
		
		// insert reauirement IDs into the test case DOM
		element = Utilities.findElementById(doc,"requirements");
		element.appendChild(doc.createTextNode(database.getRequirementsInThisTest(id)));

		// write updated DOM
		DOMizedTemplate.writeDocument();
	}
	/**
	 * Method to return the status of a test case
	 * @return status - getter
	 */
	public String getStatus() {
		return status;
	}

}
