package musicstore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A Band object represents a musical band.
 * 
 * @author jmac
 */
public class Band implements CreatesMusic {
	protected String name;
	private ArrayList<String> albums;

	/**
	 * Create a new Band object representing the band with the given name.
	 * 
	 * @param bandName The name of the band, e.g. "Arctic Monkeys"
	 */
	public Band(String bandName) {
		this.name = bandName;
		this.albums = new ArrayList<String>();
	}

	@Override
	public String getArtistName() {
		return name;
	}

	@Override
	public Set<String> getAlbums() {
		return new HashSet<String>(albums);
	}

	@Override
	public void addAlbum(String name) {
		albums.add(name);
	}

	/*
	 * Create and return a new Band object using data from a string with a
	 * fixed format.
	 * 
	 * The format of the string is bandName,albumName,albumName,...
	 * 
	 * For example, "Blackpink,Square One,Blackpink in Your Area,The Album"
	 * 
	 * @param line A string containing the band data in the above format.
	 * @return A CreatesMusic reference is returned, but this reference is
	 *         to a new Band object based on the data given in the
	 *         parameter.
	 */
	public static CreatesMusic fromString(String s) {
		String[] vals = s.split(",");
		Band band = new Band(vals[0]);
		for (int i = 1; i < vals.length; i++) {
			band.addAlbum(vals[i]);
		}
		return band;
	}

	@Override
	public String toString() {
		return "Band [name=" + name + "]";
	}

}
