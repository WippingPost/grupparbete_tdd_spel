package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Treasure {

	private int x, y;
	private float width, height;
	private Rectangle hitBox, smallHitBox, largeHitBox;
	private boolean visible;
	private long nowTime, deltaTime;
	private BufferedImage image = null;
	private BufferedImage treasure = null;
	private BufferedImage treasure1 = null;
	private BufferedImage treasure2 = null;
	private BufferedImage treasure3 = null;
	private BufferedImage treasure4 = null;

	// Constructor
	public Treasure(Point point, int gridSize) {

		try {
			treasure = ImageIO.read(new File("Assets/Treasure/treasure.png"));
			treasure1 = ImageIO.read(new File("Assets/Treasure/treasure1.png"));
			treasure2 = ImageIO.read(new File("Assets/Treasure/treasure2.png"));
			treasure3 = ImageIO.read(new File("Assets/Treasure/treasure3.png"));
			treasure4 = ImageIO.read(new File("Assets/Treasure/treasure4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Deciding what kind of image for treasure object.
		int random = new Random().nextInt(5);
		switch(random) {

		case 0:
			image = treasure;
			break;
		case 1:
			image = treasure1;
			break;
		case 2:
			image = treasure2;
			break;
		case 3:
			image = treasure3;
			break;
		case 4:
			image = treasure4;
			break;
		}


		// Size of small game object
		height = (gridSize * 0.60f);
		//width = (gridSize * 0.30f);
		width = (gridSize * 0.60f);

		// Centering the Object in the grid
		x = (int)(point.x + (gridSize - width) / 2);
		y = (int)(point.y + (gridSize - height) / 2);

		// Setting the small hitBox
		smallHitBox = new Rectangle(x, y, (int)width, (int)height);

		// Size of large game object
		height = (gridSize * 0.65f);
		//width = (gridSize * 0.34f);
		width = (gridSize * 0.65f);

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


	public BufferedImage getImage() {
		return image;
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

}
