package tools;
/**
 * 
 * @author broihier
 * <pre>
 * Wrapper class for the main program that builds HTML books
 * </pre>
 */

public class BuildHTMLBook {
	/** 
	 * Main program for building HTML books
	 * 
	 * <pre>
	 * Pseudo-code:
	 * {@code
	 * create a book object passing args[0] as the book path;
	 * }
	 * 
	 * </pre>
	 * @param args[0] path to the book file that defines the contents of the book
	 * 
	 * 
	 */

	public static void main(String[] args) {
		try {
			Book book = new Book(args[0]);
			if (book != null) {
				System.out.println("HTML book successfully built");
			}
		} catch (Exception e) {
			System.out.println("HTML book failed to build: " + e);
		}
	}

}
