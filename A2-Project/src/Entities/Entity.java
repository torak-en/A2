package Entities;

import Enum.EntityType;
public abstract class Entity {
	private int x;
	private int y;
	private EntityType type;

	public Entity(int x, int y, EntityType type){
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public EntityType getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
