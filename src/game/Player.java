package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;


public class Player {

	private Rectangle hitBox;
	private int speed, x, y;
	private String playerDirection;
	//Player color
	private Color color = new Color(0,  0,  0);		// Black


	// Creating player object at start position
	public Player(Point point) {

		x = point.x;
		y = point.y;
		speed = 120;  // pixels per second
		playerDirection = "idle";
		hitBox = new Rectangle(x, y, 30, 30);

	}


	// PLayer update method
	public void update(int fps) {

		switch (playerDirection) {

		case "left":
			x = x - (int)((float)speed / fps);
			break;

		case "right":
			x = x + (int)((float)speed / fps);
			break;

		case "down":
			y = y + (int)((float)speed / fps);
			break;

		case "up":
			y = y - (int)((float)speed / fps);
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



	public Rectangle getHitBox() {
		return hitBox;
	}


	public Color getColor() {
		return color;
	}

}
