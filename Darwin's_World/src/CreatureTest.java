import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreatureTest {
	Creature c0;
	Creature c1;
	Creature c2;
	Creature c3;
	Program p;

	@BeforeEach
	void setUp() throws Exception {
		p = new Program();
		c0 = new Creature("Flytrap", 5, 6, 0, "green", p);
		c1 = new Creature("Food", 6, 7, 1, "blue", p);
		c2 = new Creature("Hop", 7, 8, 2, "pink", p);
		c3 = new Creature("Rover", 8, 9, 3, "red", p);
	}

	@Test
	void testCreature() {
		assertEquals("Flytrap", c0.getSpecies());
		assertEquals(5, c0.getXPos());
		assertEquals(6, c0.getYPos());
		assertEquals("up", c0.getDirection());
		assertEquals("green", c0.getColor());
		assertEquals(p, c0.getProgram());
	}

	@Test
	void testGetDirection() {
		assertEquals("up", c0.getDirection());
		assertEquals("right", c1.getDirection());
		assertEquals("down", c2.getDirection());
		assertEquals("left", c3.getDirection());
	}

	

	@Test
	void testGetXPos() {
		assertEquals(5, c0.getXPos());
	}

	@Test
	void testGetYPos() {
		assertEquals(6, c0.getYPos());
	}

	@Test
	void testGetColor() {
		assertEquals("green", c0.getColor());
	}

	@Test
	void testGetSpecies() {
		assertEquals("Flytrap", c0.getSpecies());
	}

	@Test
	void testGetCounter() {
		assertEquals(0, c0.getCounter());
	}

	@Test
	void testSetCounter() {
		Instruction i1 = new Instruction("ifenemy", 3);
		Instruction i2 = new Instruction("left");
		Instruction i3 = new Instruction("go", 0);
		Instruction i4 = new Instruction("infect");
		Instruction i5 = new Instruction("go", 0);
		p.addInstruction(i1);
		p.addInstruction(i2);
		p.addInstruction(i3);
		p.addInstruction(i4);
		p.addInstruction(i5);
		c0.setCounter(2);
		assertEquals(2, c0.getCounter());
		c0.setCounter(-1);//invalid counter
		assertEquals(2, c0.getCounter());
		c0.setCounter(6);//invalid counter
		assertEquals(2, c0.getCounter()); 
	}


	@Test
	void testGetCurrentInstruction() {
		Instruction i1 = new Instruction("ifenemy", 3);
		c0.setCounter(0);
		assertEquals(null, c0.getCurrentInstruction()); //the program is empty
		p.addInstruction(i1);
		assertEquals(i1, c0.getCurrentInstruction());
	}


	@Test
	void testGetProgram() {
		assertEquals(p, c0.getProgram());
	}


	@Test
	void testTurnLeft() {
		c0.turnLeft();
		assertEquals("left", c0.getDirection());
		c1.turnLeft();
		assertEquals("up", c1.getDirection());
		c2.turnLeft();
		assertEquals("right", c2.getDirection());
		c3.turnLeft();
		assertEquals("down", c3.getDirection());
	}
	
	
	@Test
	void testTurnRight() {
		c0.turnRight(); 
		assertEquals("right", c0.getDirection());
		c1.turnRight();
		assertEquals("down", c1.getDirection());
		c2.turnRight();
		assertEquals("left", c2.getDirection());
		c3.turnRight();
		assertEquals("up", c3.getDirection());
	}


	@Test 
	void testGetXFront() {
		assertEquals(5, c0.getXFront());
		assertEquals(7, c1.getXFront());
		assertEquals(7, c2.getXFront());
		assertEquals(7, c3.getXFront());
	}

	@Test
	void testGetYFront() {
		assertEquals(5, c0.getYFront());
		assertEquals(7, c1.getYFront());
		assertEquals(9, c2.getYFront());
		assertEquals(9, c3.getYFront());
	}

	@Test
	void testHop() {
	c0.hop();
	assertEquals(5, c0.getXPos());
	assertEquals(5, c0.getYPos());
	
	c1.hop();
	assertEquals(7, c1.getXPos());
	assertEquals(7, c1.getYPos());
	
	c2.hop();
	assertEquals(7, c2.getXPos());
	assertEquals(9, c2.getYPos());
	
	c3.hop();
	assertEquals(7, c3.getXPos());
	assertEquals(9, c3.getYPos());
	}
	
	@Test
	void testPrint() {
		c0.print();
		System.out.println("");
		System.out.println("Species: Flytrap\n"
				+ "X Position: 5\n"
				+ "Y Position: 6\n"
				+ "Orientation: up\n"
				+ "Color: green\n"
				+ "Program: Program@1c655221");
		// Looking at the results on the console to test print
	}
}
