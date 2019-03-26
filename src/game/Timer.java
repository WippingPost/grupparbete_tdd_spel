package game;

public class Timer {

	private long startTime, passedTime, finalTime;
	private boolean isRunning;

	public Timer() {
		startTime = 0;
		passedTime = 0;
		finalTime = 0;
	}

	// Starts timer
	public void start() {
		isRunning = true;
		startTime = System.currentTimeMillis();
		passedTime = startTime;
	}

	// Stops timer
	public void stop() {
		isRunning = false;
		finalTime = System.currentTimeMillis() - startTime;
	}

	// Resets timer
	public void reset() {
		startTime = 0;
		passedTime = 0;
		finalTime = 0;
	}

	// Returns seconds passed since start.
	public double getPassedTime() {

		if (isRunning) {
			passedTime = System.currentTimeMillis() - startTime;
		}
		return round(passedTime, 1);
	}

	public double getFinalTime() {
		return round(finalTime, 1);
	}
	public boolean getisRunning() {
		return isRunning;
	}

	// Returns passed time as a double with selected number of decimals
	private double round(double value, int noOfDecimals) {

		double precision = Math.pow(10, noOfDecimals);

		// Returning the time with number of decimals as chosen
		return Math.round((value / 1000) * precision) / precision;
	}

}
