package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

	private Direction playerInput = Direction.IDLE;


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

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if ((playerInput == Direction.RIGHT) && (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			playerInput = Direction.IDLE;
		}

		if ((playerInput == Direction.LEFT) && (e.getKeyCode() == KeyEvent.VK_LEFT)) {
			playerInput = Direction.IDLE;
		}

		if ((playerInput == Direction.UP) && (e.getKeyCode() == KeyEvent.VK_UP)) {
			playerInput = Direction.IDLE;
		}

		if ((playerInput == Direction.DOWN) && (e.getKeyCode() == KeyEvent.VK_DOWN)) {
			playerInput = Direction.IDLE;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

	public Direction getPlayerInput() {
		return playerInput;
	}

}
