package pong;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PongBallTest {
	private PongBall b1;
	
	@BeforeEach
	void setUp() {
		b1 = new PongBall(1, 2, 3, 4);
	}
	

	@Test
	void testPongBall() {
		assertEquals(1, b1.getHorizPos());
		assertEquals(2, b1.getVertPos());
		assertEquals(3, b1.getHorizSpeed());
		assertEquals(4, b1.getVertSpeed());
	}

	@Test
	void testMove() {
		b1.move();
		assertEquals(4, b1.getHorizPos());
		assertEquals(6, b1.getVertPos());
	}

	@Test
	void testHorizBounce() {
		b1.horizBounce();
		assertEquals(-3, b1.getHorizSpeed());
	}

	@Test
	void testVertBounce() {
		b1.vertBounce();
		assertEquals(-4, b1.getVertSpeed());
	}

	@Test
	void testGetHorizPos() {
		assertEquals(1, b1.getHorizPos());
	}

	@Test
	void testGetVertPos() {
		assertEquals(2, b1.getVertPos());
	}

	@Test
	void testGetHorizSpeed() {
		assertEquals(3, b1.getHorizSpeed());
	}

	@Test
	void testGetVertSpeed() {
		assertEquals(4, b1.getVertSpeed());
	}

}
