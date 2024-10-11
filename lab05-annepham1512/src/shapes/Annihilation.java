package shapes;

import java.awt.Color;

/**
 * A little program to test and illustrate the functionality of the
 * DrawingTablet and DrawableObjectList classes.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 18, 2009
 */
public class Annihilation {

    /**
     * Display a pulsing blue Circle slowly moving horizontally across the
     * window and a pulsing red Circle slowly moving down the window. When the
     * red and blue circles overlap a yellow Circle expands from the center and
     * covers them and then contracts leaving the screen blank.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        DrawableObjectList objList = new DrawableObjectList();
        DrawingTablet tablet = new DrawingTablet("Annihilation", 200, 200, objList);

        // Blue circle to move horizontally across the tablet.
        Circle blue = new Circle(20, 100, 20, Color.blue);
        objList.addDrawable(blue);

        // Red circle to move vertically down the tablet.
        Circle red = new Circle(100, 20, 20, Color.red);
        objList.addDrawable(red);

        /*
         * Yellow circle is small and sitting at the center of the screen but is
         * not initially visible.
         */
        Circle yellow = new Circle(100, 100, 2, Color.yellow);
        yellow.setVisible(false);
        objList.addDrawable(yellow);

        // Repaint so that the added circles appear.
        tablet.repaint();

        /*
         * For enough steps to move the circles to the center of the tablet,
         * translate them by 2 pixels, change their radius to make them appear
         * to pulse and repaint the tablet. Then wait 100ms and do it again.
         */
        for (int i = 0; i < 40; i++) {
            blue.translate(2, 0);
            blue.setRadius(15 + i % 5);
            red.translate(0, 2);
            red.setRadius(15 + i % 5);
            tablet.repaint();
            AnimationTimer.sleep(100);
        }

        /*
         * The red and blue Circles overlap now, so draw the expanding yellow
         * circle.
         */
        yellow.setVisible(true);
        tablet.repaint();
        for (int i = 0; i < 15; i++) {
            yellow.scale(1.25);
            tablet.repaint();
            AnimationTimer.sleep(75);
        }

        /*
         * The yellow Circle has expanded to its maximum size, so erase the red
         * and blue circles and slowly contract the yellow circle back to
         * nothing.
         */
        objList.removeDrawable(red);
        objList.removeDrawable(blue);
        for (int i = 0; i < 15; i++) {
            yellow.scale(0.75);
            tablet.repaint();
            AnimationTimer.sleep(75);
        }
        objList.removeDrawable(yellow);
        tablet.repaint();
    }
}
