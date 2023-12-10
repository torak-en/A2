package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class ChipSocket extends Tile{
	private int numOfGoldRequired;
	private boolean locked = true;
	/**
	 * Creates a chip socket with the coordinates and the number of gold required to unlock it
	 *
	 * @param x                  The x-coordinate of the chip socket
	 * @param y                  The y-coordinate of the chip socket
	 * @param numOfGoldRequired The number of gold pieces required to unlock the chip socket
	 */
	public ChipSocket(int x, int y, int numOfGoldRequired) {
		super(x, y, EntityType.CHIP_SOCKET);
		this.numOfGoldRequired = numOfGoldRequired;
	}

	/**
	 * Logic for the chip socket during each tick
	 * If the chip socket is unlocked, it changes to a path tile
	 *
	 * @param level The current level in-game
	 * @return The updated game level after a tick
	 */
	@Override
	public Level tick(Level level) {
		if (!locked) {
			level.getTileLayer()[getX()][getY()] = new Path(getX(),getY());
		}
		return level;
	}
	/**
	 * Gets the number of gold pieces required to unlock the chip socket
	 *
	 * @return The number of gold pieces to unlock the chip socket
	 */
	public int getNumOfGoldRequired () {
		return  numOfGoldRequired;
	}
	/**
	 * Sets the status of the lock of the chip socket
	 *
	 * @param locked true locks the chip socked, otherwise false (unlocked)
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
