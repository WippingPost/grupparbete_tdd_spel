package Test;

import static org.junit.Assert.*;

import org.junit.*;

import game.Timer;

public class TimerTest {

	Timer timer;
	
	@Before
	public void init() {
	timer = new Timer();
	}

	@Test
	public void startTest() {
		assertFalse(timer.getisRunning());
		timer.start();
		assertTrue(timer.getisRunning());
	}
	
	@Test
	public void stopTest() {
		assertFalse(timer.getisRunning());
		timer.stop();
		assertFalse(timer.getisRunning());
	}
	
	@Test
	public void resetTest () {
		timer.reset();
		double acual = timer.getFinalTime();
		assertEquals(0d, acual, 0.111);		
	}
}