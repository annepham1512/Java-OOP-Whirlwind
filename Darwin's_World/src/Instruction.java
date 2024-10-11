/** 
  * Represent instructions for Darwin's word creature programs.  Each
  * instruction has an operation code (the operation performed by the
  * instruction) and a target, which is the line to jump to for branch
  * instructions.  For nonbranch instructions, the target should be -1.
  * @author Tim Wahls 
  * @version 7/13/2005 */
   
public class Instruction {
    private String opCode; // the operation
    private int target; // target instruction for branching operations

    /** create an instruction with branch target.  Use this constructor
     * for creating branching instructions, such as "go 0" and "ifenemy 10".
     * @param opC the operation contained in the instruction
     * @param targ the target (if the instruction is a branch)
     */
    public Instruction(String opC, int targ) {
        opCode = opC;
        target = targ;
    }

    /** create an instruction.  Use this constructor for creating nonbranch
     *  instructions such as "hop" and "left".
     *  @param opC the operation contained in the instruction
     */
    public Instruction(String opC) {
        opCode = opC;
        target = -1;
    }

    /** 
     * return the opCode of the instruction
     * @return the opCode of the instruction */
    public String getOpCode() {
        return opCode;
    }
    
    /** 
     * return the branch target of the instruction
     * @return the branch target of the instruction */
    public int getTarget() {
        return target;
    }

    /** 
     * return a String representation of the instruction
     * @return a String representation of the instruction */
    /** print the instruction to the terminal window */
    public void print() {
        System.out.print(opCode);
        if (target != -1) {
            System.out.print(" " + target);
        }
        System.out.println();
    }
}
