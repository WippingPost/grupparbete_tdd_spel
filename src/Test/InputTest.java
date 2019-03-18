package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Direction;
import game.InputManager;

public class InputTest {

	
	/*
	 * Testar Default Direction state för InputManager, när inputmanager körs ska denna som standard vara IDLE
	 * 
	 */
	@Test
	public void testInputManagerDefaultState() {
		// Act
		Direction dir;
		//KeyEvent e = KeyEvent.VK_RIGHT;
		InputManager input = new  InputManager();
		dir = input.getPlayerInput();
		// Assert
		assertEquals(Direction.IDLE, dir);
		
	}
	
}
