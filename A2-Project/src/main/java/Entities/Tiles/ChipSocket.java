package Entities.Tiles;

import Enum.EntityType;
public class ChipSocket extends Tile{
	private int numOfKeysRequired;

	public ChipSocket(int x, int y, int numOfKeysRequired) {
		super(x, y, EntityType.CHIP_SOCKET);
		this.numOfKeysRequired = numOfKeysRequired;
	}

	public int getNumOfKeysRequired () {
		return  numOfKeysRequired;
	}
}
