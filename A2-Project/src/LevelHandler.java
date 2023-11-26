import Enum.Direction;
import Tiles.*;

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

        initialiseTileLayer(newLevelLayout, in, levelHeight, levelWidth);

        return newLevelLayout;
    }

    //Continue with TileParserAccessory Methods
    //You have the general pattern now

    //Ice will be a bit of a pain.


    private static LevelLayout initialiseTileLayer(LevelLayout newLevelLayout, Scanner tileScanner, int levelHeight, int levelWidth) {
        String tileSection = tileScanner.next();
        Scanner tileDataScanner = new Scanner(tileSection); //Specific to parsing 1st Line
        tileDataScanner.useDelimiter(",");

        //Loops through the file and outputs the location on an x, y level. (Should allow for multi-line/height support)
        for (int j = 0; j < levelHeight; j++) {
            for (int i = 0; i < levelWidth; i++) {
                String currentLetter = tileDataScanner.next(); //Reads the currentTile Letter and assignment for comparisons


                //Testing for specialisedTiles, non-specialised Tiles can be set perfectly well.

                if (currentLetter.equalsIgnoreCase("B")) {
                    Button buttonTile = new Button(i, j, ButtonAttributeParse("B", tileScanner));
                    newLevelLayout.setTile(i, j, buttonTile);
//                } else if (currentLetter.equalsIgnoreCase("CS")) {
//                    ChipSocket chipSocketTile = new ChipSocket(i, j, ChipSocketAttributeParse("CS", tileScanner));
                } else if (currentLetter.equalsIgnoreCase("D")) {
                    Dirt dirtTile = new Dirt(i, j);
                    newLevelLayout.setTile(i, j, dirtTile);
                } else if (currentLetter.equalsIgnoreCase("E")) {
                    Exit exitTile = new Exit(i, j);
                    newLevelLayout.setTile(i, j, exitTile);
                } else if (currentLetter.equalsIgnoreCase("P")) {
                    Path pathTile = new Path(i, j);
                    newLevelLayout.setTile(i, j, pathTile);
                } else if (currentLetter.equalsIgnoreCase("W")) {
                    Water waterTile = new Water(i, j);
                    newLevelLayout.setTile(i, j, waterTile);
                } else if (currentLetter.equalsIgnoreCase("WL")) {
                    Wall wallTile = new Wall(i, j);
                    newLevelLayout.setTile(i, j, wallTile);
                } else if (currentLetter.equalsIgnoreCase("S")) {
                    newLevelLayout.setSpawn(i, j);
                } else {
                    System.out.println("CurrentTile is: " + i + currentLetter);
                }
            }
        }

        return (newLevelLayout);
    }

    //issue is we need to start from 0 everytime.
    private static int ButtonAttributeParse(String currentLetter, Scanner in) {
        int currentButtonID = 0;
        String specialisedTileAttribute = in.next();

        Scanner idParser = new Scanner(specialisedTileAttribute);
        idParser.useDelimiter(",");

        int z = 0;

        while ((idParser.hasNext()) && z < specialisedTileAttribute.length()) {
            String tileSpecialisedValue = idParser.next();
            Scanner breakdownParser = new Scanner(tileSpecialisedValue);
            breakdownParser.useDelimiter("-");


            String tileIdentifier = breakdownParser.next();

            if (tileIdentifier.equalsIgnoreCase(currentLetter)) {
                int tileSpecialisedID = breakdownParser.nextInt();
                currentButtonID = tileSpecialisedID;
                return currentButtonID;
            } else {
                z++;
            }
        }
        return currentButtonID;
    }

    private static int ChipSocketAttributeParse(String currentLetter, Scanner in) {
        int currentChipSocketNumberOfChips = 0;

        String specialisedTileAttribute = in.next();

        Scanner idParser = new Scanner(specialisedTileAttribute);
        idParser.useDelimiter(",");

        int z = 0;

        while ((idParser.hasNext()) && z < specialisedTileAttribute.length()) {
            String tileSpecialisedValue = idParser.next();
            Scanner breakdownParser = new Scanner(tileSpecialisedValue);
            breakdownParser.useDelimiter("-");


            String tileIdentifier = breakdownParser.next();

            if (tileIdentifier.equalsIgnoreCase(currentLetter)) {
                int tileSpecialisedID = breakdownParser.nextInt();
                currentChipSocketNumberOfChips = tileSpecialisedID;
                return currentChipSocketNumberOfChips;
            }

            z++;
        }

        return currentChipSocketNumberOfChips;
    }

    private static int TrapAttributeParse(String currentLetter, Scanner in) {
        int currentTrapID = 0;

        String specialisedTileAttribute = in.next();

        Scanner idParser = new Scanner(specialisedTileAttribute);
        idParser.useDelimiter(",");

        int z = 0;

        while ((idParser.hasNext()) && z < specialisedTileAttribute.length()) {
            String tileSpecialisedValue = idParser.next();
            Scanner breakdownParser = new Scanner(tileSpecialisedValue);
            breakdownParser.useDelimiter("-");


            String tileIdentifier = breakdownParser.next();

            if (tileIdentifier.equalsIgnoreCase(currentLetter)) {
                int tileSpecialisedID = breakdownParser.nextInt();
                currentTrapID = tileSpecialisedID;
            } else {
                z++;
            }
        }


        return currentTrapID;
    }

    private static String LockedDoorAttributeParse(String currentLetter, Scanner in) {
        String currentLockedDoorColour = "";

        String specialisedTileAttribute = in.next();

        Scanner idParser = new Scanner(specialisedTileAttribute);
        idParser.useDelimiter(",");

        int z = 0;

        while ((idParser.hasNext()) && z < specialisedTileAttribute.length()) {
            String tileSpecialisedValue = idParser.next();
            Scanner breakdownParser = new Scanner(tileSpecialisedValue);
            breakdownParser.useDelimiter("-");


            String tileIdentifier = breakdownParser.next();

            if (tileIdentifier.equalsIgnoreCase(currentLetter)) {
                String tileSpecialisedAttribute = breakdownParser.next();
                currentLockedDoorColour = tileSpecialisedAttribute;
            } else {
                z++;
            }
        }

        return currentLockedDoorColour;
    }

    private static Direction IceAttributeParse(String currentLetter, Scanner in) {
        Direction currentDirection = null;

        String specialisedTileAttribute = in.next();

        Scanner idParser = new Scanner(specialisedTileAttribute);
        idParser.useDelimiter(",");

        int z = 0;

        while ((idParser.hasNext()) && z < specialisedTileAttribute.length()) {
            String tileSpecialisedValue = idParser.next();
            Scanner breakdownParser = new Scanner(tileSpecialisedValue);
            breakdownParser.useDelimiter("-");


            String tileIdentifier = breakdownParser.next();

            if (tileIdentifier.equalsIgnoreCase(currentLetter)) {
                String tileSpecialisedAttribute = breakdownParser.next();
                currentDirection = translateDirection(tileSpecialisedAttribute);
            } else {
                z++;
            }
        }

        return currentDirection;
    }

    private static Direction translateDirection(String currentDirectionFromFile) {
        Scanner directionParser = new Scanner(currentDirectionFromFile);

        int z = 0;
        while ((directionParser.hasNext()) && z < currentDirectionFromFile.length()) {

            String tileSpecialisedValue = directionParser.next();
            Scanner breakdownParser = new Scanner(tileSpecialisedValue);
            breakdownParser.useDelimiter(":");

            while (breakdownParser.hasNext()) {
                String currentDirection = breakdownParser.next();
                System.out.println(currentDirection);
            }
            z++;
        }

        return null;
    }
}
