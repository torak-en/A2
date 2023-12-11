package Level;

import Entities.Actors.Actor;
import Entities.Actors.Player;
import Entities.Items.Item;
import Entities.Tiles.Tile;

import java.util.List;

public class Level {

	private int currentTime;
	private String levelName;
	private Tile[][] tileLayer;
	private List<Item> itemList;
	private List<Actor> actorList;
	private Player player;
	private int currentTicks = 60;
	private int timeTaken = 0;

	public Level(int levelTime, String levelName, Tile[][] tileLayer, List<Item> itemList, List<Actor> actorList, Player player){
		this.currentTime = levelTime;
		this.levelName = levelName;
		this.tileLayer = tileLayer;
		this.itemList = itemList;
		this.actorList = actorList;
		this.player = player;
	}

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

	//region Getters and Setters
	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Tile[][] getTileLayer() {
		return tileLayer;
	}

	public void setTileLayer(Tile[][] tileLayer) {
		this.tileLayer = tileLayer;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public Player getPlayer() {
		return player;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	//endregion
}
