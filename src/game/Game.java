package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {

	private Thread gameThread = null;
    private boolean running, levelCleared, gameOver;
	private int maxFps = 60;	// Target frame rate
	private long maxFrameTime = 1000 / maxFps;	 // Frame time at maxFps
	private int fps, frameCounter, level;
	private long thisFrameTimeStart, lastTimeUpdate;
	private Player player;
	private InputManager inputManager;
	private LevelManager levelManager;
	private Wall wall;
	private Treasure treasure;
	private ExitDoor exitDoor;
	private Laser laser;

	// ArrayLists of game objects
	private ArrayList<Treasure> treasureList = new ArrayList<>();
	private ArrayList<Wall> wallList= new ArrayList<>();
	private ArrayList<Laser> laserList= new ArrayList<>();


	// Constructor
	public Game(InputManager inputManager) {

		// TODO Create game objects and levels

		levelManager = new LevelManager();
		setBackground(Color.GRAY);
		this.inputManager = inputManager;	// Used to handle player input

		level = 1;	// Setting first level number
		frameCounter = 0;	// Counting frames per second
		running = true;
		gameOver = false;
		levelCleared = false;

		// Loading the level and it's game objects into memory
		loadNextLevel(level);

		// Creating new thread and starting it
		gameThread = new Thread(this);
		gameThread.start();

	}


	// The game loop
	@Override
	public void run() {

		lastTimeUpdate = System.currentTimeMillis();

		// The game loop...
		while (running) {

			// Timer for the fpsControl() method
			// So it knows how long it takes to finish each frame
			thisFrameTimeStart = System.currentTimeMillis();

			getPlayerInput();	// Did the player press any keys?

			updateGame();		// Updating logics, positions etc...

			drawGame();			// Drawing the game objects

			fpsControl(thisFrameTimeStart);		// Handler for frames per second

		}

	}


	// This is the method that draws everything to the screen
	@Override
	public void paint(Graphics graphics) {

		// Clear canvas and prepare to redraw everything
		super.paint(graphics);
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// TODO Draw the game board

		// TODO Draw walls
		for (Wall wall : wallList) {
			graphics2d.setColor(wall.getColor());
			graphics2d.fill(wall.getHitBox());
		}
		// TODO Draw treasures
		for (Treasure treasure : treasureList) {
			graphics2d.setColor(treasure.getColor());
			graphics2d.fill(treasure.getHitBox());
		}


		// TODO Draw Lasers
		graphics2d.setColor(Color.RED);

		// TODO Draw Exit door if active
		graphics2d.setColor(exitDoor.getColor());

		// Draw player
		graphics2d.setColor(player.getColor());
		graphics2d.fill(player.getHitBox());

		// Draw current fps
		graphics2d.setColor(Color.WHITE);
		graphics2d.drawString("" + fps, 7, 17);
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
		// TODO Did player hit a wall?
		// TODO Did player pick up the last treasure? If so, activate exit door!
		// TODO Did player exit the game?
		// ...

		// TODO Update laser

		// Update player
		player.update(fps);
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



	// Loading the level and its game objects
	private void loadNextLevel(int level) {

		int gridSize = 30;
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
			wallList.add(new Wall(new Point(x, y)));
		}
		// The treasures
		for (Point point : levelManager.getTreasures()) {
			x = point.x * gridSize;
			y = point.y * gridSize;
			treasureList.add(new Treasure(new Point(x, y)));
		}
		// TODO The lasers

		// Exit door
		x = levelManager.getExitDoor().x * gridSize;
		y = levelManager.getExitDoor().y * gridSize;
		exitDoor = new ExitDoor(new Point(x, y));

		// Player
		x = levelManager.getPlayer().x * gridSize;
		y = levelManager.getPlayer().y * gridSize;
		player = new Player(new Point(x, y));

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
