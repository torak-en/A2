package Entities.Actors;

import Entities.Entity;
import Entities.Tiles.LockedDoor;
import Entities.Tiles.Tile;
import Enum.Direction;
import Enum.EntityType;
import Level.Level;

import java.nio.channels.NonWritableChannelException;

public abstract class Actor extends Entity {
	private char layout;
	private boolean isMovedByIce;
	private Direction pendingDirection;
	private Direction previousDirection;

	public Actor (int x, int y, EntityType type){
		super(x,y,type);
	}

	public abstract Direction calculateMove(Level level);

	public void applyMove(){
		int x = getX();
		int y = getY();
		if (pendingDirection == Direction.UP){
			setY(y - 1);
		} else if (pendingDirection == Direction.DOWN){
			setY(y + 1);
		} else if (pendingDirection == Direction.LEFT) {
			setX(x - 1);
		} else if (pendingDirection == Direction.RIGHT){
			setX(x + 1);
		}
		previousDirection = pendingDirection;
		pendingDirection = null;
	}

	public boolean checkLocation(Direction direction, Level level){
		int newX = getX();
		int newY = getY();
		if (direction == Direction.UP){
			newY--;
		} else if (direction == Direction.DOWN){
			newY++;
		} else if (direction == Direction.LEFT) {
			newX--;
		} else if (direction == Direction.RIGHT){
			newX++;
		} else if (direction == Direction.NONE){
			return true;
		}
		EntityType nextTile = level.getTileLayer()[newX][newY].getType();
		if (nextTile == EntityType.EXIT || nextTile == EntityType.WATER || nextTile == EntityType.WALL || nextTile == EntityType.LOCKED_DOOR || nextTile == EntityType.CHIP_SOCKET){
			return false;
		}

		for (Actor a : level.getActorList()) {
			if (newX == a.getX() && newY == a.getY()){
				if (a.getType() == EntityType.PLAYER){
					Player p = (Player)a;
					p.setAlive(false);
				} else if (a.getType() == EntityType.BLOCK){
					return false;
				}
			}
		}
		return true;
	}

	public void setLayout(char layout) {
		this.layout = layout;
	}

	public char getLayout() {
		return layout;
	}

	public Direction getPreviousDirection() {
		return previousDirection;
	}

	public void setPreviousDirection(Direction previousDirection) {
		this.previousDirection = previousDirection;
	}

	public Direction getPendingDirection() {
		return pendingDirection;
	}

	public void setPendingDirection(Direction pendingDirection) {
		this.pendingDirection = pendingDirection;
	}


}
