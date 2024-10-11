import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProgramTest {
	private Program p;
	private Instruction i1;
	private Instruction i2;
	
	@BeforeEach
	void setUp() {
		p = new Program();
		i1 = new Instruction("ifenemy", 3);
		i2 = new Instruction("left");
	}

	@Test
	public void testProgram() {
		assertEquals(0, p.size());
	}
	@Test
	public void testaddInstruction() {
		p.addInstruction(i1);
		p.addInstruction(i2);
		assertEquals(2, p.size());
		assertEquals(i1, p.getInstruction(0));
		assertEquals(i2, p.getInstruction(1));	
	}
	
	@Test
	public void testgetInstruction() {
		p.addInstruction(i1);
		assertEquals(i1, p.getInstruction(0));
		assertEquals(null, p.getInstruction(5)); //invalid index
		assertEquals(null, p.getInstruction(-1)); //invalid index
	}
	
	@Test
	public void testSize() {
		p.addInstruction(i1);
		assertEquals(p.size(), 1);

	}
	
	@Test
	public void testPrint() {
		
		p.addInstruction(i1);
		p.print();
		System.out.println("ifenemy 3");
		// Testing if p1.print is the same as in the console		
	}
}
		
	

