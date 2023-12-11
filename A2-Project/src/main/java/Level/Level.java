package Level;

import Entities.Actors.Actor;
import Entities.Actors.Player;
import Entities.Items.Item;
import Entities.Tiles.Tile;

import java.util.List;

/**
 * Represents a level in the game.
 */

public class Level {

	private int currentTime;
	private String levelName;
	private Tile[][] tileLayer;
	private List<Item> itemList;
	private List<Actor> actorList;
	private Player player;
	private int currentTicks = 60;
	private int timeTaken = 0;

	/**
	 * Constructor for a level object.
	 * @param levelTime The time of a level.
	 * @param levelName The name of a level.
	 * @param tileLayer The tilelayer of a level.
	 * @param itemList The itemlayer of a level.
	 * @param actorList The actorlayer of a level.
	 * @param player The player of a level.
	 */

	public Level(int levelTime, String levelName, Tile[][] tileLayer,
				 List<Item> itemList, List<Actor> actorList, Player player){
		this.currentTime = levelTime;
		this.levelName = levelName;
		this.tileLayer = tileLayer;
		this.itemList = itemList;
		this.actorList = actorList;
		this.player = player;
	}

	/**
	 * Logic for a level during every tick in-game.
	 */

	public void tick(){
		currentTicks--;
		if (currentTicks == 0){
			currentTicks = 60;
			currentTime--;
			timeTaken++;
		}
		if (currentTime == 0){
			player.setAlive(false);
		}
	}

	/**
	 * Gets the current time of a level.
	 * @return The current time of a level.
	 */

	public int getCurrentTime() {
		return currentTime;
	}

	/**
	 * Sets the current time of a level.
	 * @param currentTime The current time of a level.
	 */

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * Gets the level name.
	 * @return The level's name.
	 */

	public String getLevelName() {
		return levelName;
	}

	/**
	 * Sets the level name.
	 * @param levelName The level's name.
	 */

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * Gets the tile layer of a level object.
	 * @return The tile layer of a level object.
	 */

	public Tile[][] getTileLayer() {
		return tileLayer;
	}

	/**
	 * Sets the tile layer of a level object.
	 * @param tileLayer The tile layer of a level object.
	 */

	public void setTileLayer(Tile[][] tileLayer) {
		this.tileLayer = tileLayer;
	}

	/**
	 * Gets the item list of a level object.
	 * @return The item list of a level object.
	 */

	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * Sets the item list of a level object.
	 * @param itemList The item list of a level object.
	 */

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * Gets the actor list of a level object.
	 * @return The actor list of a level object.
	 */

	public List<Actor> getActorList() {
		return actorList;
	}

	/**
	 * Sets the actor list of a level object.
	 * @param actorList The actor list of a level object.
	 */

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	/**
	 * Gets the player of a level object.
	 * @return The player of a level object.
	 */

	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the time taken to complete a level.
	 * @return The time taken to complete a level.
	 */

	public int getTimeTaken() {
		return timeTaken;
	}
}
