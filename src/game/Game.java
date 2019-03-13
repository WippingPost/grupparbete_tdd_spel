package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
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
    private boolean running, levelCleared, gameOver;
	private int maxFps = 60;	// Target frame rate
	private long maxFrameTime = 1000 / maxFps;	 // Frame time at maxFps
	private int fps, frameCounter, level;
	private long thisFrameTimeStart, lastTimeUpdate;
	private Player player;
	private InputManager inputManager;
	private LevelManager levelManager;
	private ExitDoor exitDoor;
	private Line2D line;
	private Collision col;
	private final BasicStroke BRUSH_WIDTH = new BasicStroke(2f);	// The width of the laser line
	
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

		levelManager = new LevelManager();
		setBackground(Color.GRAY);
		this.inputManager = inputManager;	// Used to handle player input

		level = 1;	// Setting first level number
		frameCounter = 0;	// Counting frames per second
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

		running = true;		// This is what keeps the game loop running...
		lastTimeUpdate = System.currentTimeMillis();	// Helper for fps counter

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

		// Draw the game board
		// Draw walls
		for (Wall wall : wallList) {
			graphics2d.setColor(wall.getColor());
			graphics2d.fill(wall.getHitBox());
		}

		// Draw treasures
		for (Treasure treasure : treasureList) {
			graphics2d.setColor(treasure.getColor());
			graphics2d.fill(treasure.getHitBox());
		}


		// Draw Lasers
		graphics2d.setStroke(BRUSH_WIDTH);	// The width of the laser line
		for (Laser laser : laserList) {
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
		
		for(Wall wall : wallList) {
			//Vi fångar om player collide med väggen
			/*if(player.collideWith(wall.getHitBox())) {
				System.out.println("Hit wall");
			}*/
		}
		
		// TODO Did player pick up the last treasure? If so, activate exit door!
		// TODO Did player exit the game?
		// ...

		// TODO Update laser
		for (Laser laser : laserList) {
			laser.update();
		}
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
		for (Point point : levelManager.getTreasures()) {
			x = point.x * gridSize;
			y = point.y * gridSize;
			treasureList.add(new Treasure(new Point(x, y), gridSize));
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
