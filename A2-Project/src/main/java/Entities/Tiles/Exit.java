package Entities.Tiles;

import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

public class Exit extends Tile{

	public Exit(int x, int y) {
		super(x, y, EntityType.EXIT);
	}
	/**
	 * Logic for the exit tile during each tick
	 * If the player is on the exit tile, it sets the player's win status to true
	 *
	 * @param level The current level in-game
	 * @return The updated game level after each tick
	 */
	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (p.getX() == getX() && p.getY() == getY()){
			p.setWon(true);
		}
		return level;
	}
}
