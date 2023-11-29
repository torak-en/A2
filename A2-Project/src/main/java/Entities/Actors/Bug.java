package Entities.Actors;

import Enum.Direction;
import Enum.EntityType;
import Enum.Direction;
import Level.Level;

public class Bug extends Actor{
	private Direction initialDirection;
	private Direction edgeDirection;
	private final int TICKS_BETWEEN_MOVE = 30;
	private int ticksTillMove = 0;

	public Bug(int x, int y, Direction edgeDirection, Direction initialDirection) {
		super(x, y, EntityType.BUG);
		this.edgeDirection = edgeDirection;
		this.initialDirection = initialDirection;
		ticksTillMove = TICKS_BETWEEN_MOVE;
		setPreviousDirection(initialDirection);
	}

	@Override
	public Level tick(Level level) {
		if (ticksTillMove == 0 && getPendingDirection() == null){
			ticksTillMove = TICKS_BETWEEN_MOVE;
			setPendingDirection(calculateMove(level));
			applyMove();
		}
		ticksTillMove--;
		return level;
	}

	@Override
	public Direction calculateMove(Level level) {
		boolean clearSpace = false;
		Direction d = Direction.NONE;
		if (edgeDirection == Direction.LEFT){
			d = getAntiClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		} else if (edgeDirection == Direction.RIGHT){
			d = getClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		}
		if (clearSpace){
			return d;
		}
		if (checkLocation(getPreviousDirection(), level)){
			return getPreviousDirection();
		}
		if (edgeDirection == Direction.LEFT){
			d = getClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		} else if (edgeDirection == Direction.RIGHT){
			d = getAntiClockwise(getPreviousDirection());
			clearSpace = checkLocation(d, level);
		}
		if (clearSpace){
			return d;
		}
		if (checkLocation(getInverse(getPreviousDirection()), level)){
			return getPreviousDirection();
		}
		return Direction.NONE;
	}

}
