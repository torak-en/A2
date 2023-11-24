package Entities.Actors;

import Enum.Direction;

public class PinkBall extends Actor{

	private Direction initialDirection;

	public PinkBall (int x, int y, Direction initialDirection){
		super(x,y);
		this.initialDirection = initialDirection;
	}

	protected void tick() {

	}
}
