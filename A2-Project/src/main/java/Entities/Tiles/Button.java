package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.EntityType;
import Level.Level;

import java.util.ArrayList;
import java.util.List;

public class Button extends Tile{
	private List<Trap> linkedTraps = new ArrayList<>();
	private final int id;

	public Button(int x, int y, int id) {
		super(x, y, EntityType.BUTTON);
		this.id = id;
	}

	@Override
	public Level tick(Level level) {
		if (linkedTraps.isEmpty()){
			for (Tile[] tileLayer: level.getTileLayer()) {
				for (Tile tile:tileLayer) {
					if (tile.getType() == EntityType.TRAP){
						Trap trap = (Trap) tile;
						if (trap.getId() == id){
							linkedTraps.add(trap);
						}
					}
				}
			}
		}

		for (Actor a : level.getActorList()) {
			if (a.getX() == getX() && a.getY() == getY()){
				for (Trap t: linkedTraps) {
					t.setActive(true);
				}
			} else {
				for (Trap t: linkedTraps) {
					t.setActive(false);
				}
			}
		}




		return level;
	}

}
