package Game;

import Entities.Actors.Actor;
import Entities.Items.Item;
import Entities.Tiles.Tile;
import Highscore.Highscore;
import Highscore.HighscoreHandler;
import Level.Level;
import Level.LevelHandler;
import Profile.Profile;

import java.util.List;

/**
 * Represents the main game control and logic class.
 */

public class Game {
	private Level level;
	private int levelNum;
	private Profile currentProfile;


	/**
     * Main method used for testing highscore functionality.
     * @param args Command-line arguments (unused).
     */

 	public static void main(String[] args) {
		HighscoreHandler highscoreHandler = new HighscoreHandler();
		System.out.println(highscoreHandler.getHighscores(2));
		highscoreHandler.newHighscore(2, new Highscore("jeff", 99, 10, 10, 10));
		System.out.println(highscoreHandler.getHighscores(2));
	}

	/**
     * Default constructor for the Game class.
     */

	public Game(){
		 // Initialization logic
	}


	/**
     * Method to update the game state in each tick.
     * Moves all actors, updates items and tiles on the level.
     */

	public void tick(){
		level.tick();

		List<Actor> actors = level.getActorList();
		for (int i = 0; i < actors.size(); i++) {
			level = actors.get(i).tick(level);
		}

		List<Item> items = level.getItemList();
		for (int i = 0; i < items.size(); i++) {
			level = items.get(i).tick(level);
		}

		for (Tile[] tileLayer : level.getTileLayer()) {
			for (Tile tile : tileLayer) {
				level = tile.tick(level);
			}
		}
	}


	/**
     * Updates the current level to a new level based on level number.
     * @param levelNum The number of the level to be updated.
     */
	
	public void updateLevel(int levelNum){
		 this.levelNum = levelNum;
		 LevelHandler handler = new LevelHandler();
		 level = handler.createLevel(levelNum);
	}

	/**
	 * Gets the current level.
	 * @return The current level.
	 */

	public Level getLevel() {
		return level;
	}

	/**
	 * Sets the level to whatever level object is passed.
	 * @param level The level that it will be set to.
	 */

	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * Gets the current level number.
	 * @return The level number.
	 */

	public int getLevelNum() {
		return levelNum;
	}

	/**
	 * Sets the current level number to whatever level number is passed in.
	 * @param levelNum The new level number.
	 */

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	/**
	 * Gets the current profile.
	 * @return The current profile.
	 */

	public Profile getCurrentProfile() {
		return currentProfile;
	}

	/**
	 * Sets the current profile to whatever profile object is passed in.
	 * @param currentProfile The current profile.
	 */

	public void setCurrentProfile(Profile currentProfile) {
		this.currentProfile = currentProfile;
	}
}