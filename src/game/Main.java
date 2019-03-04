package game;



import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("SpelExempel");
		InputManager inputManager = new InputManager();
		Game game = new Game(inputManager);
		frame.setSize(900, 900);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game).setBackground(Color.GRAY);
		frame.addKeyListener(inputManager);
	}

}
