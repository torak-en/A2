package Entities.Actors;

import Enum.Direction;
import Enum.EntityType;
import Level.Level;


/**
 * PinkBall is an Actor that moves in a straight line until it hits a wall, then it turns around and moves in the opposite direction.
 * If it hits the player, the player dies.
 * Extends Actor.
 */

public class PinkBall extends Actor{
	private final Direction initialDirection;
	private final int TICKS_BETWEEN_MOVE = 25;
	private int ticksTillMove = 0;


	 /**
 	 * Constructor for a PinkBall object.
 	 * @param x The x-coordinate of the pink ball.
 	 * @param y The y-coordinate of the pink ball.
	 * @param initialDirection The initial direction of the pink ball.
	 */

	public PinkBall (int x, int y, Direction initialDirection){
		super(x,y, EntityType.PINK_BALL);
		this.initialDirection = initialDirection;
		ticksTillMove = TICKS_BETWEEN_MOVE;
		setPreviousDirection(initialDirection);
	}


	/**
     * Overrides the tick method inherited from Actor class.
     * This method defines the actions performed by the pink ball during each game tick.
     * @param level The level where the pink ball exists.
     * @return The modified level after the tick.
     */

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (getX() == p.getX() && getY() == p.getY()){
			p.setAlive(false);
		}
		if (ticksTillMove == 0 && getPendingDirection() == null){
			ticksTillMove = TICKS_BETWEEN_MOVE;
			if (getPreviousDirection() == Direction.NONE){
				setPreviousDirection(initialDirection);
			}
			setPendingDirection(calculateMove(level));
			applyMove();
		}
		ticksTillMove--;
		return level;
	}


	/**
     * Overrides the calculateMove method inherited from Actor class.
     * This method calculates the movement direction of the pink ball.
     * @param level The level where the pink ball exists.
     * @return The calculated movement direction of the pink ball (or NONE if no movement).
     */
	
	@Override
	public Direction calculateMove(Level level){
		Direction pd = getPreviousDirection();
		if (checkLocation(pd, level)){
			return pd;
		} else if (checkLocation(getInverse(pd), level)){
			return getInverse(pd);
		} else {
			return Direction.NONE;
		}
	}

}
