package tools;


public class Objective extends DatabaseComponent {
	public Objective(String category) {
		super(category + ".testDbObjective");
	}
	public String getObjectiveText(String testID) {
		return getComponentText(testID);
	}
	public String getRequirementsInThisTest(String testID) {
		return getTraceKeys(testID);
	}

}
