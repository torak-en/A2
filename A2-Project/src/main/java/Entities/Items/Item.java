package Entities.Items;

import Entities.Entity;
import Enum.EntityType;
import Level.Level;

public class Item extends Entity {
	private int x;
	private int y;
	private boolean pickedUp = false;

	public Item(int x, int y, EntityType type){
		super(x,y,type);
	}

	@Override
	public Level tick(Level level) {
		if (pickedUp){
			level.getItemList().remove(this);
		}
		return level;
	}

	public int getX() {
		return super.getX();
	}

	public void setX(int x) {
		super.setX(x);
	}

	public void setY(int y) {
		super.setY(y);
	}

	public int getY() {
		return super.getY();
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}
}
