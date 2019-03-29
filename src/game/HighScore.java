package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class HighScore {

	private final String FILENAME = "highscore.txt";
	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private boolean exists, empty;
	private boolean replacedTime = false;


	// Constructor
	public HighScore() {

		file = new File(FILENAME);

		// Create file if it doesn't exist
		try {
			file.createNewFile();
			exists = file.exists();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	// Writing to high score file
	public void updateHighScoreFile(String level, String time) {
		
		replacedTime = false;
		
		String line = null;
		String newContent = "";

		try {

			fileReader = new FileReader(FILENAME);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		bufferedReader = new BufferedReader(fileReader);

		// Reading the file line by line
		try {
			while ((line = bufferedReader.readLine()) != null) {

				// Checking if the line contains high score of level that needs to be updated
				if (line.contains(level)) {
					String splitString = level + "-time=";
					String[] temp = line.split(splitString);
					// Replacing the old time with the new time
					temp[1] = time;
					line = splitString + temp[1];
					replacedTime = true;
				}
				// Add line to newContent and set a new line starting position
				newContent += line + "\r\n";
			}

			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Did we replace an old high score?
		// If not, we need to create a new line with the level and time values
		if (!replacedTime) {
			newContent = "";
			newContent += level + "-time=" + time + "\r\n";
			addLineToFile(newContent);

		} else {
			replaceAllContentInFile(newContent);
		}
	}



	// Adding a new line to "highscore.txt"
	public void addLineToFile(String newContent) {

		try {
			fileWriter = new FileWriter(FILENAME, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(newContent);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	// Writing all new content to "highscore.txt"
	public void replaceAllContentInFile(String newContent) {

		try {
			fileWriter = new FileWriter(FILENAME);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(newContent);
			bufferedWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	

	}



	// Setting the high score of a certain level
	public void setNewHighScore(int level, double time) {
		String levelNumber = "level=" + level;
		String newTime = "" + time;
		updateHighScoreFile(levelNumber, newTime);
	}



	// Getting the High Score of a certain level as a double
	public double getCurrentHighScore(int level) {

		String line = null;
		String string = "level=" + level;
		double time = -1;

		try {
			fileReader = new FileReader(FILENAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		bufferedReader = new BufferedReader(fileReader);

		try {
			while ((line = bufferedReader.readLine()) != null) {

				if (line.contains(string)) {
					String splitString = string + "-time=";
					String[] temp = line.split(splitString);
					time = Double.parseDouble(temp[1]);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return time;	// Returns -1 if no high score for level exists
	}



	public boolean getExists() {
		return exists;
	}
	public boolean getReplacedTime() {
		return replacedTime;
	}
	public boolean getEmpty() {
		return empty;
	}
}
