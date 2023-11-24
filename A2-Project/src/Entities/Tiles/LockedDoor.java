package Entities.Tiles;

public class LockedDoor extends Tile{
	private String doorColour;

	public LockedDoor(int x, int y, String doorColour) {
		super(x, y);
		this.doorColour = doorColour;
	}

	public String getDoorColour(){
		return doorColour;
	}
}
