package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class BuildHTMLTraceTables {
	static final Logger logger = Logger.getLogger("BuildHTMLTraceTables");
	public static void main(String[] args) {
		if (System.getProperty("java.util.logging.config.class") == null && 
				System.getProperty("java.util.logging.config.file") == null) {
			try {
				Logger.getLogger("BuildHTMLTraceTables").setLevel(Level.SEVERE);
				Logger.getLogger("BuildHTMLTraceTables").setUseParentHandlers(true);
				final int LOG_ROTATION_COUNT = 2;
				Handler handler = new FileHandler("%h/BuildHTMLTraceTables.log",0, LOG_ROTATION_COUNT);
				Logger.getLogger("BuildHTMLTraceTables").addHandler(handler);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Can't create log file handler", e);
			}
		}
		try {
			String bookPath = args[0];
			logger.log(Level.INFO, "Building trace tables for book: " + bookPath);
			BookInformation bookInformation = new BookInformation(bookPath);
			ArrayList<String> categories = bookInformation.getChapters();
			RequirementsDatabase requirementsDatabase = new RequirementsDatabase();
			String project = bookInformation.getProjectAcronym();
			String[] pseudoIds = {project};
			requirementsDatabase.mergeRequirements((new Requirements(pseudoIds)));
			for (String chapter : categories) {
				Map <String,String> traceInformation = ((new Objective(chapter)).getAllTraceKeys());
				for (String testCaseId : traceInformation.keySet()) {
					requirementsDatabase.bidirectionallyMapItems(testCaseId, traceInformation.get(testCaseId));
				}
			}
			TreeSet<String> sortedRequirementIds = new TreeSet<String>(requirementsDatabase.getRequirementIds());
			DOMizeTemplate DOMizedTemplate = new DOMizeTemplate("../tools_configuration/REQ_TEST_ID_TEMPLATE.html","./req_test_id.html");
			Document doc = DOMizedTemplate.getDoc();
			Element table = Utilities.findElementById(doc, "traceTable");
			for (String requirementId : sortedRequirementIds){
				Element row = doc.createElement("tr");
				Element cell = doc.createElement("td");
				cell.appendChild(doc.createTextNode(requirementId));
				row.appendChild(cell);
				cell = doc.createElement("td");
				Set<String> testCases = new HashSet<String>(Arrays.asList(requirementsDatabase.getTestCasesTestingRequirements(requirementId).replace(" ", "").replaceFirst(",", "").split(",")));
				TreeSet<String> sortedTestCases = new TreeSet<String>(testCases);
				cell.appendChild(doc.createTextNode(String.join(", ", sortedTestCases)));
				row.appendChild(cell);
				table.appendChild(row);
			}
			DOMizedTemplate.writeDocument();
			DOMizedTemplate = new DOMizeTemplate("../tools_configuration/TEST_ID_REQ_TEMPLATE.html","./test_id_req.html");
			doc = DOMizedTemplate.getDoc();
			table = Utilities.findElementById(doc, "traceTable");
			TreeSet<String> sortedTestCaseIds = new TreeSet<String>(requirementsDatabase.getTestCaseIds());
			for (String testCaseId : sortedTestCaseIds){
				Element row = doc.createElement("tr");
				Element cell = doc.createElement("td");
				cell.appendChild(doc.createTextNode(testCaseId));
				row.appendChild(cell);
				cell = doc.createElement("td");
				cell.appendChild(doc.createTextNode(requirementsDatabase.getRequirementsTestedByCase(testCaseId).replaceAll(" ", "").replace(",",", ")));
				row.appendChild(cell);
				table.appendChild(row);
			}
			DOMizedTemplate.writeDocument();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "HTML trace table build failed: " + e, e);
		}
	}

}
