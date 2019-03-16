package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Treasure {

	private int x, y;
	private float width, height;
	private Rectangle hitBox, smallHitBox, largeHitBox;
	private boolean visible;
	private long nowTime, deltaTime;
	// Setting the color of the object
	private Color color = new Color(255, 153, 51); 	// Orange

	// Constructor
	public Treasure(Point point, int gridSize) {

		// Size of small game object
		height = (gridSize * 0.50f);
		width = (gridSize * 0.30f);

		// Centering the Object in the grid
		x = (int)(point.x + (gridSize - width) / 2);
		y = (int)(point.y + (gridSize - height) / 2);

		// Setting the small hitBox
		smallHitBox = new Rectangle(x, y, (int)width, (int)height);

		// Size of large game object
		height = (gridSize * 0.55f);
		width = (gridSize * 0.34f);

		// Centering the Object in the grid
		x = (int)(point.x + (gridSize - width) / 2);
		y = (int)(point.y + (gridSize - height) / 2);

		// Setting the large hitBox
		largeHitBox = new Rectangle(x, y, (int)width, (int)height);


		// Setting the size and position of the treasure beginning with a small
		hitBox = new Rectangle(smallHitBox);

		visible = true;		// True as long as not picked up by player

	}

	public void update() {

		nowTime = System.currentTimeMillis();

		// Causing the game object to flash...
		if (hitBox.equals(smallHitBox) && (nowTime - deltaTime) > 500) {
			hitBox = largeHitBox;
			deltaTime = System.currentTimeMillis();

		} else if (hitBox == largeHitBox && (nowTime - deltaTime) > 85) {
			hitBox = smallHitBox;
			deltaTime = System.currentTimeMillis();
		}
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
