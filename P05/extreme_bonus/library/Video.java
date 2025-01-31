package library;
import java.time.Duration;

/**
 * This class models a Video
 *
 * @author Sarvesh Ingale
 * @version 1.0
 * @since 1.0
 */

public class Video extends Publication {
	
	/**
	 * Custom exception for invalid runtime
	 *
	 * @author Sarvesh Ingale
	 * @version 1.0
	 * @since 1.0
	 */
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
	/**
	 * Instances Video object
	 *
	 * @param title Title of the video.
	 * @param author Author of the video.
	 * @param runtime Duration of the video
	 */
	public Video(String title, String author, int copyright, int runtime) {
		super(title, author, copyright);
		if(runtime <= 0) {
			throw new InvalidRuntimeException(title, runtime);
		}
		this.runtime = Duration.ofMinutes(runtime);
	}
	
	/**
	 * Overriden toString method that calls on toStringBuilder
	 *
	 * @return Formatted String obtained from StringBuilder
	 */
	@Override
	public String toString() {
		
		String result = toStringBuilder("Video", ", runtime " + runtime.toMinutes() + " minutes ");
		return result;
	}
	private Duration runtime;
}