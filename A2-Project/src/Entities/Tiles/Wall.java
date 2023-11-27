package Entities.Tiles;

import Enum.EntityType;
public class Wall extends Tile{

	public Wall(int x, int y) {
		super(x, y, EntityType.WALL);
	}
}
