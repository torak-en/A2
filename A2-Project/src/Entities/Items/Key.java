package Entities.Items;

import Enum.EntityType;
public class Key extends Item {
	private String colour;

	public Key(int x, int y, String colour) {
		super(x, y, EntityType.KEY);
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}
}
