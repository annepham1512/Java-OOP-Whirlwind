package recursion;

/**
 * The palindrome class can be used to check whether strings are
 * palindromes.
 * 
 * @author jmac
 *
 */
public class Palindrome {
	/**
	 * Determine whether a given string is a palindrome. That is, is the
	 * string the same backwards as forwards?
	 * 
	 * @param s The string to be analyzed.
	 * @return True if s is a palindrome and false otherwise.
	 */
	public static boolean isPalindrome(String s) {
		
		return efficientIsPalindrome(s, 0, s.length() - 1);
	}
	public static boolean efficientIsPalindrome(String s, int fromChar, int toChar) {

		// Base cases: strings of length zero or one are always
		// palindromes.
		if (toChar - fromChar < 1) {
			return true;
		}
		// Recursive case: If the first and last characters are
		// different, it's definitely not a palindrome. If the first and
		// last characters are the same, remove them and check whether the
		// remaining string is a palindrome.
		else {
//			efficientIsPalindrome(s, s.charAt(fromChar), s.charAt(toChar));
			if (s.charAt(fromChar) != s.charAt(toChar)) {
				return false;
			} else {
				return efficientIsPalindrome(s, fromChar + 1, toChar - 1);
			}
		}
	}
	public static void main (String[] args) {
		System.out.println("Base case with String length zero: " + isPalindrome("")); //Should be true
		System.out.println("Base case with String length one: " + isPalindrome("i")); //Should be true
		System.out.println("Recursive case that is not a Palindrome: " + isPalindrome("hjhj")); // Should be false
		System.out.println("Another recursive case that is not a Palindrome: " + isPalindrome("lmao")); // Should be false
		System.out.println("Recursive case that is a Palindrome: " + isPalindrome("lol")); // Should be true
		System.out.println("Another recursive case that is a Palindrome: " + isPalindrome("racecar")); // Should be true
		System.out.println("Another recursive case that is a Palindrome: " + isPalindrome("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeea"
				+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")); // Should be true
	}
}
