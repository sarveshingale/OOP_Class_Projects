import java.util.Scanner;
public class LibraryManager {
	
	public static void main(String[] args) {
		
		Library library = new Library("UTA Central Library");
		
		// Adding publications
		Publication book1 = new Publication("Harry Potter", "JK Rowling", 2001);
		library.addPublication(book1);
		Publication book2 = new Publication("Geronimo Stilton", "Mouse", 2014);
		library.addPublication(book2);
		Publication book3 = new Publication("Lord of the Rings", "R.R Tolken", 2002);
		library.addPublication(book3);
		
		// Adding patrons
		Patron patron1  = new Patron("John Corn", "johncorn@flux.com");
		library.addPatron(patron1);
		Patron patron2 = new Patron("Jelly Felly", "jellyfelly@flux.com");
		library.addPatron(patron2);
		Patron patron3 = new Patron("Stall Mall", "stallmall@flux.com");
		library.addPatron(patron3);
		
		System.out.println(library);
		System.out.println("\n\nWhich book would you like to check out (Provide index): ");
		Scanner sc = new Scanner(System.in);
		int index = sc.nextInt();
		sc.nextLine();
		System.out.println("Who are you? ");
		System.out.println(library.patronMenu());
		int patronindex = sc.nextInt();
		library.checkOut(index, patronindex);
		System.out.println(library);	
		sc.close();
	}
}