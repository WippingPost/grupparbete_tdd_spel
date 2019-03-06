package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Wall {

	private Point position;
	private Rectangle hitBox;
	// Setting the color of the object
	private Color color = new Color(64, 64, 64); 	// Gray

	public Wall(Point point) {

		// Setting the size and position of the wall. Placing it 2 pixels towards the center of grid (gridsize = 20x30)
		hitBox = new Rectangle(point.x + 2, point.y + 2, 26, 26);

	}

	public Rectangle getHitBox() {
		return hitBox;
	}


	public Color getColor() {
		return color;
	}
}
