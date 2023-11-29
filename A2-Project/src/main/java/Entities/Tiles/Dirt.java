package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class Dirt extends Tile{

	public Dirt(int x, int y) {
		super(x, y, EntityType.DIRT);
	}

	@Override
	public Level tick(Level level) {
		return level;
	}
}
