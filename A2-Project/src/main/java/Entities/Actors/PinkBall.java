package Entities.Actors;

import Enum.Direction;
import Enum.EntityType;
import Level.Level;

public class PinkBall extends Actor{
	private Direction initialDirection;
	private final int TICKS_BETWEEN_MOVE = 20;
	private int ticksTillMove = 0;

	public PinkBall (int x, int y, Direction initialDirection){
		super(x,y, EntityType.PINK_BALL);
		this.initialDirection = initialDirection;
		ticksTillMove = TICKS_BETWEEN_MOVE;
		setPreviousDirection(initialDirection);
	}

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (getX() == p.getX() && getY() == p.getY()){
			p.setAlive(false);
		}
		if (ticksTillMove == 0 && getPendingDirection() == null){
			ticksTillMove = TICKS_BETWEEN_MOVE;
			setPendingDirection(calculateMove(level));
			applyMove();
		}
		ticksTillMove--;
		return level;
	}

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
