package shapes;

import java.awt.*;

/**
 * Sample program that draws a multicolored bullseye at the center of the
 * window.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 18, 2009
 */
public class Bullseye {

    /**
     * Draw a multicolored bullseye at the center of the window.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        DrawableObjectList objList = new DrawableObjectList();
        DrawingTablet tablet = new DrawingTablet("Bullseye", 200, 200, objList);

        Circle c1 = new Circle(100, 100, 100, Color.blue);
        objList.addDrawable(c1);
        
        Circle c2 = new Circle(100, 100, 75, Color.red);
        objList.addDrawable(c2);
        
        Circle c3 = new Circle(100, 100, 50, Color.green);
        objList.addDrawable(c3);
        
        Circle c4 = new Circle(100, 100, 25, Color.yellow);
        objList.addDrawable(c4);
        
        tablet.repaint();
    }
}
