package shapes;

import java.awt.*;

/**
 * Representation of a Point. A point is a single pixel on the screen. This
 * class is the root of the inheritance hierarchy for all of the Drawable
 * shapes in this lab.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 29, 2009
 */
public class Point implements Drawable {

    private int x;
    private int y;
    private Color theColor;
    private boolean isVisible;
    
    public Point(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        theColor = c;
        isVisible = true;
    }

    /**
     * Get the x coordinate of the Point.
     * 
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate of the Point.
     * 
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Move the Point to a new location. This method changes the location
     * of the point to the values provided by the parameters.
     * 
     * @param newX the new x coordinate
     * @param newY the new y coordinate
     */
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    /**
     * Move the Point relative to its current location. The location of the
     * point is moved by adding the parameters to the Point's current
     * location.
     * 
     * @param deltaX the change in the x coordinate. Positive values move the
     *            Point to the right, negative values move it to the left.
     * @param deltaY the change in the y coordinate. Positive values move the
     *            Point down, negative values move it up.
     */
    public void translate(int deltaX, int deltaY) {
        x = x + deltaX;
        y = y + deltaY;
    }
    
    // === Implementation of the Drawable interface ===
    
    /**
     * Get the Color of this Point.
     * 
     * @return the color.
     */
    public Color getColor() {
        return theColor;
    }

    /**
     * Change the color of this Point to the newColor.
     * 
     * @param newColor the new color.
     */
    public void setColor(Color newColor) {
        theColor = newColor;
    }

    /**
     * Set whether or not this Point will be visible. If it is visible its draw
     * method will be invoked when the DrawingTablet is repainted. If it is not
     * visible its draw method will not be invoked.
     * 
     * @param visible true to make this Point visible, false to make it
     *            invisible.
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Find out if this Point is visible or not.
     * 
     * @return true if the Point is visible, false if it is not.
     */
    public boolean isVisible() {
        return isVisible;
    }
    
    /**
     * Draw the Point on the graphics context.
     * 
     * @param g the Graphics context on which to draw the Point.
     */
    public void draw(Graphics g) {
        g.setColor(theColor);
        
        /*
         * There is no method in the Graphics class for drawing 
         * a point.  So it is drawn as a line with both end points
         * being the same.
         */
        g.drawLine(x, y, x, y);
    }
}
