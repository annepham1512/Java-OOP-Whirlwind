import java.util.ArrayList;

/** 
 * Darwin's world programs are lists of Instruction objects (contained
 * in an ArrayList).
 * 
 * @author Tim Wahls 
 * @author Son Nguyen and Anne Pham
 * @version 02/12/2024
 */
   
public class Program {
	private ArrayList<Instruction> program;
    // declare your fields here

    /** 
     * create an empty program 
     */
    public Program() {
        program = new ArrayList<Instruction>();
    }

    /** add the specified instruction to the end of the program
      * @param instr the instruction */
    public void addInstruction(Instruction instr) {
    	program.add(instr);
    }
    
    /** get the specified instruction by number.  Note that instructions
     *  are numbered starting from 0.  If the instruction number is invalid,
     *  print an error message and return null.
     *  @param index the instruction to fetch */
    public Instruction getInstruction(int index) {
        if (index >= 0 && index < program.size()) {
        	return program.get(index);
        } else {
        	System.out.println("Error: The instruction number is invalid!");
        	return null;
        }
  
    }

    /** return the number of instructions in the program
        @return the number of instructions */
    public int size() {
        return program.size();
    }
    
    /** print all instructions in the program to the terminal window.
     *  Note that class Instruction does have a print method of its own.
     */
    public void print() {
    	for (int i = 0; i < program.size(); i++) {
    		Instruction instruction = program.get(i);
    		instruction.print(); 
    	}
    }
    
}
