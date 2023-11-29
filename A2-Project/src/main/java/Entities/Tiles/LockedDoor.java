package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class LockedDoor extends Tile{
	private String doorColour;
	private boolean locked = true;

	public LockedDoor(int x, int y, String doorColour) {
		super(x, y, EntityType.LOCKED_DOOR);
		this.doorColour = doorColour;
	}

	@Override
	public Level tick(Level level) {
		if (!locked) {
			level.getTileLayer()[getX()][getY()] = new Path(getX(),getY());
		}
		return level;
	}

	public String getDoorColour(){
		return doorColour;
	}
}
