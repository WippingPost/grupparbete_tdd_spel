package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class ExitDoor {

	private Point position;
	private Rectangle hitBox;
	private boolean isActive;
	// Setting the color of the door
	private Color color = new Color(0, 153, 0); // Green


	// Creating door at position
	public ExitDoor(Point point) {

		// Setting the size and position of the door. Placing it 3 pixels towards the center of grid (gridsize = 30x30)
		hitBox = new Rectangle(point.x + 3, point.y + 3, 24, 24);

		isActive= false;

	}


	// When player has picked up all collectables
	public void setPickedUp() {
		isActive = true;
	}


	public Rectangle getHitBox() {
		return hitBox;
	}


	public Color getColor() {
		return color;
	}

}
