package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.Direction;
import Enum.EntityType;

public class Ice extends Tile{
	private Direction entranceDirection1;
	private Direction entranceDirection2;

	public Ice(int x, int y, Direction entranceDirection1, Direction entranceDirection2) {
		super(x, y, EntityType.ICE);
		this.entranceDirection1 = entranceDirection1;
		this.entranceDirection2 = entranceDirection2;
	}

	private Boolean checkMoveOntoIce(Direction direction){
		return direction == entranceDirection1 || direction == entranceDirection2;
	}

	private Direction slide(Actor a){
		if (a.getPreviousDirection() == entranceDirection1){
			return entranceDirection2;
		} else {
			return entranceDirection1;
		}
	}
}
