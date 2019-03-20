package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Game;
import game.InputManager;
import game.LevelManager;

public class GameTest {

	
	/*
	 * 
	 * Ett test för att kolla om initeringen av Threaden blir gjort utan problem
	 * (Skulle vara önskavärt att få till ett test som kollar att run körs)
	 */

/**
 * Testar att startleveln är mindre än antalet existerande levels
 */
	@Test
	public void testStartlevel() {

		// Act
		LevelManager LM = new LevelManager();
		InputManager inputManager = new InputManager();
		Game game = new Game(inputManager); 
		// Assert
		assertTrue(game.getLevel()<LM.getNumberOfLevels());
		assertFalse(game.getLevel()>LM.getNumberOfLevels());
	}


}
