package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class Exit extends Tile{

	public Exit(int x, int y) {
		super(x, y, EntityType.EXIT);
	}

	@Override
	public Level tick(Level level) {
		return level;
	}
}
