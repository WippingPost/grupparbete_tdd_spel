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
	  
	@Test
	public void updateHighScoreFileTrueTest() {
		
		assertFalse(highscore.getReplacedTime());
		String Level = "1";
		String Time = "1";
		highscore.updateHighScoreFile(Level, Time);
		assertTrue(highscore.getReplacedTime());
		
	}
	
	/**
	 * stämmer inte då raderna 63-69 i HighScore är baserat på om leveln finns. Ska kolla mer på detta
	 * Dvs replaced time ger true även om tiden inte är bättre men om leveln finns.
	 */
	
	@Test
	public void updateHighScoreFileFalseTest() {
		assertFalse(highscore.getReplacedTime());
		String Level = "1";
		String Time = "2";
		highscore.updateHighScoreFile(Level, Time);
		assertFalse(highscore.getReplacedTime());
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
     * Testet sätter in strängen "TDD was here (:" och kollar om den finns i highscore.txt
     * som används i spelet. Detta funkar, problemet är bara att jag inte lyckats radera strängen i metoden.
     * 
     */
    
    @Ignore
    @Test
    public void newHighscoreTest () {
    	
    	boolean check = false;
    	String checkString = "TDD was here (:";
    	highscore.addLineToFile(checkString);
    	String str = null; 
    		try {
				//FileWriter FL = new FileWriter("highscore.txt", true);
				FileReader FR = new FileReader ("highscore.txt");
				BufferedReader BR = new BufferedReader(FR);
			//	BufferedWriter BW = new BufferedWriter(FL);
				try {
					while ((str = BR.readLine()) != null) {

						if (str.contains(checkString)) {
							check = true;
					
						
						}
						
						
					}
					
					
							
					//PW.close();
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
    
}
