package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.*;

import game.ExitDoor;
;

public class ExitDoorTest {
	

	ExitDoor Exit;
	
	@Before
	public void init() {
	Exit = new ExitDoor(new Point(50, 50), 30);
	}
	
	@Test
	public void responsetest() {
		
			Rectangle actual = Exit.getHitBox();
			int actualCoordinateX = actual.x;
			int actualCoordinateY = actual.y;
			assertEquals(50, actualCoordinateX);
			assertEquals(50, actualCoordinateY);
	}

	/**
	 * Testar om både boolean ändras från false till active
	 */
	@Test
	public void active() {
		boolean falselIsActive = Exit.isActive();
		boolean falselIsVisible = Exit.isVisible();	
		assertEquals(true, falselIsActive);
		assertEquals(false, falselIsVisible);
		Exit.setActive();
		boolean actualIsActive = Exit.isActive();
		boolean actualIsVisible = Exit.isVisible();
		assertEquals(true, actualIsActive);
		assertEquals(true, actualIsVisible);
	}
}
