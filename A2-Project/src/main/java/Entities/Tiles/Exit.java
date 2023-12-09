package Entities.Tiles;

import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

public class Exit extends Tile{

	public Exit(int x, int y) {
		super(x, y, EntityType.EXIT);
	}

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (p.getX() == getX() && p.getY() == getY()){
			p.setWon(true);
		}
		return level;
	}
}
