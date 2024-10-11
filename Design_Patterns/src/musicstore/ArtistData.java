package musicstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

/**
 * An ArtistData object stores data about a collection of musical artists,
 * such as bands and singers.
 * 
 * @author jmac
 */
public class ArtistData {
	// In this map, each key is the name of an artist and the corresponding
	// value is an object representing that artist.
	private Map<String, CreatesMusic> artistMap;

	/**
	 * The name of the file storing data about bands.
	 */
	public static final String BAND_FILE = "data/bands.csv";

	/**
	 * The name of the file storing data about singers.
	 */
	public static final String SINGER_FILE = "data/singers.csv";

	/**
	 * Lists the different categories of musical artists whose data can be
	 * read from files.
	 */
	public enum ArtistCategory {
		BAND, SINGER
	};

	/**
	 * Create a new ArtistData object, reading data about artists from the
	 * data files specified as class constants.
	 */
	public ArtistData() {
		artistMap = new HashMap<String, CreatesMusic>();
		readArtistFile(BAND_FILE, ArtistCategory.BAND);
		readArtistFile(SINGER_FILE, ArtistCategory.SINGER);
	}

	/**
	 * Add a new musical artist to the map of data maintained by this
	 * object.
	 * 
	 * @param cm The musical artist to be added.
	 */
	public void addArtist(CreatesMusic cm) {
		artistMap.put(cm.getArtistName(), cm);
	}

	/**
	 * Get a reference to an object representing a given musical artist.
	 * 
	 * @param name The name of the musical artist
	 * @return A reference to an object representing the musical artist
	 *         with the given name.
	 */
	public CreatesMusic getArtist(String name) {
		return artistMap.get(name);
	}

	// Read the data file specified by the given file name. The category
	// parameter specifies what category of artist data is in the data
	// file.
	private void readArtistFile(String filename, ArtistCategory category) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				CreatesMusic cm;
				if (category == ArtistCategory.BAND) {
					cm = Band.fromString(line);
				} else if (category == ArtistCategory.SINGER) {
					cm = Singer.fromString(line);
				} else {
					reader.close();
					throw new IllegalArgumentException(
							"Unexpected artist category " + category);
				}
				addArtist(cm);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("IOException while reading " + filename);
		}
	}

	/**
	 * Print all the musical artist data stored by this object.
	 */
	public void printAll() {
		for (String artist : artistMap.keySet()) {
			CreatesMusic cm = artistMap.get(artist);
			System.out.println(cm + ", albums:");
			for (String album : cm.getAlbums()) {
				System.out.println("   " + album);
			}
		}
	}

	public static void main(String[] args) {
		ArtistData artistData = new ArtistData();
		artistData.printAll();
	}
}
