package Entities;

import Enum.EntityType;
import Enum.Direction;
import Level.Level;

public abstract class Entity {
	private boolean visible = true;
	private EntityType type;
	private int x;
	private  int y;

	public Entity(int x, int y, EntityType type){
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public abstract Level tick(Level level);

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public EntityType getType() {
		return type;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Direction getInverse(Direction d){
		if (d == Direction.UP){
			return Direction.DOWN;
		} else if (d == Direction.DOWN){
			return Direction.UP;
		} else if (d == Direction.LEFT){
			return  Direction.RIGHT;
		} else {
			return Direction.LEFT;
		}
	}

	public Direction getClockwise(Direction d){
		if (d == Direction.UP){
			return Direction.RIGHT;
		} else if (d == Direction.DOWN){
			return Direction.LEFT;
		} else if (d == Direction.LEFT){
			return  Direction.UP;
		} else {
			return Direction.DOWN;
		}
	}

	public Direction getAntiClockwise(Direction d){
		if (d == Direction.UP){
			return Direction.LEFT;
		} else if (d == Direction.DOWN){
			return Direction.RIGHT;
		} else if (d == Direction.LEFT){
			return  Direction.DOWN;
		} else {
			return Direction.UP;
		}
	}
}
