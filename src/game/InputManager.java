package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

	private String playerInput = "idle";


	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput = "right";
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput = "left";
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			playerInput = "up";
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			playerInput = "down";
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if ((playerInput == "right") && (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			playerInput = "idle";
		}

		if ((playerInput == "left") && (e.getKeyCode() == KeyEvent.VK_LEFT)) {
			playerInput = "idle";
		}

		if ((playerInput == "up") && (e.getKeyCode() == KeyEvent.VK_UP)) {
			playerInput = "idle";
		}

		if ((playerInput == "down") && (e.getKeyCode() == KeyEvent.VK_DOWN)) {
			playerInput = "idle";
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

	public String getPlayerInput() {
		return playerInput;
	}

}
