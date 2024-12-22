package library;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * This class models a Publication
 *
 * @author Sarvesh Ingale
 * @version 1.0
 * @since 1.0
 */
public class Publication {
	
	/**
	 * Instance a publication object
	 *
	 * @param title Title of the publication
	 * @param author Author of the Publication
	 * @param copyright The copyright year of the publication
	 * @version 1.0
	 */
	public Publication(String title, String author, int copyright){
		this.title = title;
		this.author = author;
		try {
			LocalDate current = LocalDate.now();
			int year = current.getYear();
			
			if(copyright < 1900 || copyright > year) {
				throw new IllegalArgumentException("copyright year was either < 1900 or greater than current year");
			}
			
			
			this.copyright = copyright;
		}
		catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}				
	}
	
		
	
	/**
	 * Checks out a publicaton to a Patron
	 *
	 * @param patron The borrower for the publication
	 * @version 1.0
	 */
	 // Publication is checked out for 14 days
	public void checkOut(Patron patron) {
		
		loanedTo = patron;
		dueDate = LocalDate.now();
		dueDate = dueDate.plusDays(14);
	}
	
	/**
	 * Checks in borrowed Publication
	 *
	 * @version 1.0
	 */
	public void checkIn() {
		loanedTo = null;
		dueDate = null;
	}
	
	/** Saves the publication data to a file
	 *
	 * @param bw BufferedWriter to write to file
	 * @version 1.0
	 */
	 public void save(BufferedWriter bw) throws IOException {
		
		String writeData = title + "\n" + author + "\n" + copyright + "\n";
		if(loanedTo == null) {
			writeData = writeData + "null\n" + "null\n" + "null"; 
			bw.write(writeData);
		}
		else {
			bw.write(writeData);
			loanedTo.save(bw);
			String sdate = dueDate.toString();
			bw.write(sdate);
		}
	
	 }
	
	/**
	 * Construct toString for Publication and its subclasses
	 *
	 * @param pre First part of the String
	 * @param mid Last part of the String
	 * @return String that has appropriate formatting
	 * @version 1.0
	 */
	protected String toStringBuilder(String pre, String mid) {
		String result = pre + " " + title + " by " + author + ", " + copyright + " " + mid;
		if(dueDate != null) {
			result = result + "\nLoaned to " + loanedTo + " until " + dueDate;
		}
		return result;
	}
	
	/**
	 * Overidden toString method that formats Book Publication
	 *
	 * @return String that contains details of a publication
	 * @version 1.0
	 */
	@Override
	public String toString() {
		
		String result = toStringBuilder("Book", "");
		return result;
	}
	
	private String title;
	private String author;
	private int copyright; // This is year
	private Patron loanedTo; // Name of person who borrowed book or null
	private LocalDate dueDate; // Will be set in method checkout()
	
}