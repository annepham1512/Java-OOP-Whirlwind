package musicstore;

import java.util.Set;

/**
 * The CreatesMusic interface represents a musical artist; an entity that
 * creates music. Possible examples include bands, singers, and composers.
 * 
 * @author jmac
 */
public interface CreatesMusic {
	/**
	 * @return The name of the musical artist
	 */
	public String getArtistName();

	/**
	 * @return The albums released by the musical artist. Each album is
	 *         represented by its title, stored as a String.
	 */
	public Set<String> getAlbums();

	/**
	 * Add the album with the given title to the list of albums released by
	 * this musical artist.
	 * 
	 * @param name The title of the album to be added.
	 */
	public void addAlbum(String name);
}
