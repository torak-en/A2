import Entities.Tiles.Tile;

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

        LevelLayout newLevelLayout = new LevelLayout();
        String parseThis = in.next(); //This -> S,I,D,P,T-1,CS-2,B,E,LD
        //System.out.println(parseThis);


        //Tile Scanning

        Scanner tileParseScanner = new Scanner(parseThis);
        tileParseScanner.useDelimiter(",");



        //Use another loop to get multi-line?
//        for (int j = 0; j < levelHeight; j++) {
//
//        }
        for (int i = 0; i < levelWidth; i++) {
            String currentVal = tileParseScanner.next();
            if (currentVal.equalsIgnoreCase("B")) {
                //System.out.println("Button" + i);
                //Specialised
                specialisedTileCount++;
            } else if (currentVal.equalsIgnoreCase("CS")) {
                //System.out.println("ComputerSocket" + i);
                //Specialised
                specialisedTileCount++;
            } else if (currentVal.equalsIgnoreCase("D")) {
                //System.out.println("Dirt" + i);
            } else if (currentVal.equalsIgnoreCase("E")) {
                //System.out.println("Exit" + i);
            } else if (currentVal.equalsIgnoreCase("I")) {
                //System.out.println("Ice" + i);
                //Specialised
                specialisedTileCount++;
            } else if (currentVal.equalsIgnoreCase("LD")) {
                //System.out.println("LockedDoor" + i);
                //Specialised
                specialisedTileCount++;
            } else if (currentVal.equalsIgnoreCase("P")) {
                //System.out.println("Path" + i);
            } else if (currentVal.equalsIgnoreCase("T")) {
                //System.out.println("Trap" + i);
                //Specialised
                specialisedTileCount++;
            } else if (currentVal.equalsIgnoreCase("W")) {
                //System.out.println("Water" + i);
            } else if (currentVal.equalsIgnoreCase("WL")) {
                //System.out.println("Wall" + i);
            } else if (currentVal.equalsIgnoreCase("S")) {
                newLevelLayout.setSpawn(i, levelWidth);
            } else {
                System.out.println("CurrentVal is: " + i + currentVal);
            }
        }

        specialisedParse(in, specialisedTileCount);

        newLevel.currentLevel = newLevelLayout;
        return newLevel;
    }

    private static Tile specialisedParse(Scanner in, int specialisedTileCount) {
        String currentTileVal = in.next();

        for (int i = 0; i < specialisedTileCount; i++) {
            System.out.println(currentTileVal);
        }

        return null;
    }
}
