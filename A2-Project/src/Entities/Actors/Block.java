package Entities.Actors;

import Enum.EntityType;
public class Block extends Actor{

	public Block(int x, int y) {
		super(x, y, EntityType.BLOCK);
	}


	protected void tick() {

	}

	private void replaceWater(){

	}
}
