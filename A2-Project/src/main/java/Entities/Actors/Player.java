package Entities.Actors;

import Entities.Items.ComputerChip;
import Entities.Items.Item;
import Entities.Items.Key;
import Enum.EntityType;
import Enum.Direction;
import Level.Level;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor{

	private List<Key> heldKeys = new ArrayList<>();
	private List<ComputerChip> heldGold = new ArrayList<>();
	private boolean alive = true;
	private int cooldown = 0;
	private boolean beingMovedByIce = false;
	private boolean won = false;


	public Player(int x, int y) {
		super(x, y, EntityType.PLAYER);
	}

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

	@Override
	public Direction calculateMove(Level level) {
		return null;
	}

	public void attemptUseKey (Key key){

	}

	public void attemptUseChips(int num){

	}

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
