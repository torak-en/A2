package Entities.Tiles;

import Entities.Actors.Actor;
import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

public class Trap extends Tile{
	private boolean active = false;
	private final int id;

	public Trap(int x, int y, int id) {
		super(x, y, EntityType.TRAP);
		this.id = id;
		this.active = false;
	}

	@Override
	public Level tick(Level level) {
		return level;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}
}
