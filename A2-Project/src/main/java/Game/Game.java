package Game;

import Entities.Actors.Actor;
import Entities.Items.Item;
import Entities.Tiles.Tile;
import Highscore.Highscore;
import Highscore.HighscoreHandler;
import Level.Level;
import Level.LevelHandler;
import Profile.Profile;
import Profile.ProfileHandler;
import Render.Render;

public class Game {
	private Level level;

 	public static void main(String[] args) {
		HighscoreHandler highscoreHandler = new HighscoreHandler();
		System.out.println(highscoreHandler.getHighscores(2));
		highscoreHandler.newHighscore(2, new Highscore("jeff", 99, 10, 10, 10));
		System.out.println(highscoreHandler.getHighscores(2));
	}

	public Game(){
		level = new LevelHandler().createLevel(1);
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