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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	public Profile getCurrentProfile() {
		return currentProfile;
	}

	public void setCurrentProfile(Profile currentProfile) {
		this.currentProfile = currentProfile;
	}
}