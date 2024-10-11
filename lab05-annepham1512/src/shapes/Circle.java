package shapes;

import java.awt.*;

/**
 * A Circle is an object representing a circle. A Circle has a center point
 * (x,y), a radius and a color. It can be moved, translated, scaled and drawn.
 */
public class Circle implements Drawable, Scaleable {

    private int x;
    private int y;
    private int radius;
    private Color theColor;
    private boolean isVisible;

    /**
     * Construct a new Circle centered at initX, initY with a radius of
     * initRadius and color indicated by initColor. The new Circle is visible by
     * default.
     * 
     * @param initX the x coordinate of the center of the circle.
     * @param initY the y coordinate of the center of the circle.
     * @param initRadius the radius of the circle.
     * @param initColor the color of the circle.
     */
    public Circle(int initX, int initY, int initRadius, Color initColor) {
        x = initX;
        y = initY;
        radius = initRadius;
        theColor = initColor;
        isVisible = true;
    }

    /**
     * Get the x coordinate of the center of this Circle.
     * 
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate of the center of this Circle.
     * 
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get the radius of this Circle.
     * 
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Set the radius of this Circle. The Circle's radius is set to the absolute
     * value of the specified radius to eliminate any negative radius values.
     * 
     * @param newRadius the radius of the new circle.
     */
    public void setRadius(int newRadius) {
        radius = Math.abs(newRadius);
    }

    /**
     * Move the Circle to a new location. This method changes the center point
     * of the circle using the values provided by the parameters.
     * 
     * @param newX the new x coordinate
     * @param newY the new y coordinate
     */
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    /**
     * Move the Circle relative to its current location. The center of the
     * circle is moved by adding the parameters to the Circle's current
     * location.
     * 
     * @param deltaX the change in the x coordinate. Positive values move the
     *            Circle to the right, negative values move it to the left.
     * @param deltaY the change in the y coordinate. Positive values move the
     *            Circle down, negative values move it up.
     */
    public void translate(int deltaX, int deltaY) {
        x = x + deltaX;
        y = y + deltaY;
    }
    
    // === Implementation of the Scaleable interface ===
    /**
     * Scale this Circle by the specified factor. For example a factor of 2.0
     * will make the radius twice as bit and a factor of 0.5 will make it half
     * as large. If the factor is negative the radius is not changed.
     * 
     * @param factor the factor by which this Circle is to be scaled.
     */
    public void scale(double factor) {
        if (factor > 0) {
            radius = (int) (Math.round(radius * factor));
        }
    }

    // === Implementation of the Drawable interface ===

    /**
     * Draw this DrawableCircle onto the specified Graphics object.
     * 
     * @param g the Graphics object on which to draw this DrawableCircle.
     */
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
    }

    /**
     * Get the Color of this Circle.
     * 
     * @return the color.
     */
    public Color getColor() {
        return theColor;
    }

    /**
     * Change the color of this Circle to the newColor.
     * 
     * @param newColor the new color.
     */
    public void setColor(Color newColor) {
        theColor = newColor;
    }

    /**
     * Set whether or not this Circle will be visible. If it is visible its draw
     * method will be invoked when the DrawingTablet is repainted. If it is not
     * visible its draw method will not be invoked.
     * 
     * @param visible true to make this Circle visible, false to make it
     *            invisible.
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Find out if this Circle is visible or not.
     * 
     * @return true if the Circle is visible, false if it is not.
     */
    public boolean isVisible() {
        return isVisible;
    }
}
