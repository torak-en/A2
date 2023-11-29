package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.EntityType;
import Level.Level;

public class Button extends Tile{
	private Trap linkedTrap = null;
	private final int id;

	public Button(int x, int y, int id) {
		super(x, y, EntityType.BUTTON);
		this.id = id;
	}

	@Override
	public Level tick(Level level) {
		if (linkedTrap == null){
			for (Tile[] tileLayer: level.getTileLayer()) {
				for (Tile tile:tileLayer) {
					if (tile.getType() == EntityType.TRAP){
						Trap trap = (Trap) tile;
						if (trap.getId() == id){
							linkedTrap = trap;
						}
					}
				}
			}
		}

		for (Actor a: level.getActorList()) {
			linkedTrap.setActive(a.getX() == getX() && a.getY() == getY());
		}

		return level;
	}

}
