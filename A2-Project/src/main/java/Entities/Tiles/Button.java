package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.EntityType;
import Level.Level;

public class Button extends Tile{
	private Trap linkedTrap = null;
	private final int id;
	/**
	 * Creates a button with the x and y coordinates and unique ID.
	 *
	 * @param x  The x-coordinate of the button
	 * @param y  The y-coordinate of the button
	 * @param id The unique ID for the button
	 */
	public Button(int x, int y, int id) {
		super(x, y, EntityType.BUTTON);
		this.id = id;
	}
	/**
	 * Logic for the button during every tick in-game
	 * If the button is not linked to a trap, it searches for a trap in the level with a matching ID
	 * It then activates the linked trap if an actor is on the button's position
	 *
	 * @param level The current level in the game
	 * @return The updated game level after the tick
	 */
	@Override
	public Level tick(Level level) {
		if (linkedTrap == null){
			for (Tile[] tileLayer: level.getTileLayer()) {
				for (Tile tile:tileLayer) {
					if (tile.getType() == EntityType.TRAP){
						Trap trap = (Trap) tile;
						if (trap.getId() == id){
							linkedTrap = trap;
						}
					}
				}
			}
		}

		for (Actor a: level.getActorList()) {
			linkedTrap.setActive(a.getX() == getX() && a.getY() == getY());
		}

		return level;
	}

}
