package Entities.Actors;

import java.util.*;

import Enum.EntityType;
import Enum.Direction;
import Level.Level;


/**
 * Represents a frog entity in the game.
 * Extends the Actor class.
 */

public class Frog extends Actor{
	private Node nodePathToPlayer;
	private int playerX = -1;
	private int playerY = -1;

	private final int TICKS_BETWEEN_MOVE = 100;
	private int ticksTillMove = 0;


	/**
     * Constructor for a Frog object.
     * @param x The x-coordinate of the frog.
     * @param y The y-coordinate of the frog.
     */

	public Frog(int x, int y) {
		super(x, y, EntityType.FROG);
		ticksTillMove = TICKS_BETWEEN_MOVE;
	}


	/**
     * Overrides the tick method inherited from Actor class.
     * This method defines the actions performed by the frog during each game tick.
     * @param level The level where the frog exists.
     * @return The modified level after the tick.
     */

	@Override
	public Level tick(Level level) {
		if (playerX == getX() && playerY == getY()){
			level.getPlayer().setAlive(false);
			setPendingDirection(Direction.NONE);
		}
		if (ticksTillMove == 0){
			Player p = level.getPlayer();
			ticksTillMove = TICKS_BETWEEN_MOVE;
			if (playerX != p.getX() && playerY != p.getY()) {
				long startTime = System.nanoTime();
				nodePathToPlayer = calculatePathToPlayer(p, level);
				long endTime = System.nanoTime();
				long difference = endTime - startTime;
				System.out.println(String.format("%.3fms", difference / 1_000_000d));
				playerX = p.getX();
				playerY = p.getY();
			}
			if (nodePathToPlayer != null) {
				setX(nodePathToPlayer.getX());
				setY(nodePathToPlayer.getY());
				nodePathToPlayer = nodePathToPlayer.getParentNode();
				System.out.println("Actual Move");
			} else {
				System.out.println("Random Move");
				Direction d = randomDirection(level);
				setPendingDirection(d);
				applyMove();
			}

		}
		ticksTillMove--;
		return level;
	}


	/**
     * Overrides the calculateMove method inherited from Actor class.
     * This method calculates the movement direction of the frog.
     * @param level The level where the frog exists.
     * @return The calculated movement direction of the frog (or NONE if no movement).
     */

	@Override
	public Direction calculateMove(Level level) {
		// This method is not used for frog movement; movement logic is handled in the tick method.
		return null;
	}

	/**
	 * Calculates the path to the player using a breadth first search algorithm.
	 * @param player The player object.
	 * @param level The level where the frog exists.
	 * @return The node containing the path to the player.
	 */

	private Node calculatePathToPlayer(Player player, Level level) {
		int pX = player.getX();
		int pY = player.getY();

		Node playerNode = new Node(pX, pY, null);

		LinkedList<Node> toScan = new LinkedList<>();
		toScan.add(playerNode);

		boolean[][] scanned = new boolean[level.getTileLayer().length][level.getTileLayer()[0].length];

		while (!toScan.isEmpty()){
			Node currentNode = toScan.removeFirst();
			scanned[currentNode.getX()][currentNode.getY()] = true;
			for (int[] delta : new int[][]{{0,-1},{1,0},{0,1},{-1,0}}) {
				int newX = delta[0] + currentNode.getX();
				int newY = delta[1] + currentNode.getY();

				if (checkValidLocation(newX, newY, level) && !scanned[newX][newY]){
					Node newNode = new Node(newX, newY, currentNode);
					toScan.add(newNode);
					if (newX == getX() && newY == getY()){
						return newNode;
					}
				}
			}
		}
		return null;
	}


	/**
	 * Checks if a given location is valid for movement.
	 * @param x The x-coordinate of the location.
	 * @param y The y-coordinate of the location.
	 * @param level The level where the check is performed.
	 * @return True if the location is valid for movement, false otherwise.
	 */

	private boolean checkValidLocation (int x, int y, Level level){
		EntityType nextTile = level.getTileLayer()[x][y].getType();
		if (nextTile != EntityType.PATH && nextTile != EntityType.BUTTON && nextTile != EntityType.TRAP ){
			return false;
		}

		for (Actor a : level.getActorList()) {
			if (x == a.getX() && y == a.getY()){
				if (a.getType() != EntityType.PLAYER && a != this){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Generates a random direction for the frog to move in.
	 * @param level The level where the frog exists.
	 * @return The randomly generated direction.
	 */
	
	private Direction randomDirection (Level level){
		boolean upValid = checkLocation(Direction.UP, level);
		boolean rightValid = checkLocation(Direction.RIGHT, level);
		boolean downValid = checkLocation(Direction.DOWN, level);
		boolean leftValid = checkLocation(Direction.LEFT, level);
		if (!upValid && !rightValid && !downValid && !leftValid){
			return Direction.NONE;
		}
		List<Direction> validDirections = new ArrayList<>();
		if (upValid){
			validDirections.add(Direction.UP);
		}
		if (rightValid){
			validDirections.add(Direction.RIGHT);
		}
		if (downValid){
			validDirections.add(Direction.DOWN);
		}
		if (leftValid){
			validDirections.add(Direction.LEFT);
		}
		Random rand = new Random();
		int bound = validDirections.size();
		int r = rand.nextInt(bound);
		return validDirections.get(r);
	}

}
