package musicstore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingerTest {
	private Singer s;
	private static final String NAME = "Édith Piaf";
	private static final int born = 1915;
	private static final String[] ALBUMS = { "Les Mômes de la cloche",
			"J'suis mordue", "Mon légionnaire", "Le Contrebandier",
			"La Fille et le chien", "La Julie jolie" };

	private static final String CSV = "Édith Piaf,1915,"
			+ "Les Mômes de la cloche,J'suis mordue,Mon légionnaire,"
			+ "Le Contrebandier,La Fille et le chien,La Julie jolie,";

	@BeforeEach
	void setUp() throws Exception {
		s = new Singer(NAME, born);
		for (String album : ALBUMS) {
			s.addAlbum(album);
		}
	}

	@Test
	void testFromString() {
		CreatesMusic cm = Singer.fromString(CSV);
		assertEquals(NAME, cm.getArtistName());
		assertEquals(6, cm.getAlbums().size());

		Singer singer = (Singer) cm;
		assertEquals(born, singer.getBirthYear());
	}

	@Test
	void testSinger() {
		assertEquals(NAME, s.getArtistName());
		assertEquals(6, s.getAlbums().size());
		assertEquals(born, s.getBirthYear());
	}

}
