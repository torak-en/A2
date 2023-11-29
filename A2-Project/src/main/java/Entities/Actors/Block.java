package Entities.Actors;

import Enum.EntityType;
import Enum.Direction;
import Level.Level;

public class Block extends Actor{

	public Block(int x, int y) {
		super(x, y, EntityType.BLOCK);
	}


	protected void tick() {

	}

	private void replaceWater(){

	}

	@Override
	public Level tick(Level level) {
		return level;
	}

	@Override
	public Direction calculateMove(Level level) {
		return null;
	}
}
