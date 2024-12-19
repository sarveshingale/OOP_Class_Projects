import java.util.ArrayList;

public class Library {
	
	public Library(String name) {
		this.name = name;
	}
	
	public void addPublication(Publication publication) {
		
		publications.add(publication);
	}
	
	public void checkOut(int publicationIndex, String patron) {
		try {
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
			String line = index + ") " + publication;
			result = result + line;
			index++;
		}
		
		return result;
	}
	
	private String name;
	private ArrayList<Publication> publications = new ArrayList<Publication>();
}