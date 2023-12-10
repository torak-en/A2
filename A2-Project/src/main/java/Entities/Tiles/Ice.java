package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.Direction;
import Enum.EntityType;
import Level.Level;

public class Ice extends Tile{
	private Direction entranceDirection1;
	private Direction entranceDirection2;
	/**
	 * Creates an ice tile with the coordinates and entrance directions
	 *
	 * @param x                  The x-coordinate of the ice tile
	 * @param y                  The y-coordinate of the ice tile
	 * @param entranceDirection1 The first entrance direction for sliding
	 * @param entranceDirection2 The second entrance direction for sliding
	 */
	public Ice(int x, int y, Direction entranceDirection1, Direction entranceDirection2) {
		super(x, y, EntityType.ICE);
		this.entranceDirection1 = entranceDirection1;
		this.entranceDirection2 = entranceDirection2;
	}
	/**
	 * Logic for the ice tile during each game tick
	 * If a player is on the ice tile, it sets the players new direction to head in
	 *
	 * @param level The current level in-game
	 * @return The updated game level after the ice tile's tick
	 */
	@Override
	public Level tick(Level level) {
		for (Actor a : level.getActorList()) {
			if (a.getX() == getX() && a.getY() == getY()){
				a.setPendingDirection(slide(a));
			}
		}
		return level;
	}

	public boolean checkMoveOntoIce(Direction direction){
		return getInverse(direction) == entranceDirection1 || getInverse(direction) == entranceDirection2;
	}
	/**
	 * Determines the direction in which an actor should slide based on its previous direction
	 *
	 * @param a The player sliding on the ice
	 * @return The direction in which the player should slide
	 */
	public Direction slide(Actor a){
		if (getInverse(a.getPreviousDirection()) == entranceDirection1){
			return entranceDirection2;
		} else {
			return entranceDirection1;
		}
	}

}
