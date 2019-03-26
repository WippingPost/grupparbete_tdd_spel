package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import game.Ground;

public class GroundTest {

	Ground ground;
		
	@Before
	public void init() {
		ground = new Ground (new Point(), 30) ;
	}
	
	
	
	@Test
	public void responsetest() {
		
			Rectangle actual = ground.getHitBox();
			int actualCoordinateX = actual.x;
			int actualCoordinateY = actual.y;
			assertEquals(0, actualCoordinateX);
			assertEquals(0, actualCoordinateY);
	}
}
