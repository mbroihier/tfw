package tools;

public class BuildHTMLBook {

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
