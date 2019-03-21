package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall {

	private int width, height, x, y;
	private Rectangle hitBox;
	private BufferedImage image = null;

	public Wall(Point point, int gridSize) {

		try {
			image = ImageIO.read(new File("Assets/Wall/wall3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Scaling object to fit screen size
		height = gridSize;
		width = gridSize;
		// Centering the wall in the grid
		x = point.x;
		y = point.y;

		// Setting the size and position of the wall.
		hitBox = new Rectangle(x, y, width, height);

	}


	public BufferedImage getImage() {
		return image;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
