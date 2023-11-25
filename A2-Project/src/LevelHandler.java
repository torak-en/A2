import Entities.Tiles.Tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelHandler {
    static int levelTime = 0; //The amount of completion time a level file has specified.

    /***
     * Constructor to create a LevelHandler object.
     * @param levelFile
     */
    public LevelHandler(File levelFile) {
        readDataFile(levelFile); //Produces a complete LevelLayout object.
    }

    /***
     * Private method that initiates call from Constructor.
     * @param fileLevel
     * @return A completed LevelLayout object with all it's attributes.
     */
    private static Level readDataFile(File fileLevel) {
        Scanner in;

        Level newLevelLayout = null;

        try {
            in = new Scanner(fileLevel);
            newLevelLayout = readLineByLine(in);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + fileLevel.getName());
            System.exit(1);
        }
        return newLevelLayout;
    }

    /***
     * Method that allows for the retrieval of the levelTime attribute.
     * @return levelTime of the Level.
     */
    public static int getLevelTime() {
        return levelTime;
    }

    /***
     * Private Method that produces a LevelLayout from the data read from the Scanner reading the file.
     * @param in
     * @return Completed LevelLayout structure.
     */
    private static Level readLineByLine(Scanner in) {
        String levelName = in.next();
        levelTime = in.nextInt();
        int levelWidth = in.nextInt();
        int levelHeight = in.nextInt();
        String levelShape = in.next();


        //Work Zone.
        Level newLevel = new Level();
        LevelLayout newLevelLayout = null;

        newLevel.currentLevel = newLevelLayout;

        return newLevel;
    }

    /**
     * Private method that initialises the Tile, Actor and Item layers that make up the LevelLayout
     * @param in
     * @param newLevelLayout
     * @param levelWidth
     * @param levelHeight
     * @return A completed LevelLayout structure.
     */
    private static LevelLayout initialiseLayers(Scanner in, LevelLayout newLevelLayout, int levelWidth, int levelHeight) {
        //TBD
        return newLevelLayout;
    }

    /***
     * Private method that parses the Tile data in the file being read
     * @param in
     * @return The respective tile produced by the letter in the file.
     */
    private static Tile parseTileData(Scanner in) {
        //TBD
        return null;
    }
}
