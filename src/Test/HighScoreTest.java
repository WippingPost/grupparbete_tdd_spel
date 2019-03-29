package Test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


import game.HighScore;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class HighScoreTest {

	//Använder oss av temporaryFolder för att ta bort filer vid avslutat test
	  @Rule
	  public TemporaryFolder folder= new TemporaryFolder();

	HighScore highscore;
	
	@Before
	public void init() {
		highscore = new HighScore();
	}
	
	@Test
	public void HighscoreConstructorTest() {
		assertTrue (highscore.getExists());
	}
	 
	/**
	 * Detta test baseras på att det redan finns någon sparad highscore i .txt filen.
	 */
	@Test
	public void updateHighScoreFileTrueTest() {
		
		assertFalse(highscore.getReplacedTime());
		String Level = "1";
		String Time = "1";
		highscore.updateHighScoreFile(Level, Time);
		assertTrue(highscore.getReplacedTime());
		
	}
	
	/**
	 * OBS!! Detta test raderar hela highscore filen!
	 * 
	 * testar att lägga till en sträng i filen för att sen kontrollera den och rensa filen.
	 */
	@Test
	public void addLineToFileTest() {
		boolean check = false;
		String checkString = "add line to string test";
		String str = "";
		highscore.addLineToFile(checkString);
		try {
			
			FileReader FR = new FileReader ("highscore.txt");
			BufferedReader BR = new BufferedReader(FR);
			try {
				while ((str = BR.readLine()) != null) {

					if (str.contains(checkString)) {
						check = true;
						FileWriter FL = new FileWriter("highscore.txt");		
					}
				}
				
				BR.close();
				FR.close();
			} catch (IOException e) {
				e.printStackTrace();
			} } catch (IOException e) {
				e.printStackTrace();
			} 
		
		assertTrue(check);
	}
	
	/**
	 * testar att tömma innehållet i highscore.txt och byta ut det mot en ny sträng. Problemet jag haft här var
	 * att jag inte kunde tömma filen på korrekt sätt utan var tvungen att skapa en ny fil som skrev över den gamla.
	 */
	@Test
	public void replaceAllContentInFileTest() {
		assertFalse(highscore.getEmpty());
		String checkString = "newContent";
		highscore.replaceAllContentInFile(checkString);
		assertTrue(highscore.getEmpty());
	}
	/**
	 * testet matar in förbestämt level och tid till metoden. Sen söker den i highscore.txt efter den exakta raden.
	 */
	@Test
	public void setNewHighScoreTestInHighScore() {
		boolean check = false;
		int level = 99;
		double time = 1;
		highscore.setNewHighScore(level, time);
		try {
			FileReader FR = new FileReader ("highscore.txt");
			BufferedReader BR = new BufferedReader(FR);
			String str = null;
			
			while ((str =  BR.readLine()) !=null) {
				if (str.matches("level=99-time=1.0")) {
					check = true;
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(check);
	}
	
    @Test
    public void checkForHighscoreFile(){
        //Arrange
    	File CreateFolder = null;
    	File CreateFile = null;
    	try {
    		 CreateFolder = folder.newFolder("txt");
    		 CreateFile = folder.newFile("highscore.txt");
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
        boolean exist;

        // Act
        if (!CreateFile.exists()){
            try {
            	CreateFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        exist = CreateFile.exists();

        //Assert
        assertTrue(exist);

    }
    
    @Test
    public void writeToHighscoreFile(){
        //Arrange
    	File CreateFolder = null;
    	File CreateFile = null;
    	try {
    		 CreateFolder = folder.newFolder("txt");
    		 CreateFile = folder.newFile("highscore.txt");
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	boolean writable = false;
        //Act
        if (CreateFile.exists()){
        	if(CreateFile.canWrite()) {
        		writable = true;
        	}
        }
        //Assert
        assertTrue(writable);
    }

    @Test
    public void readFromHighscoreFile(){
        //Arrange
    	File CreateFolder = null;
    	File CreateFile = null;
    	try {
    		 CreateFolder = folder.newFolder("txt");
    		 CreateFile = folder.newFile("highscore.txt");
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	boolean readable = false;

        //Act
        if (CreateFile.exists()){
        	if(CreateFile.canRead()) {
        		readable = true;
        	}
        }
        //Assert
        assertTrue(readable);
    }

    
    /**
     * OBS!!! Detta test raderar innehållet i highscore.txt!
     * 
     * Testet sätter in strängen "TDD was here (:" och kollar om den finns i highscore.txt
     * som används i spelet. Efteråt skapar den en ny och tom highscore.txt
     */
    
    @Ignore
    @Test
    public void newHighscoreTest () {
    	
    	boolean check = false;
    	String checkString = "TDD was here (:";
    	highscore.addLineToFile(checkString);
    	String str = null; 
    		try {
    			
				FileReader FR = new FileReader ("highscore.txt");
				BufferedReader BR = new BufferedReader(FR);
				try {
					while ((str = BR.readLine()) != null) {

						if (str.contains(checkString)) {
							check = true;
							FileWriter FL = new FileWriter("highscore.txt");		
						}
					}
					
					BR.close();
					FR.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    		assertTrue(check);
    }

    // Testar att casta ints och doubles som strängar
	@Test
	public void setNewHighScoreTest(){
    	//Arrange
		int level = 1;
		double time = 60.0;

		//Act
		String levelNumber = "level=" + level;
		String newTime = "" + time;

		//Assert
		assertEquals("level=1",levelNumber);
		assertEquals("60.0",newTime);
	}







	/*
	@Test
	public void getCurrentHighScoreTest(){
    	//Arrange
		int level = 1;
		String line = null;
		String string = "level=" + level;
		double time = -1;
		highscore = new HighScore();

		//Act
		try {
			FileReader fileReader = new FileReader("highscore.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			try {
				while ((line = bufferedReader.readLine()) !=null){
					if (line.contains(string)){
						String splitString = string + "-time=";
						String[] temp = line.split(splitString);
						time = Double.parseDouble(temp[1]);
					}
				}
			} catch (IOException e){
				e.printStackTrace();
			}

		} catch (IOException e){
			e.printStackTrace();
		}

		//Assert




	}
*/

















}
