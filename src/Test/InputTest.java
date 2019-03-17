package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

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
		InputManager input = new  InputManager();
		dir = input.getPlayerInput();
		// Assert
		assertEquals(Direction.IDLE, dir);
		
	}
	
}
