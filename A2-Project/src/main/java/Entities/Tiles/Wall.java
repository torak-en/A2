package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class Wall extends Tile{

	public Wall(int x, int y) {
		super(x, y, EntityType.WALL);
	}

	@Override
	public Level tick(Level level) {
		return level;
	}
}
