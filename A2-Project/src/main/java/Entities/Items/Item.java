package Entities.Items;

import Entities.Actors.Player;
import Entities.Entity;
import Enum.EntityType;
import Level.Level;

/**
 * The class representing an item in the game.
 */

public class Item extends Entity {
	private int x;
	private int y;
	private boolean pickedUp = false;

	public Item(int x, int y, EntityType type){
		super(x,y,type);
	}

	/**
	 * Logic for the item during each tick
	 * If the player is on the same position as the item, it is picked up by the player and removed from the item list
	 * @param level The current level in-game
	 * @return The updated game level after the tick
	 */

	@Override
	public Level tick(Level level) {
		Player p = level.getPlayer();
		if (p.getX() == getX() && p.getY() == getY()){
			p.pickupItem(this);
			level.getItemList().remove(this);
		}
		return level;
	}

	/**
	 * Gets the x-coordinate of the item
	 * @return The new x-coordinate of the item
	 */

	public int getX() {
		return super.getX();
	}

	/**
	 * Sets the x-coordinate of the item
	 * @param x The new x-coordinate of the item
	 */

	public void setX(int x) {
		super.setX(x);
	}

	/**
	 * Sets the x-coordinate of the item
	 * @param y The new x-coordinate of the item
	 */

	public void setY(int y) {
		super.setY(y);
	}

	/**
	 * Gets the y-coordinate of the item
	 * @return The new y-coordinate of the item
	 */

	public int getY() {
		return super.getY();
	}
}
