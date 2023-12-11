package Entities.Tiles;

import Enum.EntityType;
import Level.Level;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LockedDoor extends Tile{
	private String doorColour;
	private boolean locked = true;
	/**
	 * Creates a locked door with the coordinates and door colour
	 *
	 * @param x          The x-coordinate of the locked door
	 * @param y          The y-coordinate of the locked door
	 * @param doorColour The colour of the locked door
	 */
	public LockedDoor(int x, int y, String doorColour) {
		super(x, y, EntityType.LOCKED_DOOR);
		try {
			texture = new Image(new FileInputStream("Textures/locked_door_" + doorColour + ".png"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		this.doorColour = doorColour;
	}

	/**
	 * Logic for the locked door during each tick
	 * If the locked door is unlocked, it changes to a path tile
	 *
	 * @param level The current level in-game
	 * @return The updated game level after the tick
	 */
	@Override
	public Level tick(Level level) {
		if (!locked) {
			level.getTileLayer()[getX()][getY()] = new Path(getX(),getY());
		}
		return level;
	}

	/**
	 * Gets the colour of the dor
	 *
	 * @return The colour of the door
	 */
	public String getDoorColour(){
		return doorColour;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
