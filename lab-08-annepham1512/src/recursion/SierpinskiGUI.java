package recursion;

import javax.swing.*;

/**
 * A GUI for displaying a Sierpinski Triangle.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Nov 11, 2009
 */
public class SierpinskiGUI extends JFrame {
    
    /**
     * Construct a new SierpinskiGUI.
     */
    public SierpinskiGUI() {
        super("Sierpinski Triangle");
        
        SierpinskiPanel sp = new SierpinskiPanel(6);
        this.add(sp);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    /**
     * Create an instance of the SierpinskiGUI and display it
     * on the screen.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        SierpinskiGUI sg = new SierpinskiGUI();
        sg.setVisible(true);
        
        
    }
}
