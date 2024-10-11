package musicstore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BandTest {
	private Band b;
	private static final String NAME = "The Bangles";
	private static final String[] ALBUMS = { "All Over the Place",
			"Different Light", "Everything", "Doll Revolution",
			"Sweetheart of the Sun" };

	private static final String CSV = "The Bangles,All Over the Place,"
			+ "Different Light,Everything,Doll Revolution,Sweetheart of the Sun";

	@BeforeEach
	void setUp() throws Exception {
		b = new Band(NAME);
		for (String album : ALBUMS) {
			b.addAlbum(album);
		}
	}

	@Test
	void testBand() {
		assertEquals(5, b.getAlbums().size());
		assertTrue(b.getAlbums().contains(ALBUMS[1]));
	}

	@Test
	void testAddAlbum() {
		b.addAlbum("fake");
		assertEquals(6, b.getAlbums().size());
	}

	@Test
	void testFromString() {
		CreatesMusic cm = Band.fromString(CSV);
		assertEquals(5, cm.getAlbums().size());
		assertTrue(cm.getAlbums().contains(ALBUMS[1]));
	}

}
