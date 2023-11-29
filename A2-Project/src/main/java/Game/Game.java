package Game;

import Entities.Actors.Actor;
import Entities.Items.Item;
import Entities.Tiles.Tile;
import Level.Level;
import Level.LevelHandler;
import Render.Render;

public class Game {
	private Level level;

	public static void main(String[] args) {
		System.out.println("Hello world!");
		LevelHandler nlh = new LevelHandler();
		Level nl = nlh.createLevel(2);
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
		level = new LevelHandler().createLevel(2);
	}

	public void tick(){
		for (Actor a : level.getActorList()) {
			level = a.tick(level);
		}

		for (Item i : level.getItemList()) {
			level = i.tick(level);
		}

		for (Tile[] tileLayer : level.getTileLayer()) {
			for (Tile tile : tileLayer) {
				level = tile.tick(level);
			}
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}