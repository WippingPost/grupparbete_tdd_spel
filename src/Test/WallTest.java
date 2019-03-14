package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Test;

import game.Wall;

public class WallTest {

	Wall wall;
	
	
	/**
	 * även här stämmer inte kordinaterna tillbaka pga att den är 0.9 av rutan
	 * Se playerTest -> testPlayerInit
	 */
	@Test
	public void testWallInit() {
		wall = new Wall(new Point(500, 500), 30);
		Rectangle actual = wall.getHitBox();
		int actualCoordinateX = actual.x;
		int actualCoordinateY = actual.y;
		assertEquals(501, actualCoordinateX);
		assertEquals(501, actualCoordinateY);
	}
}
