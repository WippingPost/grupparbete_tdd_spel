package game;

import java.awt.Rectangle;


public class Player {

	private Rectangle hitBox;
	private int speed, x, y, maxX, minX, maxY, minY;
	String playerDirection;

	public Player(int x, int y) {

		this.x = x;
		this.y = y;
		speed = 120;  // pixels per second
		maxX = 760;
		maxY = 730;
		minX = 20;
		minY = 20;
		playerDirection = "idle";

		hitBox = new Rectangle(x, y, 20, 30);
	}

	// PLayer update method
	public void update(int fps) {

		switch (playerDirection) {

		case "left":
			x = x - (int)((float)speed / fps);
			if (x < minX) x = minX;
			break;

		case "right":
			x = x + (int)((float)speed / fps);
			if (x + hitBox.width > maxX) x = maxX - hitBox.width;
			break;

		case "down":
			y = y + (int)((float)speed / fps);
			if (y + hitBox.height > maxY) y = maxY - hitBox.height;
			break;

		case "up":
			y = y - (int)((float)speed / fps);
			if (y < minY) y = minY;
			break;

		case "idle":
			// Do nothing
			break;
		}


		// Update player position
		hitBox.setLocation(x, y);

	}

	public void setDirection(String direction) {
		playerDirection = direction;
	}

	public void setBoundaries(int maxX, int minX, int maxY, int minY) {
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

}
