package Highscore;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Manages highscores for different levels.
 */
public class HighscoreHandler {

	/**
     * Retrieves the highscores for a specific level.
     *
     * @param levelNum The number of the level for which highscores are retrieved.
     * @return A list of highscores for the specified level.
     */
	public List<Highscore> getHighscores(int levelNum){
		List<Highscore> highscores = new ArrayList<>();
		File levelHighscores = new File("Highscores/" + levelNum + ".txt");
		Scanner sc = null;

		if (levelHighscores.exists()) {
			try {
				sc = new Scanner(levelHighscores);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}


			int count = 0;
			while (sc.hasNextLine() && count != 10) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				String name = data[0];
				int timeTaken = Integer.parseInt(data[1]);
				int day = Integer.parseInt(data[2]);
				int month = Integer.parseInt(data[3]);
				int year = Integer.parseInt(data[4]);
				highscores.add(new Highscore(name, timeTaken, day, month, year));
				count++;
			}
			sc.close();
		}
		return highscores;
	}

	 /**
     * Adds a new highscore to the highscores list for a specific level.
     *
     * @param levelNum  The number of the level for which the highscore is added.
     * @param highscore The highscore entry to be added.
     */
	public void newHighscore(int levelNum, Highscore highscore){
		File levelHighscores = new File("Highscores/"+ levelNum +".txt");

		String name = highscore.getName();
		int timeTaken = highscore.getTimeTaken();
		int day = highscore.getDay().get();
		int month = highscore.getMonth();
		int year = highscore.getYear();

		if (levelHighscores.exists()) {
			Scanner sc = null;
			try {
				sc = new Scanner(levelHighscores);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}

			StringBuilder newFileData = new StringBuilder();

			boolean notWritten = true;
			int count = 0;
			while (sc.hasNextLine() && count != 10) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				if (timeTaken <= Integer.parseInt(data[1]) && notWritten) {
					newFileData.append(name).append(",").append(timeTaken).append(",").append(day).append(",").append(month).append(",").append(year).append("\n");
					notWritten = false;
					count++;
				}
				newFileData.append(line).append("\n");
				count++;
			}
			sc.close();

			FileWriter writer = null;
			try {
				writer = new FileWriter(levelHighscores);
				writer.write(String.valueOf(newFileData));
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			StringBuilder newFileData = new StringBuilder();
			newFileData.append(name).append(",").append(timeTaken).append(",").append(day).append(",").append(month).append(",").append(year).append("\n");
			FileWriter writer = null;
			try {
				writer = new FileWriter(levelHighscores);
				writer.write(String.valueOf(newFileData));
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
