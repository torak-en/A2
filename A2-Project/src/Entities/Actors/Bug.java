package Entities.Actors;

import Enum.Direction;

public class Bug extends Actor{

	private Direction edgeDirection;

	public Bug(int x, int y, Direction edgeDirection) {
		super(x, y);
		this.edgeDirection = edgeDirection;
	}

	protected void tick() {

	}
}
