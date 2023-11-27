package Game;

import Entities.Tiles.Tile;
import Level.Level;
import Level.LevelHandler;
import Render.Render;

public class Game {
	private boolean running = true;
	private String state = "Start Menu";
	private Level level;

	public static void main(String[] args) {
		System.out.println("Hello world!");
		LevelHandler nlh = new LevelHandler();
		Level nl = nlh.createLevel(1);
		System.out.println(nl.getTileLayer().length * nl.getTileLayer()[0].length);
		System.out.println(nl.getItemList().toString());
		System.out.println(nl.getActorList().toString());
		Tile tile =  nl.getTileLayer()[0][0];
		System.out.println(tile);
		System.out.println(tile.getType());
		System.out.println(tile.getType().getImage());

		Game g = new Game();

	}

	public Game(){
		level = new LevelHandler().createLevel(1);
	}

	public void tick(){

	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}