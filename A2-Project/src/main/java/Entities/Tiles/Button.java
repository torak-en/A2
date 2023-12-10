package Entities.Tiles;

import Entities.Actors.Actor;
import Enum.EntityType;
import Level.Level;

import java.util.ArrayList;
import java.util.List;

public class Button extends Tile{
	private List<Trap> linkedTraps = new ArrayList<>();
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
		if (linkedTraps.isEmpty()){
			for (Tile[] tileLayer: level.getTileLayer()) {
				for (Tile tile:tileLayer) {
					if (tile.getType() == EntityType.TRAP){
						Trap trap = (Trap) tile;
						if (trap.getId() == id){
							linkedTraps.add(trap);
						}
					}
				}
			}
		}

		for (Actor a : level.getActorList()) {
			if (a.getX() == getX() && a.getY() == getY()){
				for (Trap t: linkedTraps) {
					t.setActive(true);
				}
			} else {
				for (Trap t: linkedTraps) {
					t.setActive(false);
				}
			}
		}




		return level;
	}

}
