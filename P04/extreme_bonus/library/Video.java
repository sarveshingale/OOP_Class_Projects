package library;
import java.time.Duration;
// Creating sublass Video by inheriting from Publication

public class Video extends Publication { 
	
	public Video(String title, String author, int copyright, int runtime) {
		super(title, author, copyright);
		this.runtime = Duration.ofMinutes(runtime);
	}
	
	@Override
	public String toString() {
		
		String result = toStringBuilder("Book", " runtime " + runtime.toMinutes() + " minutes ");
		return result;
	}
	private Duration runtime;
}