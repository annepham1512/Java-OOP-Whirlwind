package shapes;

import javax.swing.*;
import java.awt.*;

/**
 * The DrawingTablet class represents a window on the graphics screen onto which
 * objects that implement the Drawable interface may be drawn.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version 4/22/2002
 */
public class DrawingTablet {

    private DrawingCanvas canvas;
    private JFrame myFrame;

    /**
     * Create a new DrawingTablet with the specified size.
     * 
     * @param title the title of the window.
     * @param width the width of the window.
     * @param height the height of the window.
     * @param shapes the DrawableObjectList holding the objects to be drawn.
     */
    public DrawingTablet(String title, int width, int height, DrawableObjectList shapes) {
        myFrame = new JFrame(title);
        canvas = new DrawingCanvas(width, height, shapes);

        Container thePane = myFrame.getContentPane();
        thePane.add(canvas, BorderLayout.CENTER);

        // Handle closing the window.
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.setResizable(false);
        myFrame.pack();
        myFrame.setLocation(100, 100);

        myFrame.setVisible(true);
    }

    /**
     * Cause all of the shapes in the DrawableObjectList to be repainted. This
     * will allow changes to the shapes or to the DrawableObjectList to be
     * reflected in the window.
     */
    public void repaint() {
        canvas.repaint();
    }

    /*
     * This is the component on which the shapes are actually painted.
     */
    class DrawingCanvas extends JPanel {

        private static final long serialVersionUID = 1L;

        private int width;
        private int height;
        private DrawableObjectList shapes;

        /**
         * Construct a new DrawingCanvas with the specified width and height.
         * 
         * @param width the width of the new DrawingCanvas.
         * @param height the height of the new DrawingCanvas.
         */
        public DrawingCanvas(int width, int height, DrawableObjectList shapes) {
            this.width = width;
            this.height = height;
            this.shapes = shapes;
        }

        /**
         * Return the width and height of this DrawingCanvas as its preferred,
         * minimum and maximum sizes.
         * 
         * @return a new Dimension object specifying the width and height of
         *         this DrawingCanvas.
         */
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }

        /**
         * Return the width and height of this DrawingCanvas as its preferred,
         * minimum and maximum sizes.
         * 
         * @return a new Dimension object specifying the width and height of
         *         this DrawingCanvas.
         */
        public Dimension getMinimumSize() {
            return new Dimension(width, height);
        }

        /**
         * Return the width and height of this DrawingCanvas as its preferred,
         * minimum and maximum sizes.
         * 
         * @return a new Dimension object specifying the width and height of
         *         this DrawingCanvas.
         */
        public Dimension getMaximumSize() {
            return new Dimension(width, height);
        }

        /**
         * Paint all of the shapes onto the specified graphics context.
         * 
         * @param g the graphics context onto which the shapes are to be drawn.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            shapes.drawAll(g);
        }
    }
}
