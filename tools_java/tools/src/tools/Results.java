package tools;


public class Results extends DatabaseComponent {
	public Results(String category) {
		super(category+".testDbResults");
	}
	public String getResultsText(String testID) {
		return getComponentText(testID);
	}

}
