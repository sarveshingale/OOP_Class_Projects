package mdi;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import library.Publication;
import library.Patron;
import library.Library;
import library.Video;
import library.Video.InvalidRuntimeException;

public class LibraryManager {
	
	private final static String pubFile = "Publication.txt";
	private final static String patFile = "Patron.txt";
	private final static String libraryName = "UTA Central Library";
	
	// Constructor for LibraryManager
	public LibraryManager(Library library) {
		
		this.library = library;
	}
	
	// Instance methods
	public void displayMenu() {
		System.out.println(libraryName + "\n\n");
		System.out.println("=========\nMain Menu\n=========\n\n");
				
		System.out.println("Publications");
		System.out.println("1) List\n" + "2) Add\n" + "3) Check out\n" + "4) Check in\n");
		System.out.println("Patrons");
		System.out.println("5) List\n" + "6) Add\n");
		System.out.println("Files");
		System.out.println("7) Load Test Data\n" + "8) Save\n" + "9) Load\n" + "0) exit\n\n");
		System.out.println("Selection: ");
	}
	
	public void addPub(Scanner sc) {
		System.out.println("Enter Publication Title");
		String title = sc.nextLine();
		System.out.println("Enter Publication Author");
		String author = sc.nextLine();
		System.out.println("Enter copyright year");
		int copyright = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Video Runtime (Press Enter for Book)");
		String runtimes = sc.nextLine();
		if(runtimes == "") {
			library.addPublication(new Publication(title, author, copyright));
		}
		else {
						
			int runtime = Integer.parseInt(runtimes);
			library.addPublication(new Video(title, author, copyright, runtime));
		}
	}
	
	public void checkOutPub(Scanner sc) {
		System.out.println("Which publication would you like to check out (Provide index): ");
		int index = sc.nextInt();
		sc.nextLine();
		System.out.println("Who are you? ");
		System.out.println(library.patronMenu());
		int patronindex = sc.nextInt();
		library.checkOut(index, patronindex);
	}
	
	public void checkInPub(Scanner sc) {
		System.out.println("Which publication would you like to check in (Provide index): ");
		int index = sc.nextInt();
		sc.nextLine();
		library.checkIn(index);
	}
	
	public  void displayPatronMenu() {
		System.out.println(library.patronMenu());
	}
	
	public void addPatron(Scanner sc) {
		System.out.println("Please enter your name");
		String name = sc.nextLine();
		System.out.println("Please enter your email");
		String email = sc.nextLine();
		Patron patron = new Patron(name, email);
		library.addPatron(patron);
	}
	
	public void populateTestData(Scanner sc) {
	
		// Populating publications from file
		try(BufferedReader publicationFile = new BufferedReader(new FileReader("mdi/Publications.txt"))) {
			String line;
			while((line = publicationFile.readLine()) != null) {
				
				String strLine = line;
				String[] lines;
				lines = strLine.split(",");
				if((lines.length != 3) && (lines.length !=4)) {
					System.err.println("Lines in publication file formatted incorrectly");
					continue;
				}
				
				// Constructing Publication Object
				if(lines.length == 3) {
					try {
						String title = lines[0].trim();
						String author = lines[1].trim();
						int copyright = Integer.parseInt(lines[2].trim());
						library.addPublication(new Publication(title, author, copyright));
					}
					catch(IllegalArgumentException e) {
						System.err.println(e.getMessage());
						continue;
					}
				}
				// Constructing Video Object
				else if(lines.length == 4) {
					try {
						String title = lines[0].trim();
						String author = lines[1].trim();
						int copyright = Integer.parseInt(lines[2].trim());
						int runtime = Integer.parseInt(lines[3].trim());
						try {
							library.addPublication(new Video(title, author, copyright, runtime));
						}
						catch(InvalidRuntimeException e) {
							System.err.println(e.getMessage());
						}
					}
					catch(IllegalArgumentException e) {
						System.err.println(e.getMessage());
						continue;
					}
				}
				
			}
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
		
		// Populating Patrons from file		
		try(BufferedReader patronFile = new BufferedReader(new FileReader("mdi/Patrons.txt"))) {
			String line;
			while((line = patronFile.readLine()) != null) {
				
				String strLine = line;
				String[] lines;
				lines = strLine.split(",");
				if(lines.length != 2) {
					System.err.println("Lines in Patrons file formatted incorrectly");
					continue;
				}
				
				// Constructing Publication Object	
				try {
					String name = lines[0].trim();
					String email = lines[1].trim();
					library.addPatron(new Patron(name, email));
				}
				catch(IllegalArgumentException e) {
					System.err.println(e.getMessage());
					continue;
				}
				
			}
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void saveLibrary(Scanner sc) {
		System.out.print("Please Enter Name of file for saving library(+ extension): ");
		String libraryFile = sc.nextLine();
		
		try(
			BufferedWriter bw = new BufferedWriter(new FileWriter(libraryFile));
		)
		{							
			library.save(bw);
			System.out.println("Saved Succesfully");
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}					
	}
		
	
	// MAIN METHOD
	public static void main(String[] args) {
	
	
	
		LibraryManager lm = new LibraryManager(new Library(libraryName));
		
		Scanner sc = new Scanner(System.in);
		int selection = 1;
		
		while(selection != 0) {
			
			lm.displayMenu();
			
			selection = sc.nextInt();
			sc.nextLine();
			switch(selection) {
				
				case 1 -> System.out.println(lm.library);
				case 2 -> lm.addPub(sc);
				case 3 -> lm.checkOutPub(sc);			
				case 4 -> lm.checkInPub(sc);
				case 5 -> lm.displayPatronMenu();
				case 6 -> lm.addPatron(sc);
				case 7 -> lm.populateTestData(sc);	
				case 8 -> lm.saveLibrary(sc);
				case 0 -> {}
				default -> System.out.println("Invalid Selection");	
			}
		
		}
		sc.close();
	}
	
	private Library library;
}