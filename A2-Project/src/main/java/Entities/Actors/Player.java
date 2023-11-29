package Entities.Actors;

import Entities.Items.ComputerChip;
import Entities.Items.Key;
import Enum.EntityType;
import Enum.Direction;
import Level.Level;

import java.util.ArrayList;

public class Player extends Actor{

	private ArrayList<Key> heldKeys;
	private ArrayList<ComputerChip> heldChips;
	private boolean alive = true;
	private int cooldown = 0;
	private boolean beingMovedByIce = false;


	public Player(int x, int y) {
		super(x, y, EntityType.PLAYER);
	}

	@Override
	public Level tick(Level level) {
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

	public void pickupKey(){

	}

	public void pickupChip(){

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
}
