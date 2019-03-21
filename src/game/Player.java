package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player {

	private Rectangle hitBox;
	private int speed, x, y, width, height;
	private int oldX, oldY;
	private Direction playerDirection;
	private boolean isActive;
	BufferedImage image;
	BufferedImage imagePlayer;
	BufferedImage imageLeft;
	BufferedImage imageRight;


	// Creating player object at start position
	public Player(Point point, int gridSize) {


		// Getting the image for the ground object
		try {
			imagePlayer = ImageIO.read(new File("Assets/Player/player.png"));
			imageLeft = ImageIO.read(new File("Assets/Player/left.png"));
			imageRight = ImageIO.read(new File("Assets/Player/right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		height = (int)(gridSize * 0.65f);
		width = height;
		// Centering the Player in the grid
		x = point.x + (gridSize - width) / 2;
		y = point.y + (gridSize - height) / 2;
		isActive = true;
		speed = gridSize * 6;  // pixels per second
		playerDirection = Direction.IDLE;
		image = imagePlayer;
		hitBox = new Rectangle(x, y, width, height);

	}


	// PLayer update method
	public void update(int fps) {
		oldX = x;
		oldY = y;

		switch (playerDirection) {

		case LEFT:
			x = x - (int)((float)speed / fps);
			image = imageLeft;
			break;

		case RIGHT:
			x = x + (int)((float)speed / fps);
			image = imageRight;
			break;

		case DOWN:
			y = y + (int)((float)speed / fps);
			image = imagePlayer;
			break;

		case UP:
			y = y - (int)((float)speed / fps);
			image = imagePlayer;
			break;

		case IDLE:
			// Do nothing
			image = imagePlayer;
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


	public BufferedImage getImage() {
		return image;
	}

	// Reset the player position
	public void resetPosition() {
		x = oldX;
		y = oldY;
	}


}
