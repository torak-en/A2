package Entities.Items;

import Enum.EntityType;
public class Key extends Item {
	private String colour;

	public Key(int x, int y, String colour) {
		super(x, y, EntityType.KEY);
		this.colour = colour;
	}
	/**
	 * Gets the color of the key
	 *
	 * @return The color of the key
	 */
	public String getColour() {
		return colour;
	}
}
