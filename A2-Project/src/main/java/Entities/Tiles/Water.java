package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class Water extends Tile{

	private boolean replace = false;
	public Water(int x, int y) {
		super(x, y, EntityType.WATER);
	}

	@Override
	public Level tick(Level level) {
		if (replace){
			level.getTileLayer()[getX()][getY()] = new Dirt(getX(),getY());
		}
		return level;
	}
}