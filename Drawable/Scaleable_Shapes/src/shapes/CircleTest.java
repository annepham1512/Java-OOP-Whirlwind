package shapes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class CircleTest {

    private Circle c1;
    
    @BeforeEach
    public void setUp() throws Exception {
        c1 = new Circle(10,20,5,Color.red);
    }

    @Test
    public void testConstructor() {
        assertEquals(10, c1.getX(), "wrong x");
        assertEquals(20, c1.getY(), "wrong y");
        assertEquals(5, c1.getRadius(), "wrong radius");
        assertEquals(Color.red, c1.getColor(), "wrong color");
        assertTrue(c1.isVisible(), "should be visible");
    }
    
    @Test
    public void testSetColor() {
    	assertEquals(Color.red, c1.getColor(), "wrong initial color"); 
        c1.setColor(Color.blue);
        assertEquals(Color.blue, c1.getColor(), "wrong color");     
    }
    
    @Test
    public void testSetVisible() {
        c1.setVisible(false);
        assertFalse(c1.isVisible(), "should be invisible");
        
        c1.setVisible(true);
        assertTrue(c1.isVisible(), "should be visible");
    }
    
    @Test 
    public void testSetRadius() {
        c1.setRadius(10);
        assertEquals(10, c1.getRadius(), "wrong radius");
        
        c1.setRadius(-20);
        assertEquals(20, c1.getRadius(), "wrong radius");
    }
    
    @Test
    public void testMove() {
        c1.move(20,30);
        assertEquals(20, c1.getX(), "wrong x");
        assertEquals(30, c1.getY(), "wrong y");
    }
    
    @Test
    public void testTranslate() {
        c1.translate(5,10);
        assertEquals(15, c1.getX(), "wrong x");
        assertEquals(30, c1.getY(), "wrong y");
    }
    
    @Test
    public void testScale() {
        c1.scale(2.0);
        assertEquals(10, c1.getRadius(), "wrong radius");
        
        c1.scale(0.5);
        assertEquals(5, c1.getRadius(), "wrong radius");
        
        c1.scale(-2.0);
        assertEquals(5, c1.getRadius(), "wrong radius");
    }
}
