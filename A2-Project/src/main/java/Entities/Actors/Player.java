package Entities.Actors;

import Entities.Items.ComputerChip;
import Entities.Items.Item;
import Entities.Items.Key;
import Enum.EntityType;
import Enum.Direction;
import Level.Level;

import java.util.ArrayList;
import java.util.List;


/**
 * Player is an Actor that is controlled by the user.
 * Extends Actor.
 */

public class Player extends Actor{

	private List<Key> heldKeys = new ArrayList<>();
	private List<ComputerChip> heldGold = new ArrayList<>();
	private boolean alive = true;
	private int cooldown = 0;
	private boolean beingMovedByIce = false;
	private boolean won = false;


	/**
	 * Constructor for a Player object.
	 * @param x The x-coordinate of the player.
	 * @param y The y-coordinate of the player.
	 */

	public Player(int x, int y) {
		super(x, y, EntityType.PLAYER);
	}


	/**
	 * Overrides the tick method inherited from Actor class.
	 * This method defines the actions performed by the player during each game tick.
	 * @param level The level where the player exists.
	 * @return The modified level after the tick.
	 */

	@Override
	public Level tick(Level level) {
		List<Actor> actors = level.getActorList();
		for (Actor a : actors) {
			if (a.getX() == getX() && a.getY() == getY() && a != this){
				alive = false;
			}
		}
		return level;
	}


	/**
     * Overrides the calculateMove method inherited from Actor class.
     * This method calculates the movement direction of the player.
     * @param level The level where the player exists.
     * @return The calculated movement direction of the player (or NONE if no movement).
     */

	@Override
	public Direction calculateMove(Level level) {
		// This method is not used for player movement; movement logic is handled in the tick method.
		return null;
	}


	/**
     * Attempts to use a key by the player.
     * @param key The key to be used.
     */

	public void attemptUseKey (Key key){

	}


	/**
     * Attempts to use a number of computer chips by the player.
     * @param num The number of chips to be used.
     */

	public void attemptUseChips(int num){

	}


	/**
     * Allows the player to pick up an item.
     * @param item The item to be picked up.
     */

	public void pickupItem(Item item){
		if (item.getType() == EntityType.KEY){
			Key key = (Key) item;
			heldKeys.add(key);
		} else if (item.getType() == EntityType.COMPUTER_CHIP) {
			ComputerChip gold = (ComputerChip) item;
			heldGold.add(gold);
		}
	}

	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setBeingMovedByIce(boolean beingMovedByIce) {
		this.beingMovedByIce = beingMovedByIce;
	}

	public boolean isBeingMovedByIce() {
		return beingMovedByIce;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public boolean hasWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public List<Key> getHeldKeys() {
		return heldKeys;
	}

	public List<ComputerChip> getHeldGold() {
		return heldGold;
	}
}
