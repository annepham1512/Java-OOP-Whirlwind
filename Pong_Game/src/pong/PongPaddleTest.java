package pong;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PongPaddleTest {
	private PongPaddle p1;
	
	@BeforeEach
	void setUp( ) {
		p1 = new PongPaddle(1, 2, 3, 4);
	}
	

	@Test
	void testPongPaddle() {
		assertEquals(1, p1.getTopEdgePos());
		assertEquals(2, p1.getLeftEdgePos());
		assertEquals(3, p1.getRightEdgePos() - p1.getLeftEdgePos());
		assertEquals(4, p1.getBottomEdgePos() - p1.getTopEdgePos());
	}

	@Test
	void testMoveUp() {
		p1.moveUp(2);
		assertEquals(-1, p1.getTopEdgePos());
	}

	@Test
	void testMoveDown() {
		p1.moveDown(2);
		assertEquals(3, p1.getTopEdgePos());
	}

	@Test
	void testGetLeftEdgePos() {
		assertEquals(2, p1.getLeftEdgePos());
	}

	@Test
	void testGetTopEdgePos() {
		assertEquals(1, p1.getTopEdgePos());
	}

	@Test
	void testGetRightEdgePos() {
		assertEquals(5, p1.getRightEdgePos());
	}

	@Test
	void testGetBottomEdgePos() {
		assertEquals(5, p1.getBottomEdgePos());
	}

}
