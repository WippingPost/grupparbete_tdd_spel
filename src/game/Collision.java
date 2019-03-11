package game;

import java.awt.Rectangle;

public class Collision {

	public boolean between(Rectangle hitBox1, Rectangle hitBox2) {

		/*
		 * Returns true if hitBox1 is intersecting hitBox2
		 */
		return (hitBox1.intersects(hitBox2));
	}
}
