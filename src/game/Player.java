package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;


public class Player {

	private Rectangle hitBox;
	private Rectangle oldHitBox;
	private int speed, x, y, width, height;
	private Direction playerDirection;
	private boolean isActive;
	//Player color
	private Color color = new Color(0,  0,  0);		// Black


	// Creating player object at start position
	public Player(Point point, int gridSize) {

		height = (int)(gridSize * 0.8f);
		width = height;
		// Centering the Player in the grid
		x = point.x + (gridSize - width) / 2;
		y = point.y + (gridSize - height) / 2;
		isActive = true;
		speed = gridSize * 5;  // pixels per second
		playerDirection = Direction.IDLE;
		hitBox = new Rectangle(x, y, width, height);
		oldHitBox = new Rectangle(x, y, width, height);
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

	public boolean isActive() {
		return isActive;
	}


	public void inActivate() {
		isActive = false;
	}


	// Returns player hitbox
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	// Returns player hitbox
	public Rectangle getOldHitBox() {
		return oldHitBox;
	}

	// Returns players color
	public Color getColor() {
		return color;
	}
	// Reset the player position
	public void resetPosition() {
		hitBox.setLocation(oldHitBox.x, oldHitBox.y);
	}
	// hold the player position
	public void saveOldPosition(int x, int y) {
		oldHitBox.setLocation(x, y);
	}

}
