package shapes;

import java.awt.Color;
import java.util.*;

/**
 * A little test program that displays a bunch of randomly generated points on a
 * DrawingTablet. Not particularly interesting, but it tests the basic
 * functionality of the provided code.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 29, 2009
 */
public class RandomPoints {

    /**
     * Generate and display random points on a DrawingTablet.
     * 
     * @param args none.
     */
    public static void main(String[] args) {
        DrawableObjectList objList = new DrawableObjectList();
        DrawingTablet tablet = new DrawingTablet("Random Points", 200, 200, objList);

        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            objList.addDrawable(new Point(rnd.nextInt(200), rnd.nextInt(200), Color.red));
        }

        tablet.repaint();
    }
}
