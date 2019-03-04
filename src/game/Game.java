package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {

	private Thread gameThread = null;
	private boolean running;
	private int maxFps = 60;
	private int fps, frameCounter;
	private long maxFrameTime = 1000 / maxFps;
	private long thisFrameTimeStart, lastTimeUpdate;
	private Player player;
	private InputManager inputManager;

	// Constructor
	public Game(InputManager inputManager) {

		// TODO Create game objects and levels
		player = new Player(20, 20);
		setBackground(Color.GRAY);
		this.inputManager = inputManager;
		gameThread = new Thread(this);
		gameThread.start();
		frameCounter = 0;
		running = true;
	}



	@Override
	public void run() {

		lastTimeUpdate = System.currentTimeMillis();


		// The game loop...
		while (running) {

			// Timer for the fpsControl() method
			// So it knows how long it takes to finish each frame
			thisFrameTimeStart = System.currentTimeMillis();

			getPlayerInput();

			updateGame();

			drawGame();

			fpsControl(thisFrameTimeStart);

		}

	}



	@Override
	public void paint(Graphics graphics) {

		// Clear canvas and prepare to redraw everything
		super.paint(graphics);
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw current fps
		graphics2d.setColor(Color.BLACK);
		graphics2d.drawString("fps = " + fps, 10, 10);

		// TODO Draw walls
		graphics2d.setColor(Color.DARK_GRAY);

		// TODO Draw treasures
		graphics2d.setColor(Color.ORANGE);

		// TODO Draw Lasers
		graphics2d.setColor(Color.RED);

		// TODO Draw Exit door if active
		graphics2d.setColor(Color.GREEN);

		// Draw player
		graphics2d.setColor(Color.BLACK);
		graphics2d.fillRect(player.getHitBox().x,
				player.getHitBox().y,
				player.getHitBox().width,
				player.getHitBox().height);
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
