package library;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
/**
 * This class models a library.
 * @author Sarvesh Ingale
 * @version 1.0
 * @since 1.0
 */

public class Library {
	
	/**
	 * Constructor for class Library
	 *
	 * @version 1.0
	 * @param Name of the Library
	 * @since 1.0
	 */
	public Library(String name) {
		this.name = name;
	}
	
	public Library(BufferedReader br) {
		String strLine = line;
		String[] lines;
		lines = strLine.split(",");
		String title = lines[0].trim();
		String author = lines[1].trim();
		int copyright = Integer.parseInt(lines[2].trim());
		try {
			LocalDate current = LocalDate.now();
			int year = current.getYear();
			
			if(copyright < 1900 || copyright > year) {
				throw new IllegalArgumentException("copyright year was either < 1900 or greater than current year");
			}
			
		}
		catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		
		String loanedTo = lines[3].trim();
		if(loanedTo.equals("null")) {
			addPublication(new Publication(title, author, copyright));
		}
		else {
			String patronName = lines[4].trim();
			String patronEmail = lines[5].trim();
			//loanedTo = new Patron(patronName, patronEmail);
			addPublication(new Publication)
		}
	/**
	 * Adds a publication to the Library
	 *
	 * @param An Publication object
	 * @version 1.0
	 * @since 1.0
	 */
	public void addPublication(Publication publication) {
		
		publications.add(publication);
	}
	
	/**
	 * Adds a Patron to the Library
	 *
	 * @param A Patron Object
	 * @version 1.0
	 * @since 1.0
	 */
	public void addPatron(Patron patron) {
		
		patrons.add(patron);
	}
	
	/**
	 * Constructs a menu of all Patrons
	 *
	 * @return String containing the constructed menu
	 * @version 1.0
	 * @since 1.0
	 */
	public String patronMenu() {
		
		String result = "Patrons \n\n";
		int index = 0;
		for(Patron patron : patrons) {
			String line = index + ") " + patron + "\n";
			result = result + line;
			index++;
		}
		return result;
	}
	
	/**
	 * Checks out a Publication
	 *
	 * The checkOut in publication actually changes 
	 * the fields and is called here
	 *
	 * @param publicationIndex The index of the publication as shown in the toString()
	 * @version 1.0
	 * @since 1.0
	 */
	public void checkOut(int publicationIndex, int patronIndex) {
		try {
			Patron patron = patrons.get(patronIndex);
			Publication publication = publications.get(publicationIndex);
			publication.checkOut(patron);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Checks in a publication
	 *
	 * @param Index of publication to be checked in
	 * @version 1.0
	 */
	public void checkIn(int publicationIndex) {
		
		Publication publication = publications.get(publicationIndex);
		publication.checkIn();
	}
	
	/** Saves the Library data to a file
	 *
	 * @param publicationWriter writing to publication file
	 * @param patronWriter writing to patron file
	 * @version 1.0
	 */
	 public void save(BufferedWriter publicationWriter, BufferedWriter patronWriter) throws IOException {
		
		for(Publication publication : publications) {
			publication.save(publicationWriter);
			publicationWriter.newLine();
		}
		
		for(Patron patron : patrons) {
			patron.save(patronWriter);
			patronWriter.newLine();
		}
	
	 }
	 
	/**
	 * Overriden toString method to return menu of publications
	 *
	 * @return A menu of all publications
	 * @version 1.0
	 */
	@Override
	public String toString() {
		
		String result = name + "\n\n";
		int index = 0;
		for(Publication publication : publications) {
			String line = index + ") " + publication + "\n";
			result = result + line;
			index++;
		}
		
		return result;
	}
	
	private String name;
	private ArrayList<Publication> publications = new ArrayList<Publication>();
	private ArrayList<Patron> patrons = new ArrayList<Patron>();
}