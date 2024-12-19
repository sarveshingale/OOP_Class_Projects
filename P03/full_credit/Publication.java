import java.time.LocalDate;

public class Publication {
	
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
	
	// Set due date to 14 days from now
	public void checkOut(String patron) {
		
	}
	
	public void checkIn() {
		
	}
	
	@Override
	public String toString() {
		
		return "";
	}
	
	private String title;
	private String author;
	private int copyright; // This is year
	private String loanedTo; // Name of person who borrowed book or null
	private LocalDate dueDate; // Will be set in method checkout()
	
}