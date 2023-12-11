package Entities.Actors;

import Enum.Direction;
import Enum.EntityType;
import Level.Level;


/**
 * Represents a bug entity in the game.
 * Extends the Actor class.
 */

public class Bug extends Actor {
	private Direction initialDirection;
	private Direction edgeDirection;
	private final int TICKS_BETWEEN_MOVE = 40;
	private int ticksTillMove = 0;


	/**
     * Constructor for a Bug object.
     * @param x The x-coordinate of the bug.
     * @param y The y-coordinate of the bug.
     * @param edgeDirection The initial edge direction of the bug.
     * @param initialDirection The initial direction of the bug.
     */

	public Bug(int x, int y, Direction edgeDirection, Direction initialDirection) {
		super(x, y, EntityType.BUG);
		this.edgeDirection = edgeDirection;
		this.initialDirection = initialDirection;
		ticksTillMove = TICKS_BETWEEN_MOVE;
		setPreviousDirection(initialDirection);
	}


	/**
     * Overrides the tick method inherited from Actor class.
     * This method defines the actions performed by the bug during each game tick.
     * @param level The level where the bug exists.
     * @return The modified level after the tick.
     */

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (getX() == p.getX() && getY() == p.getY()){
			p.setAlive(false);
		}

		if (ticksTillMove == 0 && getPendingDirection() == null) {
			ticksTillMove = TICKS_BETWEEN_MOVE;
			if (getPreviousDirection() == Direction.NONE) {
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
     * This method calculates the movement direction of the bug.
     * @param level The level where the bug exists.
     * @return The calculated movement direction of the bug (or NONE if no movement).
     */
	
	@Override
	public Direction calculateMove(Level level) {
		boolean clearSpace = false;
		Direction d = Direction.NONE;
		if (edgeDirection == Direction.LEFT) {
			d = getAntiClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		} else if (edgeDirection == Direction.RIGHT) {
			d = getClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		}
		if (clearSpace){
			return d;
		}
		if (checkLocation(getPreviousDirection(), level)) {
			return getPreviousDirection();
		}
		if (edgeDirection == Direction.LEFT) {
			d = getClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		} else if (edgeDirection == Direction.RIGHT) {
			d = getAntiClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		}
		if (clearSpace){
			return d;
		}
		if (checkLocation(getInverse(getPreviousDirection()), level)) {
			return getInverse(getPreviousDirection());
		}
		return Direction.NONE;
	}
}
