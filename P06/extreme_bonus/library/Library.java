// Copyright 2024 Sarvesh Milind Ingale <https://github.com/sarveshingale/LMS_OOP.git>
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

package library;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
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
	
	/**
	 * Instance a Library object
	 *
	 * @param br BufferedReader of file for data extraction
	 * @version 1.0
	 */
	public Library(BufferedReader br) throws IOException {
		this.name = br.readLine();
		String line = "";
		
		while(line != null) {
			line = br.readLine();
			if(line == null) {
				break;
			}
			if(line.equals("Publication")) {
				addPublication(new Publication(br));
			}
			else if(line.equals("Video")) {
				addPublication(new Video(br));
			}
			else if(line.equals("Patron")) {
				addPatron(new Patron(br));
			}
			else {
				throw new IOException("#####Error -> Unexpected file formatting");
			}
		}
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
	 public void save(BufferedWriter bw) throws IOException {
		
		bw.write(name + "\n");
		for(Publication publication : publications) {
			if(publication instanceof Video) {
				bw.write("Video\n");
			}
			else {
				bw.write("Publication\n");
			}
			publication.save(bw);
			bw.newLine();
		}
		
		for(Patron patron : patrons) {
			bw.write("Patron\n");
			patron.save(bw);
			bw.newLine();
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