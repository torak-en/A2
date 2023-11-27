package Entities.Actors;

import Enum.Direction;
import Enum.EntityType;

public class Bug extends Actor{

	private Direction edgeDirection;

	public Bug(int x, int y, Direction edgeDirection) {
		super(x, y, EntityType.BUG);
		this.edgeDirection = edgeDirection;
	}

	protected void tick() {

	}
}
