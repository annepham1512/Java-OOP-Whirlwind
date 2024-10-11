package pong;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PongScoreTest {
	private PongScore s1;
	
	@BeforeEach
	void setUp( ) {
		s1 = new PongScore();
	}

	@Test
	void testPongScore() {
		assertEquals(0, s1.getScore());
	}

	@Test
	void testGetScore() {
		assertEquals(0, s1.getScore());
	}

	@Test
	void testScorePoints() {
		s1.scorePoints(3);
		assertEquals(3, s1.getScore());
	}

}
