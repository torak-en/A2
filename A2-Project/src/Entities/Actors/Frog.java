package Entities.Actors;

import java.util.List;

public class Frog extends Actor{
	private Node nodePathToPlayer;
	private int playerX;
	private int playerY;

	public Frog(int x, int y) {
		super(x, y);
	}

	protected void tick(){

	}

	private Node calculatePathToPlayer(int playerX, int playerY) {
		return new Node(1,1,null);
	}

	private Boolean checkValidLocation (int x, int y, char layout, List<Node> filledNodes, List<Node> lastNodes){
		return true;
	}
}
