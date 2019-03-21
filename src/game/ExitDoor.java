package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ExitDoor {

	private int x, y, width, height;
	private Rectangle hitBox;
	private boolean isActive, visible;
	private long timeNow, spawnTime, deltaTime;  // Timers for door blinking when spawning
	private final int BLINK_TIME = 5000;
	private final int BLINK_INTERVAL = 300;
	private BufferedImage image;


	// Creating door at position
	public ExitDoor(Point point, int gridSize) {

		try {
			image = ImageIO.read(new File("Assets/ExitDoor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Size of game object
		//height = (int)(gridSize * 0.9f);
		//width = (int)(gridSize * 0.9f);
		height = gridSize;
		width = gridSize;
		// Centering the Object in the grid
		x = point.x;
		y = point.y;

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


	public BufferedImage getImage() {
		return image;
	}


	public Rectangle getHitBox() {
		return hitBox;
	}


	public boolean contains(Rectangle other) {
		return hitBox.contains(other);		// Returns true if object is completely surrounded by hitBox
	}

}
