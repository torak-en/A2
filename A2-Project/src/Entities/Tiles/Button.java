package Entities.Tiles;

public class Button extends Tile{
	private Trap linkedTrap;
	private int id;

	public Button(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}

	public Trap getLinkedTrap() {
		return linkedTrap;
	}

	public void setLinkedTrap(Trap trap) {
		linkedTrap = trap;
	}
}
