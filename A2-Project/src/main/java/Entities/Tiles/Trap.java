package Entities.Tiles;

import Entities.Actors.Actor;
import Entities.Actors.Player;
import Enum.EntityType;
import Level.Level;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Trap extends Tile{
	private boolean active = false;
	private final int id;
	private static Image activeTexture;
	private static Image normalTexture;
	static {
		try {
			activeTexture = new Image(new FileInputStream("Textures/trap_active.png"));
			normalTexture = new Image(new FileInputStream("Textures/trap.png"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Creates a trap with the coordinates and a Unique ID
	 *
	 * @param x  The x-coordinate of the trap
	 * @param y  The y-coordinate of the trap
	 * @param id The unique identifier for the trap
	 */
	public Trap(int x, int y, int id) {
		super(x, y, EntityType.TRAP);
		this.id = id;
		this.active = false;

	}

	@Override
	public Level tick(Level level) {
		List<Actor> actors = new ArrayList<>(level.getActorList());
		for (Actor a : actors) {
			if (a.getX() == getX() && a.getY() == getY() && active){
				System.out.println(a.getType());
				System.out.println("X: " + a.getX() + ",Y: " + a.getY());
				System.out.println("Should be: " + getX() + "," + getY());
				level.getActorList().remove(a);
			}
		}
		return level;
	}



	/**
	 * Sets the activation status of the trap
	 *
	 * @param active true activates the trap, otherwise false deactivates it
	 */
	public void setActive(boolean active) {
		this.active = active;
		if (active) {
			texture = activeTexture;
		} else {
			texture = normalTexture;
		}
	}
	/**
	 * Gets the ID of the trap
	 *
	 * @return The ID of the trap
	 */
	public int getId() {
		return id;
	}
	/**
	 * Checks if the trap is active
	 *
	 * @return true if the trap is active, false otherwise.
	 */
	public boolean isActive() {
		return active;
	}
}
