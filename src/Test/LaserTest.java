package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.awt.Point;


import game.Laser;

public class LaserTest {
	/*
	 * 
	 * Testar så att vi kan initera en ny laser
	 */
	
	Laser laser;
	
	@Before
	public void init() {
		Point point1 = new Point();
		Point point2 = new Point();
		int gridsize = 1;
		point1.x = 1;
		point2.y = 1;
		laser = new Laser(point1, point2, gridsize);
	}
	
	@Test
	public void testInitNewLaserResponse() {

		assertEquals(1, laser.getPoint1().x);
		assertEquals(1, laser.getPoint2().y);
	}
	
	@Test
	public void updateTrueTest() {
		assertFalse(laser.getUpdate());
		laser.setisActvieTrue();
		laser.update();
		laser.getUpdate();
		assertTrue(laser.getUpdate());
	}

	@Test
	public void updateFalseTest() {
		laser.setupdateTrue();
		assertTrue(laser.getUpdate());
		laser.update();
		assertFalse(laser.getUpdate());
		
	}
	
}
