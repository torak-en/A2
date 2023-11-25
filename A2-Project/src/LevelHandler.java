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
        readDataFile(levelFile); //Produces a Complete LevelLayout Object.
    }

    /***
     * Private method that initiates call from Constructor.
     * @param fileLevel
     * @return A completed LevelLayout object with all it's attributes.
     */
    private static LevelLayout readDataFile(File fileLevel) {
        Scanner in;

        LevelLayout newLevelLayout = null;

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
    private static LevelLayout readLineByLine(Scanner in) {
        String levelName = in.next();
        levelTime = in.nextInt();
        int levelWidth = in.nextInt();
        int levelHeight = in.nextInt();
        String levelShape = in.next();

        Level newLevel = new Level();

        LevelLayout newLevelMock = null;

        return newLevelMock;
    }
}
