package musicstore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComposerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testComposer() {
		Composer c = new Composer("Beethoven", "Germany", 1770, 1827);
		String[] albums = { "Symphony No. 1", "Piano Concerto No. 3",
				"Egmont Overture" };
		ArrayList<String> albumList = new ArrayList<String>(
				Arrays.asList(albums));
		c.setAlbums(albumList);
		assertEquals(3, c.getAlbums().size());
		assertTrue(c.getAlbums().contains("Piano Concerto No. 3"));
		assertEquals("Beethoven", c.getName());
	}

	@Test
	void testFromString() {
		String s = "Beethoven,Germany,1770,1827,Symphony No. 1,"
				+ "Piano Concerto No. 3,Egmont Overture";
		Composer c = Composer.fromString(s);
		assertEquals(3, c.getAlbums().size());
		assertTrue(c.getAlbums().contains("Piano Concerto No. 3"));
		assertEquals("Beethoven", c.getName());
	}

}
