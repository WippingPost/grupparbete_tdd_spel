package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class ExitDoor {

	private int x, y, width, height;
	private Rectangle hitBox;
	private boolean isActive, visible;
	private long timeNow, spawnTime, deltaTime;  // Timers for door blinking when spawning
	private final int BLINK_TIME = 5000;
	private final int BLINK_INTERVAL = 300;
	// Setting the color of the door
	private Color color = new Color(51, 102, 0); // Green


	// Creating door at position
	public ExitDoor(Point point, int gridSize) {

		// Size of game object
		//height = (int)(gridSize * 0.9f);
		//width = (int)(gridSize * 0.9f);
		height = (int)(gridSize * 1.2f);
		width = (int)(gridSize * 1.2f);;
		// Centering the Object in the grid
		x = point.x + (gridSize - width) / 2;
		y = point.y + (gridSize - height) / 2;

		// Setting position of the door.
		hitBox = new Rectangle(x, y, width, height);

		isActive= false;
		visible = false;

	}


	public void update() {

		timeNow = System.currentTimeMillis();

		// Only updates if active and BLINK_TIME is not reached
		if (isActive && (timeNow - spawnTime < BLINK_TIME)) {

			// If a second (1000 milliseconds) has passed since spawn
			if (timeNow - deltaTime > BLINK_INTERVAL) {
				visible = !visible;		// Make the door visible/invisible
				deltaTime = System.currentTimeMillis();
			}

		} else if (isActive) {
			// When blinking is done setting visible to true
			visible = true;
		}

	}


	// When player has picked up all collectables
	public void setActive() {
		isActive = true;
		visible = true;
		// Timers for blinking the door
		spawnTime = System.currentTimeMillis();
		deltaTime = System.currentTimeMillis();
		// Number of times the door will blink after spawning
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isVisible() {
		return visible;
	}


	public Rectangle getHitBox() {
		return hitBox;
	}


	public boolean contains(Rectangle other) {
		return hitBox.contains(other);		// Returns true if object is completely surrounded by hitBox
	}


	public Color getColor() {
		return color;
	}

}
