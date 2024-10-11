package pong;

/**
 * This class describes an object that is used to
 * represent the ball in a game of pong.  Code in the
 * PongGame class constructs a PongBall object that is
 * used to keep track of the position and speed of the
 * ball on the screen. Each time the ball is to be moved,
 * code within the PongGame calls the move method.  Each
 * time the ball collides with a wall or a paddle, code
 * within the PongGame calls the horizBounce or vertBounce
 * methods, as appropriate.
 * 
 * @author Grant Braught, edited by John MacCormick
 * 
 * @author Anne Pham and Ben Brandt
 * @version 01/29/2024
 */
public class PongBall {

    // Define the field(s) for your PongBall here.
    // Hint: You need fields to keep track of the horizontal 
    //       and vertical position of the ball as well as its 
    //       horizontal and vertical speed.
	private int horizPos;
	private int vertPos;
	private int horizSpeed;
	private int vertSpeed;
  
    /**
     * Construct a new PongBall with the specified initial
     * positions and speeds.
     * 
     * @param initHorizPos the initial X position of this PongBall.
     * @param initVertPos the initial Y position of this PongBall.
     * @param initHorizSpeed the initial horizontal speed of this PongBall.
     * @param initVertSpeed the initial vertical speed of this PongBall.
     */
    public PongBall(int initHorizPos, int initVertPos, 
                    int initHorizSpeed, int initVertSpeed) {
        // Initialize the fields of your PongBall here.
    	horizPos = initHorizPos;
    	vertPos = initVertPos;
    	horizSpeed = initHorizSpeed;
    	vertSpeed = initVertSpeed;
    }

    /**
     * Move this PongBall. Each time a PongBall is moved, its
     * horizontal speed is added to its horizontal position and
     * its vertical speed is added to its vertical position.
     * 
     * Note that code in the PongGame class invokes this method 
     * each time the the ball is to be moved.
     */
    public void move() {
        // Update the horizontal and vertical position of the PongBall here.
    	horizPos += horizSpeed;
    	vertPos += vertSpeed;
    }

    /**
     * This PongBall has collided with a vertical obstacle (wall or paddle)
     * so its direction of horizontal travel must be reversed.
     * The direction of travel can be reversed by changing the sign of 
     * the value in the field that indicates the horizontal speed of the ball.
     * 
     * NOTE: The PongGame invokes this method each time this PongBall
     * collides with a vertical obstruction such as a wall or paddle.
     */
    public void horizBounce() {
        // Add your code here.
    	horizSpeed = - horizSpeed;
    }

    /**
     * This PongBall has collided with a horizontal obstacle (wall or paddle)
     * so is direction of vertical travel must be reversed.
     * The direction of vertical travel can be reversed by changing the
     * sign of the value in the field that indicates the vertical speed 
     * of the ball.
     */
    public void vertBounce() {
        // Add your code here.
    	vertSpeed = -vertSpeed;
    }

    /**
     * Return the current horizontal position of this PongBall.
     * 
     * @return the horizontal position.
     */
    public int getHorizPos() {
        // Change this to return the actual horizontal position of
        // this PongBall.
        return horizPos;
    }

    /**
     * Return the current vertical position of this PongBall.
     * 
     * @return the vertical position.
     */
    public int getVertPos() {
         // Change this to return the actual vertical position of
         // this PongBall.
        return vertPos;
    }
    
    /**
     * Return the current horizontal speed of this PongBall.
     * 
     * @return the horizontal speed.
     */
    public int getHorizSpeed() {
         // Change this to return the actual horizontal speed of
        // this PongBall.
        return horizSpeed;
    }
    
    /**
     * Return the current vertical speed of this PongBall.
     * 
     * @return the vertical speed.
     */
    public int getVertSpeed() {    
        // Change this to return the actual vertical speed of
        // this PongBall.    
        return vertSpeed;
    }
}
