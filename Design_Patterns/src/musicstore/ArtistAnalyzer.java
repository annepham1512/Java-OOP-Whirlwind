package musicstore;

/**
 * An ArtistAnalyzer object is used to analyze information about musical
 * artists. It is provided here as an example of a class that makes use of
 * the ArtistData class. We will not actually be doing any kind of analysis
 * of the musical artists, and hence the ArtistAnalyzer class is
 * deliberately incomplete. Nevertheless, this class is useful because it
 * will help us understand why it is beneficial to convert the ArtistData
 * class to a singleton.
 * 
 * @author jmac
 */
public class ArtistAnalyzer {
	private ArtistData artistData;

	// Other fields have been omitted. We imagine fields to store
	// additional information about the artists that have been analyzed.
	//
	// ...
	// ...

	/**
	 * Construct a new ArtistAnalyzer object.
	 */
	public ArtistAnalyzer() {
		artistData = new ArtistData();
	}

	// All methods have been omitted. We imagine methods that enable
	// the programmer to analyze information about the artists, such as
	// those with the most albums, the number of singers born after 1990,
	// and so on.
	//
	// ...
	// ...

}
