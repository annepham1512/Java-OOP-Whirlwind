package pong;

/**
 * This class describes objects that are used to track
 * the player's scores in a game of Pong.  Code within
 * the PongGame class constructs two new PongScore 
 * objects and uses them to keep track of the score for
 * each of the players in the game.  Each time the PongBall
 * contacts the wall behind a players' paddle, code within
 * the PongGame will call the scorePoints method on the 
 * appropriate PongScore object passing the number of points
 * scored as the actual parameter.
 * 
 * @author Grant Braught, edited by John MacCormick
 * 
 * @author Anne Pham and Ben Brandt
 * @version 01/29/2024
 */
public class PongScore {
    
    // Define the field(s) for your PongScore objects here.
	int score;
    
    /**
     * Construct a new PongScore object with an initial 
     * score of zero points.
     */
    public PongScore() {
        // Initialize the state of your PongScore object here.  
    	score = 0;
    }

    /**
     * Return the current score of this PongScore object.
     */
    public int getScore() {
        // Change this to return the actual score for this
        // PongScore object.    
        return score;
    }

    /**
     * Increase the current score of this PongScore object
     * by points.
     * 
     * @param points the number of points to add to this
     * PongScore object.
     */
    public void scorePoints(int points) {
        // Add code here to increase the score of this
        // PongScore object by the specified number of 
        // points.
    	score += points;
    }
}


