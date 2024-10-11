package recursion;

/**
 * Recursive computation of x^n.
 *
 * @author Anne Pham
 * @version Mon Apr 22
 */
public class Exponentiation {

    /**
     * Compute the value of x^n.
     * 
     * @param x the base (non-negative integer)
     * @param n the exponent (non-negative integer)
     * @return x^n
     */

    
	public static long exp(int x, int n) {
	    if (n == 0) {
	        return 1; // Base case: x^0 = 1
	    } else {
	        long temp = exp(x, n / 2); // Calculate x^(n/2) only once

	        if (n % 2 == 0) {
	            return temp * temp; // If n is even, x^n = (x^(n/2))^2
	        } else {
	            return x * temp * temp; // If n is odd, x^n = x * (x^(n/2))^2
	        }
	    }
	}

    
    public static void main(String args[]) {
    	// Test base case with exponent 0
        System.out.println("Base case with exponent 0: " + exp(5, 0)); // Output should be 1

        // Test recursive case with base 0
        System.out.println("Base case with base 0: " + exp(0, 5)); // Output should be 0

        // Test recursive case with even exponent
        System.out.println("Recursive case with even exponent: " + exp(2, 4)); // Output should be 16

        // Test recursive case with odd exponent
        System.out.println("Recursive case with odd exponent: " + exp(5, 3)); // Output should be 125

    	
    }
}
