package library;
import java.util.ArrayList;

public class Library {
	
	public Library(String name) {
		this.name = name;
	}
	
	public void addPublication(Publication publication) {
		
		publications.add(publication);
	}
	
	public void addPatron(Patron patron) {
		
		patrons.add(patron);
	}
	
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