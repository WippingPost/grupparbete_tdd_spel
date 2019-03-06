package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Treasure {

	private Point position;
	private Rectangle hitBox;
	private boolean visible;
	// Setting the color of the object
	private Color color = new Color(255, 153, 51); 	// Orange

	// Constructor
	public Treasure(Point point) {

		// Setting the size and position of the treasure. Placing it 5 pixels towards the center of grid (gridsize = 20x30)
		hitBox = new Rectangle(point.x + 5, point.y + 5, 20, 20);

		visible = true;		// True as long as not picked up by player

	}


	public void setPickedUp() {
		visible = false;
	}


	public Rectangle getHitBox() {
		return hitBox;
	}


	public Color getColor() {
		return color;
	}

}
