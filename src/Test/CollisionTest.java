package Test;

import static org.junit.Assert.*;
import game.*;
import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

public class CollisionTest {

	 Collision bang;
	
	@Before
	public void setUp() throws Exception {
	bang = new Collision();
	}

	//Basic test ingen collision
	@Test
	public void falseTest() {
		Rectangle r1 = new Rectangle(20, 30, 10, 10);  //x, y, b, h
		Rectangle r2 = new Rectangle(60, 60, 10, 10);
		boolean actual = bang.between(r1, r2);
		assertEquals(false, actual);
	}
	
	//Test collision X
	@Test
	public void collisionTrueXTest() {
		Rectangle r1 = new Rectangle(20, 30, 10, 10); //x, y, b, h
		Rectangle r2 = new Rectangle(20, 60, 10, 10);
		boolean actual = bang.between(r1, r2);
		assertEquals(false, actual);
	}

	//Test collision Y
	@Test
	public void collisionTrueYTest() {
		Rectangle r1 = new Rectangle(20, 30, 10, 10); //x, y, b, h
		Rectangle r2 = new Rectangle(60, 30, 10, 10);
		boolean actual = bang.between(r1, r2);
		assertEquals(false, actual);
	}
	
}
