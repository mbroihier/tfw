package tools;

import java.util.ArrayList;

/**
 * 
 * @author broihier
 *<pre>
 *Class that holds a database for a test category
 *</pre>
 */
public class TestDatabase {
	private CategoryInformation categoryInformation = null;
	private Identification identification = null;
	private Requirements requirements = null;
	private Objective objective = null;
	private Setup setup = null;
	private Procedures procedures = null;
	private ExpectedResults expectedResults = null;
	private Results results = null;
	private Cleanup cleanup = null;
	/**
	 * 
	 * @param category name of database
	 */
	public TestDatabase (String category, String project) {
		categoryInformation = new CategoryInformation(category);
		identification = new Identification(category);
		String [] ids = {project};
		requirements = new Requirements(ids);
		objective = new Objective(category);
		setup = new Setup(category);
		procedures = new Procedures(category);
		expectedResults = new ExpectedResults(category);
		results = new Results(category);
		cleanup = new Cleanup(category);
	}
	public String getCategoryTitle() {
		return categoryInformation.getCategoryTitle();
	}
	public String getCategoryDescriptionText() {
		return categoryInformation.getCategoryDescriptionText();
	}
	/**
	 * 
	 * @return test identification IDs in this test category
	 */
	public ArrayList<String> getIdentificationIDs() {
		return identification.getIdentificationIDs();
	}
	/**
	 * 
	 * @param testID test identification text case identifier
	 * @return test case title / description
	 */
	public String getIdentificationText(String testID) {
		return identification.getIdentificationText(testID);
	}
	public String getObjectiveText(String testID) {
		return objective.getObjectiveText(testID);
	}
	public String getRequirementsInThisTest(String testID) {
		return objective.getRequirementsInThisTest(testID);
	}
	public String getRequirementsText(String testID) {
		return requirements.getRequirementsText(testID);
	}
	public String getSetupText(String testID) {
		return setup.getSetupText(testID);
	}
	public String getProcedureText(String testID) {
		return procedures.getProcedureText(testID);
	}
	public String getExpectedResultsText(String testID) {
		return expectedResults.getExpectedResultsText(testID);
	}
	public String getResultsText(String testID) {
		return results.getResultsText(testID);
	}
	public String getCleanupText(String testID) {
		return cleanup.getCleanupText(testID);
	}
}
