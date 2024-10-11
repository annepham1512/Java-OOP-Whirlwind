
package shapes;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

public class PointTest {

    private Point p1;
    
    @BeforeEach
    public void setUp() throws Exception {
        p1 = new Point(10,20,Color.red);
    }

    @Test
    public void testConstructor() {
        assertEquals(10, p1.getX());
        assertEquals(20, p1.getY());
        assertEquals(Color.red, p1.getColor());
        assertTrue(p1.isVisible());
    }
    
    @Test
    public void testSetColor() {
        p1.setColor(Color.blue);
        assertEquals(Color.blue, p1.getColor());     
    }
    
    @Test
    public void testSetVisible() {
        p1.setVisible(false);
        assertFalse(p1.isVisible());
        
        p1.setVisible(true);
        assertTrue(p1.isVisible());
    }
    
    @Test
    public void testMove() {
        p1.move(20,30);
        assertEquals(20, p1.getX());
        assertEquals(30, p1.getY());
    }
    
    @Test
    public void testTranslate() {
        p1.translate(5,10);
        assertEquals(15, p1.getX());
        assertEquals(30, p1.getY());
    }
}
