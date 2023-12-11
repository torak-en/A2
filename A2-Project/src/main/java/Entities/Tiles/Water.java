package Entities.Tiles;

import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

/**
 * The class representing a water tile in the game.
 */

public class Water extends Tile{
	private boolean replace = false;

	/**
	 * Creates a water tile with the coordinates.
	 * @param x          The x-coordinate of the path.
	 * @param y          The y-coordinate of the path.
	 */


	public Water(int x, int y) {
		super(x, y, EntityType.WATER);
	}

	/**
	 * Logic for the water tile during each tick
	 * If the player is on the water tile, it sets the player's alive status to false
	 * If the water tile is marked for replacement, it changes to a dirt tile in the level.
	 * @param level The current level in-game.
	 * @return The updated game level after the water tick.
	 */

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (getX() == p.getX() && getY() == p.getY()){
			p.setAlive(false);
		}
		if (replace){
			level.getTileLayer()[getX()][getY()] = new Dirt(getX(),getY());
		}
		return level;
	}
}