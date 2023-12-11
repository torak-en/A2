package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

/**
 * The class representing a path tile in the game.
 */

public class Path extends Tile{

	/**
	 * Creates a path tile with the coordinates.
	 * @param x          The x-coordinate of the path.
	 * @param y          The y-coordinate of the path.
	 */

	public Path(int x, int y) {
		super(x, y, EntityType.PATH);
	}

	/**
	 * Logic for the path tile during each tick.
	 * @param level The current level in-game.
	 * @return The updated game level after a tick.
	 */

	@Override
	public Level tick(Level level) {
		return level;
	}
}
