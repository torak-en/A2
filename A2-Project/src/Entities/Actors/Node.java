package Entities.Actors;

public class Node {
	private final Node parentNode;
	private int x;
	private int y;

	public Node (int x, int y, Node parentNode){
		this.x = x;
		this.y = y;
		this.parentNode = parentNode;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Node getParentNode() {
		return parentNode;
	}
}
