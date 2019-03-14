package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Test;

import game.Treasure;


public class TreasureTest {

	Treasure treasure;
	/**
	 * även här stämmer inte kordinaterna tillbaka pga att den är 0.4 och 0.5 av rutan
	 * Se playerTest -> testPlayerInit
	 */
	
	@Test
	public void testTreasureInit() {
		treasure = new Treasure(new Point(500, 500), 30);
		Rectangle actual = treasure.getHitBox();
		int actualCoordinateX = actual.x;
		int actualCoordinateY = actual.y;
		assertEquals(509, actualCoordinateX);
		assertEquals(507, actualCoordinateY);
	}
}
	

