package tools;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public class RequirementsDatabase {
	Map<String,String> requirementTagsToTestCaseIds = new Hashtable<String,String>();
	Map<String,String> testCaseIdsToRequirements = new Hashtable<String,String>();
	
	public RequirementsDatabase() {};
	
	public void mergeRequirements(Requirements r) {
		for (String potentiallyNewKey : r.getRequirements().keySet()) {
			if (!requirementTagsToTestCaseIds.containsKey(potentiallyNewKey)) {
				requirementTagsToTestCaseIds.put(potentiallyNewKey, " ");
			}
		}
	}
	
	public void bidirectionallyMapItems (String testCaseId, String requirementsList) {
		testCaseIdsToRequirements.put(testCaseId, requirementsList);
		for (String requirementId : requirementsList.split(",")){
			String trimmedRequirementId = requirementId.replace(" ","");
			BuildHTMLTraceTables.logger.log(Level.FINER, "Requirement: " + trimmedRequirementId +" test case ID: " + testCaseId);
			requirementTagsToTestCaseIds.put(trimmedRequirementId, requirementTagsToTestCaseIds.get(trimmedRequirementId)+", "+testCaseId);
		}
	}
	
	public String getRequirementsTestedByCase (String testId) {
		return testCaseIdsToRequirements.get(testId);
	}
	
	public String getTestCasesTestingRequirements (String requirementId) {
		return requirementTagsToTestCaseIds.get(requirementId);
	}
	
	public Set<String> getRequirementIds() {
		return requirementTagsToTestCaseIds.keySet();
	}

	public Set<String> getTestCaseIds() {
		return testCaseIdsToRequirements.keySet();
	}
}
