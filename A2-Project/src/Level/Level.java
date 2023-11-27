package Level;

import Entities.Actors.Actor;
import Entities.Items.Item;
import Entities.Tiles.Tile;

import java.util.List;

public class Level {

	private int levelTime;
	private int currentTime;
	private String levelName;
	private Tile[][] tileLayer;
	private List<Item> itemList;
	private List<Actor> actorList;

	public Level(int levelTime, String levelName, Tile[][] tileLayer, List<Item> itemList, List<Actor> actorList){
		this.levelTime = levelTime;
		this.levelName = levelName;
		this.tileLayer = tileLayer;
		this.itemList = itemList;
		this.actorList = actorList;
	}
	public void tick(){

	}

	//region Getters and Setters
	public int getLevelTime() {
		return levelTime;
	}

	public void setLevelTime(int levelTime) {
		this.levelTime = levelTime;
	}

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

	//endregion
}
