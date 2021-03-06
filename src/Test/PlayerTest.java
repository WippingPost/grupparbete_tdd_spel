package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import game.Direction;
import game.Player;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
@Category(UnitTests.class)
public class PlayerTest {

	Player player;
	
	// Skapar en ny Player vid koordinaterna x=500, y=500
	@Before
	public void setUp() throws Exception {
		player = new Player(new Point(500, 500), 30);
	}



	/**
	 *  Testar så att Player verkligen initieras korrekt
	 *  Stämmer inte helt korrekt då player är 0.8 av rektangeln (se Player->player metod). 
	 */
	@Test
	public void testPlayerInit() {

		// Act
		Rectangle actual = player.getHitBox();
		int actualCoordinateX = actual.x;
		int actualCoordinateY = actual.y;
		// Assert
		assertEquals(505, actualCoordinateX);
		assertEquals(505, actualCoordinateY);
	}



	/**
	 *  Testar så att Player flyttas åt rätt håll
	 *  Vid olika direction inputs
	 *  Se testPlayerInit för att se varför x, y ej stämmer helt korrekt 
	 */
	// 
	@Test
	@Parameters({
		"LEFT, 502, 505",
		"RIGHT, 508, 505",
		"UP, 505, 502",
		"DOWN, 505, 508",
		"IDLE, 505, 505"
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
	public void collisionWithBoxFalseTest() {
		Rectangle rectangle = new Rectangle(50, 30, 10, 10);  //x, y, b, h
		assertFalse(player.collideWith(rectangle));
	}
	@Test
	public void collisionWithBoxTrueTest() {
		Rectangle rectangle = new Rectangle(500, 500, 10, 10);
		assertTrue(player.collideWith(rectangle));
	}
	@Test
	public void collisionWithLaserFalseTest() {
		Rectangle rectangle = new Rectangle(50, 30, 10, 10);  //x, y, b, h
		assertFalse(player.collideWith(rectangle));
	}
	@Test
	public void collisionWithLaserTrueTest() {
		Rectangle rectangle = new Rectangle(500, 500, 10, 10);
		assertTrue(player.collideWith(rectangle));
	}
}
