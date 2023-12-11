package Entities.Items;

import Enum.EntityType;

/***
 * Represents a computer chip item in the game.
 * Extends the Item class.
 */

public class ComputerChip extends Item{

	/***
	 * Constructor for a Computer Chip object.
	 * @param x The x-coordinate of the computer chip.
	 * @param y The y-coordinate of the computer chip.
	 */

	public ComputerChip(int x, int y) {
		super(x, y, EntityType.COMPUTER_CHIP);
	}
}
