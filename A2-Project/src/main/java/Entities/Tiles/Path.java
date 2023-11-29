package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class Path extends Tile{
	public Path(int x, int y) {
		super(x, y, EntityType.PATH);
	}

	@Override
	public Level tick(Level level) {
		return level;
	}
}
