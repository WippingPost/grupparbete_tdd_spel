package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;


public class Player {

	private Rectangle hitBox;
	private int speed, x, y;
	private Direction playerDirection;
	//Player color
	private Color color = new Color(0,  0,  0);		// Black


	// Creating player object at start position
	public Player(Point point) {

		x = point.x;
		y = point.y;
		speed = 120;  // pixels per second
		playerDirection = Direction.IDLE;
		hitBox = new Rectangle(x, y, 26, 26);
	}


	// PLayer update method
	public void update(int fps) {

		switch (playerDirection) {

		case LEFT:
			x = x - (int)((float)speed / fps);
			break;

		case RIGHT:
			x = x + (int)((float)speed / fps);
			break;

		case DOWN:
			y = y + (int)((float)speed / fps);
			break;

		case UP:
			y = y - (int)((float)speed / fps);
			break;

		case IDLE:
			// Do nothing
			break;
		}


		// Update player position
		hitBox.setLocation(x, y);
	}


	// Collision detection against other hitbox rectangle
	public boolean collideWith(Rectangle other) {
		return hitBox.intersects(other);
	}

	// Collision detection against Line object (Laser)
	public boolean collideWith(Line2D other) {
		return hitBox.intersectsLine(other);
	}


	// Sets player movement direction
	public void setDirection(Direction direction) {
		playerDirection = direction;
	}


	// Returns player hitbox
	public Rectangle getHitBox() {
		return hitBox;
	}


	// Returns players color
	public Color getColor() {
		return color;
	}

}
