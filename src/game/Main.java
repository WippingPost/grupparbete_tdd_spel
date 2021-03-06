package game;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Grupprojekt_Spel");
		InputManager inputManager = new InputManager();
		Game game = new Game(inputManager);

		// Fullscreen mode
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		// Adds everything to the frame
		frame.add(game);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(inputManager);
		frame.requestFocus();
	}
}
