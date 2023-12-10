package Entities.Actors;

import Enum.EntityType;
import Enum.Direction;
import Level.Level;

/**
 * Represents a block entity in the game.
 * Extends the Actor class.
 */

public class Block extends Actor{

	/**
	 * Constructor for a block object.
	 * @param x The x-coordinate of the block.
	 * @param y The y-coordinate of the block.
	 */

	public Block(int x, int y) {
		super(x, y, EntityType.BLOCK);
	}


	/**
     * Method to perform actions during each game tick for the block.
     */

	protected void tick() {

	}


	/**
	 * Method to replace water with floor tiles.
	 */

	private void replaceWater(){

	}


	/**
     * Overrides the tick method inherited from Actor class.
     * This method performs actions during each game tick.
     * @param level The level where the block exists.
     * @return The modified level after the tick.
     */

	@Override
	public Level tick(Level level) {
		return level;
	}


	/**
	 * Overrides the calculateMove method inherited from Actor class.
	 * This method calculates the movement direction of the block.
	 * @param level The level where the block exists.
	 * @return The calculated movement direction.
	 */
	@Override
	public Direction calculateMove(Level level) {
		return null;
	}
}
