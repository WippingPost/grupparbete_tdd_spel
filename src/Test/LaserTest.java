package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Point;


import game.Laser;

public class LaserTest {
	/*
	 * 
	 * Testar så att vi kan initera en ny laser
	 */
	
	@Test
	public void testInitNewLaser() {

		// Act
		Point point1 = new Point();
		Point point2 = new Point();
		int gridsize = 1;
		point1.x = 1;
		point2.y = 1;
		Laser laser = new Laser(point1, point2, gridsize);
		// Assert
		assertEquals(1, laser.getPoint1().x);
		assertEquals(1, laser.getPoint2().y);
	}

	
}
