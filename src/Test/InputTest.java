package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import game.Direction;
import game.InputManager;
import game.Player;

public class InputTest {

	
	InputManager inputmanager;
	
	// Skapar en ny inputmanager
	@Before
	public void setUp() throws Exception {
		inputmanager = new InputManager();
	}
	
	/*
	 * Testar Default Direction state för InputManager, när inputmanager körs ska denna som standard vara IDLE
	 * 
	 */
	@Test
	public void testInputManagerDefaultState() {
		// Act
		Direction dir;
		dir = inputmanager.getPlayerInput();
		// Assert
		assertEquals(Direction.IDLE, dir);
		
	}
	

	
}
