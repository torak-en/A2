package Entities.Actors;


/**
 * Represents a node in a pathfinding algorithm.
 */

public class Node {
	private final Node parentNode;
	private int x;
	private int y;


	/**
     * Constructor for a Node object.
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     * @param parentNode The parent node of this node in the path.
     */

	public Node (int x, int y, Node parentNode){
		this.x = x;
		this.y = y;
		this.parentNode = parentNode;
	}


	/**
     * Retrieves the x-coordinate of the node.
     * @return The x-coordinate of the node.
     */

	public int getX() {
		return x;
	}


	 /**
     * Retrieves the y-coordinate of the node.
     * @return The y-coordinate of the node.
     */

	public int getY() {
		return y;
	}


	/**
     * Retrieves the parent node of this node in the path.
     * @return The parent node of this node in the path.
     */
	
	public Node getParentNode() {
		return parentNode;
	}
}
