package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {

	private Dimension screenSize;
	private int screenWidth, screenHeight;
	private final int gridSize, gameStartX, gameStartY, gameEndX;
    private boolean pausedState, running, levelCleared, gameOver, loadingScreen, newHighScore;
	private int maxFps = 60;	// Target frame rate
	private long maxFrameTime = 1000 / maxFps;	 // Frame time at maxFps
	private int fps, frameCounter, level;
	private long thisFrameTimeStart, lastTimeUpdate;
	private long nowTime, deltaTime;

	// Variables for timing the player on each level
	private double timeThisLevel, bestTimeThisLevel;
	private boolean newLevel;
	// Timer object for timing the player on each level
	private Timer timer;

	private Thread gameThread = null;
	private Player player;
	private InputManager inputManager;
	private HighScore highScore;
	private LevelManager levelManager;
	private ExitDoor exitDoor;
	private Line2D line;
	private final BasicStroke BRUSH_WIDTH = new BasicStroke(2f);	// The width of the laser line

	private int noOFTreasuresInLevel, treasuresCollected;
	private Font font;
	private Font font2;

	// Background image
	private BufferedImage background;

	// ArrayLists of game objects
	private ArrayList<Treasure> treasureList = new ArrayList<>();
	private ArrayList<Wall> wallList= new ArrayList<>();
	private ArrayList<Laser> laserList= new ArrayList<>();
	private ArrayList<Ground> groundList = new ArrayList<>();



	// Constructor
	public Game(InputManager inputManager) {

		// Getting the screen size
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		// This sets the size (in pixels) of each grid on the game board
		gridSize = screenHeight / 31;
		// Centering the gameboard on the screen
		gameStartX = (screenWidth / 2) - ((gridSize * 30) / 2);
		gameStartY = gridSize / 2;
		gameEndX = gameStartX + (gridSize * 30);

		// Text font and size
		font = new Font("Serif", Font.BOLD, screenHeight / 40);
		font2 = new Font("Serif", Font.PLAIN, screenHeight / 55);

		levelManager = new LevelManager();
		highScore = new HighScore();
		timer = new Timer();
		this.inputManager = inputManager;	// Used to handle player input

		level = 1;	// Setting starting level number
		frameCounter = 0;	// Counting frames per second
		gameOver = false;
		levelCleared = false;
		fps = maxFps;	// Setting fps for the first second of the game

		// Setting background image
		try {
			background = ImageIO.read(new File("Assets/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Loading the level and it's game objects into memory
		loadLevel(level);

		// Creating new thread and starting it
		running = true;		// This is what keeps the game loop running...
		gameThread = new Thread(this);
		gameThread.start();
	}




	// The game loop
	@Override
	public void run() {

		lastTimeUpdate = System.currentTimeMillis();	// Helper for fps counter

		// The game loop...
		while (running) {

			// Timer for the fpsControl() method
			// So it knows how long it takes to finish each frame
			thisFrameTimeStart = System.currentTimeMillis();


			// Start the timer if a new level just was started
			if (newLevel) {
				newLevel = false;
				timer.start();
			}

			// Did the player press any keys?
			getPlayerInput();

			// Updating logics, positions etc...
			if (!loadingScreen) {
				updateGame();

			// ...unless the Loading Screen is active
			} else {
				updateLoadingScreen();
			}

			drawGame();			// Drawing the game objects

			fpsControl(thisFrameTimeStart);		// Handler for frames per second

		}

		// Exiting...
		System.exit(0);
	}

	// This is the method that draws everything to the screen
	@Override
	public void paint(Graphics graphics) {

		// Clear canvas and prepare to redraw everything
		super.paint(graphics);
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw background
		graphics2d.drawImage(background, 0, 0, screenWidth, screenHeight, null);


		//Draw Loading Screen if active
		if (!loadingScreen) {

			// Draw the game board

			// Draw ground
			for (Ground ground : groundList) {
				graphics2d.drawImage(ground.getImage(), ground.getHitBox().x, ground.getHitBox().y,
						ground.getHitBox().width, ground.getHitBox().height, null);
			}


			// Draw walls
			for (Wall wall : wallList) {
				graphics2d.drawImage(wall.getImage(), wall.getHitBox().x, wall.getHitBox().y,
						wall.getHitBox().width, wall.getHitBox().height, null);
			}

			// Draw treasures
			for (Treasure treasure : treasureList) {
				// Only draw if still active (not picked up by player)
				if (treasure.isActive()) {
					graphics2d.drawImage(treasure.getImage(), treasure.getHitBox().x, treasure.getHitBox().y,
							treasure.getHitBox().width, treasure.getHitBox().height, null);
				}

			}


			// Draw Lasers
			for (Laser laser : laserList) {

				// The width of the laser line
				graphics2d.setStroke(BRUSH_WIDTH);
				// Only draw the laser if it is active
				if (laser.isActive()) {
					graphics2d.setColor(laser.getColor());
					line = new Line2D.Float(laser.getPoint1(), laser.getPoint2());
					graphics2d.draw(line);
				}
				// Then draw the anchor points of the laser
				graphics2d.setColor(Color.BLACK);
				// Centering over the endpoints of the lasers
				graphics2d.fillOval(laser.getPoint1().x - (gridSize / 6),
						laser.getPoint1().y - (gridSize / 6), (gridSize / 3), (gridSize / 3));
				graphics2d.fillOval(laser.getPoint2().x - (gridSize / 6),
						laser.getPoint2().y - (gridSize / 6), (gridSize / 3), (gridSize / 3));
			}

			// Draw Exit door if active and visible
			if (exitDoor.isVisible()) {
				graphics2d.drawImage(exitDoor.getImage(), exitDoor.getHitBox().x, exitDoor.getHitBox().y,
						exitDoor.getHitBox().width, exitDoor.getHitBox().height, null);
			}

			// Draw player
			graphics2d.drawImage(player.getImage(), player.getHitBox().x, player.getHitBox().y,
					player.getHitBox().width, player.getHitBox().height, null);

			// Drawing score etc...
			drawInformation(graphics2d);

		// Drawing Loading Screen if active
		} else {
			drawLoadingScreen(graphics2d);
		}




	}
	// End paint()



	// Get player input method
	private void getPlayerInput() {

		player.setDirection(inputManager.getPlayerInput());

		// Check to see if to quit game
		running = inputManager.getGameState();

	}



	// Update method
	private void updateGame() {

		// Collision detection

		// Did player hit a laser?
		for (Laser laser : laserList){

            line = new Line2D.Float(laser.getPoint1(), laser.getPoint2());

			if (laser.isActive() && player.collideWith(line) && !pausedState){
				player.inActivate();
				pausedState = true;
				gameOver = true;
				deltaTime = System.currentTimeMillis();
			}
		}

		// Did player hit a wall object?
		for(Wall wall : wallList) {
			//Vi fångar om player collide med väggen
			if(player.collideWith(wall.getHitBox())) {
				player.resetPosition();
			}
		}


		// Did player pick up the last treasure? If so, activate exit door!
		for (Treasure other : treasureList) {
			if (other.isActive() && player.collideWith(other.getHitBox())) {
				other.setPickedUp();
				treasuresCollected ++;
				// Did player pick up all treasures?
				if (treasuresCollected == noOFTreasuresInLevel) {
					exitDoor.setActive();
				}
			}

			// Updating treasure (flashing)
			if (other.isActive()) {
				other.update();
			}
		}


		// Did player exit the game?
		if (exitDoor.isActive() && !pausedState) {
			if (exitDoor.contains(player.getHitBox())) {
				player.inActivate();	// Inactivates the player while loading new level
				levelCleared = true;
				deltaTime = System.currentTimeMillis();
				timer.stop();  // Stops the timer for played time
				timeThisLevel = timer.getFinalTime();
				// Did player get a new best time for this level?
				if (highScore.getCurrentHighScore(level) == -1
						|| (timeThisLevel < highScore.getCurrentHighScore(level))) {
					newHighScore = true;
					highScore.setNewHighScore(level, timeThisLevel);
				}
				pausedState = true;
			}
			exitDoor.update();
		}


		// Updating game objects

		// Update laser
		for (Laser laser : laserList) {
			laser.update();
		}


		// Update player if active
		if (player.isActive()) {
			player.update(fps);
		}


		// Short pause before Loading Screen if player died or cleared level
		if (pausedState) {
			if (System.currentTimeMillis() - deltaTime > 700) {
				loadingScreen = true;
				pausedState = false;
				deltaTime = System.currentTimeMillis();
			}
		}
	}



	// Draw method
	private void drawGame() {

		// fps counter
		frameCounter ++;
		if ((System.currentTimeMillis() - lastTimeUpdate) >= 1000) {
			fps = frameCounter;
			frameCounter = 0;
			lastTimeUpdate = System.currentTimeMillis();
		}

		// Draw the scene
		repaint();
	}



	// Update loading Screen
	private void updateLoadingScreen() {

		nowTime = System.currentTimeMillis();

		if (gameOver && (nowTime - deltaTime) > 4000) {		// 4 second wait
			loadingScreen = false;
			loadLevel(level);
			// Adds the time it took to load next level
			lastTimeUpdate += (System.currentTimeMillis() - nowTime);
		}

		if (levelCleared && (nowTime - deltaTime) > 4000) {		// 4 second wait

			// Did we reach the last level? If so, quit the game
			if (levelManager.getNumberOfLevels() == level) {
				running = false;
			} else {
				level++;
				loadLevel(level);
			}
			loadingScreen = false;
			// Adds the time it took to load next level
			lastTimeUpdate += (System.currentTimeMillis() - nowTime);
		}
	}

	private void drawInformation(Graphics2D graphics2d) {

		// Draw current fps
		graphics2d.setColor(Color.BLACK);
		graphics2d.setFont(font2);
		//graphics2d.drawString("FPS = " + fps, gridSize, gridSize);
		graphics2d.setFont(font);
		graphics2d.drawString("LEVEL " + level, gridSize, gridSize);
		// Do we have a fastest time to show?
		if (bestTimeThisLevel != -1) {
			graphics2d.setColor(Color.BLUE);
			graphics2d.drawString("FASTEST TIME: " + getFormattedTime(bestTimeThisLevel), gridSize, gridSize * 3);
			graphics2d.setColor(Color.BLACK);
			graphics2d.drawString("TIME: " + timer.getFormattedPassedTime(), gridSize, gridSize * 5);
			graphics2d.drawString("TREASURES STOLEN: " + treasuresCollected
					+ " / " + noOFTreasuresInLevel, gridSize, gridSize * 7);
		} else {
			graphics2d.drawString("TIME: " + timer.getFormattedPassedTime(), gridSize, gridSize * 3);
			graphics2d.drawString("TREASURES STOLEN: " + treasuresCollected
					+ " / " + noOFTreasuresInLevel, gridSize, gridSize * 5);
		}
		graphics2d.drawString("ESC = QUIT GAME", gridSize, gridSize * 28);

		graphics2d.setFont(font2);
		graphics2d.drawString("   Mission Objectives:", gameEndX + (gridSize / 2), gridSize * 4);
		graphics2d.drawString("* Control the burglar (little Minion) with the arrow keys!", gameEndX + (gridSize / 2), gridSize * 5);
		graphics2d.drawString("* Steal all the treasures to advance to the next level."
				, gameEndX + (gridSize / 2), gridSize * 6);
		graphics2d.drawString("* Avoid burglar alarm lasers or you will be caught!"
				, gameEndX + (gridSize / 2), gridSize * 7);
		graphics2d.drawString("* When all treasures are stolen an exit door will appear."
				, gameEndX + (gridSize / 2), gridSize * 8);
	}



	// Draw Loading Screen
	private void drawLoadingScreen(Graphics2D graphics2d) {

		String text = "";
		int x;

		graphics2d.setColor(Color.BLACK);
		graphics2d.setFont(font);

		// If player was hit by laser
		if (gameOver) {
			text = "YOU WERE CAUGHT STEALING!";
			// Center text
			x = getXForText(text, graphics2d);
			graphics2d.drawString(text, x, gridSize * 12);
			text = "TRY AGAIN!";
			// Center text
			x = getXForText(text, graphics2d);
			graphics2d.drawString(text, x, gridSize * 14);
		}

		// If player clears a level
		if (levelCleared && levelManager.getNumberOfLevels() > level) {
			text = "LEVEL CLEARED!";
			// Center text
			x = getXForText(text, graphics2d);
			graphics2d.drawString(text, x, gridSize * 12);

			text = "LOADING NEXT LEVEL";
			// Center text
			x = getXForText(text, graphics2d);
			graphics2d.drawString(text, x, gridSize * 14);

			// Did player set a new best time?
			if (newHighScore) {
				graphics2d.setColor(Color.BLUE);
				text = "CONGRATULATIONS!";
				x = getXForText(text, graphics2d);
				graphics2d.drawString(text, x, gridSize * 16);
				text = "YOU JUST SET A NEW RECORD TIME!";
				x = getXForText(text, graphics2d);
				graphics2d.drawString(text, x, gridSize * 17);
				graphics2d.setColor(Color.BLACK);
			}
		}

		// If player clears the last level
		if (levelCleared && (levelManager.getNumberOfLevels() == level)) {
			text = "ALL LEVELS CLEARED!";
			// Center text
			x = getXForText(text, graphics2d);
			graphics2d.drawString(text, x, gridSize * 12);

			text = "GAME OVER!";
			// Center text
			x = getXForText(text, graphics2d);
			graphics2d.drawString(text, x, gridSize * 14);

			text = "QUITING GAME!";
			// Center text
			x = getXForText(text, graphics2d);
			graphics2d.drawString("QUITING GAME!", x, gridSize * 16);

			// Did player set a new best time?
			if (newHighScore) {
				graphics2d.setColor(Color.BLUE);
				text = "CONGRATULATIONS!";
				x = getXForText(text, graphics2d);
				graphics2d.drawString(text, x, gridSize * 16);
				text = "YOU JUST SET A NEW RECORD TIME!";
				x = getXForText(text, graphics2d);
				graphics2d.drawString(text, x, gridSize * 17);
				graphics2d.setColor(Color.BLACK);
			}
		}
	}


	// Method to center text on the screen on the X coordinate
	private int getXForText(String text, Graphics2D graphics2d) {

		// Get the FontMetrics
	    FontMetrics metrics = graphics2d.getFontMetrics(font);

	    // Determine the X coordinate for the text
	    int x = (screenWidth - metrics.stringWidth(text)) / 2;

	    return x;
	}



	// Loading the level and its game objects
	private void loadLevel(int level) {

		// Setting/reseting different variables
		levelCleared = false;
		newLevel = true;
		gameOver = false;
		// Getting the current levels best time.
		bestTimeThisLevel = highScore.getCurrentHighScore(level);
		newHighScore = false;
		timer.reset();

		int x = 0;
		int y = 0;

		// Clearing the lists
		wallList.clear();
		treasureList.clear();
		laserList.clear();
		groundList.clear();

		// Loading all game objects into separate lists
		levelManager.setNextLevel(level);

		// Populating lists with new game objects
		// The walls
		for (Point point : levelManager.getWalls()) {
			x = (gameStartX) + point.x * gridSize;
			y = (gameStartY) + point.y * gridSize;
			wallList.add(new Wall(new Point(x, y), gridSize));
		}
		// The treasures
		// Reset counters for each level.
		noOFTreasuresInLevel = 0;
		treasuresCollected = 0;
		for (Point point : levelManager.getTreasures()) {
			x = (gameStartX) + point.x * gridSize;
			y = (gameStartY) + point.y * gridSize;
			treasureList.add(new Treasure(new Point(x, y), gridSize));
			noOFTreasuresInLevel ++;
		}
		// The lasers
		Point point1;
		Point point2;
		for (int i = 0; i < levelManager.getLasers().size(); i = i + 2) {	// Getting the points of the lasers as pairs, therefore increment by 2
			point1 = levelManager.getLasers().get(i);
			point1.x = (gameStartX) + (point1.x * gridSize);
			point1.y = (gameStartY) + (point1.y * gridSize);
			point2 = levelManager.getLasers().get(i + 1);
			point2.x = (gameStartX) + (point2.x * gridSize);
			point2.y = (gameStartY) + (point2.y * gridSize);
			laserList.add(new Laser(point1, point2, gridSize));
		}

		// Exit door
		x = (gameStartX) + levelManager.getExitDoor().x * gridSize;
		y = (gameStartY) + levelManager.getExitDoor().y * gridSize;
		exitDoor = new ExitDoor(new Point(x, y), gridSize);

		// Player
		x = (gameStartX) + levelManager.getPlayer().x * gridSize;
		y = (gameStartY) + levelManager.getPlayer().y * gridSize;
		player = new Player(new Point(x, y), gridSize);

		// Ground object
		for (Point point : levelManager.getGroundList()) {
			x = (gameStartX) + point.x * gridSize;
			y = (gameStartY) + point.y * gridSize;
			groundList.add(new Ground(new Point(x, y), gridSize));
		}
	}


	// Returns a double value of seconds to minutes:seconds
	private String getFormattedTime(double time) {

		String formattedTime = "";

		int minutes = (int)(time / 60);
		float seconds = (float)(time - (minutes * 60));

		if (minutes != 0) {
			formattedTime = "" + minutes + ":" + seconds;
		} else {
			formattedTime = "" + time;
		}

		return formattedTime;
	}



	// Method to control the game speed and limit frames per second
	private void fpsControl(long thisFrameTimeStart) {

        long thisFrameTime = System.currentTimeMillis() - thisFrameTimeStart;
        long threadWaitTime = maxFrameTime - thisFrameTime;

        // If we have time to spend, lets pause the thread
        if (threadWaitTime > 0) {
            try {
                Thread.sleep(threadWaitTime);
            } catch (InterruptedException e){
            }
        }
	}
public int  getLevel() {
	return level;
}
}

