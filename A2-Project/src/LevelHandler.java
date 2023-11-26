import Entities.Tiles.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelHandler {
    static int levelTime = 0; //The amount of completion time a level file has specified.
    static int specialisedTileCount = 0;

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

        Level newLevel = null;

        try {
            in = new Scanner(fileLevel);
            newLevel = readLineByLine(in);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + fileLevel.getName());
            System.exit(1);
        }
        return newLevel;
    }

    /***
     * Method that allows for the retrieval of the levelTime attribute.
     * @return The completion time of the Level.
     */
    public static int getLevelTime() {
        return levelTime;
    }

    /***
     * Private method that produces a LevelLayout from the data read from the Scanner reading the file.
     * @param in
     * @return A completed LevelLayout structure.
     */
    private static Level readLineByLine(Scanner in) {
        String levelName = in.next();
        levelTime = in.nextInt();
        int levelWidth = in.nextInt();
        int levelHeight = in.nextInt();

        Level newLevel = new Level();
        newLevel.levelName = levelName;
        newLevel.timer = levelTime;

        newLevel.currentLevel = initialiseLevelLayout(in, levelWidth, levelHeight); //Initialises the levelLayout
        return newLevel;
    }


    /*
    Main class to parse through the Tile, Item and Actor levelLayout information-
    currently the main priority is initialising tiles and specialised tile attributes.
     */
    private static LevelLayout initialiseLevelLayout(Scanner in, int levelWidth, int levelHeight) {
        LevelLayout newLevelLayout = new LevelLayout();
        newLevelLayout.createTileLayer(levelWidth, levelHeight);

        String tileSection = in.next();
        Scanner tileParseScanner = new Scanner(tileSection);
        tileParseScanner.useDelimiter(",");

        //Loops through the file and outputs the location on an x, y level. (Should allow for multi-line/height support)
        for (int j = 0; j < levelHeight; j++) {
            for (int i = 0; i < levelWidth; i++) {
                String currentLetter = tileParseScanner.next(); //Reads the currentTile Letter and assignment for comparisons
                if (currentLetter.equalsIgnoreCase("B")) {
                    System.out.println("Button" + i + "," + j);
                    //SpecialisedTile
                    specialisedTileCount++;
                } else if (currentLetter.equalsIgnoreCase("CS")) {
                    System.out.println("ComputerSocket" + i + "," + j);
                    //SpecialisedTile
                    specialisedTileCount++;
                } else if (currentLetter.equalsIgnoreCase("D")) {
//                    System.out.println("Dirt" + i + "," + j);
                    Dirt dirtTile = new Dirt(i, j);
                    newLevelLayout.setTile(i, j, dirtTile);
                } else if (currentLetter.equalsIgnoreCase("E")) {
//                    System.out.println("Exit" + i + "," + j);
                    Exit exitTile = new Exit(i, j);
                    newLevelLayout.setTile(i, j, exitTile);
                } else if (currentLetter.equalsIgnoreCase("I")) {
                    System.out.println("Ice" + i + "," + j);
                    //SpecialisedTile
                    specialisedTileCount++;
                } else if (currentLetter.equalsIgnoreCase("LD")) {
                    System.out.println("LockedDoor" + i + "," + j);
                    //SpecialisedTile
                    specialisedTileCount++;
                } else if (currentLetter.equalsIgnoreCase("P")) {
//                    System.out.println("Path" + i + "," + j);
                    Path pathTile = new Path(i, j);
                    newLevelLayout.setTile(i, j, pathTile);
                } else if (currentLetter.equalsIgnoreCase("T")) {
                    System.out.println("Trap" + i + "," + j);
                    //SpecialisedTile
                    specialisedTileCount++;
                } else if (currentLetter.equalsIgnoreCase("W")) {
//                    System.out.println("Water" + i + "," + j);
                    Water waterTile = new Water(i, j);
                    newLevelLayout.setTile(i, j, waterTile);
                } else if (currentLetter.equalsIgnoreCase("WL")) {
//                    System.out.println("Wall" + i + "," + j);
                    Wall wallTile = new Wall(i, j);
                    newLevelLayout.setTile(i, j, wallTile);
                } else if (currentLetter.equalsIgnoreCase("S")) {
                    newLevelLayout.setSpawn(i, j);
                } else {
                    System.out.println("CurrentTile is: " + i + currentLetter);
                }
            }
        }

        //specialisedParse(in, specialisedTileCount);


        return newLevelLayout;
    }

    /* TBD: Idea behind this is to parse the data for specialised tiles
     that have additional attributes, that can then be added to the levelLayout.
     */
    private static Tile specialisedParse(Scanner in, int specialisedTileCount) {
        String specialisedTileAttribute = in.next();

        for (int i = 0; i < specialisedTileCount; i++) {
            System.out.println(specialisedTileAttribute);
        }

        return null;
    }
}
