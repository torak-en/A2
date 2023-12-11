package Enum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.*;
import javafx.scene.image.Image;

/**
 * The class representing entity types in the game.
 */

public enum EntityType {
	BUTTON,CHIP_SOCKET,DIRT,EXIT,ICE,LOCKED_DOOR,PATH,TRAP,WALL,WATER,KEY,COMPUTER_CHIP,BLOCK,BUG,FROG,PINK_BALL,PLAYER,EMPTY;

	private Image image;

	/**
	 * A method that finds the various entity type texture images within the project
	 * and initialises an image with their respective file.
	 */

	EntityType(){
		System.out.println("Textures/" + toString().toLowerCase() + ".png");
		try {
			image = new Image(new FileInputStream("Textures/" + toString().toLowerCase() + ".png"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the texture image of an entity type.
	 * @return The respective image of each entity.
	 */

	public Image getImage() {
		return image;
	}

}
