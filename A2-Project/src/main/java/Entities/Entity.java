package Entities;

import Enum.EntityType;
import Enum.Direction;
import Level.Level;


public abstract class Entity {
	private boolean visible = true;
	private EntityType type;
	private int x;
	private  int y;

	/**
	 * Constructs an entity with a specific type and coordinates
	 *
	 * @param x    The x-coordinate of the entity
	 * @param y    The y-coordinate of the entity
	 * @param type The type of the entity
	 */
	public Entity(int x, int y, EntityType type){
		this.x = x;
		this.y = y;
		this.type = type;
	}

	/**
	 *  Tick function
	 *
	 * @param level The current level in the game
	 * @return Updated game level after a tick
	 */
	public abstract Level tick(Level level);

	/**
	 * Gets the current y-coordinate
	 *
	 * @return The y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the current x-coordinate
	 *
	 * @return The x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the new y-coordinate
	 *
	 * @return The new y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Sets the new x-coordinate
	 *
	 * @return The new x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the type of the entity
	 *
	 * @return The type of that entity
	 */
	public EntityType getType() {
		return type;
	}

	/**
     * Checks if the entity is visible
	 *
	 * @return true if the entity is visible, otherwise false
     */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the visibility
	 *
	 * @param visible true, making the entity visible, otherwise false
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Gets the inverse direction of the current direction
	 *
	 * @param d The input direction
	 * @return The inverse direction
	 */
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

	/**
	 * Gets the clockwise direction of the current direction
	 *
	 * @param d The input direction
	 * @return The clockwise direction
	 */
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

	/**
	 * Gets the anti-clockwise direction of the current direction
	 *
	 * @param d The input direction
	 * @return The anti-clockwise direction
	 */
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
