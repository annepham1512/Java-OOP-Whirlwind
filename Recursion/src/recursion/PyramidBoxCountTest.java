package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PyramidBoxCountTest {

	@Test
	void testBaseCaseWithBase1() {
		assertEquals(1, PyramidBoxCount.computePyramidBoxes(1));
	}

	@Test
	void testBaseCaseWithBase2() {
		assertEquals(2, PyramidBoxCount.computePyramidBoxes(2));
	}

	@Test
	void testRecursiveCaseWithBase3() {
		assertEquals(4, PyramidBoxCount.computePyramidBoxes(3));
	}

	@Test
	void testRecursiveCaseWithBase4() {
		assertEquals(6, PyramidBoxCount.computePyramidBoxes(4));
	}

	@Test
	void testRecursiveCaseWithBase5() {
		assertEquals(9, PyramidBoxCount.computePyramidBoxes(5));
	}

	@Test
	void testRecursiveCaseWithBase6() {
		assertEquals(12, PyramidBoxCount.computePyramidBoxes(6));
	}

}
