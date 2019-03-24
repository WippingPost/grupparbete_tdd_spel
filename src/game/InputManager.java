package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class InputManager implements KeyListener {

	private Direction playerInput = Direction.IDLE;
	private boolean gameRunning = true;

	private HashMap<Integer, Direction> keys = new HashMap<>();  // Holds the key values if multiple keys are pressed


	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput = Direction.RIGHT;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput = Direction.LEFT;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			playerInput = Direction.UP;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			playerInput = Direction.DOWN;
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameRunning = false;
		}

		// Put playInput in HashMap to handle multiple keys pressed
		keys.put(e.getKeyCode(), playerInput);
	}

	@Override
	public void keyReleased(KeyEvent e) {

		// Removing the last key released
		keys.remove(e.getKeyCode());

		// Setting playerInput to the last pressed value
		for (Direction direction : keys.values()) {
			playerInput = direction;
		}

		// If there all keys are released make player idle
		if (keys.size() == 0) {
			playerInput = Direction.IDLE;
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {

	}

	public Direction getPlayerInput() {
		return playerInput;
	}

	public boolean getGameState() {
		return gameRunning;
	}

}
