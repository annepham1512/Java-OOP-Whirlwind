package shapes;

/**
 * The Scaleable interface is implemented by any object that can be scaled.
 */
public interface Scaleable {
    /**
     * Scale this object by the the specified factor. For example a factor of
     * 2.0 will make the object twice as bit and a factor of 0.5 will make it
     * half as large.  If the factor is negative the object is not changed.
     * 
     * @param factor the factor by which the object should be scaled.
     */
    void scale(double factor);
}
