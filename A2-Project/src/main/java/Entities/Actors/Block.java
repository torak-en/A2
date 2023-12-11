package Entities.Actors;

import Entities.Tiles.Path;
import Enum.EntityType;
import Enum.Direction;
import Level.Level;

/**
 * Represents a block entity in the game.
 * Extends the Actor class.
 */

public class Block extends Actor{

	private final int TICKS_BETWEEN_MOVE = 10;
	private int ticksTillMove = 0;

	/**
	 * Constructor for a block object.
	 * @param x The x-coordinate of the block.
	 * @param y The y-coordinate of the block.
	 */

	public Block(int x, int y){
		super(x, y, EntityType.BLOCK);
	}


	/**
	 * Overrides the tick method inherited from Actor class.
	 * This method performs actions during each game tick.
	 * @param level The level where the block exists.
	 * @return The modified level after the tick.
	 */

	@Override
	public Level tick(Level level){
		if (level.getTileLayer()[getX()][getY()].getType() == EntityType.WATER){
			System.out.println("Block on water");
			level.getTileLayer()[getX()][getY()] = new Path(getX(), getY());
			level.getActorList().remove(this);
		}
		if (ticksTillMove == 0){
			if (getPendingDirection() != null){
				applyMove();
			}
			ticksTillMove = TICKS_BETWEEN_MOVE;
		}
		ticksTillMove--;


		return level;
	}

	/**
	 * Overrides the calculateMove method inherited from Actor class.
	 * This method calculates the movement direction of the block.
	 * @param level The level where the block exists.
	 * @return The calculated movement direction.
	 */

	@Override
	public Direction calculateMove(Level level){
		return null;
	}
}
