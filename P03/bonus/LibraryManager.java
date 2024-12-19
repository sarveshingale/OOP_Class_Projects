import java.util.Scanner;
public class LibraryManager {
	
	public static void main(String[] args) {
		
		Library library = new Library("UTA Central Library");
		//System.out.println(library);
		Publication book1 = new Publication("Harry Potter", "JK Rowling", 2001);
		library.addPublication(book1);
		Publication book2 = new Publication("Geronimo Stilton", "Mouse", 2014);
		library.addPublication(book2);
		Publication book3 = new Publication("Lord of the Rings", "R.R Tolken", 2002);
		library.addPublication(book3);
		System.out.println(library);
		System.out.println("\n\nWhich book would you like to check out (Provide index): ");
		Scanner sc = new Scanner(System.in);
		int index = sc.nextInt();
		sc.nextLine();
		System.out.println("Who are you? ");
		String patron = sc.nextLine();
		library.checkOut(index, patron);
		System.out.println(library);	
		sc.close();
	}
}