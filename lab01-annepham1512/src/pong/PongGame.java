package pong;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame {
        
    private PongCanvas canvas;
    private JFrame myFrame;

    public PongGame() {
        
        int width=300;
        int height=200;
        
        myFrame = new JFrame("OLD Co. Pong");
        canvas = new PongCanvas(width,height); 
        
        Container thePane = myFrame.getContentPane();
        thePane.add(canvas, BorderLayout.CENTER);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*
        // Handle closing the window.
        myFrame.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });
         */
        
        myFrame.setResizable(false);
        myFrame.pack();
        myFrame.setLocation(100,100);
    }

    /**
     * Display this PongTable on the screen.
     */
    public void show() {
        //myFrame.show();   // Deprecated in 1.5
        myFrame.setVisible(true);

        // needs to be done on Swing Event Thread.
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    canvas.requestFocus();
                }
            });

    }

    /**
     * Hide this PongTable from view.
     */
    public void hide() {
        //myFrame.hide();    // Deprecated in 1.5
        myFrame.setVisible(false);
    }

    
    public static void main(String[] args) {
        PongGame pt = new PongGame();
        pt.show();
    }
    

}

