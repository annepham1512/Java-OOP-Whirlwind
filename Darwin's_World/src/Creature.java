import java.io.*;

/**
  * Implements the behavior of Darwin's World Creatures.
  * 
  * @author Tim Wahls
  * @author Son Nguyen and Anne Pham
  * @version 02/12/2024
*/

public class Creature {

    // declare your fields here
	private String species;
	private int xCord;
	private int yCord;
	private int direction;
	private String color;
	private Program program;
	private int counter;

     /** initialize Creature species, color, initial position, direction and
      * program using the parameters, and initialize the program counter to 0.<br>
      * X and Y coordinates default to 1 if parameters are off the world.
      * The horizontal dimensions of the world are from 1 to 21,  
      * and the vertical dimensions are from 1 to 21<br> 
      * direction defaults to 0 ("up") if parameter is not one of 0, 1, 2 or 3
      * The program counter (which keeps track of which instruction in its program
      * the Creature is currently executing) should be initialized to 0 so that
      * the Creature starts executing with the first instruction its program.
      * @param initSpecies the species of the creature
      * @param initX the initial X coordinate
      * @param initY the initial Y coordinate
      * @param initDir the initial direction
      * @param initColor the initial color
      * @param initProgram the initial program
    */ 
    public Creature(String initSpecies, int initX, int initY, int initDir,
                    String initColor, Program initProgram) {
    	species = initSpecies;
    	xCord = initX;
    	yCord = initY;
    	direction = initDir;
    	color = initColor;
    	program = initProgram;   
    	counter = 0;
    }

    /** 
     * Return the direction of the Creature as one of "up", "down",
     *  "right" or "left".  You can assume that the direction will always
     *  be stored as 0 through 3.  A direction of 0 indicates "up", 1 is "right",
     *  2 is "down" and 3 is "left"
     * @return the direction of the Creature as one of "up", "down",
     *  "right" or "left"
     */
    public String getDirection() {
        // replace with your own code
    	String strDir = "";
        if(direction == 0) {
        	strDir = "up";
        } else if(direction == 1) {
        	strDir = "right";
        } else if(direction == 2) {
        	strDir = "down";
        } else if(direction == 3) {
        	strDir = "left";
        }
        return strDir;
    }
  
    /** 
     * Print the species, position, orientation, color and program of the Creature
     * to the terminal window. */

    public void print() {
    	System.out.println("Species: " + getSpecies());
    	System.out.println("X Position: " + getXPos());
    	System.out.println("Y Position: " + getYPos());
    	System.out.println("Orientation: " + getDirection());
    	System.out.println("Color: " + getColor());
    	System.out.println("Program: " + getProgram());
    	
            }
  
    /** 
     * Return the X-coordinate of the Creature
     * @return the X-coordinate of the Creature */
    public int getXPos() {
        // replace with your own code
        return xCord;
    }
    
    /** 
     * Return the Y-coordinate of the Creature
     * @return the Y-coordinate of the Creature */
    public int getYPos() {
        // replace with your own code
        return yCord;
    }
    
    /** 
     * Return the color of the Creature.
     * @return the color of the Creature.*/
    public String getColor() {
        // replace with your own code
        return color;
    }
    
    /** 
     * Return the species of the Creature
     * @return the species of the Creature */
    public String getSpecies() {
        // replace with your own code
        return species;
    }
    
    /** Return the program counter of this creature
     * @return the program counter of this creature
     */
    public int getCounter() {
        // replace with your own code
        return counter;
    }
    
    /** Set the counter of this creature.  If the specified new counter
     * value is not a valid instruction index for this creature's program,
     * display an error message and do not update the counter.
     * @param newValue the new counter value
     */
    public void setCounter(int newValue) {
        if (newValue >= 0 && newValue < program.size()) {
            counter = newValue;
        } else {
            System.out.println("Error: Invalid value!");
        }
    }
    
    /** get the Instruction that this creature is currently executing.
     * Recall that the program counter keeps track of which instruction the
     * creature is currently executing.  If the program does not contain any
     * instructions, this method should return null.
     * Hint: to test this method, you should call setCounter to set the counter
     * to something other than 0, and then call this method and check that the instruction
     * returned is the expected one.
     * @return the current instruction.
     */
    public Instruction getCurrentInstruction() {
        if (program.size() > 0) {
        	return program.getInstruction(counter);
        } else {
        	return null;
        }
    }
    
    /** Return the program of this creature
     * @return the program of this creature
     */
    public Program getProgram() {
        // replace with your own code
        return program;
    }
    
    /** Turn 90 degrees to the left */
    public void turnLeft() {
    	if (direction > 0) {
    		direction -= 1;
    	} else {
    		direction = 3;
    	}
    }
    

    /** Turn 90 degrees to the right */
    public void turnRight() {
        if (direction < 3) {
        	direction += 1;
        } else {
        	direction = 0;
        }
    }

  
    /** Return the X-coordinate of the square directly in front of the creature
     *  @return the X-coordinate directly in front of the creature
     */
    public int getXFront() {
    	int xFront = 0;
    	if(direction == 0) {
    		xFront = xCord;
        } else if(direction == 1) {
        	xFront = xCord + 1;
        } else if(direction == 2) {
        	xFront = xCord;
        } else if(direction == 3) {
        	xFront = xCord - 1;
        }
    	return xFront;
    }

    /** Return the Y-coordinate of the square directly in front of the creature 
     * @return the Y-coordinate directly in front of the creature
     */
    public int getYFront() {
    	int yFront = 0;
    	if(direction == 0) {
    		yFront = yCord - 1;
        } else if(direction == 1) {
        	yFront = yCord;
        } else if(direction == 2) {
        	yFront = yCord + 1;
        } else if(direction == 3) {
        	yFront = yCord;
        }
    	return yFront;
    }

    /** Hop forward.  You can assume that the square in front of the
     *  creature is empty.
     */
    public void hop() {
    	if (getDirection() == "up" || getDirection() == "down") {
        	yCord = getYFront();
    	} else {
    		xCord = getXFront();
    	}
    	
    }
   
    

}
