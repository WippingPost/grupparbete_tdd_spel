package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ground {

	private int width, height, x, y;
	private Rectangle hitBox;
	private BufferedImage image = null;

	public Ground(Point point, int gridSize) {

		// Getting the image for the ground object
		try {
			image = ImageIO.read(new File("Assets/Ground/floor1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Scaling object
		height = gridSize;
		width = height;

		x = point.x;
		y = point.y;

		// Setting the size and position of the ground.
		hitBox = new Rectangle(x, y, width, height);
	}


	public BufferedImage getImage() {
		return image;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

}
