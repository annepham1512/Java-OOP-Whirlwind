package recursion;

/**
 * Compute the number of boxes in a pyramid of boxes based on the number of
 * boxes in the base of the pyramid. All boxes are stacked directly on top of
 * another box.
 * 
 * Shown below are two examples. One pyramid with base 5 and another with base
 * 6. The number of boxes in the the pyramid with base 5 is 9. The number of
 * boxes in the pyramid with base 6 is 12.
 * 
 * <pre>
 *     []           [][]           
 *   [][][]       [][][][]
 * [][][][][]   [][][][][][]
 *</pre>
 * 
 * @author Anne Pham
 * @version Mon Apr 22
 */
public class PyramidBoxCount {

    /**
     * Compute and return the number of boxes in a pyramid with the specified
     * number of boxes in its base.
     * 
     * @param base the size of the base (positive integer)
     * @return the number of boxes in the pyramid.
     */
    public static int computePyramidBoxes(int base) {
    	int numBox = 0;
        if (base <= 2) {
        	numBox = base;
        } else {
        	numBox = base + computePyramidBoxes(base - 2);
        }
        return numBox;
    }
    public static void main(String[] args) {
    	System.out.println("Testing base case with base 1: " + computePyramidBoxes(1)); //Should be 1
        System.out.println("Testing base case with base 2: " + computePyramidBoxes(2)); //Should be 2
        System.out.println("Testing recursive case with base 3: " + computePyramidBoxes(3)); //Should be 4
        System.out.println("Testing recursive case with base 4: " + computePyramidBoxes(4)); //Should be 6
        System.out.println("Testing recursive case with base 9: " + computePyramidBoxes(5)); //Should be 9
        System.out.println("Testing recursive case with base 6: " + computePyramidBoxes(6)); //Should be 12
        
        
    }
}

