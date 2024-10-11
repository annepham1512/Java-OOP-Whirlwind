package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeTest {

	@Test
    public void testBaseCaseWithEmptyString() {
        assertTrue(Palindrome.isPalindrome(""));
    }

    @Test
    public void testBaseCaseWithSingleCharacter() {
        assertTrue(Palindrome.isPalindrome("i"));
    }

    @Test
    public void testNonPalindromeString1() {
        assertFalse(Palindrome.isPalindrome("hjhj"));
    }

    @Test
    public void testNonPalindromeString2() {
        assertFalse(Palindrome.isPalindrome("lmao"));
    }

    @Test
    public void testPalindromeString1() {
        assertTrue(Palindrome.isPalindrome("lol"));
    }

    @Test
    public void testPalindromeString2() {
        assertTrue(Palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testLongPalindromeString() {
        assertTrue(Palindrome.isPalindrome("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeea"
				+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"));
    }


}
