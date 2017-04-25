package tools;

import java.util.ArrayList;
/**
 * 
 * @author broihier
 * <pre>
 * Class for creating TestDatabase objects
 * </pre>
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
	 * Constructor for creating TestDatabase objects
	 * 
	 * @param category category/section/chapter of test procedures
	 * @param project project being tested
	 * 
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
	/**
	 * Method to get the title of the test category
	 * @return categoryInformation.getCategoryTitle();
	 */
	public String getCategoryTitle() {
		return categoryInformation.getCategoryTitle();
	}
	/**
	 * Method to get the description of the test category
	 * @return categoryInformation.getCategoryDescriptionText();
	 */
	public String getCategoryDescriptionText() {
		return categoryInformation.getCategoryDescriptionText();
	}
	/**
	 * Method to get the list of test IDs for a category
	 * @return identification.getIdentificationIDs();
	 */
	public ArrayList<String> getIdentificationIDs() {
		return identification.getIdentificationIDs();
	}
	/**
	 * Method to get the test case title
	 * @param testID test case identification
	 * @return identification.getIdentificationTest(testID);
	 */
	public String getIdentificationText(String testID) {
		return identification.getIdentificationText(testID);
	}
	/**
	 * Method to get the test objective text
	 * @param testID test case identification
	 * @return objective.getObjectiveText(testID);
	 */
	public String getObjectiveText(String testID) {
		return objective.getObjectiveText(testID);
	}
	/**
	 * Method to get the requirements for a test case
	 * @param testID test case identification
	 * @return objective.getRequirementsInThisTest(testID);
	 */
	public String getRequirementsInThisTest(String testID) {
		return objective.getRequirementsInThisTest(testID);
	}
	/**
	 * Method to get requirements text associated with the requirement ID
	 * @param requirementID requirement ID
	 * @return requirements.getRequirementsText(requirementID);
	 */
	public String getRequirementsText(String requirementID) {
		return requirements.getRequirementsText(requirementID);
	}
	/**
	 * Method to get the text associate with the setup of a test case
	 * @param testID test case identification
	 * @return setup.getSetupText(testID);
	 */
	public String getSetupText(String testID) {
		return setup.getSetupText(testID);
	}
	/**
	 * Method to get the text associated with the procedure steps
	 * @param testID test case identification
	 * @return procedures.getProcedureText(testID);
	 */
	public String getProcedureText(String testID) {
		return procedures.getProcedureText(testID);
	}
	/**
	 * Method to get the expected results text associated with a test case
	 * @param testID test case identification
	 * @return expectedResults.getExpectedResultsTest(testID);
	 */
	public String getExpectedResultsText(String testID) {
		return expectedResults.getExpectedResultsText(testID);
	}
	/**
	 * Method to get the test results of a test case
	 * @param testID test case identification
	 * @return results.getResultsText(testID);
	 */
	public String getResultsText(String testID) {
		return results.getResultsText(testID);
	}
	/**
	 * Method to get the cleanup steps for a test case
	 * @param testID test case identification
	 * @return cleanup.getCleanupText(testID);
	 */
	public String getCleanupText(String testID) {
		return cleanup.getCleanupText(testID);
	}
}
