package Test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

import java.io.*;

public class HighscoreTest {

	//Använder oss av temporaryFolder för att ta bort filer vid avslutat test
	  @Rule
	  public TemporaryFolder folder= new TemporaryFolder();

	
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

}
