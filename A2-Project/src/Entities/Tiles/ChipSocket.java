package Entities.Tiles;

public class ChipSocket extends Tile{
	private int numOfKeysRequired;

	public ChipSocket(int x, int y, int numOfKeysRequired) {
		super(x, y);
		this.numOfKeysRequired = numOfKeysRequired;
	}

	public int getNumOfKeysRequired () {
		return  numOfKeysRequired;
	}
}
