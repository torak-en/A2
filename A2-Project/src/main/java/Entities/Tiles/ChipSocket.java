package Entities.Tiles;

import Enum.EntityType;
import Level.Level;

public class ChipSocket extends Tile{
	private int numOfKeysRequired;
	private boolean locked = true;

	/**
	 * Creates a chip socket with coordinates and the number of keys required to unlock it
	 *
	 * @param x                  The x-coordinate of the chip socket
	 * @param y                  The y-coordinate of the chip socket
	 * @param numOfKeysRequired The number of keys required to unlock the chip socket
	 */
	public ChipSocket(int x, int y, int numOfKeysRequired) {
		super(x, y, EntityType.CHIP_SOCKET);
		this.numOfKeysRequired = numOfKeysRequired;
	}
	/**
	 * logic for the chip socket during each game tick
	 * If the chip socket is unlocked, it changes to a path tile
	 *
	 * @param level The current level in-game
	 * @return The updated game level after the chip socket's tick
	 */
	@Override
	public Level tick(Level level) {
		if (!locked) {
			level.getTileLayer()[getX()][getY()] = new Path(getX(),getY());
		}
		return level;
	}
	/**
	 * Gets the number of keys required to unlock the chip socket
	 *
	 * @return The number of keys required to unlock the chip socket
	 */
	public int getNumOfKeysRequired () {
		return  numOfKeysRequired;
	}
}
