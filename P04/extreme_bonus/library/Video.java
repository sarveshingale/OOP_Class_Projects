package library;
import java.time.Duration;
// Creating sublass Video by inheriting from Publication

public class Video extends Publication {
		
	public class InvalidRuntimeException extends ArithmeticException {
		public InvalidRuntimeException() {
			super();
		}
		public InvalidRuntimeException(String message) {
			super(message);
		}
		public InvalidRuntimeException(String title, int runtime) {
			super(String.format("%s has invalid runtime %d", title, runtime));		
		}
	}
	public Video(String title, String author, int copyright, int runtime) {
		super(title, author, copyright);
		if(runtime <= 0) {
			throw new InvalidRuntimeException(title, runtime);
		}
		this.runtime = Duration.ofMinutes(runtime);
	}
	
	@Override
	public String toString() {
		
		String result = toStringBuilder("Video", ", runtime " + runtime.toMinutes() + " minutes ");
		return result;
	}
	private Duration runtime;
}