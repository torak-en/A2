package Entities.Actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		if (ticksTillMove == 0 && getPendingDirection() == null){
			Player p = level.getPlayer();
			ticksTillMove = TICKS_BETWEEN_MOVE;
			if (playerX != p.getX() && playerY != p.getY() && p.isAlive()) {
				nodePathToPlayer = calculatePathToPlayer(p, level);
				playerX = p.getX();
				playerY = p.getY();
			}
			if (nodePathToPlayer != null) {
				setX(nodePathToPlayer.getX());
				setY(nodePathToPlayer.getY());
				nodePathToPlayer = nodePathToPlayer.getParentNode();
			} else {
				System.out.println("New Move");
				Direction d = randomDirection(level);
				setPendingDirection(d);
				applyMove();
				System.out.println(" ");
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

		boolean notFound = true;

		Node playerNode = new Node(pX, pY, null);
		Node frogNode = null;

		List<Node> lastNodes = new ArrayList<>();
		List<Node> filledNodes = new ArrayList<>();

		lastNodes.add(playerNode);

		while (notFound){
			List<Node> adjacentNodes = new ArrayList<>();

			for (Node node : lastNodes) {
				int x = node.getX();
				int y = node.getY();

				if (checkValidLocation(x+1, y, level, filledNodes, lastNodes)){
					Node n = new Node (x+1, y, node);
					adjacentNodes.add(n);

					if (n.getX() == getX() && n.getY() == getY() && frogNode == null){
						frogNode = n;
						notFound = false;
					}
				}
				if (checkValidLocation(x-1, y, level, filledNodes, lastNodes)){
					Node n = new Node (x-1, y, node);
					adjacentNodes.add(n);

					if (n.getX() == getX() && n.getY() == getY() && frogNode == null){
						frogNode = n;
						notFound = false;
					}
				}
				if (checkValidLocation(x, y-1, level, filledNodes, lastNodes)){
					Node n = new Node (x, y-1, node);
					adjacentNodes.add(n);

					if (n.getX() == getX() && n.getY() == getY() && frogNode == null){
						frogNode = n;
						notFound = false;
					}
				}
				if (checkValidLocation(x, y+1, level, filledNodes, lastNodes)){
					Node n = new Node (x, y+1, node);
					adjacentNodes.add(n);

					if (n.getX() == getX() && n.getY() == getY() && frogNode == null){
						frogNode = n;
						notFound = false;
					}
				}
			}

			filledNodes.addAll(lastNodes);

			lastNodes = adjacentNodes;

			if (lastNodes.isEmpty()){
				return null;
			}
		}
		return frogNode;

	}


	/**
	 * Checks if a given location is valid for movement.
	 * @param x The x-coordinate of the location.
	 * @param y The y-coordinate of the location.
	 * @param level The level where the check is performed.
	 * @param filledNodes The list of nodes that have already been checked.
	 * @param lastNodes The list of nodes that have been checked in the previous iteration.
	 * @return True if the location is valid for movement, false otherwise.
	 */

	private Boolean checkValidLocation (int x, int y, Level level, List<Node> filledNodes, List<Node> lastNodes){
		EntityType nextTile = level.getTileLayer()[x][y].getType();
		if (nextTile == EntityType.EXIT || nextTile == EntityType.WATER || nextTile == EntityType.WALL || nextTile == EntityType.LOCKED_DOOR || nextTile == EntityType.CHIP_SOCKET){
			return false;
		}

		for (Node n:filledNodes) {
			if (n.getX() == x && n.getY() == y){
				return false;
			}
		}

		for (Node n:lastNodes) {
			if (n.getX() == x && n.getY() == y){
				return false;
			}
		}

		for (Actor a : level.getActorList()) {
			if (x == a.getX() && y == a.getY()){
				if (a.getType() == EntityType.BLOCK){
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
