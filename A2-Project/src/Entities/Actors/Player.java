package Entities.Actors;

import Entities.Items.ComputerChip;
import Entities.Items.Key;

import java.util.ArrayList;

public class Player extends Actor{

	private ArrayList<Key> heldKeys;
	private ArrayList<ComputerChip> heldChips;
	private Boolean alive;
	private PlayerInput input = new PlayerInput();


	public Player(int x, int y) {
		super(x, y);
	}

	public void attemptUseKey (Key key){

	}

	public void attemptUseChips(int num){

	}

	public void pickupKey(){

	}

	public void pickupChip(){

	}

	public Boolean getAlive() {
		return alive;
	}
}
