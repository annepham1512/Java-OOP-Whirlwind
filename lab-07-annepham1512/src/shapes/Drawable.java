package shapes;

import java.awt.*;

/**
 * This interface is implemented by objects that can be drawn on a Graphics
 * context.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 18, 2009
 */
public interface Drawable {

    /**
     * This method is invoked when the Drawable object is to be drawn. The
     * method must draw the object onto the provided Graphics object. This
     * includes setting the color used for drawing and drawing the object.
     * 
     * @param g the Graphics object onto which the object is to be drawn.
     */
    void draw(Graphics g);

    /**
     * Get the color of the Drawable object. This is the color that will be
     * used when the object is drawn.
     * 
     * @return the color
     */
    Color getColor();

    /**
     * Set the color of the Drawable object. This method changes the color that
     * will be used when the object is drawn.
     * 
     * @param newColor the new color for the object.
     */
    void setColor(Color newColor);

    /**
     * Set the visibility of the Drawable object.  If the object is visible its
     * draw method will be invoked each time the DrawingTablet is repainted.  If
     * the object is not visible, its draw method will not be invoked.
     * 
     * @param visible true to make the object visible, false to make it invisible.
     */
    void setVisible(boolean visible);

    /**
     * Get the visibility of the Drawable object.
     * 
     * @return true if the object is visible, false if it is not.
     */
    boolean isVisible();
}
