package Test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import game.Game;
import game.InputManager;

public class GameTest {



	@Test
	public void testInitGame() {

		// Act
		InputManager inputManager = new InputManager();
		Game game = new Game(inputManager); 
		
		// Assert
		assertEquals(game, true);
	}


}
