package Entities.Items;

import Enum.EntityType;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Represents a key item in the game.
 * Extends the Item class.
 */

public class Key extends Item {
	private String colour;

	/**
	 * Constructor for a Key object.
	 * @param x The x-coordinate of the key.
	 * @param y The y-coordinate of the key.
	 * @param colour The colour of the key
	 */

	public Key(int x, int y, String colour) {
		super(x, y, EntityType.KEY);
		try {
			System.out.println(colour);
			texture = new Image(new FileInputStream("Textures/key_" + colour + ".png"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
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
