package musicstore;

/**
 * A Singer object represents a singer or singer-songwriter.
 * 
 * @author jmac
 */
public class Singer extends Band {
	int birthYear;

	/**
	 * Create a new Singer object representing the singer with the given
	 * data.
	 * 
	 * @param name      The name of the singer
	 * @param birthYear The year in which the singer was born
	 */
	public Singer(String name, int birthYear) {
		super(name);
		this.birthYear = birthYear;
	}

	/**
	 * Create and return a new Singer object using data from a string with
	 * a fixed format.
	 * 
	 * The format of the string is
	 * singerName,birthYear,albumName,albumName,...
	 * 
	 * For example, "Ariana Grande,1993,Yours Truly,My Everything,Dangerous
	 * Woman,Sweetener,Thank U Next,Positions"
	 * 
	 * @param line A string containing the singer data in the above format.
	 * 
	 * @return A CreatesMusic reference is returned, but this reference is
	 * to a new Singer object based on the data given in the parameter.
	 */
	public static CreatesMusic fromString(String s) {
		String[] vals = s.split(",");
		Singer singer = new Singer(vals[0], Integer.parseInt(vals[1]));
		for (int i = 2; i < vals.length; i++) {
			singer.addAlbum(vals[i]);
		}
		return singer;
	}

	/**
	 * @return the year of the singer's birth
	 */
	int getBirthYear() {
		return birthYear;
	}

	@Override
	public String toString() {
		return "Singer [name=" + name + ", birthYear=" + birthYear + "]";
	}

}
