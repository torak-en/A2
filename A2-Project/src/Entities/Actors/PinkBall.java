package Entities.Actors;

import Enum.Direction;
import Enum.EntityType;

public class PinkBall extends Actor{

	private Direction initialDirection;

	public PinkBall (int x, int y, Direction initialDirection){
		super(x,y, EntityType.PINK_BALL);
		this.initialDirection = initialDirection;
	}

	protected void tick() {

	}
}
