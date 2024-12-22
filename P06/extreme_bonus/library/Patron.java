package library;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * This class models a Patron
 *
 * @author Sarvesh Ingale
 * @version 1.0
 * @since 1.0
 */
public class Patron {
	
	/**
	 * Creates an instance of Patron
	 *
	 * @param name Name of the Patron
	 * @param email Email of the Patron
	 * @version 1.0
	 */
	public Patron(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Patron(BufferedReader br) {
		
	}
	/** Saves the patron data to a file
	 *
	 * @param bw BufferedWriter to write to file
	 * @version 1.0
	 */
	 public void save(BufferedWriter bw) throws IOException{
		 
		 bw.write(name + "\n" + email);
	 }
	/**
	 * Formats Patron's name and email
	 *
	 * @return Formatted string for Patron
	 * @version 1.0
	 * @since 1.0
	 */
	@Override public String toString() {
		
		return name + " (" + email + ")";
	}
	
	private String name;
	private String email;
}