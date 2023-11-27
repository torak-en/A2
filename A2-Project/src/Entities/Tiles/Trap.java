package Entities.Tiles;

import Enum.EntityType;
public class Trap extends Tile{
	private boolean active;
	private int id;

	public Trap(int x, int y, int id) {
		super(x, y, EntityType.TRAP);
		this.id = id;
		this.active = false;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
