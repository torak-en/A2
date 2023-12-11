package Entities.Items;

import Enum.EntityType;

/***
 * Represents a key item in the game.
 * Extends the Item class.
 */

public class Key extends Item {
	private String colour;

	/***
	 * Constructor for a Key object.
	 * @param x The x-coordinate of the key.
	 * @param y The y-coordinate of the key.
	 */

	public Key(int x, int y, String colour) {
		super(x, y, EntityType.KEY);
		this.colour = colour;
	}
	/**
	 * Gets the color of the key
	 * @return The color of the key
	 */

	public String getColour() {
		return colour;
	}
}
