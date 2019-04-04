package game;

import java.awt.Point;
import java.util.ArrayList;

public class LevelManager {

	private ArrayList<String[]> levels;
	private ArrayList<Point> walls;
	private ArrayList<Point> treasures;
	private ArrayList<Point> lasers;
	private ArrayList<Point> groundList;
	private Point player, exitDoor;

	/*
	 * Creating the String representing the surrounding wall
	 */
	private final String[] surroundingWalls = SurroundingWalls.WALLS.walls;




	// Constructor
	public LevelManager() {

		exitDoor = new Point();
		player = new Point();
		levels = new ArrayList<>();
		walls = new ArrayList<>();
		treasures = new ArrayList<>();
		lasers = new ArrayList<>();
		groundList = new ArrayList<>();


		// Load levels into a list
		initiateLevels();

	}





	public void setNextLevel(int levelNumber) {

		levelNumber = levelNumber - 1;
		// Clear the lists
		walls.clear();
		treasures.clear();
		lasers.clear();
		groundList.clear();

		ArrayList<Point> tempLasers = new ArrayList<>();
		// Fills up the temporary list for now
		for (int i = 0; i <= 19; i++) {
			tempLasers.add(new Point(-1, -1));
		}



		// Temporaray variables
		char currentCharacter = '.';
		Point currentPoint = new Point();

		// First we add the surrounding walls to the board
		// And the ground points
		for (int y = 0; y < surroundingWalls.length; y++) {
			for (int x = 0; x < surroundingWalls[y].length(); x++) {

				currentCharacter = surroundingWalls[y].charAt(x);
				if (currentCharacter == 'W') {
					walls.add(new Point(x, y));
				} else {
					// If it's not a surrounding wall object then it's ground.
					groundList.add(new Point(x, y));
				}
			}
		}


		// Create coordinates for each game object
		for (int y = 0; y < levels.get(levelNumber).length; y++) {				// Check for number of elements in String[]
			for (int x = 0; x < levels.get(levelNumber)[y].length(); x++) {		// Check for length of each element in String[]

				currentCharacter = levels.get(levelNumber)[y].charAt(x);

				// Creates a new Point() object if needed
				// And then checks what kind of object it is.
				if (currentCharacter != '.') {
					currentPoint = new Point(x + 1, y + 1);

					// Checks what kind of object it is
					switch (currentCharacter) {

					// Player start point
					case 'P':
						player.setLocation(currentPoint);
						break;
					// where is the exit door
					case 'X':
						exitDoor.setLocation(currentPoint);
						break;

					// Where are the walls?
					case 'W':
						walls.add(currentPoint);
						break;

					// Where are the treasures
					case 'T':
						treasures.add(currentPoint);
						break;

					// Where are the lasers?
					case '0':		// First laser start point
						tempLasers.set(0, currentPoint);
						break;
					case '1':		// First laser end point
						tempLasers.set(1, currentPoint);
						break;
					case '2':		// Second laser start point
						tempLasers.set(2, currentPoint);
						break;
					case '3':		// Second laser end point
						tempLasers.set(3, currentPoint);
						break;
					case '4':		// Third laser start point
						tempLasers.set(4, currentPoint);
						break;
					case '5':		// Third laser end point
						tempLasers.set(5, currentPoint);
						break;
					case '6':		// Fourth laser start point
						tempLasers.set(6, currentPoint);
						break;
					case '7':		// Fourth laser end point
						tempLasers.set(7, currentPoint);
						break;
					case '8':		// Fifth laser start point
						tempLasers.set(8, currentPoint);
						break;
					case '9':		// Fifth laser end point
						tempLasers.set(9, currentPoint);
						break;
					case 'A':		// First laser start point
						tempLasers.set(10, currentPoint);
						break;
					case 'B':		// First laser end point
						tempLasers.set(11, currentPoint);
						break;
					case 'C':		// Second laser start point
						tempLasers.set(12, currentPoint);
						break;
					case 'D':		// Second laser end point
						tempLasers.set(13, currentPoint);
						break;
					case 'E':		// Third laser start point
						tempLasers.set(14, currentPoint);
						break;
					case 'F':		// Third laser end point
						tempLasers.set(15, currentPoint);
						break;
					case 'G':		// Fourth laser start point
						tempLasers.set(16, currentPoint);
						break;
					case 'H':		// Fourth laser end point
						tempLasers.set(17, currentPoint);
						break;
					case 'I':		// Fifth laser start point
						tempLasers.set(18, currentPoint);
						break;
					case 'J':		// Fifth laser end point
						tempLasers.set(19, currentPoint);
						break;
					}
				}
			}
		}

		// Fills the final laserList with points
		for (int i = 0; i < tempLasers.size(); i = i + 2) {
			// Checks if there is a laser point ( -1 indicates no laser)
			if (tempLasers.get(i).x != -1) {
				lasers.add(tempLasers.get(i));
				lasers.add(tempLasers.get(i + 1));
			}
		}
	}


	// Loads the content of LevelHolder Enum into Arraylist levels
	private void initiateLevels() {

		for (LevelHolder levelHolder : LevelHolder.values()) {
			levels.add(levelHolder.level);
		}

	}


	public ArrayList<Point> getWalls() {
		return walls;
	}

	public ArrayList<Point> getTreasures() {
		return treasures;
	}

	public ArrayList<Point> getLasers() {
		return lasers;
	}

	public ArrayList<Point> getGroundList() {
		return groundList;
	}

	public Point getExitDoor() {
		return exitDoor;
	}

	public Point getPlayer() {
		return player;
	}

	public int getNumberOfLevels() {
		return levels.size();
	}


}
