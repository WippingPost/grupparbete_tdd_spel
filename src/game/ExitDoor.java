package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class ExitDoor {

	private int x, y, width, height;
	private Rectangle hitBox;
	private boolean isActive;
	// Setting the color of the door
	private Color color = new Color(0, 153, 0); // Green


	// Creating door at position
	public ExitDoor(Point point, int gridSize) {

		// Size of game object
		height = (int)(gridSize * 0.9f);
		width = (int)(gridSize * 0.9f);

		// Centering the Object in the grid
		x = point.x + (gridSize - width) / 2;
		y = point.y + (gridSize - height) / 2;

		// Setting the size and position of the door. Placing it 3 pixels towards the center of grid (gridsize = 30x30)
		hitBox = new Rectangle(x, y, width, height);

		isActive= false;

	}


	// When player has picked up all collectables
	public void setActive() {
		isActive = true;
	}


	public Rectangle getHitBox() {
		return hitBox;
	}


	public Color getColor() {
		return color;
	}

}
