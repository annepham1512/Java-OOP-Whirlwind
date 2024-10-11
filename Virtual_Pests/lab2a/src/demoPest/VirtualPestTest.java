package demoPest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VirtualPestTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor()
	{
		VirtualPest virtualP1 = new VirtualPest("Fred", 1);
		assertEquals("Fred", virtualP1.getName());
		assertEquals(1, virtualP1.getIntState());
	}
	
	@Test
	public void testGetFile()
	{
		VirtualPest virtualP1 = new VirtualPest("Fred", 1);
		assertEquals("lab2a/src/demoPest/fish.jpg", virtualP1.getFile());
    }

	@Test
	public void testGetNumActions()
	{
		VirtualPest virtualP1 = new VirtualPest("Fred", 1);
		assertEquals(3, virtualP1.getNumActions());
	}
    
	@Test
    public void testGetState()
	{
		VirtualPest virtualP2 = new VirtualPest("Fred", 1);
		assertEquals("hungry", virtualP2.getState());
	    virtualP2 = new VirtualPest("Fred", 2);
		assertEquals("sleepy", virtualP2.getState());
	    virtualP2 = new VirtualPest("Fred", 3);
		assertEquals("full", virtualP2.getState());    
	    virtualP2 = new VirtualPest("Fred", 4);
		assertEquals("grouchy", virtualP2.getState());    
	    virtualP2 = new VirtualPest("Fred", 5);
		assertEquals("error", virtualP2.getState());
    }
     
	@Test
    public void testGetAction()
	{
		VirtualPest virtualP2 = new VirtualPest("Fred", 1);
		assertEquals("feed", virtualP2.getAction(1));
		assertEquals("pet", virtualP2.getAction(2));
		assertEquals("put to bed", virtualP2.getAction(3));
		assertEquals("", virtualP2.getAction(4));
    }


}
