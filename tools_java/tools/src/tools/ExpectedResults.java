package tools;


public class ExpectedResults extends DatabaseComponent {
	public ExpectedResults(String category) {
		super(category+".testDbExpectedResults");
	}
	public String getExpectedResultsText(String testID) {
		return getComponentText(testID);
	}
}
