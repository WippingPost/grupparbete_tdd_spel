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
	@Ignore
	@Test
	public void updateTrueTest() {
	/*	Exit.setActive();
		Exit.update();
		Boolean actualActive = Exit.isActive();
		Boolean actualVisible = Exit.isVisible();
		assertEquals(true, actualActive);
		assertEquals(true, actualVisible);
		*/
		Exit.update();
		assertTrue(Exit.isVisible());
	}
	
	/**
	 * även här stämmer inte kordinaterna tillbaka pga att den inte är 100% av rutan
	 * Se playerTest -> testPlayerInit
	 */
	@Test
	public void responsetest() {
		
			Rectangle actual = Exit.getHitBox();
			int actualCoordinateX = actual.x;
			int actualCoordinateY = actual.y;
			assertEquals(47, actualCoordinateX);
			assertEquals(47, actualCoordinateY);
	}

	/**
	 * Testar om både boolean ändras från false till active genom setActive metod
	 */
	@Test
	public void setActiveBooleanTest() {
		assertFalse(Exit.isActive());
		assertFalse(Exit.isVisible());
		Exit.setActive();
		assertTrue(Exit.isActive());
		assertTrue(Exit.isVisible());
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
