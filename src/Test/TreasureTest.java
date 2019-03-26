package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import game.Treasure;


public class TreasureTest {
	
	Treasure treasure;
	
	@Before
	public void init() {
	treasure = new Treasure(new Point(500, 500), 30);
	}
	/**
	 * även här stämmer inte kordinaterna tillbaka pga att den är 0.4 och 0.5 av rutan
	 * Se playerTest -> testPlayerInit
	 */
	
	@Test
	public void testTreasureInit() {
		Rectangle actual = treasure.getHitBox();
		int actualCoordinateX = actual.x;
		int actualCoordinateY = actual.y;
		assertEquals(506, actualCoordinateX);
		assertEquals(506, actualCoordinateY);
	}
	
	//testar om bolean visible blir false när man använder metoden setPickedUp.
	@Test
	public void pickUpTest() {
		assertTrue(treasure.isActive());
		treasure.setPickedUp();
		assertFalse(treasure.isActive());
	}
}
	

