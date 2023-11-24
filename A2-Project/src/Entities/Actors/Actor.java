package Entities.Actors;

import Enum.Direction;

public abstract class Actor {
	private int x;
	private int y;
	private char layout;
	private Boolean isMovedByIce;
	private Direction pendingDirection;
	private Direction previousDirection;

	public Actor (int x, int y){
		this.x = x;
		this.y = y;
	}

	private void tick(){
		System.out.println("bruh");
	}

	private Direction calculateMove(){
		return Direction.UP;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
