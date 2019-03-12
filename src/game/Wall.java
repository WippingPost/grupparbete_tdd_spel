package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Wall {

	private int width, height, x, y;
	private Rectangle hitBox;
	// Setting the color of the object
	private Color color = new Color(64, 64, 64); 	// Gray

	public Wall(Point point, int gridSize) {

		// Scaling object to fit screen size
		height = (int)(gridSize * 0.9f);	// Making it a little smaller than gridSize
		width = height;
		// Centering the wall in the grid
		x = point.x + (gridSize - width) / 2;
		y = point.y + (gridSize - height) / 2;

		// Setting the size and position of the wall.
		hitBox = new Rectangle(x, y, width, height);

	}

	public Rectangle getHitBox() {
		return hitBox;
	}


	public Color getColor() {
		return color;
	}
}
