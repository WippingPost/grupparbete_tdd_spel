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
	 *  Testar metoden resetPositon så att spelaren får en reset till tidigare X och Y position
	 *  
	 */

	@Test
	public void testResetPosition() {

		// Act
		Player p = new Player(new Point(500, 500), 30);
		Direction directionRIGHT = Direction.RIGHT;
		p.setDirection(directionRIGHT);
		p.update(60);
		Rectangle firstActual = p.getHitBox();
		int FirstmoveX = firstActual.x;
		int FirstMoveY = firstActual.y;
		p.setDirection(directionRIGHT);
		p.update(60);
		p.resetPosition();
		Rectangle actual = p.getHitBox();
		int actualCoordinateX = actual.x;
		int actualCoordinateY = actual.y;
		// Assert
		assertEquals(FirstmoveX, actualCoordinateX);
		assertEquals(FirstMoveY, actualCoordinateY);
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
		assertEquals(503, actualCoordinateX);
		assertEquals(503, actualCoordinateY);
	}



	/**
	 *  Testar så att Player flyttas åt rätt håll
	 *  Vid olika direction inputs
	 *  Se testPlayerInit för att se varför x, y ej stämmer helt korrekt 
	 */
	// 
	@Test
	@Parameters({
		"LEFT, 501, 503",
		"RIGHT, 505, 503",
		"UP, 503, 501",
		"DOWN, 503, 505",
		"IDLE, 503, 503"
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
