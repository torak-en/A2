package Entities.Items;

import Enum.EntityType;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Key extends Item {
	private String colour;

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
	 *
	 * @return The color of the key
	 */
	public String getColour() {
		return colour;
	}
}
