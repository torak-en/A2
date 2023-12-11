package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.Direction;
import Enum.EntityType;
import Level.Level;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The class representing an ice tile in the game.
 */

public class Ice extends Tile{
	private Direction entranceDirection1;
	private Direction entranceDirection2;
	private boolean noDirections = false;

	/**
	 * Creates an ice tile with the coordinates and entrance directions.
	 * @param x                  The x-coordinate of the ice tile.
	 * @param y                  The y-coordinate of the ice tile.
	 * @param entranceDirection1 The first entrance direction for sliding.
	 * @param entranceDirection2 The second entrance direction for sliding.
	 */

	public Ice(int x, int y, Direction entranceDirection1, Direction entranceDirection2) {
		super(x, y, EntityType.ICE);
		if (entranceDirection1 == Direction.NONE && entranceDirection2 == Direction.NONE){
			try {
				System.out.println("Why");
				texture = new Image(new FileInputStream("Textures/ice.png"));
				noDirections = true;
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		} else {
			try {
				texture = new Image(new FileInputStream("Textures/ice_" + entranceDirection1.toSingleString() + entranceDirection2.toSingleString() + ".png"));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		this.entranceDirection1 = entranceDirection1;
		this.entranceDirection2 = entranceDirection2;
	}

	/**
	 * Logic for the ice tile during each game tick
	 * If a player is on the ice tile, it sets the players new direction to head in.
	 * @param level The current level in-game.
	 * @return The updated game level after the ice tile's tick.
	 */

	@Override
	public Level tick(Level level) {
		for (Actor a : level.getActorList()) {
			if (a.getX() == getX() && a.getY() == getY()){
				a.setPendingDirection(slide(a,level));
			}
		}
		return level;
	}

	public boolean checkMoveOntoIce(Direction direction){
		if (noDirections){
			System.out.println("?");
			return true;
		}
		return getInverse(direction) == entranceDirection1 || getInverse(direction) == entranceDirection2;
	}

	/**
	 * Determines the direction in which an actor should slide based on its previous direction.
	 *
	 * @param a The player sliding on the ice.
	 * @return The direction in which the player should slide.
	 */

	public Direction slide(Actor a, Level level){
		if (noDirections){
			if (a.getType() == EntityType.PLAYER){
				if(a.playerCheckLocation(a.getPreviousDirection(), level)){
					return a.getPreviousDirection();
				} else if (a.playerCheckLocation(getInverse(a.getPreviousDirection()), level)){
					return getInverse(a.getPreviousDirection());
				} else {
					return Direction.NONE;
				}
			} else if (a.getType() == EntityType.BLOCK){
				if(a.playerCheckLocation(a.getPreviousDirection(), level)){
					return a.getPreviousDirection();
				} else if (a.playerCheckLocation(getInverse(a.getPreviousDirection()), level)){
					return getInverse(a.getPreviousDirection());
				} else {
					return Direction.NONE;
				}
			}
			return Direction.NONE;
		}
		Direction directionMovedOntoIce = a.getPreviousDirection();
		if (directionMovedOntoIce == getInverse(entranceDirection1)){
			if (a.getType() == EntityType.PLAYER){
				System.out.println("Player");
				System.out.println(entranceDirection2);
				System.out.println(a.playerCheckLocation(entranceDirection2, level));
				if (a.playerCheckLocation(entranceDirection2, level)){
					System.out.println(entranceDirection2 + " Chosen");
					return entranceDirection2;
				} else if (a.playerCheckLocation(entranceDirection1, level)){
					System.out.println(entranceDirection1 + " Chosen");
					return entranceDirection1;
				} else {
					return Direction.NONE;
				}

			} else if (a.getType() == EntityType.BLOCK){
				System.out.println("Block");
				if (a.blockCheckLocation(entranceDirection2, level)){
					return entranceDirection2;
				} else if (a.blockCheckLocation(entranceDirection1, level)){
					return entranceDirection1;
				} else {
					return Direction.NONE;
				}
			}
		} else {
			if (a.getType() == EntityType.PLAYER){
				System.out.println("Player");
				System.out.println(entranceDirection1);
				System.out.println(a.playerCheckLocation(entranceDirection1, level));
				if (a.playerCheckLocation(entranceDirection1, level)){
					System.out.println(entranceDirection1 + " Chosen");
					return entranceDirection1;
				} else if (a.playerCheckLocation(entranceDirection2, level)){
					System.out.println(entranceDirection2 + " Chosen");
					return entranceDirection2;
				} else {
					return Direction.NONE;
				}

			} else if (a.getType() == EntityType.BLOCK){
				System.out.println("Block");
				if (a.blockCheckLocation(entranceDirection1, level)){
					return entranceDirection1;
				} else if (a.blockCheckLocation(entranceDirection2, level)){
					return entranceDirection2;
				} else {
					return Direction.NONE;
				}
			}
		}
		return Direction.NONE;
	}

}
