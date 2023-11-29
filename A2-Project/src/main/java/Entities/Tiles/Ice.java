package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.Direction;
import Enum.EntityType;
import Level.Level;

public class Ice extends Tile{
	private Direction entranceDirection1;
	private Direction entranceDirection2;

	public Ice(int x, int y, Direction entranceDirection1, Direction entranceDirection2) {
		super(x, y, EntityType.ICE);
		this.entranceDirection1 = entranceDirection1;
		this.entranceDirection2 = entranceDirection2;
	}

	@Override
	public Level tick(Level level) {
		for (Actor a : level.getActorList()) {
			if (a.getX() == getX() && a.getY() == getY()){
				a.setPendingDirection(slide(a));
			}
		}
		return level;
	}

	private boolean checkMoveOntoIce(Direction direction){
		return direction == entranceDirection1 || direction == entranceDirection2;
	}

	private Direction slide(Actor a){
		if (a.getPreviousDirection() == entranceDirection1){
			return getInverse(entranceDirection2);
		} else {
			return getInverse(entranceDirection1);
		}
	}

}
