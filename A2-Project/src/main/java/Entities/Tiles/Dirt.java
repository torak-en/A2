package Entities.Tiles;

import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

/**
 * The class representing a dirt tile in the game.
 */

public class Dirt extends Tile{

	/**
	 * Creates a dirt tile with the coordinates it will be placed at.
	 * @param x  The x-coordinate of the button.
	 * @param y  The y-coordinate of the button.
	 */

	public Dirt(int x, int y) {
		super(x, y, EntityType.DIRT);
	}

	/**
	 * Logic for the dirt tile during each tick.
	 * @param level The current level in-game.
	 * @return The updated game level after a tick.
	 */

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (getX() == p.getX() && getY() == p.getY()){
			level.getTileLayer()[getX()][getY()] = new Path(getX(), getY());
		}
		return level;
	}
}
