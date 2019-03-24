package Test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class HighscoreTest {

    @Test
    public void checkForHighscoreFile(){
        //Arrange
        File file = new File("src/Test/txt/highscore.txt");
        boolean exist;

        // Act
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        exist = file.exists();

        //Assert
        assertTrue(exist);

    }

    @Test
    public void writeToHighscoreFile(){
        //Arrange
        File file = new File("src/Test/txt/highscore.txt");
        boolean exist;

        //Act
        if (file.exists()){

        }


        //Assert


    }

    @Test
    public void readFromHighscoreFile(){
        //Arrange
        File file = new File("src/Test/txt/highscore.txt");

        //Act


        //Assert


    }

}
