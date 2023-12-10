package Entities.Tiles;

import Entities.Actors.Actor;
import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;

public class Trap extends Tile{
	private boolean active = false;
	private final int id;
	/**
	 * Creates a trap with the coordinates and a Unique ID
	 *
	 * @param x  The x-coordinate of the trap
	 * @param y  The y-coordinate of the trap
	 * @param id The unique identifier for the trap
	 */
	public Trap(int x, int y, int id) {
		super(x, y, EntityType.TRAP);
		this.id = id;
		this.active = false;
	}
	@Override
	public Level tick(Level level) {
		return level;
	}
	/**
	 * Sets the activation status of the trap
	 *
	 * @param active true activates the trap, otherwise false deactivates it
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * Gets the ID of the trap
	 *
	 * @return The ID of the trap
	 */
	public int getId() {
		return id;
	}
	/**
	 * Checks if the trap is active
	 *
	 * @return true if the trap is active, false otherwise.
	 */
	public boolean isActive() {
		return active;
	}
}
