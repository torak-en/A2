package Entities.Tiles;

import Level.Level;
import Enum.EntityType;

/**
 * The class representing an empty tile in the game.
 */

public class Empty extends Tile{

	/**
	 * Creates an empty tile with the coordinates it will be placed at.
	 * @param x  The x-coordinate of the empty tile.
	 * @param y  The y-coordinate of the empty tile.
	 */

	public Empty(int x, int y) {
		super(x, y, EntityType.EMPTY);
	}

	/**
	 * Logic for the empty tile during each tick.
	 * @param level The current level in-game.
	 * @return The updated game level after a tick.
	 */

	@Override
	public Level tick(Level level) {
		return level;
	}
}
