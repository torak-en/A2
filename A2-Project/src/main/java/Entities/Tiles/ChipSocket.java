package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class ChipSocket extends Tile{
	private int numOfKeysRequired;
	private boolean locked = true;

	public ChipSocket(int x, int y, int numOfKeysRequired) {
		super(x, y, EntityType.CHIP_SOCKET);
		this.numOfKeysRequired = numOfKeysRequired;
	}

	@Override
	public Level tick(Level level) {
		if (!locked) {
			level.getTileLayer()[getX()][getY()] = new Path(getX(),getY());
		}
		return level;
	}

	public int getNumOfKeysRequired () {
		return  numOfKeysRequired;
	}
}
