package mdi;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import library.Publication;
import library.Patron;
import library.Library;
import library.Video;
import library.Video.InvalidRuntimeException;
public class LibraryManager {
	
	private final static String pubFile = "Publication.txt";
	private final static String patFile = "Patron.txt";
	
	public static void main(String[] args) {
		
		Library library = new Library("UTA Central Library");
		
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