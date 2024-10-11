package musicstore;

/**
 * An MusicStore object is used to model the information about a music
 * store that sells albums. It is provided here as an example of a class
 * that makes use of the ArtistData class. We will not actually be using
 * the MusicStore class for any further applications, and hence the
 * MusicStore class is deliberately incomplete. Nevertheless, this class is
 * useful because it will help us understand why it is beneficial to
 * convert the ArtistData class to a singleton.
 * 
 * @author jmac
 */
public class MusicStore {
	private ArtistData artistData;

	// Other fields have been omitted. We imagine fields to store
	// information about customers, orders, inventory and prices.
	//
	// ...
	// ...

	/**
	 * Construct a new MusicStore object.
	 */
	public MusicStore() {
		artistData = new ArtistData();
	}

	// All methods have been omitted. We imagine methods that enable
	// customers to place orders, prices to be altered, and inventory to be
	// analyzed.
	//
	// ...
	// ...
}
