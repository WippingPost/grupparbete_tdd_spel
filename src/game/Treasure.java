package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Treasure {

	private int x, y, width, height;
	private Rectangle hitBox;
	private boolean visible;
	// Setting the color of the object
	private Color color = new Color(255, 153, 51); 	// Orange

	// Constructor
	public Treasure(Point point, int gridSize) {

		// Size of game object
		height = (int)(gridSize * 0.5f);
		width = (int)(gridSize * 0.4f);

		// Centering the Object in the grid
		x = point.x + (gridSize - width) / 2;
		y = point.y + (gridSize - height) / 2;

		// Setting the size and position of the treasure. Placing it 5 pixels towards the center of grid (gridsize = 30x30)
		hitBox = new Rectangle(x, y, width, height);

		visible = true;		// True as long as not picked up by player

	}


	public void setPickedUp() {
		visible = false;
	}


	public boolean isActive() {
		return visible;
	}


	public Rectangle getHitBox() {
		return hitBox;
	}


	public Color getColor() {
		return color;
	}

}
