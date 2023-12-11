package Entities.Tiles;

import Entities.Entity;
import Enum.EntityType;

/**
 * The abstract class representing a tile entity in the game.
 */

public abstract class Tile extends Entity {

	/**
	 * Constructor for a Tile object.
	 * @param x The x-coordinate of the actor.
	 * @param y The y-coordinate of the actor.
	 * @param type The type of entity.
	 */

	public Tile(int x, int y, EntityType type) {
		super(x,y,type);
	}

}
