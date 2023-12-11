package Entities.Tiles;

import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

public class Dirt extends Tile{

	public Dirt(int x, int y) {
		super(x, y, EntityType.DIRT);
	}

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (getX() == p.getX() && getY() == p.getY()){
			level.getTileLayer()[getX()][getY()] = new Path(getX(), getY());
		}
		return level;
	}
}
