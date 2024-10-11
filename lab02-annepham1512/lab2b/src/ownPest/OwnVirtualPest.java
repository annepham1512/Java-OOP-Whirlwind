package ownPest;

/** 
 * This class implements the behavior of virtual pests that annoy their
 * owner.
 * 
 * @author Tim Wahls
 * @author Anne Pham and Ben Brandt
 * @version 02/05/2024
 */

public class OwnVirtualPest {

    private String name; // pest name
    private int state; // current state of the pest

    /** 
     * initialize Virtual Pest name and state
     * @param initName the name of the pest
     * @param initState the initial state of the Pest
     */
    public OwnVirtualPest(String initName, int initState) {
        name = initName;
        state = initState;
    }
  
    /** 
     * get the name of the Pest
     * @return the name of the Pest
     */
    public String getName() {
        return name;
    }
  
    /** 
     * get the state of the Pest (as an int)
     * @return the state of the Pest
     */
    public int getIntState() {
        return state;
    }
    
    /** 
     * returns the file name for the Pest image.  The file must be located  
     * in the DemoPest project directory.
     * @return the file name for the Pest image
     */
    public String getFile() {
        return "lab2b/src/ownPest/myImg.jpg";
    }
    
    /** 
     * returns the number of actions that the Pest responds to (NOT including
     * the random action)
     * @return the number of actions
     */
    public int getNumActions() {
        return 3;
    }
    
    /** 
     * return the current state of the Pest (as a String)
     * if the current state is invalid, this method returns "error"
     * @return the current state of the Pest
     */
    public String getState() {
    	String stringState = "";
    	if(state == 1) {
    		stringState = "hungry";
    	} else if(state == 2) {
    		stringState = "sleepy";
    	} else if(state == 3) {
    		stringState = "full";
    	} else if(state == 4) {
    		stringState = "grouchy";
    	} else {
    		stringState = "error";
    	}
        return stringState;
    }
    
    /** 
     * returns the actionNumth action that can be applied to the Pest 
     * (not including random)
     * if actionNum does not correspond to a valid action, this method returns
     * the empty string ""
     * @param actionNum the number of the requested action
     * @return the name of the actionNumth action
     */
    public String getAction(int actionNum) {
    	String action = "";
    	if(actionNum == 0) {
    		action = "random";
    	} else if(actionNum == 1) {
    		action = "feed";
    	} else if(actionNum == 2) {
    		action = "pet";
    	} else if(actionNum == 3) {
    		action = "put to bed";
    	} else {
    		action = "";
    	}       
        return action;
    }
  
    /**
     * return the sound that the pest makes in its current state
     * if the state is invalid, this method returns the empty string ""
     * @return the pest sound in the current state
     */
    public String getPestSound() {
    	String sound = "";
    	if(state == 1) {
    		sound = "bark";
    	} else if(state == 2) {
    		sound = "grunting";
    	} else if(state == 3) {
    		sound = "sigh";
    	} else if(state == 4) {
    		sound = "grrrrr";
    	} else {
    		sound = "";
    	}       
        return sound;
    }
  
    /** 
     * return how the Pest acts in its current state
     * if the state is invalid, this method returns the empty string ""
     * @return the pest action in the current state
     */  
    public String getPestAction() {
    	String action = "";
    	if(state == 1) {
    		action = "licking lips";
    	} else if(state == 2) {
    		action = "yawning";
    	} else if(state == 3) {
    		action = "circling";
    	} else if(state == 4) {
    		action = "snapping";
    	} else {
    		action = "";
    	}       
        return action;
    }

   
   /** 
    * change the state of the Pest in response to the specified owner action 
    * (and also the random action).  If the action is not valid, the state
    * of the pet does not change
    * @param action the action that the Pest responds to 
    */
    public void doAction(int action) {
        if(state == 1) {
        	if(action == 1) {
        		state = 3;
        	} else if(action == 3) {
        		state = 4;
        	} else {
        		state = 1;
        	}
        } else if(state == 2) {
        	if(action == 0) {
        		state = 1;
        	} else if(action == 3) {
        		state = 4;
        	} else {
        		state = 2;
        	} 
        } else if(state == 3) {
        	if(action == 1) {
        		state = 4;
        	} else if(action == 2) {
        		state = 2;
        	} else {
        		state = 3;
        	} 
        } else if(state == 4) {
        	if(action == 0) {
        		state = 1;
        	} else {
        		state = 4;
        	}
        }
    }

}

