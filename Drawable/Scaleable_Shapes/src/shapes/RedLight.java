package shapes;

import java.awt.Color;

/**
 * A simple example of how to create a picture and an animation.
 * This example draws a green circle at the center of the window,
 * after 3 seconds the circle turns yellow and after one more second
 * it turns red.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 18, 2009
 */
public class RedLight {
    /**
     * Draw a green circle in the center of the window, after 3 seconds
     * turn it yellow and after another second turn it red.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        /*
         * Create a DrawableObject list and use it to create a DrawingTablet.
         */
        DrawableObjectList objList = new DrawableObjectList();
        DrawingTablet tablet = new DrawingTablet("Red Light", 200, 200, objList);

        // Create a green circle at the center of the screen.
        Circle light = new Circle(100, 100, 75, Color.green);
        objList.addDrawable(light);
   
        /*
         * Each time a change is made to the DrawableObjectList or to one of
         * the Drawable objects that it contains you need to invoke repaint() 
         * on the DrawingTablet.  This causes the Drawing tablet to repaint
         * the scrren reflecting the chages. 
         */
        tablet.repaint();
        
        /*
         * sleep is a static method in the AnimationTimer class that causes
         * the program to pause for a specified number of milliseconds when 
         * it is invoked.  This line sleeps for 3 seconds.
         */
        AnimationTimer.sleep(3000);
        
        // Change the color and repaint!
        light.setColor(Color.yellow);
        tablet.repaint();
        
        // Sleep for 1 second.
        AnimationTimer.sleep(1000);
        
        // Change the color and repaint.
        light.setColor(Color.red);
        tablet.repaint();
    }
}
