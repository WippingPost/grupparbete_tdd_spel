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
	private final String[] surroundingWalls = {"WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
			   							       "W............................W",
	                                           "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"};


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
		for (int i = 0; i <= 9; i++) {
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


	// The Levels
	private void initiateLevels() {

		// **************************************************************
		// **************************************************************
		// **** Level creation is done by creating a String[] like this:
		/*
		 * The string is 28 characters of length and 28 elements in size
		 * The '.' (dots) are optional. Can be any character except the ones
		 * representing game objects in the description below.
		 *
		 * @Example:
		 *
		String[] level1 = {"................T...........",
				   		   "P...........................",
				   		   "............................",
				           "............................",
				           ".................0..........",
				           "............................",
			  	           ".......................T....",
				   	       "............................",
				           "......T.....1...............",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           ".....................T......",
				           ".........2.................T",
				           "....................3.......",
				           "........T...................",
				           "............................",
				           "........................4...",
				           "...................T........",
				           "............................",
				           "............................",
				           "............................",
				           ".............T..............",
				           "............................",
				           ".....T......................",
				           "..................WWWWW5....",
				           "T..........................X"};
		*/
		// **** 'W' represents a Wall object
		// **** 'P' represents the Player object
		// **** 'T' represents Treasure object
		// **** 'X' represent the Exit Door
		// **** '0' and '1' represent a Laser objects start- and end points
		// **** '2' and '3' represent a Laser objects start- and end points
		// **** '4' and '5' represent a Laser objects start- and end points
		// **** '6' and '7' represent a Laser objects start- and end points
		// **** '8' and '9' represent a Laser objects start- and end points


		// The levels

		String[] level1 = {"X.............0.............",
						   "P.......................T...",
						   "............................",
						   ".............T..............",
						   "............................",
						   "............................",
					  	   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "......................T.....",
						   "............................",
						   "............................",
						   "2..........................3",
						   "T..........................T",
						   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "............................",
						   "...T.....................T..",
						   "............................",
						   ".............T1............."};




		levels.add(level1);


		String[] level2 = {"T............T0T...........T",
				           "X...........................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "P...........................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "T..........................T",
				           "WWWWWWWWWWWWWW.WWWWWWWWWWWWW",
				           "T..........................T",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
				           "............................",
						   "T............T1T...........T"};

		levels.add(level2);


		String[] level3 = {"P0WTWWWWWWWWWWWWWWWWWWWWWWW2",
		                   "X.W.........................",
		                   "..WWWWWWWWWWWWWWWWWWWWWWWWW.",
		                   ".............T..............",
		                   "W.WWWWWWWWWWWWWWWWWWWWWWWWW.",
		                   "W............T..............",
		                   "W.WWWWWWWWWWWWWWWWWWWWWWWWW.",
		                   "W............T............W.",
		                   "W.WWWWWWWWWWWWWWWWWWWWWWW...",
		                   "W............T..........WT..",
		                   "W.WWWWWWWWWWW4WWWWWWWWWWWWW.",
		                   ".............T..............",
		                   "W1WWWWWWWWWWW.WWWWWWWWWWWWW3",
		                   "6.W........T..WT..........8W",
		                   "W...........W.W...........WW",
		                   "T...........W.W............T",
		                   "WWWWW....WWWW.WWWW.....WWWWW",
		                   "T..........WW.WW...........T",
		                   "WWWWWWWW............WWWWWWWW",
		                   "T..........................T",
		                   "WWWWWWWWWWW..T...WWWWWWWWWWW",
		                   "T..........................T",
		                   "WWWWWWWW............WWWWWWWW",
		                   "T...........W.WWW..........T",
		                   "WWWWW....WWWW.WWWWW....WWWWW",
		                   "T......WWWWWW.WWWWWWW......T",
		                   "W...WWWWWWWWW.WWWWWWWWW....W",
		                   "9.WWWWWWWWWWW5WWWWWWWWWWWW.7"};

		levels.add(level3);


		String[] level4 = {"0..........................1",
				           "X.W..W.WWWW.W....W....WWWW..",
						   "P.W..W.W.T..W....W....W..W..",
						   "..W..W.W....W....W....W..W..",
						   "..WWWW.WWWW.W....W....W..W..",
						   "..W..W.W....W....W....W..W..",
						   "..WT.W.W....W....W....W..W..",
						   "..W..W.WWWW.WWWW.WWWW.WWWW..",
						   "2..........................3",
						   ".WW.WWW.W.WWW.W....W.WWW.WW.",
						   "....WT..W.W.W.W....W.W.W....",
						   "....WWW.W.WWW.W.WW.W.WWW....",
						   "......W.W.WT..W....W.W.W....",
					  	   "....WWW.W.W...W....W.WWW....",
						   "4..........................5",
						   ".W...W.WWWW.WWW..W....WWWW..",
						   ".W...W.W..W.W..W.W....W...W.",
						   ".W.T.W.W..W.W..W.W....W...W.",
						   ".W...W.W..W.WWW..W....W...W.",
						   ".W.W.W.W..W.WTWW.W.T..W...W.",
						   ".WW.WW.WWWW.W..W.WWWW.WWWW.T",
						   "6..........................7",
						   "............................",
						   "............................",
					  	   "......T.....................",
						   "............................",
						   "............................",
						   "...........................T"};

		levels.add(level4);


		String[] level5 = {"9..........................6",
					  	   "PWWWWWWWWWWWWWWWWWWWWWWWWWW.",
						   "XW......................WT..",
				           ".W.W.WWWWWWWWWWWWWWWWWW.W.W.",
				           ".W.W..................W.W.W.",
						   ".W.W.WWWWWWWWWWWWWWWW.W.W.W.",
						   ".W.W.W..............W.W.W.W.",
						   ".W.W.W.W.WWWWWWWWWW.W.W.W.W.",
						   ".W.W.W.WT.........W.W.W.W.W.",
						   ".W.W.W.W.WWWWWWWW.W.W.W.W.W.",
						   ".W.W.W.W.W......W.W.W.W.W.W.",
						   ".W.W.W.W.W.WWWW.W.W.W.W.W.W.",
						   ".W.W.W.W.W.W....W.W.W.W.W.W.",
						   ".W.W.W.W.W.W.TT.W.W.W.W.W.W.",
						   ".W.W.W.W.W.W.TT.WWW.W.W.W.W.",
						   ".W.W.W.W.W.W....W.W.W.W.W.W.",
						   ".W.W.W.W.W.WWWWWW.W.W.W.W.W.",
						   ".W.W.W.W.W........W.W.W.W.W.",
						   ".W.W.W.W.WWWWWWWW.W.W.W.W.W.",
						   ".W.W.W.W...........TW.W.W.W.",
						   ".W.W.W.WWWWWWWWWWWWWW.W.W.W.",
						   ".W.W.W................W.W.W.",
						   ".W.W.W.WWWWWWWWWWWWWWWW.W.W.",
						   ".W.WT...................W.W.",
						   ".W.WWWWWWWWWWWWWWWWWWWW.W.W.",
						   ".W.W.....................TW.",
						   ".WTWWWWWWWWWWWWWWWWWWWWWWWW.",
						   "7..........................8"};

		levels.add(level5);


        String[] level6 = {"X...........................",
                           "P...........................",
                           "............................",
                           "...WWWWWWWWWWWWWWWWWWWWWWW..",
                           "...WWWWWWWWWWWWWWWWWWWWWWW..",
                           "...WW....6....W....2....WW..",
                           "...WW.WWW.WWW.W.WWW.WWW.WW..",
                           "...WW.WWW.WWW.5.WWW.WWW.WW..",
                           "...WW.........W.........WW..",
                           "...WW.WWW.W.WWWWW.W.WWW.WW..",
                           "...WW.....W...W...W.....WW..",
                           "...WWWW.W.WWW.W.WWW.W.WWWW..",
                           "...WW...W.0.T...T.1.W...WW..",
                           "...WW.WWW.W.WW.WW.W.WWW.WW..",
                           "..........W.W.T.W.W.........",
                           "...WW.WWW.W.WWWWW.W.WWW.WW..",
                           "...WW...W.W.......W.W...WW..",
                           "...WWWW.W.W.WWWWW.W.W.WWWW..",
                           "...WW8........4........9WW..",
                           "...WW.WWW.WWW.W.WWW.WWW.WW..",
                           "...WW..TW....TWT....WT..WW..",
                           "...WWWW.W.W.WWWWW.W.W.WWWW..",
                           "...WW.....W...W...W.....WW..",
                           "...WW.WWW.WWW.W.WWW.WWW.WW..",
						   "...WW....7.........3....WW..",
						   "...WWWWWWWWWWWWWWWWWWWWWWW..",
						   "...WWWWWWWWWWWWWWWWWWWWWWW..",
						   "............................",};

        levels.add(level6);




        String[]level7={"............................",
						"............................",
						"............................",
						"............................",
						"............................",
						"............................",
						".................WWWWWWWWWWW",
						".................W..TTTTTTTT",
						".................WW.WTTTTTTT",
						".................W0.1WWWWWWW",
						".................WW.WWWWW.WW",
						"........WWWWWWW..W8........9",
						"........W.P...W..WWWWWWWWW.W",
						"........W.....W..W6........7",
						"........W........WW.W.WWWWWW",
						"........W.X...W..WW.WWW.WWWW",
						"........WWWWWWW..W4........5",
						".................WWWWWWWWW.W",
						".................WW2.......3",
						".................WWW.WWWWWWW",
						"............................",
						"............................",
						"............................",
						"............................",
						"............................",
						"............................",
						"............................",
						"............................"};

        levels.add(level7);


		String[] level8 = {"X...........................",
						   "P...........................",
						   "....W..................WWWW8",
						   "....W..................WT.W.",
						   "....W....W.............WTT..",
						   "....W....WW............W.TW.",
						   "....W.....W............WWWW9",
						   "....W..................W....",
						   "....W..................W....",
						   "....W..................W....",
						   "....W..................W....",
						   "....W..................W....",
						   "....W...........W......W....",
						   "....W..........WWW.....W....",
						   "....W..................W....",
						   "....W...............WW.W....",
						   "....W................W.W....",
						   "....W................W.W....",
						   "....W..................W....",
						   "....W.W........3WWW....W....",
						   "....W.WW..WW....WTW....W....",
						   "....WWWW..WWWW0..1W.W..W....",
						   "....W.W47.WWWWW..TWWWW.W....",
						   "....WWWT...WW.W.TTW..WWW....",
						   "....WWWT.WWWWWW2WWWW.WWW....",
						   "....W.W65WWWWWWWWWWW.WWW....",
						   "....WWWWWWWWWWWWWWWWWWWW....",
						   "............................"};

		levels.add(level8);


		String[] level9 = {"T............WWWW..........T",
				           "..............T.............",
				           ".............W.W............",
				           ".............W.W............",
				           ".............W.W............",
				           ".............W.W............",
				           ".............W.W............",
				           ".............W.W............",
				           ".............W.W............",
				           ".............W.W............",
				           ".WWWWWWWWWWWWW.W............",
				           ".............W.W............",
				           ".WWWWWWWWWWW.W.W............",
				           ".W...........W.WT...........",
				           ".W.WWWWWWWWWTW.WW..........T",
				           "T8..TWWWWWWWWW.TW....WWWWWWW",
				           "WWT.......TWWWWWWWWW.W.T...7",
				           "WWWWW.........T........WWWWW",
				           ".6.....TW.TWWTWTWWT.....WWWW",
				           ".WWWWWWWWWWWWW1WWWWWWWW....9",
				           "...............WT...........",
				           "WWWWWWWWWWWWWW.W............",
				           "T..............WT..........X",
				           "WWWWWWWWWWWWWW.WWWWWWWWWWWWW",
				           "5............402...........3",
				           "WW..WWWW..WWWW.WWWW.WWWWW..W",
				           "WW.....WWWW.TW.WT.WWWTWWW..W",
				           "WWWWW.......WWPWW..........W",};

		levels.add(level9);




		String[] level10 = {"X...........................",
						    "P...........................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............0.2.4...........",
						    "............................",
						    "...........W.W.W.W..........",
						    ".........6.........7........",
						    "...........W.WTW.W..........",
						    ".........8.........9........",
						    "...........W.W.W.W..........",
						    "............................",
						    "............1.3.5...........",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................"};

		levels.add(level10);

		String[] level11 = {"............................",
						    "P...........................",
						    "............................",
						    "........WW0.......1WW.......",
						    "........WW....T....WW.......",
							".........WW.......WW........",
							".........WW2.....3WW........",
						    "..........WW..T..WW.........",
						    "..........WW.....WW.........",
						    ".......WWWWWWWWWWWWWW.......",
						    ".......WWWWWWWWWWWWWW.......",
						    ".....WWWW..WWWWWW..WWWW.....",
						    ".....WWWWX.WWWWWW..WWWW.....",
						    "...WWWWWWWWWWWWWWWWWWWWWW...",
						    "...WWWWWWWWWWWWWWWWWWWWWW...",
						    ".WW.TWWWWWWWWWWWWWWWWWWT.WW.",
						    ".WW..WWWWWWWWWWWWWWWWWW..WW.",
						    ".WW..WWWWWWWWWWWWWWWWWW..WW.",
						    ".WW..WWWWWWWWWWWWWWWWWW..WW.",
						    ".WW..WW6...........T7WW..WW.",
						    ".WW..WW4T...........5WW..WW.",
						    ".......WWWWWW..WWWWWW.......",
						    ".......WWWWWW..WWWWWW.......",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................",
						    "............................"};

levels.add(level11);


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
