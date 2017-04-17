package tools;


public class Setup extends DatabaseComponent{
	public Setup(String category) {
		super(category+".testDbPre");
	}
	public String getSetupText(String testID) {
		return getComponentText(testID);
	}
}
