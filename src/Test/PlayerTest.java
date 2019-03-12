package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import game.Collision;
import game.Direction;
import game.Player;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
@Category(UnitTests.class)
public class PlayerTest {

	Player player;

	Collision bang;
	
	// Skapar en ny Player vid koordinaterna x=500, y=500
	@Before
	public void setUp() throws Exception {
		player = new Player(new Point(500, 500));
		bang = new Collision();
	}



	// Testar s� att Player verkligen initieras korrekt
	@Test
	public void testPlayerInit() {

		// Act
		Rectangle actual = player.getHitBox();
		int actualCoordinateX = actual.x;
		int actualCoordinateY = actual.y;
		// Assert
		assertEquals(500, actualCoordinateX);
		assertEquals(500, actualCoordinateY);
	}



	// Testar s� att Player flyttas �t r�tt h�ll
	// Vid olika direction inputs
	@Test
	@Parameters({
		"LEFT, 498, 500",
		"RIGHT, 502, 500",
		"UP, 500, 498",
		"DOWN, 500, 502",
		"IDLE, 500, 500"
	})
	public void testPlayerMoveLeft(Direction direction, int expectedX, int expectedY) {

		// Act
		player.setDirection(direction);
		player.update(60);
		Point actual = player.getHitBox().getLocation();
		// Assert
		assertEquals(expectedX, actual.x);
		assertEquals(expectedY, actual.y);
	}
	
	@Test
	public void falseTest() {
		Rectangle r1 = new Rectangle(20, 30, 10, 10);  //x, y, b, h
		Rectangle r2 = new Rectangle(60, 60, 10, 10);
		boolean actual = bang.between(r1, r2);
		assertEquals(false, actual);
	}
	@Test
	public void trueTest() {
		Rectangle r1 = new Rectangle(20, 30, 10, 10); //x, y, b, h
		Rectangle r2 = new Rectangle(20, 60, 10, 10);
		boolean actual = bang.between(r1, r2);
		assertEquals(false, actual);
	}
}
