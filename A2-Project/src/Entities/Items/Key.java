package Entities.Items;

public class Key extends Item {
	private String colour;

	public Key(int x, int y, String colour) {
		super(x, y);
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}
}
