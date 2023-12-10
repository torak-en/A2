package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class ChipSocket extends Tile{
	private int numOfGoldRequired;
	private boolean locked = true;

	public ChipSocket(int x, int y, int numOfGoldRequired) {
		super(x, y, EntityType.CHIP_SOCKET);
		this.numOfGoldRequired = numOfGoldRequired;
	}

	@Override
	public Level tick(Level level) {
		if (!locked) {
			level.getTileLayer()[getX()][getY()] = new Path(getX(),getY());
		}
		return level;
	}

	public int getNumOfGoldRequired () {
		return  numOfGoldRequired;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
