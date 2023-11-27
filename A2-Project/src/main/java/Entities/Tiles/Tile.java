package Entities.Tiles;

import Entities.Entity;
import Enum.EntityType;

public abstract class Tile extends Entity {

	public Tile(int x, int y, EntityType type) {
		super(x,y,type);
	}

}
