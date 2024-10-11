
package pong;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PongCanvas 
    extends JPanel
    implements KeyListener {
 
    private int width;
    private int height;

    private PongBall ball;
    private PongPaddle paddle1, paddle2;
    private PongScore player1, player2;

    private java.util.Timer ballTimer;

    public PongCanvas (int width, int height) {
        this.width = width;
        this.height = height;

        ball = new PongBall(width/2, (height-40)/2 + 5, 3, 4);

        paddle1 = new PongPaddle((height - 40)/2 - 20,25, 10,50);
        paddle2 = new PongPaddle((height - 40)/2 - 20,width-35,10,50);
        
        //paddle1 = new PongPaddle(25,(height - 40)/2 - 20,10,50);
        //paddle2 = new PongPaddle(width-35, (height - 40)/2 - 20,10,50);

        player1 = new PongScore();
        player2 = new PongScore();

        ballTimer = new java.util.Timer();

        this.addKeyListener(this);
    }
    
    // NOTE: This method is for JDK1.4 or newer and replaces
    // the depricated isFocusTraversable defined below.  WHen
    // compling with JDK1.4 or newer it is recommended that
    // isFocusTraversable be removed.
    public boolean isFocusable() {
        return true;
    }

    /*
    public boolean isFocusTraversable() {
        return true;
    }
    */
   
    /**
     * Return the width and height of this PongCanvas
     * as its preferred, minimum and maximum sizes.
     *
     * @return a new Dimension object specifying the width
     *         and height of this PongCanvas.
     */
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    /**
     * Return the width and height of this PongCanvas
     * as its preferred, minimum and maximum sizes.
     *
     * @return a new Dimension object specifying the width
     *         and height of this PongCanvas.
     */
    public Dimension getMinimumSize() {
        return new Dimension(width, height);
    }

    /**
     * Return the width and height of this PongCanvas
     * as its preferred, minimum and maximum sizes.
     *
     * @return a new Dimension object specifying the width
     *         and height of this PongCanvas.
     */
    public Dimension getMaximumSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.fillOval(ball.getHorizPos()-4,ball.getVertPos()-4,8,8);

        g.setColor(Color.blue);
        g.fillRect(paddle1.getLeftEdgePos(),
                   paddle1.getTopEdgePos(),
                   paddle1.getRightEdgePos() - paddle1.getLeftEdgePos(),
                   paddle1.getBottomEdgePos() - paddle1.getTopEdgePos());

        g.setColor(Color.green.darker());
        g.fillRect(paddle2.getLeftEdgePos(),
                   paddle2.getTopEdgePos(),
                   paddle2.getRightEdgePos() - paddle2.getLeftEdgePos(),
                   paddle2.getBottomEdgePos() - paddle2.getTopEdgePos());
    }
    
    public void paintBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(5,5,width-11,height-35);
        
        g.drawLine(5,(height-30)/2 - 15,15,(height-30)/2 - 15);
        g.drawLine(5,(height-30)/2 + 15,15,(height-30)/2 + 15);
        g.drawString("5", 9 ,(height-30)/2 + 5);
        g.drawLine(5,(height-30)/2 - 45,15,(height-30)/2 - 45);
        g.drawLine(5,(height-30)/2 + 45,15,(height-30)/2 + 45);
        g.drawString("3", 9 ,(height-30)/2 + 5 - 30);
        g.drawString("3", 9 ,(height-30)/2 + 5 + 30);
        g.drawString("1", 9 ,(height-30)/2 + 5 - 65);
        g.drawString("1", 9 ,(height-30)/2 + 5 + 65);

        g.drawLine(width-19,(height-30)/2 - 15,width-6,(height-30)/2 - 15);
        g.drawLine(width-19,(height-30)/2 + 15,width-6,(height-30)/2 + 15);
        g.drawString("5", width-15 ,(height-30)/2 + 5);
        g.drawLine(width-19,(height-30)/2 - 45,width-6,(height-30)/2 - 45);
        g.drawLine(width-19,(height-30)/2 + 45,width-6,(height-30)/2 + 45);
        g.drawString("3", width-15 ,(height-30)/2 + 5 - 30);
        g.drawString("3", width-15 ,(height-30)/2 + 5 + 30);
        g.drawString("1", width-15 ,(height-30)/2 + 5 - 65);
        g.drawString("1", width-15 ,(height-30)/2 + 5 + 65);

        g.setColor(Color.blue);
        g.drawString("Blue: " + player1.getScore(), 
                     15, height-10);

        g.setColor(Color.green.darker());
        g.drawString("Green: " + player2.getScore(), 
                     width-75, height-10);
        
    }


    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {

        // Add code here to limit movement of pong paddles.

        if (e.getKeyChar() == 'a' ||
            e.getKeyChar() == 'A') {
            if (paddle1.getTopEdgePos() > 7) {
                paddle1.moveUp(5);
                repaint();
            }
        }
        else if (e.getKeyChar() == 'z' ||
                 e.getKeyChar() == 'Z') {
            if (paddle1.getBottomEdgePos() < height-30) {
                paddle1.moveDown(5);
                repaint();
            }
        }
        else if (e.getKeyChar() == 'k' ||
                 e.getKeyChar() == 'K') {
            if (paddle2.getTopEdgePos() > 7) {
                paddle2.moveUp(5);
                repaint();
            }
        }
        else if (e.getKeyChar() == 'm' ||
                 e.getKeyChar() == 'M') {
            if (paddle2.getBottomEdgePos() < height-30) {
                paddle2.moveDown(5);
                repaint();
            }
        }             
        else if (e.getKeyChar() == 'b' ||
                 e.getKeyChar() == 'B') {
            ballTimer.scheduleAtFixedRate(new PongBallTimer(ball,this,
                                                            paddle1,
                                                            paddle2,
                                                            player1,
                                                            player2),
                                           0,40);
        }

    }
}
