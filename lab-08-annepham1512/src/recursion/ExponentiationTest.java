package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExponentiationTest {

	@Test
    public void testBaseCaseWithExponent0() {
        assertEquals(1, Exponentiation.exp(5, 0));
    }

    @Test
    public void testBaseCaseWithBase0() {
        assertEquals(0, Exponentiation.exp(0, 5));
    }

    @Test
    public void testRecursiveCaseWithEvenExponent() {
        assertEquals(16, Exponentiation.exp(2, 4));
    }

    @Test
    public void testRecursiveCaseWithOddExponent() {
        assertEquals(125, Exponentiation.exp(5, 3));
    }

	 

}
