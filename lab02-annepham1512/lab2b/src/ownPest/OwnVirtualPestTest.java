package ownPest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import demoPest.VirtualPest;

class OwnVirtualPestTest { 
	OwnVirtualPest p1;
	OwnVirtualPest p2;
	OwnVirtualPest p3;
	OwnVirtualPest p4;
	OwnVirtualPest p5;
	
	@BeforeEach
	public void setUp() throws Exception {
		p1 = new OwnVirtualPest("John", 1);
		p2 = new OwnVirtualPest("John2", 2);
		p3 = new OwnVirtualPest("John3", 3);
		p4 = new OwnVirtualPest("John4", 4);
		p5 = new OwnVirtualPest("John5", 5);
	}

	@Test
	public void testOwnVirtualPest() {
		assertEquals("John", p1.getName());
		assertEquals(1, p1.getIntState());
	}

	@Test
	public void testGetName() {
		assertEquals("John", p1.getName());
	}

	@Test
	public void testGetIntState() {
		assertEquals(1, p1.getIntState());
	}

	@Test
	public void testGetFile() {
		assertEquals("lab2b/src/ownPest/myImg.jpg", p1.getFile());
	}

	@Test
	public void testGetNumActions() {
		assertEquals(3, p1.getNumActions());
	}

	@Test
	public void testGetState() {
		
		assertEquals("hungry", p1.getState());
		assertEquals("sleepy", p2.getState());
		assertEquals("full", p3.getState());    
		assertEquals("grouchy", p4.getState());    
		assertEquals("error", p5.getState());
	}


	@Test
	public void testGetAction() {
		assertEquals("random", p1.getAction(0));
		assertEquals("feed", p1.getAction(1));
		assertEquals("pet", p1.getAction(2));
		assertEquals("put to bed", p1.getAction(3));
		assertEquals("", p1.getAction(4));
	}

	@Test
	public void testGetPestSound() {
		assertEquals("bark", p1.getPestSound());
		assertEquals("grunting", p2.getPestSound());
		assertEquals("sigh", p3.getPestSound());    
		assertEquals("grrrrr", p4.getPestSound());    
		assertEquals("", p5.getPestSound());
	}
	


	@Test
	public void testGetPestAction() {
		assertEquals("licking lips", p1.getPestAction());
		assertEquals("yawning", p2.getPestAction());
		assertEquals("circling", p3.getPestAction());    
		assertEquals("snapping", p4.getPestAction());    
		assertEquals("", p5.getPestAction());
	}
	
	

	@Test
	public void testDoAction() {
		p1.doAction(0); //invalid action
		assertEquals(1, p1.getIntState());
		p1 = new OwnVirtualPest("John", 1);
		p1.doAction(1);
		assertEquals(3, p1.getIntState());
		p1 = new OwnVirtualPest("John", 1);
		p1.doAction(3);
		assertEquals(4, p1.getIntState());
		
		p2.doAction(1); //invalid action
		assertEquals(2, p2.getIntState());
		p2 = new OwnVirtualPest("John2", 2);
		p2.doAction(0);
		assertEquals(1, p2.getIntState());
		p2 = new OwnVirtualPest("John2", 2);
		p2.doAction(3);
		assertEquals(4, p2.getIntState());
		
		p3.doAction(0); //invalid action
		assertEquals(3, p3.getIntState());
		p3 = new OwnVirtualPest("John3", 3);
		p3.doAction(1);
		assertEquals(4, p3.getIntState());
		p3 = new OwnVirtualPest("John3", 3);
		p3.doAction(2);
		assertEquals(2, p3.getIntState());
		
		p4.doAction(2); //invalid action
		assertEquals(4, p4.getIntState());
		p4 = new OwnVirtualPest("John4", 4);
		p4.doAction(0);
		assertEquals(1, p4.getIntState());
	}

}
