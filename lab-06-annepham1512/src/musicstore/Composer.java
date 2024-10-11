package musicstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import musicstore.ArtistData.ArtistCategory;

/**
 * A Composer object represents information about a classical composer such
 * as Beethoven or Stravinsky.
 */
public class Composer {
	private String name;
	private String nationality;
	private int yearBorn;
	private int yearDied; // if unknown, set to Integer.MAX_VALUE

	// The concept of `album' for a classical composer is left deliberately
	// vague. These could be released albums that incorporate works by the
	// composer, or they could simply be the names of works by the
	// composer.
	private ArrayList<String> albums;

	/**
	 * Create a new Composer object with the given data values.
	 * 
	 * @param name        The name of the composer, e.g. "Amy Beach"
	 * @param nationality The nationality of the composer, e.g. "USA"
	 * @param yearBorn    The year in which the composer was born
	 * @param yearDied    The year in which the composer died. If the
	 *                    composer is still alive or the year of death is
	 *                    unknown, this field has the value
	 *                    Integer.MAX_VALUE
	 */
	public Composer(String name, String nationality, int yearBorn,
			int yearDied) {
		this.name = name;
		this.nationality = nationality;
		this.yearBorn = yearBorn;
		this.yearDied = yearDied;
		this.albums = new ArrayList<String>();
	}

	/*
	 * Create and return a new Composer object using data from a string
	 * with a fixed format.
	 * 
	 * The format of of the string is
	 * name,nationality,yearBorn,yearDied,album,album,...
	 * 
	 * For example, "Duke Ellington,USA,1899,1974,Duke Ellington At Fargo,
	 * The Ellington Suites,Togo Brava Suite,..."
	 * 
	 * If composer is still alive, yearDied is omitted and thus represented
	 * by two consecutive commas. When stored as a field, however, an
	 * unknown death year will have the special value Integer.MAX_VALUE.
	 * 
	 * @param line A string containing the composer data in the above
	 * format.
	 * 
	 * @return A CreatesMusic reference is returned, but this reference is
	 * to a new Composer object based on the data given in the parameter.
	 */
	public static Composer fromString(String s) {
		String[] vals = s.split(",");
		String name = vals[0];
		String nationality = vals[1];
		int yearBorn = Integer.parseInt(vals[2]);
		int yearDied;
		if (vals[3].equals("")) {
			yearDied = Integer.MAX_VALUE;
		} else {
			yearDied = Integer.parseInt(vals[3]);
		}

		ArrayList<String> albums = new ArrayList<String>();
		for (int i = 4; i < vals.length; i++) {
			String albumName = vals[i];
			if (!albumName.equals("")) {
				albums.add(albumName);
			}
		}

		Composer c = new Composer(name, nationality, yearBorn, yearDied);
		c.setAlbums(albums);
		return c;
	}

	/**
	 * @return the albums produced by this composer
	 */
	public ArrayList<String> getAlbums() {
		return albums;
	}

	/**
	 * @param albums the list of albums that will be set as the albums
	 *               produced by this composer
	 */
	public void setAlbums(ArrayList<String> albums) {
		this.albums = albums;
	}

	/**
	 * @return the name of the composer
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the nationality of the composer
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @return the year in which the composer was born
	 */
	public int getYearBorn() {
		return yearBorn;
	}

	/**
	 * @return the year in which the composer died, or Integer.MAX_VALUE if
	 *         the composer is still alive.
	 */
	public int getYearDied() {
		return yearDied;
	}

	@Override
	public String toString() {
		String yearDiedStr;
		if (yearDied == Integer.MAX_VALUE) {
			yearDiedStr = "unkown";
		} else {
			yearDiedStr = Integer.toString(yearDied);
		}
		return "Composer [name=" + name + ", nationality=" + nationality
				+ ", yearBorn=" + yearBorn + ", yearDied=" + yearDiedStr
				+ "]";
	}

	public static void main(String[] args) {
		String filename = "data/composers.csv";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				Composer c = Composer.fromString(line);
				line = reader.readLine();
				System.out.println(c + "   albums:");
				for (String album : c.getAlbums()) {
					System.out.println("     " + album);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("IOException while reading " + filename);
		}

	}
}
