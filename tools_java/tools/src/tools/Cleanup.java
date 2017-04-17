package tools;


public class Cleanup extends DatabaseComponent {
	public Cleanup(String category) {
		super(category + ".testDbPost");
	}
	public String getCleanupText(String testID) {
		return getComponentText(testID);
	}

}
