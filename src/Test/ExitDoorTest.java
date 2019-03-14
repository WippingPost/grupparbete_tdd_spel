package Test;

import static org.junit.Assert.*;

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
	public void updateFalselTest() {
		Exit.update();
		Boolean actual = Exit.isActive();
		assertEquals(false, actual);
	}
	/**
	 * Försökte göra ett test för att testa else if satsen i update. Tänkte fysiskt ändra
	 * visible = false för att se om jag byggt upp det rätt, men funkar inte.
	 */
	@Test
	public void updateTrueTest() {
		//Exit.setActive();
		Exit.update();
		Boolean actualActive = Exit.isActive();
		Boolean actualVisible = Exit.isVisible();
		// assertEquals(true, actualActive);
		assertEquals(true, actualVisible);
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
	 * Testar om både boolean ändras från false till active genom setActive metod
	 */
	@Test
	public void setActiveBooleanTest() {
		boolean falselIsActive = Exit.isActive();
		boolean falselIsVisible = Exit.isVisible();	
		assertEquals(false, falselIsActive);
		assertEquals(false, falselIsVisible);
		Exit.setActive();
		boolean actualIsActive = Exit.isActive();
		boolean actualIsVisible = Exit.isVisible();
		assertEquals(true, actualIsActive);
		assertEquals(true, actualIsVisible);
	}
	
	/**
	 * Kontrollerar contains vad den returnerar
	 */
	@Test
	public void containsTest() {
		Rectangle contains = new Rectangle(50, 50, 30, 30); //rektangel innanför
		Rectangle NOTcontains = new Rectangle(150, 150, 10, 10); //rektangel utanför 
		assertTrue(Exit.contains(contains));
		assertFalse(Exit.contains(NOTcontains));
	}
}
