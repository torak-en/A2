package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

/**
 * The class representing a trap tile in the game.
 */

public class Wall extends Tile{

	/**
	 * Creates a wall tile with the coordinates.
	 * @param x          The x-coordinate of the path.
	 * @param y          The y-coordinate of the path.
	 */

	public Wall(int x, int y) {
		super(x, y, EntityType.WALL);
	}

	/**
	 * Logic for the wall tile during each tick.
	 * @param level The current level in-game.
	 * @return The updated game level after a tick.
	 */

	@Override
	public Level tick(Level level) {
		return level;
	}
}
