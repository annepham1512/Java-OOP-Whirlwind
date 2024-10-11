package musicstore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArtistDataTest {
	ArtistData artistData;
	
	@BeforeEach
	void setUp() throws Exception {
		artistData = new ArtistData();
	}

	@Test
	void testArtistData() {
		Band b = (Band) artistData.getArtist("Waxahatchee");
		assertEquals(5, b.getAlbums().size());
		Singer s = (Singer) artistData.getArtist("Taylor Swift");
		assertEquals(8, s.getAlbums().size());
	}

	@Test
	void testAddArtist() {
		String name = "The Bangles";
		Band b = new Band(name);
		artistData.addArtist(b);
		Band b2 = (Band) artistData.getArtist(name);
		assertEquals(name, b2.getArtistName());
	}

}
