package library;
import java.util.ArrayList;

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
		publication.checkin();
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