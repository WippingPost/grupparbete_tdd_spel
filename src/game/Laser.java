package game;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Laser {

	private Point point1;
	private Point point2;
	private boolean isActive;
	private int offTime, onTime;
	private long deltaTime, thisTime;
	private Color color = new Color(255, 0, 0);
	private Random random = new Random();
	private final int MAX_ON_TIME = 6000;
	private final int MIN_ON_TIME = 2000;
	private final int MAX_OFF_TIME = 3500;
	private final int MIN_OFF_TIME = 1500;

	public Laser(Point point1, Point point2) {

		// Sets the two points between which the Line is drawn
		this.point1 = point1;
		this.point2 = point2;
		// point1, and point2 represents coordinates 0,0 in the 30x30 pixel size grid.
		// Offset the point coordinates to be in the middle of grid.
		point1.x += 15;
		point1.y += 15;
		point2.x += 15;
		point2.y += 15;

		// Sets onTime and offTime for the Laser
		onTime = random.nextInt(MAX_ON_TIME - MIN_ON_TIME) + MIN_ON_TIME ;	// Randomize time between min and max

		offTime = random.nextInt(MAX_OFF_TIME - MIN_OFF_TIME) + MIN_OFF_TIME;	// Randomize time between min ans max

		deltaTime = System.currentTimeMillis();
		isActive = false;
	}


	public void update() {

		thisTime = System.currentTimeMillis();

		// Checks if it's time to activate/deactivate the laser
		if (isActive) {
			if (thisTime - deltaTime > onTime) {
				toggleState();
			}
		} else {
			if (thisTime -deltaTime > offTime) {
				toggleState();
			}
		}
	}


	private void toggleState() {
		isActive = !isActive;		// Changes the state of isActive
		deltaTime = thisTime;		// Resets deltaTime
	}


	public Point getPoint1() {
		return point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public boolean isActive() {
		return isActive;
	}

	public Color getColor() {
		return color;
	}

}
