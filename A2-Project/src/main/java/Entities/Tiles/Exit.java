package Entities.Tiles;

import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

/**
 * The class representing an exit tile in the game.
 */

public class Exit extends Tile{

	/**
	 * Creates an exit tile with the coordinates it will be placed at.
	 * @param x  The x-coordinate of the empty tile.
	 * @param y  The y-coordinate of the empty tile.
	 */

	public Exit(int x, int y) {
		super(x, y, EntityType.EXIT);
	}

	/**
	 * Logic for the exit tile during each tick
	 * If the player is on the exit tile, it sets the player's win status to true.
	 *
	 * @param level The current level in-game.
	 * @return The updated game level after each tick.
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
