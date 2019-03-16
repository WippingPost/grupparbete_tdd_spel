package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {

	private Dimension screenSize;
	private int screenWidth, screenHeight;
	private final int gridSize;
	private Thread gameThread = null;
    private boolean pausedState, running, levelCleared, gameOver, loadingScreen;
	private int maxFps = 60;	// Target frame rate
	private long maxFrameTime = 1000 / maxFps;	 // Frame time at maxFps
	private int fps, frameCounter, level;
	private long thisFrameTimeStart, lastTimeUpdate;
	private long nowTime, deltaTime;
	private Player player;
	private InputManager inputManager;
	private LevelManager levelManager;
	private ExitDoor exitDoor;
	private Line2D line;
	private final BasicStroke BRUSH_WIDTH = new BasicStroke(2f);	// The width of the laser line

	private int noOFTreasuresInLevel, treasuresCollected;
	private Font font;
	private Font font2;

	// ArrayLists of game objects
	private ArrayList<Treasure> treasureList = new ArrayList<>();
	private ArrayList<Wall> wallList= new ArrayList<>();
	private ArrayList<Laser> laserList= new ArrayList<>();



	// Constructor
	public Game(InputManager inputManager) {

		// Getting the screen size
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		// This sets the size (in pixels) of each grid on the game board
		gridSize = screenHeight / 32;

		// Text font and size
		font = new Font("Serif", Font.BOLD, screenHeight / 30);
		font2 = new Font("Serif", Font.PLAIN, screenHeight / 50);

		levelManager = new LevelManager();
		setBackground(Color.GRAY);
		this.inputManager = inputManager;	// Used to handle player input

		level = 1;	// Setting first level number
		frameCounter = 0;	// Counting frames per second
		gameOver = false;
		levelCleared = false;
		fps = maxFps;	// Setting fps for the first second of the game

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

			getPlayerInput();	// Did the player press any keys?

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

		// TODO Draw Loading Screen if active
		if (!loadingScreen) {

			// Draw the game board
			// Draw walls
			for (Wall wall : wallList) {
				graphics2d.setColor(wall.getColor());
				graphics2d.fill(wall.getHitBox());
			}

			// Draw treasures
			for (Treasure treasure : treasureList) {
				graphics2d.setColor(treasure.getColor());
				// Only draw if still active (not picked up by player)
				if (treasure.isActive()) {
					graphics2d.fillRoundRect(treasure.getHitBox().x, treasure.getHitBox().y,
							treasure.getHitBox().width, treasure.getHitBox().height, 6 , 6);
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
				graphics2d.setColor(exitDoor.getColor());
				graphics2d.fill(exitDoor.getHitBox());
			}

			// Draw player
			graphics2d.setColor(player.getColor());
			graphics2d.fillRoundRect(player.getHitBox().x, player.getHitBox().y,
					player.getHitBox().width, player.getHitBox().height, 5, 5);

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

	}



	// Update method
	private void updateGame() {

		// Collision detection
		// TODO Did player hit a laser?

		for (Laser laser : laserList){

            line = new Line2D.Float(laser.getPoint1(), laser.getPoint2());

			if (laser.isActive() && player.collideWith(line) && !pausedState){
				player.inActivate();
				pausedState = true;
				gameOver = true;
				deltaTime = System.currentTimeMillis();
			}


		}

		// TODO Did player hit a wall?

		for(Wall wall : wallList) {
			//Vi fångar om player collide med väggen
			if(player.collideWith(wall.getHitBox())) {
				player.resetPosition();

			}
		}

		// TODO Did player pick up the last treasure? If so, activate exit door!
		for (Treasure other : treasureList) {
			if (other.isActive() && player.collideWith(other.getHitBox())) {
				other.setPickedUp();
				treasuresCollected ++;
				// Did player pick up all treasures?
				if (treasuresCollected == noOFTreasuresInLevel) {
					exitDoor.setActive();
				}
			}
		}

		// Did player exit the game?
		if (exitDoor.isActive() && !pausedState) {
			if (exitDoor.contains(player.getHitBox())) {
				player.inActivate();	// Inactivates the player while loading new level
				levelCleared = true;
				level++;
				deltaTime = System.currentTimeMillis();
				pausedState = true;
			}
			exitDoor.update();
		}

		// ...

		// TODO Update laser
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

		}

		if (levelCleared && (nowTime - deltaTime) > 4000) {		// 4 second wait

			// Did we reach the last level? If so, quit the game
			if (levelManager.getNumberOfLevels() < level) {
				running = false;
			} else {
				loadLevel(level);
			}
			loadingScreen = false;
		}
	}

	private void drawInformation(Graphics2D graphics2d) {

		// Draw current fps
		graphics2d.setColor(Color.BLACK);
		graphics2d.setFont(font2);
		graphics2d.drawString("FPS = " + fps, gridSize * 31, gridSize);
		graphics2d.setFont(font);
		if (!pausedState) {
			graphics2d.drawString("LEVEL " + level, gridSize * 31, screenHeight / 4);
		} else {
			graphics2d.drawString("LEVEL " + (level - 1), gridSize * 31, screenHeight / 4);
		}
		graphics2d.drawString("Treasures Collected = " + treasuresCollected
				+ " / " + noOFTreasuresInLevel, gridSize * 31, screenHeight / 3);
		graphics2d.setFont(font2);
		graphics2d.drawString("Control the burglar (black rectangle) with the arrow keys!", gridSize * 31, (int)(screenHeight / 1.3f));
		graphics2d.drawString("Steal all the treasures (yellow) to advance to the next level."
				, gridSize * 31, (int)(screenHeight / 1.25f));
		graphics2d.drawString("Avoid burglar alarm lasers (red) or you will be caught!"
				, gridSize * 31, (int)(screenHeight / 1.2f));
		graphics2d.drawString("When all treasures are stolen a green exit door will appear."
				, gridSize * 31, (int)(screenHeight / 1.15f));
	}



	// Draw Loading Screen
	private void drawLoadingScreen(Graphics2D graphics2d) {

		graphics2d.setColor(Color.BLACK);
		graphics2d.setFont(font);

		if (gameOver) {
			graphics2d.drawString("YOU WERE CAUGHT STEALING!", screenWidth / 3, screenHeight / 4);
			graphics2d.drawString("TRY AGAIN!", screenWidth / 3, screenHeight / 3);
		}

		if (levelCleared && levelManager.getNumberOfLevels() >= level) {
			graphics2d.drawString("LEVEL CLEARED!", screenWidth / 3, screenHeight / 4);
			graphics2d.drawString("LOADING LEVEL " + level, screenWidth / 3, screenHeight / 3);
		}

		if (levelCleared && (levelManager.getNumberOfLevels() < level)) {
			graphics2d.drawString("ALL LEVELS CLEARED!", screenWidth / 3, screenHeight / 4);
			graphics2d.drawString("GAME OVER!", screenWidth / 3, screenHeight / 3);
			graphics2d.drawString("QUITING GAME!", screenWidth / 3, screenHeight / 2);
		}
	}



	// Loading the level and its game objects
	private void loadLevel(int level) {

		levelCleared = false;
		gameOver = false;

		int x = 0;
		int y = 0;

		// Clearing the lists
		wallList.clear();
		treasureList.clear();
		laserList.clear();

		// Loading all game objects into separate lists
		levelManager.setNextLevel(level);

		// Populating lists with new game objects
		// The walls
		for (Point point : levelManager.getWalls()) {
			x = point.x * gridSize;
			y = point.y * gridSize;
			wallList.add(new Wall(new Point(x, y), gridSize));
		}
		// The treasures
		// Reset counters for each level.
		noOFTreasuresInLevel = 0;
		treasuresCollected = 0;
		for (Point point : levelManager.getTreasures()) {
			x = point.x * gridSize;
			y = point.y * gridSize;
			treasureList.add(new Treasure(new Point(x, y), gridSize));
			noOFTreasuresInLevel ++;
		}
		// TODO The lasers
		Point point1;
		Point point2;
		for (int i = 0; i < levelManager.getLasers().size(); i = i + 2) {	// Getting the points of the lasers as pairs, therefore increment by 2
			point1 = levelManager.getLasers().get(i);
			point1.x = (point1.x * gridSize);
			point1.y = (point1.y * gridSize);
			point2 = levelManager.getLasers().get(i + 1);
			point2.x = (point2.x * gridSize);
			point2.y = (point2.y * gridSize);
			laserList.add(new Laser(point1, point2, gridSize));
		}

		// Exit door
		x = levelManager.getExitDoor().x * gridSize;
		y = levelManager.getExitDoor().y * gridSize;
		exitDoor = new ExitDoor(new Point(x, y), gridSize);

		// Player
		x = levelManager.getPlayer().x * gridSize;
		y = levelManager.getPlayer().y * gridSize;
		player = new Player(new Point(x, y), gridSize);

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

}

