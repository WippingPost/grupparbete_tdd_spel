package game;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Grupprojekt_Spel");
		InputManager inputManager = new InputManager();
		Game game = new Game(inputManager);
		frame.setExtendedState(6);
		frame.add(game);
		frame.setResizable(true);
		frame.setVisible(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(inputManager);
		frame.requestFocus();
	}
}
