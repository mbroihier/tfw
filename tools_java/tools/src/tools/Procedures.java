package tools;


public class Procedures extends DatabaseComponent {
	public Procedures(String category) {
		super(category+".testDbProcedures");
	}
	public String getProcedureText(String testID) {
		return getComponentText(testID);
	}
}
