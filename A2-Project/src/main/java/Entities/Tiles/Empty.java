package Entities.Tiles;

import Level.Level;
import Enum.EntityType;

public class Empty extends Tile{
	public Empty(int x, int y) {
		super(x, y, EntityType.EMPTY);
	}

	@Override
	public Level tick(Level level) {
		return level;
	}
}
