package Enum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.*;
import javafx.scene.image.Image;

public enum EntityType {
	BUTTON,CHIP_SOCKET,DIRT,EXIT,ICE,LOCKED_DOOR,PATH,TRAP,WALL,WATER,KEY,COMPUTER_CHIP,BLOCK,BUG,FROG,PINK_BALL,PLAYER;

	private  Image image;

	EntityType(){
		System.out.println("Textures/" + toString().toLowerCase() + ".png");
		try {
			image = new Image(new FileInputStream("Textures/" + toString().toLowerCase() + ".png"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Image getImage() {
		return image;
	}
}
