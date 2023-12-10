package Entities.Actors;

import Entities.Entity;
import Entities.Items.ComputerChip;
import Entities.Items.Key;
import Entities.Tiles.ChipSocket;
import Entities.Tiles.LockedDoor;
import Entities.Tiles.Tile;
import Enum.Direction;
import Enum.EntityType;
import Level.Level;

import java.nio.channels.NonWritableChannelException;
import java.util.Objects;

/**
 * The abstract class representing an actor entity in the game.
 */
public abstract class Actor extends Entity {
	private char layout;
	private boolean isMovedByIce;
	private Direction pendingDirection;
	private Direction previousDirection;


	/**
     * Constructor for an Actor object.
     * @param x The x-coordinate of the actor.
     * @param y The y-coordinate of the actor.
     * @param type The type of entity.
     */

	public Actor (int x, int y, EntityType type){
		super(x,y,type);
	}

	/**
     * Abstract method to calculate the movement direction of the actor.
     * @param level The level where the actor exists.
     * @return The calculated movement direction.
     */

	public abstract Direction calculateMove(Level level);

	/**
     * Applies the pending movement to the actor's position.
     */

	public void applyMove(){
		int x = getX();
		int y = getY();
		if (pendingDirection == Direction.UP){
			setY(y - 1);
		} else if (pendingDirection == Direction.DOWN){
			setY(y + 1);
		} else if (pendingDirection == Direction.LEFT) {
			setX(x - 1);
		} else if (pendingDirection == Direction.RIGHT){
			setX(x + 1);
		}
		previousDirection = pendingDirection;
		pendingDirection = null;
	}

	/**
     * Checks if the specified location in a given direction is valid for movement.
     * @param direction The direction to check.
     * @param level The level where the check is performed.
     * @return True if the location is valid for movement, false otherwise.
     */

	public boolean checkLocation(Direction direction, Level level){
		int newX = getX();
		int newY = getY();
		if (direction == Direction.UP){
			newY--;
		} else if (direction == Direction.DOWN){
			newY++;
		} else if (direction == Direction.LEFT) {
			newX--;
		} else if (direction == Direction.RIGHT){
			newX++;
		} else if (direction == Direction.NONE){
			return true;
		}
		EntityType nextTile = level.getTileLayer()[newX][newY].getType();
		if (nextTile != EntityType.PATH && nextTile != EntityType.BUTTON && nextTile != EntityType.TRAP ){
			System.out.println("WHy");
			return false;
		}

		for (Actor a : level.getActorList()) {
			if (a.getX() == newX && a.getY() == newY && a.getType() != EntityType.PLAYER){
				return false;
			}
		}

		return true;
	}


	/**
     * Checks if the specified location for a player's movement is valid and performs actions accordingly.
     * @param direction The direction in which the player wants to move.
     * @param level The level where the check is performed.
     * @return True if the location is valid for the player's movement, false otherwise.
     */

	public boolean playerCheckLocation(Direction direction, Level level){
		int newX = getX();
		int newY = getY();
		if (direction == Direction.UP){
			newY--;
		} else if (direction == Direction.DOWN){
			newY++;
		} else if (direction == Direction.LEFT) {
			newX--;
		} else if (direction == Direction.RIGHT){
			newX++;
		} else if (direction == Direction.NONE){
			return true;
		}

		Tile nextTile = level.getTileLayer()[newX][newY];
		Player p = level.getPlayer();
		Key usedKey = null;
		ComputerChip usedChip = null;
		if (nextTile.getType() == EntityType.WALL){
			return false;
		} else if (nextTile.getType() == EntityType.LOCKED_DOOR){
			LockedDoor door = (LockedDoor) nextTile;
			for (Key key : p.getHeldKeys()) {
				if (Objects.equals(key.getColour(), door.getDoorColour())){
					usedKey = key;
					door.setLocked(false);
				} else {
					return false;
				}
			}
			p.getHeldKeys().remove(usedKey);
		} else if (nextTile.getType() == EntityType.CHIP_SOCKET) {
			ChipSocket altar = (ChipSocket) nextTile;
			if (p.getHeldGold().size() == altar.getNumOfGoldRequired()){
				System.out.println(altar.getNumOfGoldRequired());
				for (int i = 0; i < altar.getNumOfGoldRequired(); i++) {
					p.getHeldGold().remove(0);
				}
				altar.setLocked(false);
			} else {
				return false;
			}
		}

		for (Actor a : level.getActorList()) {
			if (a.getType() == EntityType.BLOCK){
				Block b = (Block) a;
			}
		}
		return true;
	}


	/**
     * Retrieves the previous direction of the actor.
     * @return The previous direction of the actor.
     */

	public Direction getPreviousDirection() {
		return previousDirection;
	}


	/**
     * Sets the previous direction of the actor.
     * @param previousDirection The previous direction to set for the actor.
     */

	public void setPreviousDirection(Direction previousDirection) {
		this.previousDirection = previousDirection;
	}


	/**
     * Retrieves the pending direction of the actor.
     * @return The pending direction of the actor.
     */

	public Direction getPendingDirection() {
		return pendingDirection;
	}

	
	/**
     * Sets the pending direction of the actor.
     * @param pendingDirection The pending direction to set for the actor.
     */

	public void setPendingDirection(Direction pendingDirection) {
		this.pendingDirection = pendingDirection;
	}


}
