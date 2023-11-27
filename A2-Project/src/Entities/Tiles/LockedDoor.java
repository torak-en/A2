package Entities.Tiles;

import Enum.EntityType;
public class LockedDoor extends Tile{
	private String doorColour;

	public LockedDoor(int x, int y, String doorColour) {
		super(x, y, EntityType.LOCKED_DOOR);
		this.doorColour = doorColour;
	}

	public String getDoorColour(){
		return doorColour;
	}
}
