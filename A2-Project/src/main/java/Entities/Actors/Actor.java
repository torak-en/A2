package Entities.Actors;

import Entities.Entity;
import Enum.Direction;
import Enum.EntityType;

public abstract class Actor extends Entity {
	private char layout;
	private boolean isMovedByIce;
	private Direction pendingDirection;
	private Direction previousDirection;

	public Actor (int x, int y, EntityType type){
		super(x,y,type);
	}

	private void tick(){
		System.out.println("bruh");
	}

	private Direction calculateMove(){
		return Direction.UP;
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

	public Direction getPendingDirection() {
		return pendingDirection;
	}

	public void setPendingDirection(Direction pendingDirection) {
		this.pendingDirection = pendingDirection;
	}
}
